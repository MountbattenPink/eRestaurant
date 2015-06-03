package com.bionic.edu.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bionic.edu.dao.OrderDishDAO;
import com.bionic.edu.entities.Dish;
import com.bionic.edu.entities.DishStatusType;
import com.bionic.edu.entities.OrderDish;
import com.bionic.edu.entities.Orders;
import com.bionic.edu.entities.ReportData;


public interface OrderDishService {
	  public List<OrderDish> getAll();
	  public OrderDish getByID(int orderDish_id);
	  public void add(OrderDish orderDish);
	  public void update(OrderDish orderDish);
	  public void delete(OrderDish orderDish);
	  public List<OrderDish> getAllInOrder(int orderId);
		
	  public void prepareKitchenDishInOrder(OrderDish orderDish);
	  public List<OrderDish> getListForKitchenSortedByTimeStamp(); 

}
