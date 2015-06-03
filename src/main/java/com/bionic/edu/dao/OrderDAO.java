package com.bionic.edu.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import com.bionic.edu.entities.Dish;
import com.bionic.edu.entities.Orders;
import com.bionic.edu.entities.ReportData;
import com.bionic.edu.entities.Staff;


public interface OrderDAO {
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
