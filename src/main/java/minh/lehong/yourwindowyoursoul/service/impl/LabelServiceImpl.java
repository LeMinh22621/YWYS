package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.LabelDto;
import minh.lehong.yourwindowyoursoul.dto.RoomDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.LabelRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.LabelResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Label;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.repository.LabelRepository;
import minh.lehong.yourwindowyoursoul.service.LabelService;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private CacheManager cacheManager;


    @Override
    @CachePut(key = "#label.labelId", value = "label")
    public Label save(Label label) {
        Label updatedLabel = labelRepository.save(label);
        cacheManager.getCache("label").evict(label.getLabelId());
        return updatedLabel;
    }
    @Override
    @Cacheable(key = "#labelUuid", value = "label")
    public Label findLabelById(UUID labelUuid) {
        return labelRepository.findLabelByLabelIdAndIsDeleted(labelUuid, false)
                .orElseThrow(() -> new DBException("Find Label Error!"));
    }

    @Override
    @CacheEvict(key = "#label.labelId", value = "label")
    public Label delete(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public List<Label> findAllLabelsByRoom(Room room) {
        return labelRepository.findAllByRoomAndIsDeleted(room, false);
    }
}
