package com.bionic.edu.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bionic.edu.dao.StaffDAO;
import com.bionic.edu.entities.Staff;


public interface StaffService {
	  public List<Staff> getAll();
	  public Staff getByID(int staff_id);
	  public void add(Staff staff);
	  public void update(Staff staff);
	  public Staff login(String login, String password);//returns staff or null
	  public void setActive(Staff staff);
	  public void setInCative(Staff staff);
	
}
