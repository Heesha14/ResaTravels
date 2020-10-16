package com.example.resatravels;



public class OrderedAccessories {
    private String id;
    private String accessoryKey;
    private String name;
    private String description;
    private String price;
    private String requiredQuantity;
    private Double totalAmount;
    private String date;
    private String time;

    public OrderedAccessories() {
    }

    public OrderedAccessories(String id, String accessoryKey, String name, String description, String price, String requiredQuantity, Double totalAmount, String date, String time) {
        this.id = id;
        this.accessoryKey = accessoryKey;
        this.name = name;
        this.description = description;
        this.price = price;
        this.requiredQuantity = requiredQuantity;
        this.totalAmount = totalAmount;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(String requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAccessoryKey() {
        return accessoryKey;
    }

    public void setAccessoryKey(String accessoryKey) {
        this.accessoryKey = accessoryKey;
    }
}
