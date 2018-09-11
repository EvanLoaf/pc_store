package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Feedback;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.FeedbackDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FeedbackConverterImpl implements Converter<FeedbackDTO, Feedback> {

    private Converter commentFeedbackNewsLoginUserConverter = new CommentFeedbackNewsLoginUserConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public Feedback toEntity(FeedbackDTO dto) {
        return Feedback.newBuilder()
                .withId(dto.getId())
                .withMessage(dto.getMessage())
                .withUser((User) commentFeedbackNewsLoginUserConverter.toEntity(dto.getUser()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Feedback> toEntityList(List<FeedbackDTO> dtoList) {
        return dtoList.stream().map(dto -> Feedback.newBuilder()
                .withId(dto.getId())
                .withMessage(dto.getMessage())
                .withUser((User) commentFeedbackNewsLoginUserConverter.toEntity(dto.getUser()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<Feedback> toEntitySet(Set<FeedbackDTO> dtoSet) {
        return null;
    }
}
