package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.request.LabelRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Label;

import java.text.ParseException;
import java.util.UUID;

public interface LabelService {
    Label save(Label label);
    Label findLabelById(UUID labelUuid);
}
