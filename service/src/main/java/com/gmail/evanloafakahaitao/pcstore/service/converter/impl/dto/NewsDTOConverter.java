package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Article;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ArticleDTO;
import org.springframework.stereotype.Component;

@Component("newsDTOConverter")
public class NewsDTOConverter implements DTOConverter<ArticleDTO, Article> {

    private DTOConverter simpleUserDTOConverter = new SimpleUserDTOConverter();
    private DTOConverter commentDTOConverter = new CommentDTOConverter();

    @SuppressWarnings("unchecked")
    @Override
    public ArticleDTO toDto(Article entity) {
        return ArticleDTO.newBuilder()
                .withId(entity.getId())
                .withTitle(entity.getTitle())
                .withContent(entity.getContent())
                .withCreated(entity.getCreated())
                .withUser((SimpleUserDTO) simpleUserDTOConverter.toDto(entity.getUser()))
                .withCommentSet(commentDTOConverter.toDTOSet(entity.getComments()))
                .build();
    }
}
