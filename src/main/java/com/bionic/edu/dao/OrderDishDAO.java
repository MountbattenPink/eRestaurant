package com.bionic.edu.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import com.bionic.edu.entities.Dish;
import com.bionic.edu.entities.OrderDish;
import com.bionic.edu.entities.ReportData;

public interface OrderDishDAO {
	  public List<OrderDish> getAll();
	  public OrderDish getByID(int orderDish_id);
	  public void add(OrderDish orderDish);
	  public void update(OrderDish orderDish);
	  public void delete(OrderDish orderDish);
	  public List<OrderDish> getAllInOrder(int orderId);
	  public void prepareKitchenDishInOrder(OrderDish orderDish);
	  public List<OrderDish> getListForKitchenSortedByTimeStamp(); 
	  
	  
}
