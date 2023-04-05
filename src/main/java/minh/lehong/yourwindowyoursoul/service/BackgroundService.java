package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Background;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface BackgroundService {
    Background getBackgroundByBackgroundId(UUID uuid);

    Background save(Background background);

    Response getBackgroundListByThemeId(String themeId);

    Collection<Background> getAllBackgrounds();

    Background getFirstBackground();
}
