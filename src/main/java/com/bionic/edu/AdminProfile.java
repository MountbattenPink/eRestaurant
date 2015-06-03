package com.bionic.edu;
import java.io.IOException;
import java.io.Serializable;

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

@Named("adminPofile")
@Scope("request")
public class AdminProfile implements Serializable{
	private static final long serialVersionUID = 1L;
	private Staff currentUser;
	
	private String errorMessage;
	
	@Autowired
	private Login login;
	@Autowired
	private StaffService staffService;
	
	
	public AdminProfile(){
		}
	
	@PostConstruct
	public void init() throws IOException{
		if (login.getCurrentUser()==null)
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		else if ((login.getCurrentUser().getCustomer()==null)&&(login.getCurrentUser().getStaff().getAuthorities()!=StaffRoles.Role_Admin)){
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					PageRedirectionList.pagesByRoles.get(login.getCurrentUser().getStaff().getAuthorities()));
		}
		
		else if (login.getCurrentUser().getStaff()==null){
			FacesContext.getCurrentInstance().getExternalContext().redirect("clientHome.xhtml");
			
		}
		
		 
		else{
			if (login.getCurrentUser().getStaff().getNotfirst()==0){
				FacesMessage msg = new FacesMessage("Change you password to more secure!");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
				
			}
		
		currentUser=new Staff();
		currentUser.setActive(login.getCurrentUser().getStaff().getIsActive());
		currentUser.setAuthorities(login.getCurrentUser().getStaff().getAuthorities());
		currentUser.setFullName(login.getCurrentUser().getStaff().getFullName());
		currentUser.setLogin(login.getCurrentUser().getStaff().getLogin());
		currentUser.setPassword(login.getCurrentUser().getStaff().getPassword());
		currentUser.setStaff_id(login.getCurrentUser().getStaff().getStaff_id());
		}
	}

	public Staff getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Staff currentUser) {
		this.currentUser = currentUser;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String changeData(){
		System.out.println(currentUser.getPassword());
		if (currentUser.getPassword()==""){currentUser.setPassword(login.getCurrentUser().getStaff().getPassword());}
		else {currentUser.setNotfirst(1);}
		staffService.update(currentUser);
		login.setCurrentUser(new CurrentUser(currentUser));
		return "adminHome";
	}


	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	
}

