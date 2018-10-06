package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Article;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleArticleDTO;
import org.springframework.stereotype.Component;

@Component("simpleArticleDTOConverter")
public class SimpleArticleDTOConverter implements DTOConverter<SimpleArticleDTO, Article> {

    @Override
    public SimpleArticleDTO toDto(Article entity) {
        SimpleArticleDTO article = new SimpleArticleDTO();
        article.setId(entity.getId());
        article.setCreated(entity.getCreated());
        article.setTitle(entity.getTitle());
        return article;
    }
}
