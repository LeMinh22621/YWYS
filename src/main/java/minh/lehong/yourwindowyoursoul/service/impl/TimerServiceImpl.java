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

    @Autowired
    private CommonConverter commonConverter;

    @Autowired
    private RoomService roomService;
    @Override
    public Timer findById(UUID uuid) {
        return timerRepository.findById(uuid)
                .orElseThrow(() -> new DBException("Get Timer by TimerId Error!"));
    }

    @Override
    public Timer save(Timer timer) {
        return timerRepository.save(timer);
    }

    @Override
    public Response updateTimer(String timerId, TimerRequest timerRequest) throws ParseException {
        Response response;
        Timer timer = timerRepository.findById(UUID.fromString(timerId)).orElseThrow(() -> new DBException("find timer Id error"));
        TimerDto timerDto = commonConverter.convertTimerEntityToTimerDto(timer);
        timerDto = commonConverter.convertTimerRequestToTimerDto(timerDto, timerRequest);
        timerDto.setUpdateDate(commonConverter.convertToG7(new Date()));
        timer = commonConverter.convertTimerDtoToTimerEntity(timer, timerDto);
        try {
            timer = timerRepository.save(timer);
            response = new Response(commonConverter.convertTimerEntityToTimerDto(timer),
                    true, "Update Timer Success!", HttpStatus.OK.value());
        }
        catch (Exception e)
        {
            response = new Response(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }
}
