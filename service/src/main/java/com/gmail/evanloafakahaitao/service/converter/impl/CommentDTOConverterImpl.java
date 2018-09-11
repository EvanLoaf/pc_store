package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Comment;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.service.dto.CommentFeedbackNewsLoginUserDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentDTOConverterImpl implements DTOConverter<CommentDTO, Comment> {

    private DTOConverter commentFeedbackNewsUserDTOConverter = new CommentFeedbackNewsLoginUserDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public CommentDTO toDto(Comment entity) {
        return CommentDTO.newBuilder()
                .withId(entity.getId())
                .withContent(entity.getContent())
                .withCreated(entity.getCreated())
                .withUser((CommentFeedbackNewsLoginUserDTO) commentFeedbackNewsUserDTOConverter.toDto(entity.getUser()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CommentDTO> toDTOList(List<Comment> entityList) {
        return entityList.stream().map(entity -> CommentDTO.newBuilder()
                .withId(entity.getId())
                .withContent(entity.getContent())
                .withCreated(entity.getCreated())
                .withUser((CommentFeedbackNewsLoginUserDTO) commentFeedbackNewsUserDTOConverter.toDto(entity.getUser()))
                .build()).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<CommentDTO> toDTOSet(Set<Comment> entitySet) {
        return entitySet.stream().map(entity -> CommentDTO.newBuilder()
                .withId(entity.getId())
                .withContent(entity.getContent())
                .withCreated(entity.getCreated())
                .withUser((CommentFeedbackNewsLoginUserDTO) commentFeedbackNewsUserDTOConverter.toDto(entity.getUser()))
                .build()).collect(Collectors.toSet());
    }
}
