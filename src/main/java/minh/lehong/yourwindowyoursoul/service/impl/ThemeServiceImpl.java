package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;
import minh.lehong.yourwindowyoursoul.repository.ThemeRepository;
import minh.lehong.yourwindowyoursoul.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Override
    public Theme findThemeByThemeId(UUID themeId) {
        return themeRepository.findById(themeId)
                .orElseThrow(() -> new DBException("get Theme by themeId ERROR!"));
    }
}
