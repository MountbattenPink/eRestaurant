package com.bionic.edu;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bionic.edu.dao.CategoryDAO;
import com.bionic.edu.daoimpl.CategoryDAOImpl;
import com.bionic.edu.entities.Category;
import com.bionic.edu.service.CategoryService;
import com.bionic.edu.service.CustomerService;
import com.bionic.edu.serviceImpl.CategoryServiceImpl;
 
@FacesConverter("categoryConverter")
public class CategoryConverter implements Converter {
	//@SuppressWarnings("resource")
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		String [] arr=arg2.split(",");
		int i=Integer.valueOf(arr[0].split("=")[1]);
		for (Category c: AdminHomeDish.getCategoriesForConverter()){
			if (c.getCategory_id()==i)
				return c;
		}
		return null;
	//	return new Category(465, "Cookies", true);
	
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return arg2.toString();
		 
	}
	
	 /* public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
	        if(value != null && value.trim().length() > 0) {
	            try {
	                AdminHome category = (AdminHome) fc.getExternalContext().getApplicationMap().get("categoryService");
	                return category.getCategories().get(Integer.parseInt(value));
	            } catch(NumberFormatException e) {
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
	            }
	        }
	        else {
	            return null;
	        }
	    }
	 
	    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
	        if(object != null) {
	            return String.valueOf(((Category) object).getCategory_id());
	        }
	        else {
	            return null;
	        }
	    }   */
}
