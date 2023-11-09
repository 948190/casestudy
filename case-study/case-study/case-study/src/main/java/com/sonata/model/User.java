package com.sonata.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	private String userName;
	private String email;
	private String PhoneNum;
	@OneToMany(mappedBy = "user")
    private List<Basket> baskets;
	
	
	
	public User() {
        baskets = new ArrayList<>();
    }

	
	
	public User(long userId, String userName, String email, String phoneNum, List<Basket> baskets) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.PhoneNum = phoneNum;
		this.baskets = baskets;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return PhoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}
	public List<Basket> getBaskets() {
		return baskets;
	}
	public void setBaskets(List<Basket> baskets) {
		this.baskets = baskets;
	}
	
	public void addBasketItem(Basket basketItem) {
        baskets.add(basketItem);
        basketItem.setUser(this);
    }
	
	
	
	
	

}
