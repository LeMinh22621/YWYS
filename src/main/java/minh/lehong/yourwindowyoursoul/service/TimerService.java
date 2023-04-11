package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.TimerDto;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Timer;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.OpaqueTokenDsl;

import java.text.ParseException;
import java.util.Optional;
import java.util.UUID;

public interface TimerService {
    Timer findById(UUID uuid);

    Timer save(Timer timer);

    Response updateTimer(String roomId, TimerDto timerDto) throws ParseException;
}
