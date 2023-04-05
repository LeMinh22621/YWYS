package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.model.entity.Theme;

import java.util.UUID;

public interface ThemeService {
    Theme findThemeByThemeId(UUID themeId);
}
