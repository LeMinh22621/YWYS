package minh.lehong.yourwindowyoursoul.repository;

import minh.lehong.yourwindowyoursoul.model.entity.MotivationalQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MotivationalQuoteRepository extends JpaRepository<MotivationalQuote, UUID> {
    @Query(value = "SELECT * FROM motivational_quote ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<MotivationalQuote> getFirstMotivationalQuote();
}
