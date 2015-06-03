package com.bionic.edu.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.dao.CategoryDAO;
import com.bionic.edu.entities.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

	@PersistenceContext
	private EntityManager em;
	
	public List<Category> getAll() {
		List<Category> result = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
		return result;
	}

	
	public EntityManager getEm() {
		return em;
	}


	public void setEm(EntityManager em) {
		this.em = em;
	}


	public Category getById(int category_id) {
		return em.find(Category.class, category_id);
	}

	
	public void update(Category category) {
		em.merge(category);
	}

	
	public void add(Category category) {
		em.persist(category);
	}

	
	public List<Category> getAllActive() {
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.isActive = true", Category.class);	
		return query.getResultList();
	}

	
	public List<Category> getAllInActive() {
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.isActive = false", Category.class);	
		return query.getResultList();
	}

	
	public void setActive(Category category) {
		category.setActive(true);
		em.merge(category);
	}

	
	public void setInActive(Category category) {
		category.setActive(false);
		em.merge(category);
	}


	

}
