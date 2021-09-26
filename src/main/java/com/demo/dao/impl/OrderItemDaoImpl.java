package com.demo.dao.impl;

import com.demo.dao.OrderItemDao;
import com.demo.pojo.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`," +
                "`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),
                orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
