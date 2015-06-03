package com.bionic.edu.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
public class Orders implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int order_id;
	private Timestamp timeStamp;
	@Enumerated(EnumType.STRING)
	private OrderStatusType status;
	private String client_address;
	@OneToMany(mappedBy = "order")
	private Collection<OrderDish> orderDishes;
	@ManyToOne
	@JoinColumn(name = "deliveredBy")
	private Staff deliveredBy;
	
	
	public Orders(int order_id, Timestamp timeStamp, OrderStatusType status,
			String client_address, Collection<OrderDish> orderDishes,
			Staff deliveredBy) {
		super();
		this.order_id = order_id;
		this.timeStamp = timeStamp;
		this.status = status;
		this.client_address = client_address;
		this.orderDishes = orderDishes;
		this.deliveredBy = deliveredBy;
	}
	public String getClient_address() {
		return client_address;
	}
	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}
	public Collection<OrderDish> getOrderDishes() {
		return orderDishes;
	}
	public void setOrderDishes(Collection<OrderDish> orderDishes) {
		this.orderDishes = orderDishes;
	}
	public Staff getDeliveredBy() {
		return deliveredBy;
	}
	public void setDeliveredBy(Staff deliveredBy) {
		this.deliveredBy = deliveredBy;
	}
	public Orders() {
		super();
		}
	public Orders(int order_id, Timestamp timeStamp, OrderStatusType status,
			String customer_address, Collection<OrderDish> orderDishes) {
		super();
		this.order_id = order_id;
		this.timeStamp = timeStamp;
		this.status = status;
		this.client_address = customer_address;
		this.orderDishes = orderDishes;
	}
	public Orders(Timestamp timeStamp, OrderStatusType status,
			String customer_address, Collection<OrderDish> orderDishes) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.client_address = customer_address;
		this.orderDishes = orderDishes;
	}
	public Orders(Timestamp timestamp, OrderStatusType status,
			String customerAddress) {
		this.timeStamp = timestamp;
		this.status = status;
		this.client_address = customerAddress;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public OrderStatusType getStatus() {
		return status;
	}
	public void setStatus(OrderStatusType status) {
		this.status = status;
	}
	public String getCustomer_address() {
		return client_address;
	}
	public void setCustomer_address(String customer_address) {
		this.client_address = customer_address;
	}
	//public Collection<OrderDish> getOrderDishes() {
	//	return orderDishes;
	//}
	//public void setOrderDishes(Collection<OrderDish> orderDishes) {
		//this.orderDishes = orderDishes;
	//}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((client_address == null) ? 0 : client_address.hashCode());
	//	result = prime * result
		//		+ ((orderDishes == null) ? 0 : orderDishes.hashCode());
		result = prime * result + order_id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((timeStamp == null) ? 0 : timeStamp.hashCode());
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
		Orders other = (Orders) obj;
		if (client_address == null) {
			if (other.client_address != null)
				return false;
		} else if (!client_address.equals(other.client_address))
			return false;
	//	if (orderDishes == null) {
		//	if (other.orderDishes != null)
			//	return false;
		//} else if (!orderDishes.equals(other.orderDishes))
			//return false;
		if (order_id != other.order_id)
			return false;
		if (status != other.status)
			return false;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", timeStamp=" + timeStamp
				+ ", status=" + status + ", customer_address="
				+ client_address + ", orderDishes=" +orderDishes+ " deliveredBy="+deliveredBy+"]";
	}

	
}
