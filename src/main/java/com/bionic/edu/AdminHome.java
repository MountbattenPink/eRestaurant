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
import javax.faces.bean.ManagedProperty;
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
import com.bionic.edu.entities.StaffRoles;
import com.bionic.edu.service.CategoryService;
import com.bionic.edu.service.DishService;
import com.bionic.edu.serviceImpl.CategoryServiceImpl;

@Component("adminHome")
@Scope("view")
public class AdminHome implements Serializable{

	private static final long serialVersionUID = 1L;
	private Category category;
	private List<Dish>dishes;
	private List<Category>categories;
	
	
	@Autowired
    private Login login; 

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DishService dishService;
	public AdminHome() {
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
		
		categories=categoryService.getAllActive();
		category=new Category("",true);
		}
			}
	
	
	
	public Login getLogin() {
		return login;
	}


	public void setLogin(Login login) {
		this.login = login;
	}


	public void reinit() {
		for (int i=0;i<categories.size();i++){
			if (categories.get(i).getName().equals(category.getName()))
			{
				if (i!=categories.size()-1)
				{FacesMessage msg = new FacesMessage("Category already exists");
		          FacesContext.getCurrentInstance().addMessage(null, msg);}
				if (categories.get(i).getCategory_id()!=0){
				categories.remove(categories.get(categories.size()-1));
			}
				}
			 		}
		 category=new Category("",true);   
    
    }
 

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public void refresh () throws IOException{
		category=new Category();
	}
	
	public void remove (Category category){
			FacesMessage msg=null;
		if (category.getCategory_id()!=0){
			dishes=dishService.selectByCategory(category.getCategory_id());
		for (Dish d:dishes){
			if (d.getCategory().getName().equals(category.getName())){
				 msg = new FacesMessage("Please, remove all dishes from this category before deleting");
				}
				}if (msg!=null)
				{
					FacesContext.getCurrentInstance().addMessage(null, msg);
					
			    }else 	categories.remove(category);
		} else
		categories.remove(category);
		
	}
	
		public List<Dish> getDishes() {
			return dishes;
		}

		public void setDishes(List<Dish> dishes) {
			this.dishes = dishes;
		}
	
	
		public String saveAll(){
			
			submitList(categories);
			FacesMessage msg=null;
			msg = new FacesMessage("All changes are saved");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "adminHome";
		}
		
		public void submitList(List<Category>categoryList){
			
			ArrayList <Integer>ids=new ArrayList<Integer>();
			for (Category c:categoryList){
				ids.add(c.getCategory_id());
			}

			for (Category c:categoryService.getAllActive()){
				if (!ids.contains(c.getCategory_id())){categoryService.setInActive(c);}
			}
			for (Category c:categoryList){
				if (c.getCategory_id()!=0)
				categoryService.update(c);
				else categoryService.add(c);
			}
		}
		
		}

		

		

	