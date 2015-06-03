package com.bionic.edu;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bionic.edu.entities.Customer;
import com.bionic.edu.entities.Staff;
import com.bionic.edu.service.CustomerService;


@Component("register")
@Scope("request")
public class Register implements Serializable{
	private static final long serialVersionUID = 1L;
	Customer currentUser;
	java.util.Date birthdateUtil;
	@Autowired
	CustomerService customerService;
	@Autowired
	Login login;
	
	public Register() {
	}
	@PostConstruct
	public void init() throws IOException {
		
		if (login.getCurrentUser()!=null)
		{
			if (login.getCurrentUser().getCustomer()!=null)		
				FacesContext.getCurrentInstance().getExternalContext().redirect("clientHome");
			
		}
		currentUser=new Customer();
		birthdateUtil=Date.from(Instant.now());
		
	}
	public Customer getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(Customer currentUser) {
		this.currentUser = currentUser;
	}
	
	public String registerUser(){
		currentUser.setBirthDate( new java.sql.Date(birthdateUtil.getTime()));
		System.out.println(currentUser);
		customerService.add(currentUser);
		return "login";
	}
	public java.util.Date getBirthdateUtil() {
		return birthdateUtil;
	}
	public void setBirthdateUtil(java.util.Date birthdateUtil) {
		this.birthdateUtil = birthdateUtil;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
}
