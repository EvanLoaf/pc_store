package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.News;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Comment;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.NewsDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("articleConverter")
public class NewsConverter implements Converter<NewsDTO, News> {

    private final Converter<CommentDTO, Comment> commentConverter;

    @Autowired
    public NewsConverter(
            @Qualifier("commentConverter") Converter<CommentDTO, Comment> commentConverter
    ) {
        this.commentConverter = commentConverter;
    }

    @Override
    public News toEntity(NewsDTO dto) {
        News news = new News();
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        if (dto.getId() != null) {
            news.setId(dto.getId());
        }
        if (!dto.getComments().isEmpty()) {
            news.setComments(commentConverter.toEntitySet(dto.getComments()));
        }
        return news;
    }
}
