package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface BackgroundService {
    Response getBackgroundByBackgroundId(String backgroundId);

    Background save(Background background);

    Response getBackgroundListByThemeId(String themeId);

    Collection<Background> getAllBackgrounds();

    Background getFirstBackground();

    Background getRandomBackgroundByTheme(String themeId);
}
