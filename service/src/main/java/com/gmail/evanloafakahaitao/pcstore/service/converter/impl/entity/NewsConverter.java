package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Article;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.NewsDTO;
import org.springframework.stereotype.Component;

@Component("newsConverter")
public class NewsConverter implements Converter<NewsDTO, Article> {

    private Converter simpleUserConverter = new SimpleUserConverter();
    private Converter commentConverter = new CommentConverter();

    @SuppressWarnings("unchecked")
    @Override
    public Article toEntity(NewsDTO dto) {
        return Article.newBuilder()
                .withId(dto.getId())
                .withTitle(dto.getTitle())
                .withContent(dto.getContent())
                .withCreated(dto.getCreated())
                .withUser((User) simpleUserConverter.toEntity(dto.getUser()))
                .withCommentSet(commentConverter.toEntitySet(dto.getCommentSet()))
                .build();
    }
}
