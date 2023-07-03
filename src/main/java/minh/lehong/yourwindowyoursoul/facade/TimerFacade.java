package minh.lehong.yourwindowyoursoul.facade;

import minh.lehong.yourwindowyoursoul.dto.payload.request.TimerRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;

import java.text.ParseException;

public interface TimerFacade {
    Response updateTimer(String timerId, TimerRequest timerRequest) throws ParseException;
}
