package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Article;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Comment;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ArticleDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("articleConverter")
public class ArticleConverter implements Converter<ArticleDTO, Article> {

    private final Converter<SimpleUserDTO, User> simpleUserConverter;
    private final Converter<CommentDTO, Comment> commentConverter;

    @Autowired
    public ArticleConverter(
            @Qualifier("simpleUserConverter") Converter<SimpleUserDTO, User> simpleUserConverter,
            @Qualifier("commentConverter") Converter<CommentDTO, Comment> commentConverter
    ) {
        this.simpleUserConverter = simpleUserConverter;
        this.commentConverter = commentConverter;
    }

    @Override
    public Article toEntity(ArticleDTO dto) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        if (dto.getId() != null) {
            article.setId(dto.getId());
        }
        if (!dto.getComments().isEmpty()) {
            article.setComments(commentConverter.toEntitySet(dto.getComments()));
        }
        return article;
    }
}
