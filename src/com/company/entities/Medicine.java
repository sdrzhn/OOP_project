package com.company.entities;

import java.time.LocalDate;

public class Medicine {
    private int id;
    private String name;
    private double price;
    private LocalDate expirationDate;
    private String manufacturer;
    private boolean availability = false;
    private int quantity;

    public Medicine() {       //creating constructor
    }

    public Medicine(int id, String name, double price, LocalDate expirationDate, String manufacturer, boolean availability, int quantity) {       //creating constructor2
        this.id = id;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.manufacturer = manufacturer;
        this.availability = availability;
        this.quantity = quantity;
    }

    public Medicine(String name, double price, LocalDate expirationDate, String manufacturer, boolean availability, int quantity) {       //creating constructor3
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.manufacturer = manufacturer;
        this.availability = availability;
        this.quantity = quantity;
    }

//creating Getters and Setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//method toString()
    @Override
    public String toString() {
        return "Medicine[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", expirationDate=" + expirationDate +
                ", manufacturer='" + manufacturer + '\'' +
                ", availability=" + availability +
                ", quantity=" + quantity +
                ']';
    }
}
