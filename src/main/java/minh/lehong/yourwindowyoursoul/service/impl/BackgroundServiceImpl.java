package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.repository.BackgroundRepository;
import minh.lehong.yourwindowyoursoul.service.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BackgroundServiceImpl implements BackgroundService {

    @Autowired
    private BackgroundRepository backgroundRepository;

    @Override
    public Background getBackgroundByBackgroundId(UUID uuid) {
        return backgroundRepository.findById(uuid)
                .orElseThrow(() -> new DBException("No BackgroundID found!"));
    }

    @Override
    public Background save(Background background) {
        return Optional.of(backgroundRepository.save(background))
                .orElseThrow(() -> new DBException("Save Background Error!"));
    }

    @Override
    public Collection<Background> getAllBackgrounds() {
        return backgroundRepository.findAll();
    }

    @Override
    public Background getFirstBackground() {
        return backgroundRepository.findAll().get(0);
    }
}
