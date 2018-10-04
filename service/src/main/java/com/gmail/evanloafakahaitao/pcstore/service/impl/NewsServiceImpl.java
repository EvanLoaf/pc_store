package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.NewsDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.NewsDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.model.News;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.NewsService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity.NewsConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.NewsDTOConverter;
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
            News news = (News) newsConverter.toEntity(newsDTO);
            news.setUser(user);
            newsDao.create(news);
            NewsDTO newsDTOsaved = (NewsDTO) newsDTOConverter.toDto(news);
            transaction.commit();
            return newsDTOsaved;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save News", e);
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
            News news = newsDao.findOne(newsDTO.getId());
            if (newsDTO.getContent() != null) {
                news.setContent(newsDTO.getContent());
            }
            if (newsDTO.getTitle() != null) {
                news.setTitle(newsDTO.getTitle());
            }
            newsDao.update(news);
            NewsDTO updatedNewsDTO = (NewsDTO) newsDTOConverter.toDto(news);
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
            List<News> newsCollection = newsDao.findAll();
            newsDTOS = newsDTOConverter.toDTOList(newsCollection);
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
