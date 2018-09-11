package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Comment;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.CommentDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentConverterImpl implements Converter<CommentDTO, Comment> {

    private Converter commentFeedbackNewsUserConverter = new CommentFeedbackNewsLoginUserConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public Comment toEntity(CommentDTO dto) {
        return Comment.newBuilder()
                .withId(dto.getId())
                .withContent(dto.getContent())
                .withCreated(dto.getCreated())
                .withUser((User) commentFeedbackNewsUserConverter.toEntity(dto.getUser()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> toEntityList(List<CommentDTO> dtoList) {
        return dtoList.stream().map(dto -> Comment.newBuilder()
                .withId(dto.getId())
                .withContent(dto.getContent())
                .withCreated(dto.getCreated())
                .withUser((User) commentFeedbackNewsUserConverter.toEntity(dto.getUser()))
                .build()).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Comment> toEntitySet(Set<CommentDTO> dtoSet) {
        return dtoSet.stream().map(dto -> Comment.newBuilder()
                .withId(dto.getId())
                .withContent(dto.getContent())
                .withCreated(dto.getCreated())
                .withUser((User) commentFeedbackNewsUserConverter.toEntity(dto.getUser()))
                .build()).collect(Collectors.toSet());
    }
}
