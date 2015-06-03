package com.bionic.edu.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.dao.CategoryDAO;
import com.bionic.edu.entities.Category;

public interface CategoryService {
	  public List<Category> getAll();
	  public List<Category> getAllActive();
	  public List<Category> getAllInActive();
	  public Category getById(int category_id);
	  public void setActive(Category category);
	  public void setInActive(Category category);
	  public void update(Category category);
	  public void add(Category category);
	
}
