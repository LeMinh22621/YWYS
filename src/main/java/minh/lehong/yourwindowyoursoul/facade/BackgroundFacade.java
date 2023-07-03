package minh.lehong.yourwindowyoursoul.facade;

import minh.lehong.yourwindowyoursoul.dto.payload.request.BackgroundRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import org.springframework.web.multipart.MultipartFile;

public interface BackgroundFacade {
    Response getBackgroundByBackgroundId(String backgroundId);

    Response getBackgroundListByThemeId(String themeId) throws IllegalArgumentException;

    Response addNewBackground(MultipartFile file, BackgroundRequest backgroundRequest);
}
