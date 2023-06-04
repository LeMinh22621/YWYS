package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.LabelDto;
import minh.lehong.yourwindowyoursoul.dto.RoomDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.LabelRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Label;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.repository.LabelRepository;
import minh.lehong.yourwindowyoursoul.service.LabelService;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private CommonConverter commonConverter;
    @Autowired
    private RoomService roomService;
    @Override
    public Label findLabelById(UUID labelUuid) {
        return labelRepository.findLabelByLabelIdAndIsDeleted(labelUuid, false)
                .orElseThrow(() -> new DBException("Find Label Error!"));
    }

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
            label = labelRepository.save(label);
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

        Label label = this.findLabelById(UUID.fromString(labelId));
        if(label != null)
        {
            LabelDto labelDto = commonConverter.convertLabelEntityToLabelDto(label);
            labelDto = commonConverter.convertLabelRequestToLabelDto(labelDto, labelRequest);
            label = commonConverter.convertLabelDtoToLabelEntity(label, labelDto);
            try{
                label = labelRepository.save(label);
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
        Label label = this.findLabelById(UUID.fromString(labelId));
        label.setIsDeleted(true);
        try
        {
            label = labelRepository.save(label);
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
            Label label = this.findLabelById(UUID.fromString(labelId));
            response = new Response(commonConverter.convertLabelDtoToLabelResponse(commonConverter.convertLabelEntityToLabelDto(label)),
                    true, "GET Label Success!", HttpStatus.OK.value());
        }
        catch (DBException exception)
        {
            response = new Response(null, false, "GET Label Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }
}
