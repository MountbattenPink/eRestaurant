package com.bionic.edu.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.dao.CustomerDAO;
import com.bionic.edu.entities.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
		
	}

	@Override
	public List<Customer> getAll() {
		return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
	}

	@Override
	public Customer getByID(int customer_id) {
		return em.find(Customer.class, customer_id);
	}

	
	@Override
	public void add(Customer customer) {
	System.out.println("adding element");
	if (!em.contains(customer)){em.persist(customer);}
	else {System.out.println("Such customer already exists please try another login");}
		 
	}

	@Override
	public void update(Customer customer) {
		em.merge(customer);
	}

	@Override
	public Customer login(String login, String password) {
		for (Customer c: em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList()){
			if (c.getLogin().equals(login)&&c.getPassword().equals(password)){
				return c;
			}	
			}
		return null;
		}
	
	

	@Override
	public void delete(Customer customer) {
		em.merge(customer);
		em.remove(customer);
		
	}
}
