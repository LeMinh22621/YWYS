package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;

import java.util.List;
import java.util.UUID;

public interface ThemeService {
    Theme findThemeByThemeId(UUID themeId);

    List<Theme> findAll();
}
