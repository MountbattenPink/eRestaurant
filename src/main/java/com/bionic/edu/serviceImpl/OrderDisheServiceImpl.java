package com.bionic.edu.serviceImpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.OrderDishDAO;
import com.bionic.edu.entities.OrderDish;
import com.bionic.edu.service.OrderDishService;

@Named
public class OrderDisheServiceImpl implements OrderDishService{

	@Inject
	OrderDishDAO orderDishDAO;

	@Override
	public List<OrderDish> getAll() {
		return orderDishDAO.getAll();
	}

	@Override
	public OrderDish getByID(int orderDish_id) {
		return orderDishDAO.getByID(orderDish_id);
	}

	@Transactional
	public void add(OrderDish orderDish) {
		orderDishDAO.add(orderDish);
	}

	@Transactional
	public void update(OrderDish orderDish) {
		orderDishDAO.update(orderDish);
	}

	@Transactional
	public void delete(OrderDish orderDish) {
	orderDishDAO.delete(orderDish);	
	}

	@Transactional
	public void prepareKitchenDishInOrder(OrderDish orderDish) {
		orderDishDAO.prepareKitchenDishInOrder(orderDish);
	}

	public List<OrderDish> getListForKitchenSortedByTimeStamp() {
		return orderDishDAO.getListForKitchenSortedByTimeStamp();
	}

	@Override
	public List<OrderDish> getAllInOrder(int orderId) {
		
		return orderDishDAO.getAllInOrder(orderId);
	}
}
