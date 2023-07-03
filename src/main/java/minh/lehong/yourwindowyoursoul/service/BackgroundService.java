package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.request.BackgroundRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface BackgroundService {

    Background save(Background background);

    Collection<Background> getAllBackgrounds();

    Background getFirstBackground();

    Background getRandomBackgroundByTheme(String themeId);

    Background findById(UUID backgroundId);

    Collection<Background> findBackgroundsByTheme(Theme theme);
}
