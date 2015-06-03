package com.bionic.edu.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bionic.edu.dao.StaffDAO;
import com.bionic.edu.entities.Staff;

@Repository
public class StaffDAOImpl implements StaffDAO{
	@PersistenceContext
	private EntityManager em;
	
	
	
	public List<Staff> getAll() {
		return em.createQuery("SELECT s FROM Staff s", Staff.class).getResultList();
		
	}

	
	public Staff getByID(int staff_id) {
		return em.find(Staff.class, staff_id);

	}

	
	public void add(Staff staff) {
		System.out.println("adding element");
		if (!em.contains(staff)){em.persist(staff);}
		else {System.out.println("Such customer already exists please try another login");}
	}

	
	public void update(Staff staff) {
		em.merge(staff);
	}

	
	public Staff login(String login, String password) {
		for (Staff c: em.createQuery("SELECT c FROM Staff c where c.isActive=true", Staff.class).getResultList()){
			if (c.getLogin().equals(login)&&c.getPassword().equals(password)){
				return c;
			}	
			}
		return null;
	}

	
	public void setActive(Staff staff) {
		staff.setActive(true);
		em.merge(staff);
	}

	
	public void setInCative(Staff staff) {
		staff.setActive(false);
		em.merge(staff);
		
	}

}
