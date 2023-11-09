package com.sonata.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sonata.model.User;

public class UserDTO {
    private Long userId;
    private String userName;
    private String phoneNumber;
    private String email;
    private Double totalBasketPrice; 
    private Double totalMrpPrice;
    private Double totalDiscountPrice;
    private Long totalProducts;
    
    
    
    private List<ProductDTO> products;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.phoneNumber = user.getPhoneNum();
        this.email = user.getEmail();
        this.totalBasketPrice=totalBasketPrice;
        this.totalMrpPrice=totalMrpPrice;
        this.totalDiscountPrice=totalDiscountPrice;
        this.totalProducts=totalProducts;
        
        
        
    }

    public Long getUserId() {
        return userId;
    }
    

    public Double getTotalMrpPrice() {
		return totalMrpPrice;
	}

	public void setTotalMrpPrice(Double totalMrpPrice) {
		this.totalMrpPrice = totalMrpPrice;
	}

	public Double getTotalBasketPrice() {
		return totalBasketPrice;
	}

	public void setTotalBasketPrice(Double totalBasketPrice) {
		this.totalBasketPrice = totalBasketPrice;
	}

	public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

	public Double getTotalDiscountPrice() {
		return totalDiscountPrice;
	}

	public void setTotalDiscountPrice(Double totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}

	public Long getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(Long totalProducts) {
		this.totalProducts = totalProducts;
	}
	
    
}
