package com.foodservice.model;

import java.util.Date;
//import java.util.Map;

public class Order {
	private String name;
	private String contactNumber;
	private String orderedItem;
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	private String orderId;
	private int bill;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getOrderedItem() {
		return orderedItem;
	}
	public void setOrderedItem(String orderedItem) {
		this.orderedItem = orderedItem;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getBill() {
		return bill;
	}
	public void setBill(int bill) {
		this.bill = bill;
	}
}
