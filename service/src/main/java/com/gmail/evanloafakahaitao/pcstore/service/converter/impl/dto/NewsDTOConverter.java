package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.News;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Comment;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component("articleDTOConverter")
public class NewsDTOConverter implements DTOConverter<NewsDTO, News> {

    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter;
    private final DTOConverter<CommentDTO, Comment> commentDTOConverter;

    @Autowired
    public NewsDTOConverter(
            @Qualifier("simpleUserDTOConverter") DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter,
            @Qualifier("commentDTOConverter") DTOConverter<CommentDTO, Comment> commentDTOConverter
    ) {
        this.simpleUserDTOConverter = simpleUserDTOConverter;
        this.commentDTOConverter = commentDTOConverter;
    }

    @Override
    public NewsDTO toDto(News entity) {
        NewsDTO article = new NewsDTO();
        article.setId(entity.getId());
        article.setTitle(entity.getTitle());
        article.setContent(entity.getContent());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        article.setCreated(
                entity.getCreated().format(formatter)
        );
        article.setUser(simpleUserDTOConverter.toDto(entity.getUser()));
        if (!entity.getComments().isEmpty()) {
            article.setComments(commentDTOConverter.toDTOSet(entity.getComments()));
        }
        return article;
    }
}