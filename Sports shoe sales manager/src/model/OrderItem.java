package model;

import java.time.Instant;

public class OrderItem {
    private long id;
    private double price;
    private int quantity;
    private long orderId;
    private long productId;
    private String productName;
    private int size;
    private long total;


    public OrderItem() {
    }

    public OrderItem(long id, double price, int size, int quantity, long orderId, long productId, String productName, long total) {
        this.id = id;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.total = total;
    }

    public OrderItem (String record) {
        super();
        String[] fields = record.split(",");
        id = Long.parseLong(fields[0]);
        price = Double.parseDouble(fields[1]);
        size = Integer.parseInt(fields[2]);
        quantity = Integer.parseInt(fields[3]);
        orderId = Long.parseLong(fields[4]);
        productId = Long.parseLong(fields[5]);
        productName = fields[6];
        total = Long.parseLong(fields[7]);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getTotal() {
        return quantity * price;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%s,%s,%s",
                id,
                price,
                size,
                quantity,
                orderId,
                productId,
                productName,
                total);
    }
}
