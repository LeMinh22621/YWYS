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
import java.util.Collection;
import java.util.List;
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
        return roomRepository.save(room);
    }

    @Override
    public Response getRoomFromRoomId(String roomId) throws IllegalArgumentException{
        Response response = null;
        Room room = this.findRoomById(UUID.fromString(roomId));
        if(room != null)
        {
            response = new Response();
            response.setData(this.commonConverter.convertRoomEntityToRoomDto(room));
            response.setStatus(true);
            response.setReturnCode(HttpStatus.OK.value());
            response.setTitle(HttpStatus.OK.name());
            response.setMessage(String.format("Get Room By %s Success!", roomId));
        }
        return response;
    }

    @Override
    public Room findRoomById(UUID roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new DBException("GET Room by RoomId error!"));
    }

    @Override
    public Response createRoom(String authHeader) {
        Response response = null;

        String email = jwtService.extractUsername(authHeader.substring(7));
        User user = userService.findByEmail(email);

        Background background = backgroundService.getFirstBackground();
        MotivationalQuote motivationalQuote = motivationalQuoteService.getFirstMotivationalQuote();
        Timer timer = new Timer();

        Room room = new Room(user, background, motivationalQuote, timer);

        if((room = this.saveRoom(room)) != null)
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
    @Override
    public Response getMyRooms(String authHeader) {
        Response response = null;

        String email = jwtService.extractUsername(authHeader.substring(7));
        User user = userService.findByEmail(email);

        Collection<UUID> roomIds = roomRepository.findRoomIdBy(user.getUserId());

        if(roomIds != null)
        {
            response = new Response();
            response.setData(roomIds);
            response.setReturnCode(HttpStatus.OK.value());
            response.setMessage("Get my rooms success!");
            response.setTitle(HttpStatus.OK.name());
            response.setStatus(true);
        }
        return response;
    }
}
