package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.NewsDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Article;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl extends GenericDaoImpl<Article> implements NewsDao {

    public NewsDaoImpl() {
        super(Article.class);
    }
}
