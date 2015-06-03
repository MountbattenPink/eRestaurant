package com.bionic.edu.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.springframework.stereotype.Repository;

import com.bionic.edu.dao.OrderDAO;
import com.bionic.edu.entities.Dish;
import com.bionic.edu.entities.DishStatusType;
import com.bionic.edu.entities.OrderDish;
import com.bionic.edu.entities.OrderStatusType;
import com.bionic.edu.entities.Orders;
import com.bionic.edu.entities.ReportData;
import com.bionic.edu.entities.Staff;

import java.sql.Timestamp;


public interface OrderService {
	 public List<Orders> getAll();
	  public Orders getByID(int order_id);
	  public void add(Orders order);
	  public void update(Orders order);
	  public List<Orders>getListForDeliverySortedByTimeStamp();
	  public List<Orders>getListForDeliverySortedByStatus();	 
	  public void markDone (Orders order);
	  public void takeForDelivery(Orders order);
	  public void reportDelivered(Orders order);
	  public List<ReportData> makeReport(Date start, Date finish);
	Orders submitByCustomer(String customerAddress,
			HashMap<Dish, Double> dishesAndAmount);
	public List<Orders> getListTakenByStaff(Staff staff);
	

}
