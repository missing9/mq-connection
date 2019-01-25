package com.personal.mqconnection;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;

@RestController
public class MainController {

    @Autowired
    private OrderDao orderDao;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @PostMapping("/save")
    public @ResponseBody Map<String, Object> save(@RequestBody Order order) {
        Map<String, Object> result = new HashMap<>();
        if (StringUtils.isNullOrEmpty(order.id)) {
            order.id = orderDao.insert(order);
        } else {
            orderDao.update(order);
        }
        result.put("id", order.id);
        return result;
    }

    @GetMapping("/get")
    public @ResponseBody Object get(String id) {
        return orderDao.get(id);
    }

    @GetMapping("/findAll")
    public @ResponseBody Object findAll() {
        return orderDao.findAll();
    }

    @DeleteMapping("/delete")
    public @ResponseBody Map<String, Object> delete(String id) {
        Map<String, Object> result = new HashMap<>();
        orderDao.delete(id);
        result.put("id", id);
        return result;
    }
}