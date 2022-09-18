package service;

import model.OrderItem;
import model.Product;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IItemOrderService{


    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    public static String path = "C:\\Module2\\Sports shoe sales manager\\src\\data\\oderItem.csv";

    private static OrderItemService instance;

    private OrderItemService(){}
    public static OrderItemService getInstance() {
        if (instance == null)
            instance = new OrderItemService();
        return instance;
    }

    @Override
    public List<OrderItem> getOrderItems() {
        List<OrderItem> newOrderItems = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newOrderItems.add(new OrderItem(record));
        }
        return orderItems = newOrderItems;
    }


    @Override
    public List<OrderItem> getOrderTtemsByOrderId(long orderId){
        List<OrderItem> orderItems = getOrderItems();
        List<OrderItem> orderItemsFind = new ArrayList<>();
        for (OrderItem orderItem: orderItems){
            if (orderItem.getOrderId() == orderId){
                orderItemsFind.add(orderItem);
            }
        }
        return orderItemsFind;
    }


    @Override
    public void add(OrderItem newOrderItem) {
    List<OrderItem> orderItemList = getOrderItems();
    orderItemList.add(newOrderItem);
    CSVUtils.write(path,orderItemList);
    }

    @Override
    public void update(OrderItem newOrderItem) {
    List<OrderItem> orderItemList = getOrderItems();
    CSVUtils.write(path,orderItemList);
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        List<OrderItem> orderItemList = getOrderItems();
        for (OrderItem orderItem: orderItemList) {
            if (orderItem.getOrderId() == id )
                return orderItem ;
        }
        return null;
    }
}
