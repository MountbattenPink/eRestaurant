package com.bionic.edu;
import java.io.Serializable;
import com.bionic.edu.entities.Dish;


public class BucketEntry implements Serializable{

	private static final long serialVersionUID = 1L;
private Dish dish;
private int amount;
public BucketEntry(Dish dish, int amount) {
	super();
	this.dish = dish;
	this.amount = amount;
}
public Dish getDish() {
	return dish;
}
public void setDish(Dish dish) {
	this.dish = dish;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}

}
