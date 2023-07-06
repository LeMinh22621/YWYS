package minh.lehong.yourwindowyoursoul.facade;

import minh.lehong.yourwindowyoursoul.dto.payload.request.ThemeRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;

public interface ThemeFacade {
    Response findAllThemes();

    Response newTheme(ThemeRequest themeRequest);

    Response delete(String themeId);

    Response updateTheme(String themeId, ThemeRequest themeRequest);
}
