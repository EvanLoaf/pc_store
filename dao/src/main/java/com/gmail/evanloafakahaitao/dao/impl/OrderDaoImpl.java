package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.OrderDao;
import com.gmail.evanloafakahaitao.dao.model.Order;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    //private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);

    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> findByUserId(Long id) {
        /*String findOrdersByUserId = "select id as order_id, uuid, item_id, created, quantity from `order` where user_id = ?";
        List<Order> orderList = new ArrayList<>();
        try (
                PreparedStatement preparedStatementOrders = connection.prepareStatement(findOrdersByUserId);
        ) {
            preparedStatementOrders.setLong(1, id);
            try (ResultSet resultSetOrder = preparedStatementOrders.executeQuery()) {
                while (resultSetOrder.next()) {
                    Order order = orderConverter.toOrder(resultSetOrder);
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return orderList;*/
        String hql = "from Order as O where O.user.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public int deleteByUuid(String uuid) {
        /*String deleteOrderByUuid = "delete from `order` where uuid = ?";
        int changedOrderRows = 0;
        try (
                PreparedStatement preparedStatementDeleteOrderByUuid = connection.prepareStatement(deleteOrderByUuid)
        ) {
            preparedStatementDeleteOrderByUuid.setString(1, uuid);
            changedOrderRows = preparedStatementDeleteOrderByUuid.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return changedOrderRows;*/
        String hql = "delete from Order as O where O.uuid=:uuid";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("uuid", uuid);
        return query.executeUpdate();
    }
}
