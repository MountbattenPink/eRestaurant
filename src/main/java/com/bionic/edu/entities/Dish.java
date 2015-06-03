package com.bionic.edu.entities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Dish implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int dish_id;
	private String name;
	private Double price;
	@Lob
	private byte[] image;
	private String imageName;
	private String otherInfo;	
	private boolean isKitchen;
	private boolean isActive;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	Category category;

	@OneToMany( mappedBy = "dish")
	private Collection<OrderDish> orderDishes;

	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public Dish(int dish_id, String name, Double price, byte[] image,
			boolean isKitchen, boolean isActive, Category category,String otherinfo) {
		super();
		this.dish_id = dish_id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.isKitchen = isKitchen;
		this.isActive = isActive;
		this.category = category;
		this.otherInfo=otherinfo;
	}
	public Dish() {
		super();
	}
	
	public Dish(String name) {
		super();
		this.name = name;
	}
	public Dish(String name, Double price, byte[] image, boolean isKitchen,
			boolean isActive, Category category,String otherinfo) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
		this.isKitchen = isKitchen;
		this.isActive = isActive;
		this.category = category;
		this.otherInfo=otherinfo;
	}
	
	
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public int getDish_id() {
		return dish_id;
	}
	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public boolean isKitchen() {
		return isKitchen;
	}
	
	public boolean getIsKitchen() {
		return isKitchen;
	}
	
	public void setKitchen(boolean isKitchen) {
		this.isKitchen = isKitchen;
	}

	public void setIsKitchen(boolean isKitchen) {
		this.isKitchen = isKitchen;
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

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + dish_id;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + (isKitchen ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((orderDishes == null) ? 0 : orderDishes.hashCode());
		result = prime * result
				+ ((otherInfo == null) ? 0 : otherInfo.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Dish other = (Dish) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (dish_id != other.dish_id)
			return false;
		if (!Arrays.equals(image, other.image))
			return false;
		if (isActive != other.isActive)
			return false;
		if (isKitchen != other.isKitchen)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderDishes == null) {
			if (other.orderDishes != null)
				return false;
		} else if (!orderDishes.equals(other.orderDishes))
			return false;
		if (otherInfo == null) {
			if (other.otherInfo != null)
				return false;
		} else if (!otherInfo.equals(other.otherInfo))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Dish [dish_id=" + dish_id + ", name=" + name + ", price="
				+ price + ", image=" + image + ", isKitchen="
				+ isKitchen + ", isActive=" + isActive + ", category="
				+ category + "]";
	}

	
}
