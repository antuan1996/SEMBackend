package com.khoubyari.example.api.rest;

import com.khoubyari.example.dao.jpa.OrderRepository;
import com.khoubyari.example.domain.Order;
import com.khoubyari.example.exception.DataFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;


@RestController
@RequestMapping("/orders")
public class OrderController extends AbstractRestHandler {
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody Order createOrder(@RequestBody Order order){
        order.setCreatedDatetime(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody ArrayList<Order> getOrders(){
        return (ArrayList<Order>) orderRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Order retrieveOrder(@PathVariable("id") Long id) throws Exception {
        Order order = orderRepository.findOne(id);
        checkResourceFound(order);
        return order;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody Order updateOrders(@PathVariable("id") Long id, @RequestBody Order order){
        checkResourceFound(orderRepository.findOne(id));
        if (id != order.getId()) throw new DataFormatException("ID doesn't match!");
        return orderRepository.save(order);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrders(@PathVariable("id") Long id){
        checkResourceFound(orderRepository.findOne(id));
        orderRepository.delete(id);
    }
}
