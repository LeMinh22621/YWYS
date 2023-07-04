package minh.lehong.yourwindowyoursoul.repository;

import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BackgroundRepository  extends JpaRepository<Background, UUID> {

    Optional<Background> findByBackgroundIdAndIsDeleted(UUID backgroundId, boolean isDeleted);
    @Query(value = "SELECT * FROM background ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Background getRandomBackground();

    List<Background> findAllByIsDeleted(boolean isDelted);
    Collection<Background> findBackgroundsByThemeAndIsDeleted(Theme theme, boolean isDeleted);

    @Query(value = "SELECT * FROM background WHERE theme_id = ?1 ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Background getRandomBackgroundByTheme(String themeId);
}
