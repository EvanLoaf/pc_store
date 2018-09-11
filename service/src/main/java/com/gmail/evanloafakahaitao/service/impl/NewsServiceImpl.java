package com.gmail.evanloafakahaitao.service.impl;

import com.gmail.evanloafakahaitao.dao.NewsDao;
import com.gmail.evanloafakahaitao.dao.UserDao;
import com.gmail.evanloafakahaitao.dao.impl.NewsDaoImpl;
import com.gmail.evanloafakahaitao.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.dao.model.News;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.NewsService;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.converter.impl.NewsConverterImpl;
import com.gmail.evanloafakahaitao.service.converter.impl.NewsDTOConverterImpl;
import com.gmail.evanloafakahaitao.service.dto.NewsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    private NewsDao newsDao = new NewsDaoImpl(News.class);
    private UserDao userDao = new UserDaoImpl(User.class);
    private Converter newsConverter = new NewsConverterImpl();
    private DTOConverter newsDTOConverter = new NewsDTOConverterImpl();

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
            transaction.commit();
            return (NewsDTO) newsDTOConverter.toDto(news);
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
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<News> newsList = newsDao.findAll();
            List<NewsDTO> newsDTOList = newsDTOConverter.toDTOList(newsList);
            transaction.commit();
            return newsDTOList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find all news", e);
        }
        return null;
    }
}
