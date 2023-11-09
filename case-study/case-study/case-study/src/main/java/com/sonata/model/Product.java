package com.sonata.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
   
    private String color;
    private String size;
    
    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date discontinued;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "visible", columnDefinition = "VARCHAR(3) DEFAULT 'NO'")
    private Visibility visible;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Inventory> inventory;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "audit_id")
    private Audit audit;
    
    
    
    
    public Product()
    {
    	
    }
    


	public List<Inventory> getInventory() {
		return inventory;
	}


	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}


	public Date getDiscontinued() {
		return discontinued;
	}




	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}



	public Visibility getVisible() {
		return visible;
	}

	public void setVisible(Visibility visible) {
		this.visible = visible;
	}


	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}



	public Audit getAudit() {
		return audit;
	}



	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	

    
}



