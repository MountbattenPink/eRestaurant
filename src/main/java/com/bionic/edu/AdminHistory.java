package com.bionic.edu;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.bionic.edu.entities.Category;
import com.bionic.edu.entities.Dish;
import com.bionic.edu.entities.Orders;
import com.bionic.edu.entities.StaffRoles;
import com.bionic.edu.service.CategoryService;
import com.bionic.edu.service.DishService;
import com.bionic.edu.service.OrderService;
import com.bionic.edu.serviceImpl.CategoryServiceImpl;

@Component("adminHistory")
@Scope("request")
public class AdminHistory implements Serializable{

	@Autowired
	private OrderService orderService;
	private List<Orders>orderHistory;
	@Autowired
    private Login login; 

	private static final long serialVersionUID = 1L;
		public AdminHistory() {
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
		System.out.println(login.getCurrentUser());
		

		orderHistory=orderService.getAll();}
			}


	public Login getLogin() {
		return login;
	}


	public void setLogin(Login login) {
		this.login = login;
	}


	public List<Orders> getOrderHistory() {
		return orderHistory;
	}


	public void setOrderHistory(List<Orders> orderHistory) {
		this.orderHistory = orderHistory;
	}
	
			
		}

		

		

	