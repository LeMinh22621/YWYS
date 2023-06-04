package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.request.LabelRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Label;

import java.text.ParseException;
import java.util.UUID;

public interface LabelService {
    Label findLabelById(UUID labelUuid);

    Response createLabel(LabelRequest labelRequest) throws ParseException;

    Response updateLabel(String labelId, LabelRequest labelRequest);

    Response deleteLabel(String labelId);

    Response getLabel(String labelId);
}
