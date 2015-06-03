package com.bionic.edu.daoimpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bionic.edu.dao.OrderDishDAO;
import com.bionic.edu.entities.DishStatusType;
import com.bionic.edu.entities.OrderDish;
import com.bionic.edu.entities.OrderStatusType;

@Repository
public class OrderDishDAOImpl implements OrderDishDAO{


	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
		
	}

	
	@Override
	public List<OrderDish> getAll() {
		return em.createQuery("SELECT o FROM OrderDish o", OrderDish.class).getResultList();
		 
	}

	@Override
	public OrderDish getByID(int orderDish_id) {
		return em.find(OrderDish.class, orderDish_id);
	}

	@Override
	public void add(OrderDish orderDish) {
		em.persist(orderDish);
	}
	
	
	
	
	
	@Override
	public void update(OrderDish orderDish) {
		em.merge(orderDish);
	}

	@Override
	public void delete(OrderDish orderDish) {
		em.merge(orderDish);
		em.remove(orderDish);
	}

	@Override
	public void prepareKitchenDishInOrder(OrderDish orderDish) {
		orderDish.setStatus(DishStatusType.kitchen_made);
	em.merge(orderDish);
	}

	@Override
	public List<OrderDish> getListForKitchenSortedByTimeStamp() {
		return em.createQuery("SELECT o FROM OrderDish o where o.status=:status", OrderDish.class)
				.setParameter("status", DishStatusType.kitchen_un_made).getResultList();
	}

	@Override
	public List<OrderDish> getAllInOrder(int orderId) {
		return em.createQuery("SELECT o FROM OrderDish o where o.order.order_id=:id", OrderDish.class)
				.setParameter("id", orderId).getResultList();
	
	}

}
