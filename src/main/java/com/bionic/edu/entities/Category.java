package com.bionic.edu.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Category implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int category_id;
private String name;
private boolean isActive;
@Transient
private List<Dish>dishes;

public List<Dish> getDishes() {
	return dishes;
}
public void setDishes(List<Dish> dishes) {
	this.dishes = dishes;
}
public Category() {}
public Category(int category_id, String name, boolean isActive) {
	super();
	this.category_id = category_id;
	this.name = name;
	this.isActive = isActive;
}
public Category(String name, boolean isActive) {
	super();
	this.name = name;
	this.isActive = isActive;
}


public Category(String name) {
	super();
	this.name = name;
	this.isActive=true;
}
public int getCategory_id() {
	return category_id;
}
public void setCategory_id(int category_id) {
	this.category_id = category_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public boolean isActive() {
	return isActive;
}
public void setActive(boolean isActive) {
	this.isActive = isActive;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + category_id;
	result = prime * result + (isActive ? 1231 : 1237);
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	Category other = (Category) obj;
	if (category_id != other.category_id)
		return false;
	if (isActive != other.isActive)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}
@Override
public String toString() {
	return "Category [category_id=" + category_id + ", name=" + name
			+ ", isActive=" + isActive +"]";
}


}
