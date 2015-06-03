package com.bionic.edu.serviceImpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.CategoryDAO;
import com.bionic.edu.entities.Category;
import com.bionic.edu.service.CategoryService;

@Named
public class CategoryServiceImpl implements CategoryService{

	@Inject
	private CategoryDAO categoryDAO;

	public List<Category> getAll() {
		return categoryDAO.getAll();
	}

	public List<Category> getAllActive() {
		return categoryDAO.getAllActive();
	}

	public List<Category> getAllInActive() {
		return categoryDAO.getAllInActive();
	}

	public Category getById(int category_id) {
		return categoryDAO.getById(category_id);
	}

	@Transactional
	public void setActive(Category category) {
		categoryDAO.setActive(category);
	}

	@Transactional
	public void setInActive(Category category) {
		categoryDAO.setInActive(category);
	}

	@Transactional
	public void update(Category category) {
		categoryDAO.update(category);
	}

	@Transactional
	public void add(Category category) {
		categoryDAO.add(category);
	}
}
