package minh.lehong.yourwindowyoursoul.facade.Impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.ThemeDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.ThemeRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.FullThemeResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.facade.ThemeFacade;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;
import minh.lehong.yourwindowyoursoul.service.BackgroundService;
import minh.lehong.yourwindowyoursoul.service.ThemeService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ThemeFacadeImpl implements ThemeFacade {
    @Autowired
    private ThemeService themeService;

    @Autowired
    private CommonConverter commonConverter;

    @Autowired
    private BackgroundService backgroundService;

    @Override
    public Response findAllThemes() {
        Response response;
        try
        {
            List<Theme> themes = themeService.findAll();
            themes = themes
                    .stream()
                    .map(theme -> {
                        theme.setBackgrounds(backgroundService.findBackgroundsByTheme(theme));
                        return theme;
                    })
                    .collect(Collectors.toList());
            if(themes == null || themes.isEmpty())
            {
                response = new Response(null, false, "Have No Themes", HttpStatus.NO_CONTENT.value());
            }
            else
            {
                List<FullThemeResponse> fullThemeResponses = themes
                        .stream()
                        .map(theme -> commonConverter.convertThemeEntityToFullThemeResponse(theme))
                        .collect(Collectors.toList());

                response = new Response(fullThemeResponses, true, "Get All Themes Success!", HttpStatus.OK.value());
            }
        }
        catch (Exception e)
        {
            response = new Response(null, false, "Get All themes Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return  response;
    }

    @Override
    public Response newTheme(ThemeRequest themeRequest) {
        Response response;
        try
        {
            ThemeDto themeDto = commonConverter.convertThemeRequestToThemeDto(new ThemeDto(), themeRequest);
            Theme theme = commonConverter.convertThemeDtoToThemeEntity(new Theme(), themeDto);
            theme = themeService.save(theme);
            if(theme != null)
            {
                response = new Response(
                        commonConverter.convertThemeEntityToThemeDto(theme),
                        true,
                        "Add New Theme Success!",
                        HttpStatus.OK.value()
                );
            }
            else
                response = new Response(null, false, "Add New Theme Failed!", HttpStatus.BAD_REQUEST.value());
        }
        catch (Exception e)
        {
            response = new Response(null, false, "Add New Theme Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @Override
    public Response delete(String themeId) {
        Response response;
        try{
            if(themeId != null){
                Theme theme = themeService.findThemeByThemeId(UUID.fromString(themeId));
                if(theme != null)
                {
                    themeService.delete(UUID.fromString(themeId));
                    response = new Response(commonConverter.convertThemeEntityToThemeDto(theme), true, "DELETE Theme Success!", HttpStatus.OK.value());
                }
                else
                    response = new Response(null, false, "Have No That ThemeId!", HttpStatus.BAD_REQUEST.value());
            }
            else
                response = new Response(null, false, "Delete Theme Failed!", HttpStatus.BAD_REQUEST.value());
        }
        catch (Exception e)
        {
            response = new Response(null, false, "Delete Theme Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }

    @Override
    public Response updateTheme(String themeId, ThemeRequest themeRequest) {
        Response response;
        try{
            if(themeId != null){
                Theme theme = themeService.findThemeByThemeId(UUID.fromString(themeId));
                ThemeDto themeDto = commonConverter.convertThemeEntityToThemeDto(theme);
                themeDto = commonConverter.convertThemeRequestToThemeDto(themeDto, themeRequest);
                theme = commonConverter.convertThemeDtoToThemeEntity(theme, themeDto);

                theme = themeService.save(theme);
                response = new Response(commonConverter.convertThemeEntityToThemeDto(theme), true, "Update Theme Success!", HttpStatus.OK.value());
            }
            else
                response = new Response(null, false, "Update Theme Failed!", HttpStatus.BAD_REQUEST.value());
            }
            catch (Exception e)
        {
            response = new Response(null, false, "Update Theme Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }
}
