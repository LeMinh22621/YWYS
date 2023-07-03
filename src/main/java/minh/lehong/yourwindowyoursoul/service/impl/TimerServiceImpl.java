package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.TimerDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.TimerRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.model.entity.Timer;
import minh.lehong.yourwindowyoursoul.repository.TimerRepository;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import minh.lehong.yourwindowyoursoul.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class TimerServiceImpl implements TimerService {
    @Autowired
    private TimerRepository timerRepository;
    @Override
    @Cacheable(key = "#uuid", value = "timer")
    public Timer findById(UUID uuid) {
        return timerRepository.findById(uuid)
                .orElseThrow(() -> new DBException("Get Timer by TimerId Error!"));
    }

    @Override
    @CachePut(value = "timer", condition = "#result != null && #result.timerId != null", key = "#result.timerId")
    public Timer save(Timer timer) {
        return timerRepository.save(timer);
    }
}
