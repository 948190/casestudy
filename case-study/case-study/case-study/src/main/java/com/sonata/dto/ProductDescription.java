package com.sonata.dto;

import java.util.List;

import com.sonata.model.Inventory;
import com.sonata.model.Product;

public class ProductDescription {
    private String name;
    private String color;
    private String size;
    private String discontinued;
    private String visible;
    private String brandName;
    private String categoryName;
   

    public ProductDescription(Product product) {
        this.name = product.getName();
        this.color = product.getColor();
        this.size = product.getSize();
        this.discontinued = product.getDiscontinued() != null ? product.getDiscontinued().toString() : null;
        this.visible = product.getVisible().toString();
        this.brandName = product.getBrand().getName();
        this.categoryName = product.getCategory().getName();
        
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getDiscontinued() {
    	return discontinued;
    }

    public String getVisible() {
        return visible;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

}

    