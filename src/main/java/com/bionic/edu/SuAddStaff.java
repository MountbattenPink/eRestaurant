package com.bionic.edu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.bionic.edu.entities.Staff;
import com.bionic.edu.entities.StaffRoles;
import com.bionic.edu.service.StaffService;


@Named("suAddStaff")
@Scope("request")
public class SuAddStaff implements Serializable{
	private static final long serialVersionUID = 1L;

	@Autowired
	private StaffService staffService;
	@Autowired
	private Login login;
	private String newStaffFullname;
	private StaffRoles newStaffAuthorities;
	private String newStaffLogin;
	private String newStaffPassword;
	
	private String errorMessage;
	
	private List<StaffRoles> staffroles;
	
	public SuAddStaff() {
		staffroles=new ArrayList<StaffRoles>();
		staffroles.addAll(Arrays.asList(StaffRoles.Role_SysAnalytic, StaffRoles.Role_DeliveryStaff, StaffRoles.Role_KitchenStaff, StaffRoles.Role_Admin));
			}
	
	@PostConstruct
	public void init(){
		
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public String getNewStaffFullname() {
		return newStaffFullname;
	}
	public void setNewStaffFullname(String newStaffFullname) {
		this.newStaffFullname = newStaffFullname;
	}
	public StaffRoles getNewStaffAuthorities() {
		return newStaffAuthorities;
	}
	public void setNewStaffAuthorities(StaffRoles newStaffAuthorities) {
		this.newStaffAuthorities = newStaffAuthorities;
	}
	public String getNewStaffLogin() {
		return newStaffLogin;
	}
	public void setNewStaffLogin(String newStaffLogin) {
		this.newStaffLogin = newStaffLogin;
	}
	public String getNewStaffPassword() {
		return newStaffPassword;
	}
	public void setNewStaffPassword(String newStaffPassword) {
		this.newStaffPassword = newStaffPassword;
	}

	
	
	
	public List<StaffRoles> getStaffroles() {
		return staffroles;
	}

	public void setStaffroles(List<StaffRoles> staffroles) {
		this.staffroles = staffroles;
	}

	public String addNew (){
		for (Staff s:staffService.getAll())
		{
			if (s.getLogin().equals(newStaffLogin)){
				FacesMessage msg = new FacesMessage("User already exists. Please choose other login");
			     FacesContext.getCurrentInstance().addMessage(null, msg);
			  
				return null;
			}
		}
		
		Staff newToAdd=new Staff(newStaffLogin, newStaffLogin, newStaffFullname, true, newStaffAuthorities);
		newToAdd.setNotfirst(0);
		staffService.add(newToAdd);
		return "suHome"; 
	}
}
