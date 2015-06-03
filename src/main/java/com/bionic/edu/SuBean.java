package com.bionic.edu;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;










import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bionic.edu.entities.Staff;
import com.bionic.edu.entities.StaffRoles;
import com.bionic.edu.service.StaffService;
import com.bionic.edu.serviceImpl.StaffServiceImpl;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Component("suBean")
@Scope("request")
public class SuBean implements Serializable, org.primefaces.model.SelectableDataModel {
	private static final long serialVersionUID = 1L;
	private List<Staff> staffList=null;
	@Autowired
	private StaffService staffService;
	@Autowired
	private Login login;
	private String password;
	private String errorMessage;
	private String errorDescription;
	private List<StaffRoles> staffroles;	
	//for user adding and deleting
			private List<Boolean> isActiveStates;
		
	public SuBean(){
	}
	
	@PostConstruct
	public void init() throws IOException{
		if (login.getCurrentUser()==null)
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		else if ((login.getCurrentUser().getCustomer()==null)&&(login.getCurrentUser().getStaff().getAuthorities()!=StaffRoles.Role_SUser)){
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					PageRedirectionList.pagesByRoles.get(login.getCurrentUser().getStaff().getAuthorities()));
		}
		
		else if (login.getCurrentUser().getStaff()==null){
			FacesContext.getCurrentInstance().getExternalContext().redirect("clientHome.xhtml");
			
		}
		
		staffList=staffService.getAll();
		staffroles=new ArrayList<StaffRoles>();
		staffroles.addAll(Arrays.asList(StaffRoles.Role_SysAnalytic, StaffRoles.Role_DeliveryStaff, 
					StaffRoles.Role_KitchenStaff, StaffRoles.Role_Admin));
		isActiveStates=new ArrayList<Boolean>();
		isActiveStates.addAll(Arrays.asList(true, false));
	}
	
	
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public List<StaffRoles> getStaffroles() {
		return staffroles;
	}
	public void setStaffroles( List<StaffRoles> staffroles) {
		this.staffroles = staffroles;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private Staff selectedStaff;
	
	public Staff getSelectedStaff() {
		return selectedStaff;
	}
	public void setSelectedStaff(Staff selectedStaff) {
		this.selectedStaff = selectedStaff;
	}
	
	public List<Staff> getStaffList() {
		return staffList;
	}
	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}
	
	public String logout(){
		return "logout";
		
	}
	
	
	@Override
	public Object getRowData(String arg0) {
		return selectedStaff;
	}
	@Override
	public Object getRowKey(Object arg0) {
		return selectedStaff;
	}
	
	public void edit(Staff staff){
		System.out.println( staff);
	}
	public String delete(){
		for (Staff s:this.staffList)
			if (s.getStaff_id()==selectedStaff.getStaff_id())s.setActive(false);
		return "deleted";
	}
	
	
	
	public String changePass(){
		login.getCurrentUser().getStaff().setPassword(password);
		
		staffService.update(login.getCurrentUser().getStaff());
		return "suHome";
		}
	
	public void openDialog(Staff staff){
		selectedStaff=staff;
		System.out.println(staff);
	}
	
	
    public void onRowEdit(RowEditEvent event) {
    	  staffService.update((Staff)event.getObject());
    	  FacesMessage msg = new FacesMessage("Staff Edited");
          FacesContext.getCurrentInstance().addMessage(null, msg);
     }
     
    public void onRowCancel(RowEditEvent event) {
     FacesMessage msg = new FacesMessage("Edit cancelled");
     FacesContext.getCurrentInstance().addMessage(null, msg);
   }
	public List<Boolean> getIsActiveStates() {
		return isActiveStates;
	}
	public void setIsActiveStates(List<Boolean> isActiveStates) {
		this.isActiveStates = isActiveStates;
	}
    
    
}
