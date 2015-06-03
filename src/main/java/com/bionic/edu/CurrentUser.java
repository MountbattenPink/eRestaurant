package com.bionic.edu;

import java.io.Serializable;

import com.bionic.edu.entities.Customer;
import com.bionic.edu.entities.Staff;

public class CurrentUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private Staff staff;
	private Customer customer;
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public CurrentUser(Staff staff, Customer customer) {
		super();
		this.staff = staff;
		this.customer = customer;
	}
	public CurrentUser(Staff staff) {
		super();
		this.staff = staff;
		
	}
	public CurrentUser(Customer customer) {
		super();
		this.customer = customer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((staff == null) ? 0 : staff.hashCode());
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
		CurrentUser other = (CurrentUser) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (staff == null) {
			if (other.staff != null)
				return false;
		} else if (!staff.equals(other.staff))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "currentUser [staff=" + staff + ", customer=" + customer + "]";
	}
	
	
}
