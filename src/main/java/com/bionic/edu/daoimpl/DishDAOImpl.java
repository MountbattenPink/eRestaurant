package com.bionic.edu.daoimpl;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.dao.DishDAO;
import com.bionic.edu.entities.Dish;


@Repository
public class DishDAOImpl implements DishDAO{

	@PersistenceContext
	private EntityManager em;


	@Override
	public List<Dish> getAll() {
		return em.createQuery("SELECT d FROM Dish d", Dish.class).getResultList();
		
	}

	
	@Override
	public Dish getByID(int dish_id) {
		return em.find(Dish.class, dish_id);
		}

	@Override
	public void add(Dish dish) {
		em.persist(dish);
		
	}

	@Override
	public void update(Dish dish) {
		em.merge(dish);
		
	}

	@Override
	public void setActive(Dish dish) {
		dish.setActive(true);
		em.merge(dish);
	}

	@Override
	public void setInActive(Dish dish) {
		dish.setActive(false);
		em.merge(dish);
	}

	@Override
	public List<Dish> getAllActive() {
		TypedQuery<Dish> query = em.createQuery("SELECT d FROM Dish d WHERE d.isActive = true", Dish.class);	
		return query.getResultList();
	}

	@Override
	public List<Dish> getAllInActive() {
		TypedQuery<Dish> query = em.createQuery("SELECT d FROM Dish d WHERE d.isActive = false", Dish.class);	
		return query.getResultList();
	}

	@Override
	public List<Dish> selectByCategory(String categoru) {
		TypedQuery<Dish> query = em.createQuery("Select d from Dish d WHERE d.isActive=true AND d.category.name=:categoryName", Dish.class).setParameter("categoryName", categoru);
	return query.getResultList();
	}


	@Override
	public List<Dish> selectByCategory(int categoru) {
		TypedQuery<Dish> query = em.createQuery("Select d from Dish d WHERE d.isActive=true AND d.category.category_id=:category", Dish.class).setParameter("category", categoru);
		return query.getResultList();
		}


}
