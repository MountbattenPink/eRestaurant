package com.bionic.edu.service;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.dao.DishDAO;
import com.bionic.edu.entities.Dish;

public interface DishService {
	 public List<Dish> getAll();
	 public Dish getByID(int dish_id);
	 public void add(Dish dish);
	 public void update(Dish dish);
	 public List<Dish> selectByCategory(String categoru);
	 public List<Dish> selectByCategory(int categoru);
		
	 public void setActive(Dish dish);
	  public void setInActive(Dish dish);
	  public List <Dish> getAllActive();
	  public List <Dish> getAllInActive();
	 
}
