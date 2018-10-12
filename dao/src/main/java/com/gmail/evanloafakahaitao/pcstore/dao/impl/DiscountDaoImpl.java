package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.DiscountDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements DiscountDao {

    public DiscountDaoImpl() {
        super(Discount.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Discount findByPercent(Integer percent) {
        String hql = "from Discount as d where d.percent=:percent";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("percent", percent);
        return (Discount) query.uniqueResult();
    }
}
