package minh.lehong.yourwindowyoursoul.service.impl;

import com.amazonaws.services.s3.model.ObjectMetadata;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.BackgroundDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.BackgroundRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.BackgroundResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;
import minh.lehong.yourwindowyoursoul.repository.BackgroundRepository;
import minh.lehong.yourwindowyoursoul.service.BackgroundService;
import minh.lehong.yourwindowyoursoul.service.FileStoreS3Service;
import minh.lehong.yourwindowyoursoul.service.ThemeService;
import minh.lehong.yourwindowyoursoul.utils.BucketName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.RescaleOp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BackgroundServiceImpl implements BackgroundService {

    @Autowired
    private BackgroundRepository backgroundRepository;

    @Override
    public Background save(Background background) {
        return Optional.of(backgroundRepository.save(background))
                .orElseThrow(() -> new DBException("Save Background Error!"));
    }

    @Override
    public Collection<Background> getAllBackgrounds() {
        return backgroundRepository.findAllByIsDeleted(false);
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
        return backgroundRepository.findByBackgroundIdAndIsDeleted(backgroundId, false).orElseThrow(() -> new DBException("Have No BackgroundId"));
    }

    @Override
    public Collection<Background> findBackgroundsByTheme(Theme theme) {
        return backgroundRepository.findBackgroundsByThemeAndIsDeleted(theme, false);
    }
}
