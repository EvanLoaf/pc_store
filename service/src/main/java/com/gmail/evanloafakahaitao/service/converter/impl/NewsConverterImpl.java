package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.News;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.NewsDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NewsConverterImpl implements Converter<NewsDTO, News> {

    private Converter commentFeedbackNewsUserConverter = new CommentFeedbackNewsLoginUserConverterImpl();
    private Converter commentConverter = new CommentConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public News toEntity(NewsDTO dto) {
        return News.newBuilder()
                .withId(dto.getId())
                .withTitle(dto.getTitle())
                .withContent(dto.getContent())
                .withCreated(dto.getCreated())
                .withUser((User) commentFeedbackNewsUserConverter.toEntity(dto.getUser()))
                .withCommentSet(commentConverter.toEntitySet(dto.getCommentSet()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<News> toEntityList(List<NewsDTO> dtoList) {
        return dtoList.stream().map(dto -> News.newBuilder()
                .withId(dto.getId())
                .withTitle(dto.getTitle())
                .withContent(dto.getContent())
                .withCreated(dto.getCreated())
                .withUser((User) commentFeedbackNewsUserConverter.toEntity(dto.getUser()))
                .withCommentSet(commentConverter.toEntitySet(dto.getCommentSet()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<News> toEntitySet(Set<NewsDTO> dtoSet) {
        return null;
    }
}
