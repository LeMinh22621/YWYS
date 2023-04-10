package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Timer;
import minh.lehong.yourwindowyoursoul.repository.TimerRepository;
import minh.lehong.yourwindowyoursoul.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TimerServiceImpl implements TimerService {
    @Autowired
    TimerRepository timerRepository;


    @Override
    public Timer findById(UUID uuid) {
        return timerRepository.findById(uuid)
                .orElseThrow(() -> new DBException("Get Timer by TimerId Error!"));
    }

    @Override
    public Timer save(Timer timer) {
        return timerRepository.save(timer);
    }
}
