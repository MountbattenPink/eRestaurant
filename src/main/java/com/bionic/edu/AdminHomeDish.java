package com.bionic.edu;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import com.bionic.edu.entities.Category;
import com.bionic.edu.entities.Dish;
import com.bionic.edu.entities.StaffRoles;
import com.bionic.edu.service.CategoryService;
import com.bionic.edu.service.DishService;
import com.bionic.edu.serviceImpl.CategoryServiceImpl;

@Named("adminHomeDish")
@Scope("view")
public class AdminHomeDish implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static List<Category> categoriesForConverter;
	private Category category;
	private Dish dish;
	private List<Dish>dishes;
	private UploadedFile file;
	private List<Category>categories=null;
	
	@Autowired
	private CategoryService categoryService;	
	@Autowired
	private DishService dishService;
	@Autowired
    private Login login; 

	public AdminHomeDish() {
		}

	
	
	public Login getLogin() {
		return login;
	}



	public void setLogin(Login login) {
		this.login = login;
	}



	public static List<Category> getCategoriesForConverter() {
		return categoriesForConverter;
	}


	public static void setCategoriesForConverter(
			List<Category> categoriesForConverter) {
		AdminHomeDish.categoriesForConverter = categoriesForConverter;
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
		
		
		
		categories=categoryService.getAllActive();
		dishes=dishService.getAllActive();
		dish=new Dish("", null, null, false, true, null, "");
		categoriesForConverter=categories;}
		}
	
	public void reinit() throws IOException {
		for (int i=0;i<dishes.size();i++){
			if (dishes.get(i).getName().equals(dish.getName()))
			{
				if (i!=dishes.size()-1)
				{FacesMessage msg = new FacesMessage("Dish already exists");
		          FacesContext.getCurrentInstance().addMessage(null, msg);}
				if (dishes.get(i).getDish_id()!=0){
					dishes.remove(dishes.get(dishes.size()-1));
			}
				}
			 		}
		 dish=new Dish("", null, null, false, true, null, "");
			   
    
    }
	
	public void uploadFile() throws IOException{
		if (file!=null){
			System.out.println(file);
		InputStreamReader reader = new InputStreamReader(file.getInputstream());
		int partition = 1024;
		int length = 0;
		int position = 0;
		char[] buffer = new char[partition];
		FileWriter fstream = new FileWriter("images/dishes/"+dish.getName().split(" ")[0]+"-"+dish.getCategory().getCategory_id());
		do{
		    length = reader.read(buffer, position, partition);
		    fstream.write(buffer, position, length);
		}while(length > 0);
		File f = new File("images/dishes/"+dish.getName().split(" ")[0]+"-"+dish.getCategory().getCategory_id()+".jpg");
		
		}
	
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
		dish = new Dish();
	
	}
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void remove (Dish dish){
	dishes.remove(dish);
	
}
	
	 public void onRowEdit(RowEditEvent event) {
	    	System.out.println("Dish Edited");
	    	 FacesMessage msg = new FacesMessage("Dish Edited");
	          FacesContext.getCurrentInstance().addMessage(null, msg);
	     for (Dish d:dishes)System.out.println(d);                 
	 }
	     
	    public void onRowCancel(RowEditEvent event) {
	    	System.out.println("Dish cancelled");
	    	
	    	FacesMessage msg = new FacesMessage("Dish Cancelled");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
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
	
		
		public void handleFileUpload(FileUploadEvent event) throws IOException {

		    file = event.getFile();
		    System.out.println(file.getFileName());
		    file.getFileName();
		    byte[] foto = IOUtils.toByteArray(file.getInputstream());
		    dish.setImage(foto);
		}
		
		
		public String saveAll(){
			submitList(dishes);
			FacesMessage msg=null;
			msg = new FacesMessage("All changes are saved");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "adminHomeDish";
		}
		
		public void submitList(List<Dish>dishList){
			
			ArrayList <Integer>ids=new ArrayList<Integer>();
			for (Dish d:dishList){
				ids.add(d.getDish_id());
			}

			for (Dish d:dishService.getAllActive()){
				if (!ids.contains(d.getDish_id())){dishService.setInActive(d);}
			}
			for (Dish d:dishList){
				if (d.getDish_id()!=0)
				dishService.update(d);
				else dishService.add(d);
			}
		}
		

		}

		

		

	