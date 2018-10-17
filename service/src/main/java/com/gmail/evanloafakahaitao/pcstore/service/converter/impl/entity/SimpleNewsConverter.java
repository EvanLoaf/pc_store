package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.News;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleArticleDTO;
import org.springframework.stereotype.Component;

@Component("simpleArticleConverter")
public class SimpleNewsConverter implements Converter<SimpleArticleDTO, News> {

    @Override
    public News toEntity(SimpleArticleDTO dto) {
        News news = new News();
        news.setTitle(dto.getTitle());
        if (dto.getId() != null) {
            news.setId(dto.getId());
        }
        return news;
    }
}
