package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.NewsDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Article;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.NewsService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.NewsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    private final NewsDao newsDao;
    private final UserDao userDao;
    private final Converter newsConverter;
    private final DTOConverter newsDTOConverter;

    @Autowired
    public NewsServiceImpl(
            NewsDao newsDao,
            UserDao userDao,
            @Qualifier("newsConverter") Converter newsConverter,
            @Qualifier("newsDTOConverter") DTOConverter newsDTOConverter
    ) {
        this.newsDao = newsDao;
        this.userDao = userDao;
        this.newsConverter = newsConverter;
        this.newsDTOConverter = newsDTOConverter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public NewsDTO save(NewsDTO newsDTO) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findByEmail(newsDTO.getUser().getEmail());
            Article article = (Article) newsConverter.toEntity(newsDTO);
            article.setUser(user);
            newsDao.create(article);
            NewsDTO newsDTOsaved = (NewsDTO) newsDTOConverter.toDto(article);
            transaction.commit();
            return newsDTOsaved;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Article", e);
        }
        return null;
    }

    @Override
    public boolean deleteById(NewsDTO newsDTO) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            newsDao.deleteById(newsDTO.getId());
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete news by id", e);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public NewsDTO update(NewsDTO newsDTO) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Article article = newsDao.findOne(newsDTO.getId());
            if (newsDTO.getContent() != null) {
                article.setContent(newsDTO.getContent());
            }
            if (newsDTO.getTitle() != null) {
                article.setTitle(newsDTO.getTitle());
            }
            newsDao.update(article);
            NewsDTO updatedNewsDTO = (NewsDTO) newsDTOConverter.toDto(article);
            transaction.commit();
            return updatedNewsDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update news", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<NewsDTO> findAll() {
        Session session = newsDao.getCurrentSession();
        List<NewsDTO> newsDTOS = new ArrayList<>();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Article> articleCollection = newsDao.findAll();
            newsDTOS = newsDTOConverter.toDTOList(articleCollection);
            transaction.commit();
            return newsDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find all news", e);
        }
        return newsDTOS;
    }
}
