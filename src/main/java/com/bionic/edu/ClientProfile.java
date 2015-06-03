package com.bionic.edu;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.bionic.edu.entities.Customer;
import com.bionic.edu.service.CustomerService;


@Named("clientProfile")
@Scope("request")
public class ClientProfile implements Serializable{
	private static final long serialVersionUID = 1L;
	private Customer currentUser;
	private java.util.Date birthDate;
	@Autowired
	CustomerService customerService;
	@Autowired
	Login login;
	
	private String errorMessage;
	
	public java.util.Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.util.Date birthDate) {
		this.birthDate = birthDate;
	}

	public ClientProfile(){
	}

	@PostConstruct
	public void init() throws IOException{
		if (login.getCurrentUser()==null)
			FacesContext.getCurrentInstance().getExternalContext().redirect("guestHome.xhtml");
		else if (login.getCurrentUser().getCustomer()==null){
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					PageRedirectionList.pagesByRoles.get(login.getCurrentUser().getStaff().getAuthorities()));
		}
		
		else {
			
			currentUser=new Customer();
			currentUser.setCustomer_id(login.getCurrentUser().getCustomer().getCustomer_id());
			currentUser.setAddress(login.getCurrentUser().getCustomer().getAddress());
			currentUser.setBillingInfo(login.getCurrentUser().getCustomer().getBillingInfo());
			currentUser.setBirthDate(login.getCurrentUser().getCustomer().getBirthDate());
			birthDate=new java.util.Date(currentUser.getBirthDate().getTime());
			currentUser.setFullName(login.getCurrentUser().getCustomer().getFullName());
			currentUser.setLogin(login.getCurrentUser().getCustomer().getLogin());
			currentUser.setPassword(login.getCurrentUser().getCustomer().getPassword());
		}
		
	}
	
	public Customer getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Customer currentUser) {
		this.currentUser = currentUser;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String changeData(){
		currentUser.setBirthDate(new java.sql.Date(birthDate.getTime()));
		customerService.update(currentUser);
		login.getCurrentUser().setCustomer(currentUser);
		return "clientHome";
	}
}
