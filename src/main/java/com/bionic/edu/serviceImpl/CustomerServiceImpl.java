package com.bionic.edu.serviceImpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.CustomerDAO;
import com.bionic.edu.daoimpl.CustomerDAOImpl;
import com.bionic.edu.entities.Customer;
import com.bionic.edu.service.CustomerService;

@Named
public class CustomerServiceImpl implements CustomerService{
	
	@Inject
	private CustomerDAO customerDAO;
	
	
	public List<Customer> getAll(){
		return customerDAO.getAll();
	}


	@Override
	public Customer getByID(int customer_id) {
		return customerDAO.getByID(customer_id);
	}


	@Override
	public Customer login(String login, String password) {
		return customerDAO.login(login, password);
	}


	@Transactional
	public void add(Customer customer) {
		customerDAO.add(customer);
	}


	@Transactional
	public void update(Customer customer) {
		customerDAO.update(customer);
	}


	@Transactional
	public void delete(Customer customer) {
		customerDAO.delete(customer);
	}

}
