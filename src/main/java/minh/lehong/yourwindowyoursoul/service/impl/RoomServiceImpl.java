package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.converter.impl.CommonConverterImpl;
import minh.lehong.yourwindowyoursoul.dto.RoomDto;
import minh.lehong.yourwindowyoursoul.dto.TimerDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.RoomRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.dto.payload.response.RoomItemResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.RoomResponse;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.*;
import minh.lehong.yourwindowyoursoul.model.entity.Timer;
import minh.lehong.yourwindowyoursoul.repository.RoomRepository;
import minh.lehong.yourwindowyoursoul.service.*;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.jws.Oneway;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MotivationalQuoteService motivationalQuoteService;

    @Autowired
    private TimerService timerService;

    @Autowired
    private ThemeService themeService;

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
            response.setMessage(String.format("Get Room By %s Success!", roomId));
        }
        return response;
    }

    @Override
    public Room findRoomById(UUID roomId) {
        return roomRepository.findRoomByRoomIdAndIsDeleted(roomId, false)
                .orElseThrow(() -> new DBException("GET Room by roomId error!"));
    }

    @Override
    public Response createRoom(String authHeader, RoomRequest roomRequest) throws ParseException {
        Response response = null;

        String email = jwtService.extractUsername(authHeader.substring(7));
        User user = userService.findByEmail(email);

        Background background = backgroundService.getFirstBackground();
        MotivationalQuote motivationalQuote = motivationalQuoteService.getFirstMotivationalQuote();
        Timer timer = new Timer();

        Room room = new Room(user, background, motivationalQuote, timer);
        RoomDto roomDto = commonConverter.convertRoomRequestToRoomDto(roomRequest);
        roomDto.setIsDeleted(false);
        roomDto.setCreateDate(commonConverter.convertToG7(new Date()));
        roomDto.setUpdateDate(commonConverter.convertToG7(new Date()));

        room = commonConverter.convertRoomDtoToRoomEntity(room, roomDto);

        if((room = this.saveRoom(room)) != null)
        {
            response = new Response();
            response.setData(commonConverter.convertRoomEntityToRoomDto(room));
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

        Collection<UUID> roomIds = roomRepository.findRoomIdByUserId(user.getUserId());

        if(roomIds != null)
        {
            response = new Response();
            response.setData(roomIds);
            response.setReturnCode(HttpStatus.OK.value());
            response.setMessage("Get my rooms success!");
            response.setStatus(true);
        }
        return response;
    }

    @Override
    public Response shuffleMotivationalQuote() {
        Response response = new Response();
        response.setData(commonConverter.convertMotivationalQuoteEntityToMotivationalQuoteDto(motivationalQuoteService.getFirstMotivationalQuote()));
        response.setStatus(true);
        response.setReturnCode(HttpStatus.OK.value());
        response.setMessage("shuffle Motivational Quote success!");
        return response;
    }

//    private <T> T selectRandomElement(List<T> collection) {
//        if (collection.isEmpty()) {
//            return null;
//        }
//        Random random = new Random();
//        int randomIndex = random.nextInt(collection.size());
//        return collection.get(randomIndex);
//    }
    @Override
    public Response shuffleBackgroundByThemeId(String themeId) {
        Response response = new Response();

        Theme theme = themeService.findThemeByThemeId(UUID.fromString(themeId));
        if(theme != null)
        {
            Background background = backgroundService.getRandomBackgroundByTheme(themeId);

//        Background background = this.selectRandomElement(theme.getBackgrounds().stream().collect(Collectors.toList()));

            response.setData(commonConverter.convertBackgroundEntityToBackgroundDto(background));
            response.setStatus(true);
            response.setReturnCode(HttpStatus.OK.value());
            response.setMessage("Shuffle Background By Theme Id Success!");
        }
        return response;
    }

    @Override
    public Response getMyRoomListByUserId(String userId) {
        Response response;
        try{
            User user = userService.findByUserId(UUID.fromString(userId));
            if(user != null) {
                List<Room> roomList = roomRepository.findRoomsByUserAndIsDeleted(user, false);
                if(roomList == null || roomList.isEmpty())
                    response = new Response(null, false, "Have No Any Room!", HttpStatus.NO_CONTENT.value());
                else
                {
                    List<RoomItemResponse> roomResponseList = roomList.stream().map(room ->
                            commonConverter.convertRoomDtoToRoomItemResponse(commonConverter.convertRoomEntityToRoomDto(room))
                    ).collect(Collectors.toList());
                    response = new Response(roomResponseList, true, "GET My Room List Success!", HttpStatus.OK.value());
                }

            }
            else {
                response =  new Response(null, false, "Have No User!", HttpStatus.BAD_REQUEST.value());
            }
        }
        catch (Exception e)
        {
            response = new Response(null, false, "GET My Room List Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @Override
    public Response updateRoomApart(String roomId, RoomRequest updateRoomRequest) throws ParseException {
        Response response;
        try
        {
            RoomDto roomDto = commonConverter.convertRoomRequestToRoomDto(updateRoomRequest);
            if((roomId != null || !roomId.isEmpty()) && roomDto != null)
            {
                // update date
                roomDto.setUpdateDate(commonConverter.convertToG7(new Date()));
                // fetch room by roomId
                Room room = this.findRoomById(UUID.fromString(roomId));
                // convert to Room Entity
                room = commonConverter.convertRoomDtoToRoomEntity(room, roomDto);
                // set relative update
                if(updateRoomRequest.getTimerRequest() != null)
                {
                    Timer timer = timerService.findById(UUID.fromString(updateRoomRequest.getTimerRequest().getTimerId()));
                    TimerDto timerDto = commonConverter.convertTimerEntityToTimerDto(timer);
                    timerDto = commonConverter.convertTimerRequestToTimerDto(timerDto, updateRoomRequest.getTimerRequest());

                    timer = commonConverter.convertTimerDtoToTimerEntity(timer, timerDto);
//                    try
//                    {
//                        timer = timerService.save(timer);
                        room.setTimer(timer);
//                    }
//                    catch (Exception e)
//                    {
//                        return new Response(null, true, "Save Timer Error", HttpStatus.INTERNAL_SERVER_ERROR.value());
//                    }
                }
                if(updateRoomRequest.getBackgroundId() != null)
                {
                    Background background = backgroundService.findById(UUID.fromString(updateRoomRequest.getBackgroundId()));
                    room.setBackground(background);
                }
                if(updateRoomRequest.getMotivationalQuoteId() != null)
                {
                    MotivationalQuote motivationalQuote = motivationalQuoteService.findById(UUID.fromString(updateRoomRequest.getMotivationalQuoteId()));
                    room.setMotivationalQuote(motivationalQuote);
                }

                room = this.saveRoom(room);
                response = new Response(commonConverter.convertRoomDtoToRoomResponse(commonConverter.convertRoomEntityToRoomDto(room)),
                        true, "Update Room Success!", HttpStatus.OK.value());
            }
            else {
                response = new Response(null, false, "Bad Request!", HttpStatus.BAD_REQUEST.value());
            }
        }
        catch (Exception e)
        {
            response = new Response(null, false, "Bad Request!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }


        return response;
    }
}
