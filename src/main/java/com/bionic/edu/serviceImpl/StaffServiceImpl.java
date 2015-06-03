
package com.bionic.edu.serviceImpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.StaffDAO;
import com.bionic.edu.entities.Staff;
import com.bionic.edu.service.StaffService;

@Named
public class StaffServiceImpl implements StaffService{

	@Inject
	private StaffDAO staffDAO;

	@Override
	public List<Staff> getAll() {
		return staffDAO.getAll();
	}

	@Override
	public Staff getByID(int staff_id) {
		return staffDAO.getByID(staff_id);
	}

	@Transactional
	public void add(Staff staff) {
	staffDAO.add(staff);	
	}

	@Transactional
	public void update(Staff staff) {
		staffDAO.update(staff);
	}

	@Override
	public Staff login(String login, String password) {
		return staffDAO.login(login, password);
	}

	@Transactional
	public void setActive(Staff staff) {
		staffDAO.setActive(staff);
	}

	@Transactional
	public void setInCative(Staff staff) {
		staffDAO.setInCative(staff);
	}
}
