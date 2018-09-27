package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.ItemDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;

public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    public ItemDaoImpl(Class<Item> clazz) {
        super(clazz);
    }

    @Override
    public Item findByVendorCode(String vendorCode) {
        String hql = "from Item as i where i.vendorCode=:vendorCode";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("vendorCode", vendorCode);
        return (Item) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findInPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Integer startPos, Integer maxResults) {
        String hql = "from Item as i where i.price between :minprice and :maxprice order by i.price desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("minprice", minPrice);
        query.setParameter("maxprice", maxPrice);
        if (startPos != null && !startPos.equals(0)) {
            query.setFirstResult(startPos);
        }
        if (maxResults != null && !maxResults.equals(0)) {
            query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findByDiscount(Integer percent) {
        if (percent != null && !percent.equals(0)) {
            String hql = "select i from Item as i join i.discounts as d where d.percent=:percent order by i.price desc";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("percent", percent);
            return query.getResultList();
        } else {
            String hql = "from Item as i where i.id not in (select i.id from Item as i inner join i.discounts as d) order by i.price desc";
            Query query = getCurrentSession().createQuery(hql);
            return query.getResultList();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long findCountInPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        String hql = "select count(*) from Item as i where i.price between :minprice and :maxprice";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("minprice", minPrice);
        query.setParameter("maxprice", maxPrice);
        return (Long) query.getSingleResult();
    }
}
