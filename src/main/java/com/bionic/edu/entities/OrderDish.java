package com.bionic.edu.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OrderDish implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderDish_id;
	@ManyToOne
	@JoinColumn(name = "order_id")
	Orders order;
	
	@ManyToOne
	@JoinColumn(name = "dish_id")
	private Dish dish;
	
	private Double releasedPrice;
	@Enumerated(EnumType.STRING)
	private DishStatusType status;
	public OrderDish() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDish(Orders order, Dish dish, Double releasedPrice,
			DishStatusType status) {
		super();
		this.order = order;
		this.dish = dish;
		this.releasedPrice = releasedPrice;
		this.status = status;
	}
	
	public OrderDish(int id,Orders order, Dish dish, Double releasedPrice,
			DishStatusType status) {
		super();
		this.orderDish_id=id;
		this.order = order;
		this.dish = dish;
		this.releasedPrice = releasedPrice;
		this.status = status;
	}
	
	public OrderDish(Dish dish, Double releasedPrice) {
		super();
		this.dish = dish;
		this.releasedPrice = releasedPrice;
	}
	public OrderDish(Orders order,int orderDish_id, Dish dish, Double releasedPrice,
			DishStatusType status) {
		super();
		this.orderDish_id = orderDish_id;
		this.order = order;
		this.dish = dish;
		this.releasedPrice = releasedPrice;
		this.status = status;
	}
	public int getOrderDish_id() {
		return orderDish_id;
	}
	public void setOrderDish_id(int orderDish_id) {
		this.orderDish_id = orderDish_id;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	public Double getReleasedPrice() {
		return releasedPrice;
	}
	public void setReleasedPrice(Double releasedPrice) {
		this.releasedPrice = releasedPrice;
	}
	public DishStatusType getStatus() {
		return status;
	}
	public void setStatus(DishStatusType status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dish.getDish_id();
		result = prime * result + orderDish_id;
		result = prime * result + order.hashCode();
		result = prime * result + releasedPrice.hashCode();
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDish other = (OrderDish) obj;
		if (dish != other.dish)
			return false;
		if (orderDish_id != other.orderDish_id)
			return false;
		if (order != other.order)
			return false;
		if (releasedPrice != other.releasedPrice)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderDish [orderDish_id=" + orderDish_id + ", order_id="
				+ order+ ", dish=" + dish + ", releasedPrice=" + releasedPrice
				+ ", status=" + status + "]";
	}
	
}
