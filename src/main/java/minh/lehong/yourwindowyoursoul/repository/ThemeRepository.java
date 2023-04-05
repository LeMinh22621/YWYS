package minh.lehong.yourwindowyoursoul.repository;

import minh.lehong.yourwindowyoursoul.model.entity.MotivationalQuote;
import minh.lehong.yourwindowyoursoul.model.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, UUID> {
}
