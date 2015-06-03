package com.bionic.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.EnumType;


@Entity
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customer_id;
	private String login;
	private String password;
	private String fullName;
	private String address;
	private Date birthDate;
	private String billingInfo;
	public Customer() {
		super();
	}
	public Customer(int customer_id, String login, String password,
			String fullName,  String address, Date birthDate,
			String billingInfo) {
		super();
		this.customer_id = customer_id;
		this.login = login;
		this.password = password;
		this.fullName = fullName;
		this.address = address;
		this.birthDate = birthDate;
		this.billingInfo = billingInfo;
	}
	public Customer(String login, String password, String fullName,
			 String address, Date birthDate, String billingInfo) {
		super();
		this.login = login;
		this.password = password;
		this.fullName = fullName;
		this.address = address;
		this.birthDate = birthDate;
		this.billingInfo = billingInfo;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getBillingInfo() {
		return billingInfo;
	}
	public void setBillingInfo(String billingInfo) {
		this.billingInfo = billingInfo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((billingInfo == null) ? 0 : billingInfo.hashCode());
		result = prime * result
				+ ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + customer_id;
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (billingInfo == null) {
			if (other.billingInfo != null)
				return false;
		} else if (!billingInfo.equals(other.billingInfo))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (customer_id != other.customer_id)
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", login=" + login
				+ ", password=" + password + ", fullName=" + fullName
				+ ", address=" + address + ", birthDate=" + birthDate
				+ ", billingInfo=" + billingInfo + "]";
	}
	
}
