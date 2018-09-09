package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.News;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.CommentFeedbackNewsUserDTO;
import com.gmail.evanloafakahaitao.service.dto.NewsDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NewsDTOConverterImpl<D, E> implements DTOConverter<NewsDTO, News> {

    private DTOConverter commentFeedbackNewsUserDTOConverter = new CommentFeedbackNewsUserDTOConverterImpl();
    private DTOConverter commentDTOConverter = new CommentDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public NewsDTO toDto(News entity) {
        return NewsDTO.newBuilder()
                .withId(entity.getId())
                .withTitle(entity.getTitle())
                .withContent(entity.getContent())
                .withCreated(entity.getCreated())
                .withUser((CommentFeedbackNewsUserDTO) commentFeedbackNewsUserDTOConverter.toDto(entity.getUser()))
                .withCommentSet(commentDTOConverter.toDTOSet(entity.getCommentSet()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<NewsDTO> toDTOList(List<News> entityList) {
        return entityList.stream().map(entity -> NewsDTO.newBuilder()
                .withId(entity.getId())
                .withTitle(entity.getTitle())
                .withContent(entity.getContent())
                .withCreated(entity.getCreated())
                .withUser((CommentFeedbackNewsUserDTO) commentFeedbackNewsUserDTOConverter.toDto(entity.getUser()))
                .withCommentSet(commentDTOConverter.toDTOSet(entity.getCommentSet()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<NewsDTO> toDTOSet(Set<News> entitySet) {
        return null;
    }
}
