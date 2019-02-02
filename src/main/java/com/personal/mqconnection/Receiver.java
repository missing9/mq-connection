package com.personal.mqconnection;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;

@Component
public class Receiver {

    @Autowired
    private OrderDao orderDao;

    @RabbitListener(queues = "save")
    @RabbitHandler
    public String save(String orderStr) {
        Order order = JSONObject.parseObject(orderStr, Order.class);
        if (StringUtils.isNullOrEmpty(order.id)) {
            order.id = orderDao.insert(order);
        } else {
            orderDao.update(order);
        }
        return order.id;
    }

    @RabbitListener(queues = "findAll")
    @RabbitHandler
    public List<Order> findAll(String orderStr) {
        List<Order> orders = JSONArray.parseArray(orderStr, Order.class);
        return orders;
    }

    @RabbitListener(queues = "delete")
    @RabbitHandler
    public void delete(String id) {
        orderDao.delete(id);
        System.out.println("deleteReceiver : " + id);
    }

    @RabbitListener(queues = "get")
    @RabbitHandler
    public void get(String id) {
        orderDao.delete(id);
        System.out.println("getReceiver : " + id);
    }


}
