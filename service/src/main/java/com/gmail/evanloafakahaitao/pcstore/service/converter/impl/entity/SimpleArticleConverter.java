package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Article;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleArticleDTO;
import org.springframework.stereotype.Component;

@Component("simpleArticleConverter")
public class SimpleArticleConverter implements Converter<SimpleArticleDTO, Article> {

    @Override
    public Article toEntity(SimpleArticleDTO dto) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setCreated(dto.getCreated());
        if (dto.getId() != null) {
            article.setId(dto.getId());
        }
        return article;
    }
}
