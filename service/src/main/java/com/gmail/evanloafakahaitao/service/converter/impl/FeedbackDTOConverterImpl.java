package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Feedback;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.FeedbackDTO;
import com.gmail.evanloafakahaitao.service.dto.CommentFeedbackNewsLoginUserDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FeedbackDTOConverterImpl implements DTOConverter<FeedbackDTO, Feedback> {

    private DTOConverter commentFeedbackNewsLoginUserDTOConverter = new CommentFeedbackNewsLoginUserDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public FeedbackDTO toDto(Feedback entity) {
        return FeedbackDTO.newBuilder()
                .withId(entity.getId())
                .withMessage(entity.getMessage())
                .withUser((CommentFeedbackNewsLoginUserDTO) commentFeedbackNewsLoginUserDTOConverter.toDto(entity.getUser()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FeedbackDTO> toDTOList(List<Feedback> entityList) {
        return entityList.stream().map(entity -> FeedbackDTO.newBuilder()
                .withId(entity.getId())
                .withMessage(entity.getMessage())
                .withUser((CommentFeedbackNewsLoginUserDTO) commentFeedbackNewsLoginUserDTOConverter.toDto(entity.getUser()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<FeedbackDTO> toDTOSet(Set<Feedback> entitySet) {
        return null;
    }
}
