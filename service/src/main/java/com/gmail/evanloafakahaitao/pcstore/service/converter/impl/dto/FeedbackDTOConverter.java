package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Feedback;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.FeedbackDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;

public class FeedbackDTOConverter implements DTOConverter<FeedbackDTO, Feedback> {

    private DTOConverter simpleUserDTOConverter = new SimpleUserDTOConverter();

    @SuppressWarnings("unchecked")
    @Override
    public FeedbackDTO toDto(Feedback entity) {
        return FeedbackDTO.newBuilder()
                .withId(entity.getId())
                .withMessage(entity.getMessage())
                .withUser((SimpleUserDTO) simpleUserDTOConverter.toDto(entity.getUser()))
                .build();
    }
}
