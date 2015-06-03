package com.bionic.edu;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
import com.bionic.edu.service.StaffService;


@Component("login")
@Scope("session")
public class Login implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String password;
	private boolean staff;
	private String errorMessage;
	private CurrentUser currentUser;
	private String session;
	
	@Autowired
	CustomerService customerService;
	@Autowired
	StaffService staffService;
	
	public CurrentUser getCurrentUser() {
		return currentUser;
	}


	

	




	public String getSession() throws IOException {
		if (session!=null)
			{if (currentUser.getCustomer()==null)
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect(PageRedirectionList.pagesByRoles.get(currentUser.getStaff().getAuthorities()));
			else FacesContext.getCurrentInstance().getExternalContext()
			.redirect("clientHome.xhtml");
			
			}
		
		return session;
	}




	public void setSession(String session) {
		this.session = session;
	}




	public void setCurrentUser(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}




	public String getErrorMessage() {
		return errorMessage;
	}




	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}




	public boolean isStaff() {
		return staff;
	}




	public void setStaff(boolean staff) {
		this.staff = staff;
	}




	public Login(){	
	}

	@PostConstruct
	public void init(){
		login="";
		password="";
		staff=false;
	}

	
	public String getLogin() {
		return login;
	}




	public void setLogin(String login) {
		this.login = login;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String signin(){
		System.out.println("found "+staffService.login(login, password));
		
		if (staff){
			if (staffService.login(login, password)!=null){
				currentUser=new CurrentUser(staffService.login(login, password));
				System.out.println(currentUser);
				login=null;password=null;session=" ";
				return PageRedirectionList.pagesByRoles.get(currentUser.getStaff().getAuthorities());
				}		
			else 
				{FacesMessage msg = new FacesMessage("No such user");
				 FacesContext.getCurrentInstance().addMessage(null, msg);
				 return null;
				}
		}
		else {
			if (customerService.login(login, password)!=null){
				currentUser=new CurrentUser(customerService.login(login, password));
				login=null;password=null;session=" ";
				return "clientHome";
				}		
			else 
				{FacesMessage msg = new FacesMessage("No such user");
				 FacesContext.getCurrentInstance().addMessage(null, msg);
				 return null;
				}
			
		}
	}
	
	public void logout() throws IOException{
		currentUser=null;
		session=null;
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
	}
}
