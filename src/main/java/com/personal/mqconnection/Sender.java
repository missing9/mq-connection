package com.personal.mqconnection;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderDao orderDao;

    public void sendSave(Order order) {
        String orderStr = JSON.toJSONString(order);
        rabbitTemplate.convertAndSend("save", orderStr);
    }

    public void deleteSender(String id) {
        System.out.println("deleteSender : " + id);
        rabbitTemplate.convertAndSend("delete", id);
    }

    public void deleteSave(String id) {
        System.out.println("deleteSender : " + id);
        rabbitTemplate.convertAndSend("delete", id);
    }
}