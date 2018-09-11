package com.gmail.evanloafakahaitao.service.impl;

import com.gmail.evanloafakahaitao.dao.CommentDao;
import com.gmail.evanloafakahaitao.dao.NewsDao;
import com.gmail.evanloafakahaitao.dao.UserDao;
import com.gmail.evanloafakahaitao.dao.impl.CommentDaoImpl;
import com.gmail.evanloafakahaitao.dao.impl.NewsDaoImpl;
import com.gmail.evanloafakahaitao.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.dao.model.Comment;
import com.gmail.evanloafakahaitao.dao.model.News;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.CommentService;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.converter.impl.CommentConverterImpl;
import com.gmail.evanloafakahaitao.service.converter.impl.CommentDTOConverterImpl;
import com.gmail.evanloafakahaitao.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.service.dto.NewsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    private CommentDao commentDao = new CommentDaoImpl(Comment.class);
    private NewsDao newsDao = new NewsDaoImpl(News.class);
    private UserDao userDao = new UserDaoImpl(User.class);
    private Converter commentConverter = new CommentConverterImpl();
    private DTOConverter commentDTOConverter = new CommentDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public CommentDTO save(NewsDTO newsDTO) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            News news = newsDao.findOne(newsDTO.getId());
            Comment comment = (Comment) commentConverter.toEntity(newsDTO.getCommentSet().iterator().next());
            User user = userDao.findByEmail(comment.getUser().getEmail());
            comment.setUser(user);
            news.getCommentSet().add(comment);
            newsDao.update(news);
            transaction.commit();
            return (CommentDTO) commentDTOConverter.toDto(comment);
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Comment", e);
        }
        return null;
    }

    @Override
    public boolean deleteById(NewsDTO newsDTO) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Long deleteCommentId = newsDTO.getCommentSet().iterator().next().getId();
            News news = newsDao.findOne(newsDTO.getId());
            Iterator<Comment> iterator = news.getCommentSet().iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getId().equals(deleteCommentId)) {
                    iterator.remove();
                    break;
                }
            }
            newsDao.update(news);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete comment by id", e);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CommentDTO> findByNewsId(NewsDTO newsDTO) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Comment> commentList = commentDao.findByNewsId(newsDTO.getId());
            List<CommentDTO> commentDTOList = commentDTOConverter.toDTOList(commentList);
            transaction.commit();
            return commentDTOList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find Comments by news id", e);
        }
        return null;
    }
}
