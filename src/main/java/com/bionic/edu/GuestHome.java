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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bionic.edu.entities.Category;
import com.bionic.edu.entities.Dish;
import com.bionic.edu.entities.StaffRoles;
import com.bionic.edu.service.CategoryService;
import com.bionic.edu.service.DishService;

@Component("guestHome")
@Scope("request")
public class GuestHome implements Serializable{

	private static final long serialVersionUID = 1L;
	private Category category;
	private List<Category>categories;
	private Dish dish;
	private List<Dish>dishes;
	@Autowired
	CategoryService categoryService;
	@Autowired
	DishService dishService;
	@Autowired
	Login login;
	public GuestHome () {
		
	}
	
	@PostConstruct
	public void init() throws IOException {
		if (login.getCurrentUser()!=null)
		{
			if (login.getCurrentUser().getCustomer()==null){
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					PageRedirectionList.pagesByRoles.get(login.getCurrentUser().getStaff().getAuthorities()));
		}
			else FacesContext.getCurrentInstance().getExternalContext().redirect("clientHome.xhtml");
		}
		else {
		
		categories=categoryService.getAllActive();
		category=new Category();
		for (Category c:categories){
			c.setDishes(dishService.selectByCategory(c.getCategory_id()));
		}
		}
			}

	
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
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

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	
	
	
	
	  
    public void onTabChange(TabChangeEvent event) {
        //dishes=dishService.selectByCategory(event.getTab().getTitle().split(" - ")[1]);
     
    	FacesMessage msg = new FacesMessage("Active Tab: "+  event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
         
    public void onTabClose(TabCloseEvent event) {
     }
	
			}

		

		

	