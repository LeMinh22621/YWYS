package minh.lehong.yourwindowyoursoul.facade.Impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.LabelDto;
import minh.lehong.yourwindowyoursoul.dto.RoomDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.LabelRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.LabelResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.facade.LabelFacade;
import minh.lehong.yourwindowyoursoul.model.entity.Label;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.repository.LabelRepository;
import minh.lehong.yourwindowyoursoul.service.LabelService;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class LabelFacadeImpl implements LabelFacade {
    @Autowired
    private LabelService labelService;
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private CommonConverter commonConverter;
    @Autowired
    private RoomService roomService;

    @Override

    public Response createLabel(LabelRequest labelRequest) throws ParseException {
        Response response;

        LabelDto labelDto = commonConverter.convertLabelRequestToLabelDto(new LabelDto(), labelRequest);
        labelDto.setIsDeleted(false);
        labelDto.setCreateDate(commonConverter.convertToG7(new Date()));
        labelDto.setUpdateDate(commonConverter.convertToG7(new Date()));
        // set roomDto
        if(labelRequest.getRoomId() != null)
        {
            Room room = roomService.findRoomById(UUID.fromString(labelRequest.getRoomId()));
            RoomDto roomDto = commonConverter.convertRoomEntityToRoomDto(room);
            labelDto.setRoomDto(roomDto);
        }

        Label label = commonConverter.convertLabelDtoToLabelEntity(new Label(), labelDto);
        try{
            label = labelService.save(label);
            response = new Response(commonConverter.convertLabelDtoToLabelResponse(commonConverter.convertLabelEntityToLabelDto(label)),
                    true, "Create Label Success!", HttpStatus.OK.value());
        }
        catch (Exception e)
        {
            response = new Response(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @Override
    public Response updateLabel(String labelId, LabelRequest labelRequest) {
        Response response;

        Label label = labelService.findLabelById(UUID.fromString(labelId));
        if(label != null)
        {
            LabelDto labelDto = commonConverter.convertLabelEntityToLabelDto(label);
            labelDto = commonConverter.convertLabelRequestToLabelDto(labelDto, labelRequest);
            label = commonConverter.convertLabelDtoToLabelEntity(label, labelDto);
            try{
                label = labelService.save(label);
                response = new Response(commonConverter.convertLabelDtoToLabelResponse(commonConverter.convertLabelEntityToLabelDto(label)),
                        true, "Update Label Success!", HttpStatus.OK.value());
            }
            catch (Exception e)
            {
                response = new Response(null, false, "Update Label Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }
        else {
            response = new Response(null, false, "Have No That Label Id!", HttpStatus.BAD_REQUEST.value());
        }

        return response;
    }

    @Override
    public Response deleteLabel(String labelId) {
        Response response;
        Label label = labelService.findLabelById(UUID.fromString(labelId));
        label.setIsDeleted(true);
        try
        {
            label = labelService.save(label);
            response = new Response(commonConverter.convertLabelDtoToLabelResponse(commonConverter.convertLabelEntityToLabelDto(label)),
                    true, "Delete Label Success!", HttpStatus.OK.value());
        }
        catch (Exception e)
        {
            response = new Response(null, false, "Delete Label Error!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @Override
    public Response getLabel(String labelId) {
        Response response;
        try{
            Label label = labelService.findLabelById(UUID.fromString(labelId));
            response = new Response(commonConverter.convertLabelDtoToLabelResponse(commonConverter.convertLabelEntityToLabelDto(label)),
                    true, "GET Label Success!", HttpStatus.OK.value());
        }
        catch (DBException exception)
        {
            response = new Response(null, false, "GET Label Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @Override
    public Response getAllLabelsByRoomId(String roomId) {
        Response response;
        try{
            Room room = roomService.findRoomById(UUID.fromString(roomId));
            if(room !=  null)
            {
                List<Label> labels = labelRepository.findAllByRoom(room);
                List<LabelResponse> labelResponses = labels
                        .stream()
                        .map(label -> commonConverter.convertLabelDtoToLabelResponse(commonConverter.convertLabelEntityToLabelDto(label)))
                        .collect(Collectors.toList());
                response = new Response(labelResponses,
                        true, "GET Label Success!", HttpStatus.OK.value());
            }
            else {
                response = new Response(null, false, "HAVE NO THAT ROOM!", HttpStatus.BAD_REQUEST.value());
            }
        }
        catch (DBException exception)
        {
            response = new Response(null, false, "GET Label Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }
}
