package model;

import java.time.Instant;


public class Product {
    private long id;
    private int size;
    private String title;
    private double price;
    private int quantyti;
    private Instant createdAt;

    private Instant updatedAt;
    private int productID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Product() {
    }

    public Product(long id, int size, String title, double price, int quantity) {
        this.id = id;
        this.size = size;
        this.title = title;
        this.price = price;
        this.quantyti = quantity;
    }

    public Product(long id, int size, String title, double price, int quantity, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.size = size;
        this.title = title;
        this.price = price;
        this.quantyti = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Product parseProduct(String record) {
        String[] fields = record.split(",");
        long id = Long.parseLong(fields[0]);
        int size = Integer.parseInt(fields[1]);
        String title = fields[2];
        double price = Double.parseDouble(fields[3]);
        int quantity = Integer.parseInt(fields[4]);
        Instant createdAt = Instant.parse(fields[5]);
        String temp = fields[6];
        Instant updatedAt = null;
        if (temp != null && !temp.equals("null"))
            updatedAt = Instant.parse(temp);
        return new Product(id, size, title, price, quantity, createdAt, updatedAt);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFullName() {
        return title;
    }

    public void setFillName(String fillName) {
        this.title = fillName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantyti;
    }

    public void setQuantity(int quantity) {
        this.quantyti = quantity;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%s,%s",
                id,
                size,
                title,
                price,
                quantyti,
                createdAt,
                updatedAt
        );
    }

    public int getQuantyti() {
        return quantyti;
    }

    public void setQuantyti(int quantyti) {
        this.quantyti = quantyti;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
