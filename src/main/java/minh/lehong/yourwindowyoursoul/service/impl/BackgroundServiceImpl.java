package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.BackgroundDto;
import minh.lehong.yourwindowyoursoul.dto.payload.response.BackgroundResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;
import minh.lehong.yourwindowyoursoul.repository.BackgroundRepository;
import minh.lehong.yourwindowyoursoul.service.BackgroundService;
import minh.lehong.yourwindowyoursoul.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.image.RescaleOp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BackgroundServiceImpl implements BackgroundService {

    @Autowired
    private BackgroundRepository backgroundRepository;

    @Autowired
    private CommonConverter commonConverter;

    @Autowired
    private ThemeService themeService;

    @Override
    public Response getBackgroundByBackgroundId(String backgroundId) {
        Response response;
        try
        {
            Background background = backgroundRepository.findById(UUID.fromString(backgroundId))
                    .orElseThrow(() -> new DBException("No BackgroundID found!"));
            BackgroundResponse backgroundResponse = commonConverter.convertBackgroundDtoToBackGroundResponse(commonConverter.convertBackgroundEntityToBackgroundDto(background));

            response = new Response(backgroundResponse, true, "GET Background By BackgroundId Success!", HttpStatus.OK.value());
        }
        catch (Exception e)
        {
            response = new Response(null, false, "GET Background Failed", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }

    @Override
    public Background save(Background background) {
        return Optional.of(backgroundRepository.save(background))
                .orElseThrow(() -> new DBException("Save Background Error!"));
    }

    @Override
    public Response getBackgroundListByThemeId(String themeId) throws IllegalArgumentException{
        UUID uuidThemeId = UUID.fromString(themeId);
        Theme theme = themeService.findThemeByThemeId(uuidThemeId);
        Response response = new Response();
        Collection<Background> backgrounds = backgroundRepository.findBackgroundsByTheme(theme);
        Collection<BackgroundDto> backgroundDtos = backgrounds.stream().map(background -> commonConverter.convertBackgroundEntityToBackgroundDto(background)).collect(Collectors.toList());

        response.setData(backgroundDtos);
        response.setMessage("Get List Background based on themeId is ok!");
        response.setStatus(true);
        response.setReturnCode(HttpStatus.OK.value());

        return response;
    }

    @Override
    public Collection<Background> getAllBackgrounds() {
        return backgroundRepository.findAll();
    }

    @Override
    public Background getFirstBackground() {
        return backgroundRepository.getRandomBackground();
    }

    @Override
    public Background getRandomBackgroundByTheme(String themeId) {
        return backgroundRepository.getRandomBackgroundByTheme(themeId);
    }

    @Override
    public Background findById(UUID backgroundId) {
        return backgroundRepository.findById(backgroundId).orElseThrow(() -> new DBException("Have NO BackgroundId"));
    }
}
