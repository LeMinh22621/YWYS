package minh.lehong.yourwindowyoursoul.repository;

import minh.lehong.yourwindowyoursoul.model.entity.MotivationalQuote;
import minh.lehong.yourwindowyoursoul.model.entity.Timer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TimerRepository extends JpaRepository<Timer, UUID> {

}
