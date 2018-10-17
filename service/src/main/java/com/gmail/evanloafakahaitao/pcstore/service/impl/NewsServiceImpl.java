package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.NewsDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.News;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.NewsService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.NewsDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleArticleDTO;
import com.gmail.evanloafakahaitao.pcstore.service.util.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    private final NewsDao newsDao;
    private final UserDao userDao;
    private final Converter<NewsDTO, News> articleConverter;
    private final DTOConverter<NewsDTO, News> articleDTOConverter;
    private final DTOConverter<SimpleArticleDTO, News> simpleArticleDTOConverter;

    @Autowired
    public NewsServiceImpl(
            NewsDao newsDao,
            UserDao userDao,
            @Qualifier("articleConverter") Converter<NewsDTO, News> articleConverter,
            @Qualifier("articleDTOConverter") DTOConverter<NewsDTO, News> articleDTOConverter,
            DTOConverter<SimpleArticleDTO, News> simpleArticleDTOConverter
    ) {
        this.newsDao = newsDao;
        this.userDao = userDao;
        this.articleConverter = articleConverter;
        this.articleDTOConverter = articleDTOConverter;
        this.simpleArticleDTOConverter = simpleArticleDTOConverter;
    }

    @Override
    public NewsDTO save(NewsDTO newsDTO) {
        logger.info("Saving News");
        User user = userDao.findOne(CurrentUserUtil.getCurrentId());
        News news = articleConverter.toEntity(newsDTO);
        if (news.getCreated() == null) {
            news.setCreated(LocalDateTime.now());
        }
        news.setDeleted(false);
        news.setUser(user);
        newsDao.create(news);
        return articleDTOConverter.toDto(news);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting News by Id");
        newsDao.deleteById(id);
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) {
        logger.info("Updating News");
        News news = newsDao.findOne(newsDTO.getId());
        if (newsDTO.getContent() != null && !newsDTO.getContent().equals(news.getContent())) {
            news.setContent(newsDTO.getContent());
        }
        if (newsDTO.getTitle() != null && !newsDTO.getTitle().equals(news.getTitle())) {
            news.setTitle(newsDTO.getTitle());
        }
        newsDao.update(news);
        return articleDTOConverter.toDto(news);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleArticleDTO> findAll(Integer startPosition, Integer maxResults) {
        logger.info("Retrieving all News");
        List<News> articles = newsDao.findAll(startPosition, maxResults);
        return simpleArticleDTOConverter.toDTOList(articles);
    }

    @Override
    public Long countAll() {
        logger.info("Counting all News");
        return newsDao.countAll();
    }

    @Override
    public NewsDTO findById(Long id) {
        logger.info("Retrieving News by Id");
        News article = newsDao.findOne(id);
        return articleDTOConverter.toDto(article);
    }
}
