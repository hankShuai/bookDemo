package com.demo.dao.impl;

import com.demo.dao.OrderDao;
import com.demo.pojo.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return  update(sql,order.getOrderId(),order.getCreateTime(),
                order.getPrice(),order.getStatus(),order.getUserId());
    }
}
