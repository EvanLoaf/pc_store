package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.ArticleDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleArticleDTO;

import java.util.List;

public interface ArticleService {

    ArticleDTO save(ArticleDTO articleDTO);

    SimpleArticleDTO deleteById(SimpleArticleDTO articleDTO);

    ArticleDTO update(ArticleDTO articleDTO);

    List<SimpleArticleDTO> findAll(Integer startPosition, Integer maxResults);

    Long countAll();

    ArticleDTO findById(SimpleArticleDTO news);
}
