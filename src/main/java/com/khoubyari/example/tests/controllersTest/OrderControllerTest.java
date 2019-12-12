package com.khoubyari.example.tests.controllersTest;

import com.khoubyari.example.api.rest.OrderController;
import com.khoubyari.example.dao.jpa.OrderRepository;
import com.khoubyari.example.domain.Comment;
import com.khoubyari.example.domain.Order;
import com.khoubyari.example.domain.User;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderController orderController;

    @Test
    public void testGetOrdersList() {
        Order user = new Order();
        ArrayList<Order> orders = new ArrayList<Order>();
        orders.add(user);
        when(orderRepository.findAll()).thenReturn(orders);
        ArrayList<Order> result = orderController.getOrders();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testCreateOrder() {
        Order sendOrder = new Order();
        String title = "title";
        sendOrder.setId(10);
        sendOrder.setTitle(title);
        Order responseOrder = new Order();
        sendOrder.setId(12);
        sendOrder.setTitle("newTitle");
        when(orderRepository.save(any(Order.class))).thenReturn(responseOrder);
        Order result = orderController.createOrder(sendOrder);
        assertEquals(result.getId(), responseOrder.getId());
    }

    @Test
    public void testRetrieveOrder() {
        long id = 10;
        Order responseOrder = new Order();
        responseOrder.setId(id);
        responseOrder.setTitle("title");
        when(orderRepository.findOne(id)).thenReturn(responseOrder);
        Order result = orderController.retrieveOrder(id);
        assertEquals(result.getTitle(), responseOrder.getTitle());
    }

    @Test
    public void testUpdateOrder(){
        long searchId = 10;
        Order requestOrder = new Order();
        requestOrder.setId(searchId);
        requestOrder.setTitle("123");
        requestOrder.setCreatedDatetime(LocalDateTime.now());
        Order updatedOrder = new Order();
        updatedOrder.setId(searchId);
        updatedOrder.setTitle("456");
        when(orderRepository.findOne(searchId)).thenReturn(requestOrder);
        when(orderRepository.save(any(Order.class))).thenReturn(updatedOrder);
        Order result = orderController.updateOrders(searchId, requestOrder);
        assertEquals(result.getTitle(), updatedOrder.getTitle());
    }

    @Test
    public void testDeleteOrder(){
        long id = 10;
        Order order = new Order();
        order.setId(id);
        when(orderRepository.findOne(id)).thenReturn(order);
        orderController.deleteOrders(id);
        verify(orderRepository).delete(id);
    }

}