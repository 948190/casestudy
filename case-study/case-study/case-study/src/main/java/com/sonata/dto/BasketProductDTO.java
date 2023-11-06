package com.sonata.dto;

import java.sql.Date;

import com.sonata.model.Visibility;

public class BasketProductDTO {
    
    private String name;
    private String color;
    private String size;
    private Date discontinued;
    private Visibility visible;
    private String brandName;
    private String categoryName;
    private int quantity;
    private double price;
    
    public BasketProductDTO() {
    	
    }

    public BasketProductDTO( String name,  String brandName, String categoryName, int quantity, double price) {
        
        this.name = name;
        
        this.brandName = brandName;
        this.categoryName = categoryName;
        this.quantity = quantity;
        this.price = price;
    }

    

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
