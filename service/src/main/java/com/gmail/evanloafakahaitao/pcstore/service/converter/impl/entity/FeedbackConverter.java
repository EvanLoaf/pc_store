package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Feedback;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.FeedbackDTO;

public class FeedbackConverter implements Converter<FeedbackDTO, Feedback> {

    private Converter simpleUserConverter = new SimpleUserConverter();

    @SuppressWarnings("unchecked")
    @Override
    public Feedback toEntity(FeedbackDTO dto) {
        return Feedback.newBuilder()
                .withId(dto.getId())
                .withMessage(dto.getMessage())
                .withUser((User) simpleUserConverter.toEntity(dto.getUser()))
                .build();
    }
}
