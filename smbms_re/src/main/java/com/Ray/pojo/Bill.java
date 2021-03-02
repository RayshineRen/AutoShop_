package com.Ray.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Bill {
	private Integer id;   //id 
	private String billCode; //账单编码 
	private String productName; //商品名称 
	private String productDesc; //商品描述 
	private BigDecimal productCount; //商品数量
	private BigDecimal totalPrice; //总金额
	private Date creationDate; //创建时间
	private Integer providerId; //供应商ID
	private String providerName;//供应商名称

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public BigDecimal getProductCount() {
		return productCount;
	}

	public void setProductCount(BigDecimal productCount) {
		this.productCount = productCount;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	@Override
	public String toString() {
		return "Bill{" +
				"id=" + id +
				", billCode='" + billCode + '\'' +
				", productName='" + productName + '\'' +
				", productDesc='" + productDesc + '\'' +
				", productCount=" + productCount +
				", totalPrice=" + totalPrice +
				", creationDate=" + creationDate +
				", providerId=" + providerId +
				", providerName='" + providerName + '\'' +
				'}';
	}
}
