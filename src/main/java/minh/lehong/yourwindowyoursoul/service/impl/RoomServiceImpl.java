package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.converter.impl.CommonConverterImpl;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.*;
import minh.lehong.yourwindowyoursoul.repository.RoomRepository;
import minh.lehong.yourwindowyoursoul.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.jws.Oneway;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MotivationalQuoteService motivationalQuoteService;

    @Autowired
    private TimerService timerService;

    @Autowired
    private BackgroundService backgroundService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonConverter commonConverter;

    @Override
    public Room saveRoom(Room room) {
        return Optional.of(roomRepository.save(room))
                .orElseThrow(() -> new DBException("No BackgroundID found!"));
    }

    @Override
    public Room findRoomById(UUID roomUuid) {
        return roomRepository.findById(roomUuid)
                .orElseThrow(() -> new DBException("No BackgroundID found!"));
    }

    @Override
    public Response createRoom(String authHeader) {
        Response response = null;

        String email = jwtService.extractUsername(authHeader.substring(7));
        User user = userService.findByEmail(email);

        Background background = backgroundService.getFirstBackground();

        MotivationalQuote motivationalQuote = motivationalQuoteService.getFirstMotivationalQuote();

        Timer timer = new Timer();
        if(timerService.save(timer) == null)
            throw new DBException("Create timer Error!");

        Room room = new Room(user, background, motivationalQuote, timer);

        if(this.saveRoom(room) != null)
        {
            response = new Response();
            response.setData(commonConverter.convertRoomEntityToRoomDto(room));
            response.setTitle(HttpStatus.CREATED.name());
            response.setStatus(true);
            response.setReturnCode(HttpStatus.CREATED.value());
            response.setMessage(HttpStatus.CREATED.name());
        }
        return response;
    }
}
