package model;

import java.time.Instant;

public class Order {
    private Long idOder;
    private String fullName;
    private String mobile;
    private String address;
    private double grandTotal;
    private Instant createdAt;

    public Order() {
    }


    public Order(Long idOder, String fullName, String mobile, String address, double grandTotal, Instant createdAt) {
        this.idOder = idOder;
        this.fullName = fullName;
        this.mobile = mobile;
        this.address = address;
        this.grandTotal = grandTotal;
        this.createdAt = createdAt;
    }

    public static Order parseOrder(String record) {
        Order order = new Order();
        String[] field = record.split(",");
        order.idOder = Long.parseLong(field[0]);
        order.fullName = field[1];
        order.mobile = field[2];
        order.address = field[3];
        order.grandTotal = Double.parseDouble(field[4]);
        order.createdAt = Instant.parse(field[5]);
        return order;
    }

    public Long getIdOder() {
        return idOder;
    }

    public void setIdOder(Long idOder) {
        this.idOder = idOder;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%s",
                idOder,
                fullName,
                mobile,
                address,
                grandTotal,
                createdAt );
    }
}

