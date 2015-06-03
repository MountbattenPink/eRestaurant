package com.bionic.edu.dao;

import java.util.List;
import com.bionic.edu.entities.Dish;


public interface DishDAO {
	 public List<Dish> getAll();
	 public Dish getByID(int dish_id);
	 public void add(Dish dish);
	 public void update(Dish dish);
	 public List<Dish> selectByCategory(int categoru);
	 public List<Dish> selectByCategory(String categoru);
		
	 // public void delete(Dish dish);
	  public void setActive(Dish dish);
	  public void setInActive(Dish dish);
	  public List <Dish> getAllActive();
	  public List <Dish> getAllInActive();
	  
	
}
