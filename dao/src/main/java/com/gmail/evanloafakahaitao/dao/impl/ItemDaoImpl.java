package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.ItemDao;
import com.gmail.evanloafakahaitao.dao.model.Item;
import org.hibernate.query.Query;

public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    //private static final Logger logger = LogManager.getLogger(ItemDaoImpl.class);

    public ItemDaoImpl(Class<Item> clazz) {
        super(clazz);
    }

    @Override
    public Item findByVendorCode(Long vendorCode) {
        /*String findByVendorCode = "select i.id as item_id, i.name as item_name, i.vendor_code, i.description, i.price from item i where i.vendor_code = ?";
        Item item = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(findByVendorCode)) {
            preparedStatement.setLong(1, vendorCode);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    item = itemConverter.toItem(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return item;*/
        String hql = "from Item as I where I.vendorCode=:vendorCode";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("vendorCode", vendorCode);
        return (Item) query.getSingleResult();
    }
}
