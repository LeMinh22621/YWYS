package minh.lehong.yourwindowyoursoul.repository;

import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BackgroundRepository  extends JpaRepository<Background, UUID> {

    @Query(value = "SELECT * FROM background ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Background getRandomBackground();

    Collection<Background> findBackgroundsByTheme(Theme theme);
}
