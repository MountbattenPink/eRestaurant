package com.bionic.edu.dao;

import java.util.List;

import com.bionic.edu.entities.Staff;

public interface StaffDAO {
	  public List<Staff> getAll();
	  public Staff getByID(int staff_id);
	  public void add(Staff staff);
	  public void update(Staff staff);
	  public Staff login(String login, String password);//returns staff or null
	  public void setActive(Staff staff);
	  public void setInCative(Staff staff);
		  
}
