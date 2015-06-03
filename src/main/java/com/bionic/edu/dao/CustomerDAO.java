package com.bionic.edu.dao;
import java.util.List;

import com.bionic.edu.entities.Category;
import com.bionic.edu.entities.Customer;

public interface CustomerDAO {
	  public List<Customer> getAll();
	  public Customer getByID(int customer_id);
	  public Customer login(String login, String password);//returns customer or null
	  public void add(Customer customer);
	  public void update(Customer customer);
	  public void delete(Customer customer);
	  
}
