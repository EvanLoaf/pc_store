package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.ArticleDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Article;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDaoImpl extends GenericDaoImpl<Article> implements ArticleDao {

    public ArticleDaoImpl() {
        super(Article.class);
    }
}
