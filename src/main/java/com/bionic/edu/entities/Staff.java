package com.bionic.edu.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.EnumType;


@Entity
public class Staff implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int staff_id;
	private String login;
	private String password;
	private String fullName;
	private boolean isActive;
	@Enumerated(EnumType.STRING)
	private StaffRoles authorities;
	private int notfirst;
	public Staff() {
		super();
	}
	
	
	public Staff(int staff_id, String login, String password, String fullName,
			boolean isActive, StaffRoles authorities) {
		super();
		this.staff_id = staff_id;
		this.login = login;
		this.password = password;
		this.fullName = fullName;
		this.isActive = isActive;
		this.authorities = authorities;
	}
	public Staff(String login, String password, String fullName,
			boolean isActive, StaffRoles authorities) {
		super();
		this.login = login;
		this.password = password;
		this.fullName = fullName;
		this.isActive = isActive;
		this.authorities = authorities;
	}
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
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
	public boolean isActive() {
		return isActive;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public StaffRoles getAuthorities() {
		return authorities;
	}
	public void setAuthorities(StaffRoles authorities) {
		this.authorities = authorities;
	}
	
	
	public int getNotfirst() {
		return notfirst;
	}


	public void setNotfirst(int notfirst) {
		this.notfirst = notfirst;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + staff_id;
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
		Staff other = (Staff) obj;
		if (authorities != other.authorities)
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (isActive != other.isActive)
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
		if (staff_id != other.staff_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Staff [staff_id=" + staff_id + ", login=" + login
				+ ", password=" + password + ", fullName=" + fullName
				+ ", isActive=" + isActive + ", authorities=" + authorities
				+ "]";
	}
	
}
