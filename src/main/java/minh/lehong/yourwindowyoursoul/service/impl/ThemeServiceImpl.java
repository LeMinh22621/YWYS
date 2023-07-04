package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.payload.response.FullThemeResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;
import minh.lehong.yourwindowyoursoul.repository.ThemeRepository;
import minh.lehong.yourwindowyoursoul.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Override
    @Cacheable(value = "theme", key = "#themeId")
    public Theme findThemeByThemeId(UUID themeId) {
        return themeRepository.findById(themeId)
                .orElseThrow(() -> new DBException("get Theme by themeId ERROR!"));
    }

    @Override
    @Cacheable(value = "themes", key = "'themes'")
    public List<Theme> findAll() {
        return  themeRepository.findAll();
    }
}
