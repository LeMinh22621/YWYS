package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.TimerDto;
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
    public Response updateTimer(String roomId, TimerDto timerDto) throws ParseException {
        Response response = null;
        Timer timer = commonConverter.convertTimerDtoToTimerEntity(roomId, timerDto);
        timer.setUpdatedDate(commonConverter.convertToG7(new Date()));

        if(timerRepository.save(timer) != null)
        {
            response = new Response();
            response.setData(commonConverter.convertTimerEntityToTimerDto(timer));
            response.setStatus(true);
            response.setTitle(HttpStatus.OK.name());
            response.setMessage("Update timer Success!");
            response.setReturnCode(HttpStatus.OK.value());
        }
        return response;
    }
}
