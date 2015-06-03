package com.bionic.edu.dao;
import java.util.List;
import com.bionic.edu.entities.Category;
public interface CategoryDAO {
	  public List<Category> getAll();
	  public List<Category> getAllActive();
	  public List<Category> getAllInActive();
	  public Category getById(int category_id);
	  public void setActive(Category category);
	  public void setInActive(Category category);
	  public void update(Category category);
	  public void add(Category category);
	    
}
