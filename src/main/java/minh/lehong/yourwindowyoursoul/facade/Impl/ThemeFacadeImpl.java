package minh.lehong.yourwindowyoursoul.facade.Impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.payload.response.FullThemeResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.facade.ThemeFacade;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;
import minh.lehong.yourwindowyoursoul.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ThemeFacadeImpl implements ThemeFacade {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private CommonConverter commonConverter;


    @Override
    public Response findAllThemes() {
        Response response;
        try
        {
            List<Theme> themes = themeService.findAll();
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
}
