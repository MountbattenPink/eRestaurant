package com.bionic.edu.serviceImpl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.OrderDAO;
import com.bionic.edu.entities.Dish;
import com.bionic.edu.entities.Orders;
import com.bionic.edu.entities.ReportData;
import com.bionic.edu.entities.Staff;
import com.bionic.edu.service.OrderService;

@Named
public class OrderServiceImpl implements OrderService{

	@Inject
	private OrderDAO orderDAO;

	@Override
	public List<Orders> getAll() {
		return orderDAO.getAll();
	}

	@Override
	public Orders getByID(int order_id) {
		return orderDAO.getByID(order_id);
	}

	@Transactional
	public void add(Orders order) {
		orderDAO.add(order);
	}

	@Transactional
	public void update(Orders order) {
		orderDAO.update(order);
	}

	@Override
	public List<Orders> getListForDeliverySortedByTimeStamp() {
		return orderDAO.getListForDeliverySortedByTimeStamp();
	}

	@Override
	public List<Orders> getListForDeliverySortedByStatus() {
		return orderDAO.getListForDeliverySortedByStatus();
	}

	@Transactional
	public void markDone(Orders order) {
		orderDAO.markDone(order);
	}

	@Transactional
	public void takeForDelivery(Orders order) {
		orderDAO.takeForDelivery(order);
	}

	@Transactional
	public void reportDelivered(Orders order) {
		orderDAO.reportDelivered(order);
	}

	@Transactional
	public List<ReportData> makeReport(Date start, Date finish) {
		return orderDAO.makeReport(start, finish);
	}

	@Transactional
	public Orders submitByCustomer(String customerAddress,
			HashMap<Dish, Double> dishesAndAmount) {
		return orderDAO.submitByCustomer(customerAddress, dishesAndAmount);
	}

	@Override
	public List<Orders> getListTakenByStaff(Staff staff) {
		
		return orderDAO.getListTakenByStaff(staff);
	}

	
}
