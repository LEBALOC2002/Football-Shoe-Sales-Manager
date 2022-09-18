package service;

import model.OrderItem;
import model.Product;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public interface IItemOrderService {
    List<OrderItem> getOrderItems();


    List<OrderItem> getOrderTtemsByOrderId(long orderId);

    void add(OrderItem newOrderItem);

    void update(OrderItem newOrderItem);

    OrderItem getOrderItemById(int id);
}
