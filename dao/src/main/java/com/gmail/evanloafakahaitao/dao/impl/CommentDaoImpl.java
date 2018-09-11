package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.CommentDao;
import com.gmail.evanloafakahaitao.dao.model.*;

import java.util.List;

public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {

    public CommentDaoImpl(Class<Comment> clazz) {
        super(clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> findByNewsId(long newsId) {
        String sql = "select * from t_comment c where c.f_news_id = :newsId";
        return (List<Comment>) getCurrentSession().createNativeQuery(sql)
                .addEntity("t_comment", Comment.class)
                .setParameter("newsId", newsId)
                .list();
    }
}
