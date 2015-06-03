package com.bionic.edu.serviceImpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.DishDAO;
import com.bionic.edu.daoimpl.DishDAOImpl;
import com.bionic.edu.entities.Dish;
import com.bionic.edu.service.DishService;

@Named
public class DishServiceImpl implements DishService{

	@Inject
	private DishDAO dishDAO;

	
	public List<Dish> getAll() {
		return dishDAO.getAll();
	}

	
	public Dish getByID(int dish_id) {
		return dishDAO.getByID(dish_id);
	}

	@Transactional
	public void add(Dish dish) {
	dishDAO.add(dish);	
	}

	@Transactional
	public void update(Dish dish) {
		dishDAO.update(dish);
	}

	
	public List<Dish> selectByCategory(String categoru) {
		return dishDAO.selectByCategory(categoru);
	}

	@Transactional
	public void setActive(Dish dish) {
		dishDAO.setActive(dish);
	}

	@Transactional
	public void setInActive(Dish dish) {
		dishDAO.setInActive(dish);
	}

	@Override
	public List<Dish> getAllActive() {
		return dishDAO.getAllActive();
	}

	@Override
	public List<Dish> getAllInActive() {
		return dishDAO.getAllInActive();
	}


	@Override
	public List<Dish> selectByCategory(int categoru) {
	return dishDAO.selectByCategory(categoru);
	}
}
