package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.NewsDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.News;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {

    public NewsDaoImpl() {
        super(News.class);
    }
}
