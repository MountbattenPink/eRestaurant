package com.bionic.edu.daoimpl;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Repository
public class OrderDAOimpl implements OrderDAO{

	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
		
	}
	
	@Override
	public List<Orders> getAll() {
		return em.createQuery("SELECT o FROM Orders o", Orders.class).getResultList();
		
	}

	@Override
	public Orders getByID(int order_id) {
		return em.find(Orders.class, order_id);
	}

	@Override
	public void add(Orders order) {
		em.persist(order);
		
	}

	@Override
	public void update(Orders order) {
		em.merge(order);
	}



	@Override
	public List<Orders> getListForDeliverySortedByTimeStamp() {
		return em.createQuery("SELECT o FROM Orders o WHERE o.status=:status ORDER BY o.timeStamp", Orders.class)
				.setParameter("status", OrderStatusType.ready_for_shipment).getResultList();	
	}

	@Override
	public List<Orders> getListForDeliverySortedByStatus() {
		return em.createQuery("SELECT o FROM Orders o WHERE o.status='ready_for_shipment' ORDER BY o.status", Orders.class).getResultList();	
		}

	@Override
	public void takeForDelivery(Orders order) {
		order.setStatus(OrderStatusType.delivering);
		em.merge(order);
	}

	@Override
	public void reportDelivered(Orders order) {
		order.setStatus(OrderStatusType.delivered);
		em.merge(order);
	}

	@Override
	public void markDone(Orders order) {
		order.setStatus(OrderStatusType.ready_for_shipment);
		em.merge(order);
	}

	@Override
	public List<ReportData> makeReport(Date start, Date finish) {
		return em.createQuery("select new com.bionic.edu.entities.ReportData"
				+ " (od.dish.category, func('DATE' od.order.timeStamp), count(od), sum(od.releasedPrice)) "
				+ " from OrderDish od "
				+ " where od.order.timeStamp BETWEEN :start AND :finish "
				+ " group by od.dish.category, func('DATE' od.order.timeStamp)"
				, ReportData.class).setParameter("start", start).setParameter("finish", finish).getResultList();
	}

	
	@Override
	public Orders submitByCustomer(String customerAddress, HashMap<Dish, Double> dishesAndAmount) {
		Orders ord=new Orders(new Timestamp(Calendar.getInstance().getTime().getTime()), 
				OrderStatusType.ready_for_shipment, customerAddress);
		em.persist(ord);
		ord=em.merge(ord);
		for (Dish d:dishesAndAmount.keySet()){
			for (int i=1;i<=dishesAndAmount.get(d);i++)
			{
				if (d.isKitchen()==true){
				em.persist(
				new OrderDish(ord, d, d.getPrice(), DishStatusType.kitchen_un_made));
				ord.setStatus(OrderStatusType.unprepared);
			} else {
				em.persist(
						new OrderDish(ord, d, dishesAndAmount.get(d), DishStatusType.non_kitchen_made));				
			}}
		}
		em.merge(ord);
		return null;
	}

	@Override
	public List<Orders> getListTakenByStaff(Staff staff) {
		

		return em.createQuery("SELECT o FROM Orders o WHERE o.deliveredBy=:staff", Orders.class).setParameter("staff", staff).getResultList();
	}

	

}
