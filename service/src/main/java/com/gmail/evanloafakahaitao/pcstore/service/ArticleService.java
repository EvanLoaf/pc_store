package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {

    ArticleDTO save(ArticleDTO articleDTO);

    ArticleDTO deleteById(ArticleDTO articleDTO);

    ArticleDTO update(ArticleDTO articleDTO);

    List<ArticleDTO> findAll(Integer startPosition, Integer maxResults);
}
