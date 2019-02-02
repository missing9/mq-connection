package com.personal.mqconnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class MainController {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private Sender sender;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @PostMapping("/save")
    public @ResponseBody Map<String, Object> save(@RequestBody Order order) {
        Map<String, Object> result = new HashMap<>();
        sender.sendSave(order);
        // result.put("id", order.id);
        return result;
    }

    @GetMapping("/get")
    public @ResponseBody Object get(String id) {
        return orderDao.get(id);
    }

    @GetMapping("/findAll")
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @DeleteMapping("/delete")
    public @ResponseBody Map<String, Object> delete(String id) {
        Map<String, Object> result = new HashMap<>();
        sender.deleteSender(id);
        result.put("id", id);
        return result;
    }
}