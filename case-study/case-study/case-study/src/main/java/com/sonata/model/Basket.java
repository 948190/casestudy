package com.sonata.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketId;
    
    private double price;
    private int quantity;

    
    @ManyToOne
    @JoinColumn(name = "basket_product_id") 
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "cus_user_id") 
    private User user;
    
   
    public Basket() {
    	
    }


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Long getBasketId() {
		return basketId;
	}


	public void setBasketId(Long basketId) {
		this.basketId = basketId;
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


	


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}
    
	
	
}