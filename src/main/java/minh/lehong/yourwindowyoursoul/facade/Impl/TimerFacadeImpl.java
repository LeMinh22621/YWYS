package minh.lehong.yourwindowyoursoul.facade.Impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.TimerDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.TimerRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.facade.TimerFacade;
import minh.lehong.yourwindowyoursoul.model.entity.Timer;
import minh.lehong.yourwindowyoursoul.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Component
public class TimerFacadeImpl implements TimerFacade {
    @Autowired
    private CommonConverter commonConverter;

    @Autowired
    private TimerService timerService;

    @Override
    public Response updateTimer(String timerId, TimerRequest timerRequest) throws ParseException {
        Response response;
        Timer timer = timerService.findById(UUID.fromString(timerId));
        TimerDto timerDto = commonConverter.convertTimerEntityToTimerDto(timer);
        timerDto = commonConverter.convertTimerRequestToTimerDto(timerDto, timerRequest);
        timerDto.setUpdateDate(commonConverter.convertToG7(new Date()));
        timer = commonConverter.convertTimerDtoToTimerEntity(timer, timerDto);
        try {
            timer = timerService.save(timer);
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
