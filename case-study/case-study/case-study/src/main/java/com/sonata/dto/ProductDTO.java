package com.sonata.dto;





import java.sql.Timestamp;

import com.sonata.model.Product;

public class ProductDTO {
    private Long productId;
    private String name;
    
    private String brandName;
    private String categoryName;
    private Double mrpPrice;
    private Double listPrice;
    private Double discount;
    
    private int quantity;
    private Long basketId;
    private int basketQuantity;
    private Long batchId;
    private Timestamp createdTimestamp;
    private Timestamp updatedTimestamp;
    private String updatedBy;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName() != null ? product.getName() : "N/A";
        
        this.brandName = product.getBrand() != null ? product.getBrand().getName() : "N/A";
        this.categoryName = product.getCategory() != null ? product.getCategory().getName() : "N/A";
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

    

    public Double getMrpPrice() {
		return mrpPrice;
	}

	public void setMrpPrice(Double mrpPrice) {
		this.mrpPrice = mrpPrice;
	}

	

    public Double getListPrice() {
		return listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}
	

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public int getBasketQuantity() {
        return basketQuantity;
    }

    public void setBasketQuantity(int basketQuantity) {
        this.basketQuantity = basketQuantity;
    }

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Timestamp getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	
		
	}
	
	
    







