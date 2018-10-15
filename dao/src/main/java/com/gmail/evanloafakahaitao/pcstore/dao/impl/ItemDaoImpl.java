package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.ItemDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    public ItemDaoImpl() {
        super(Item.class);
    }

    @Override
    public Item findByVendorCode(String vendorCode) {
        String hql = "from Item as i where i.vendorCode=:vendorCode";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("vendorCode", vendorCode);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return (Item) query.uniqueResult();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findInPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        String hql = "from Item as i where i.price between :minprice and :maxprice order by i.price desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("minprice", minPrice);
        query.setParameter("maxprice", maxPrice);
        return query.getResultList();
    }

    @Override
    public int softDelete(Long id) {
        String hql = "update Item as i set i.isDeleted = true where i.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findAllNotDeleted(Integer startPosition, Integer maxResults) {
        String hql = "from Item as i where i.isDeleted=false";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
}
