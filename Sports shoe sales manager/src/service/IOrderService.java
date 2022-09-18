package service;

import model.Order;
import model.OrderItem;

import java.util.List;

public interface IOrderService {
    List<Order> getOrders();


    void add(Order newOrder);



    void update();




    Order getOrderById(long id);

    boolean exist(int id);

    void remove(Order newOrder);

}
