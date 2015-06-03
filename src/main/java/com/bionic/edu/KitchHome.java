package com.bionic.edu;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bionic.edu.entities.Category;
import com.bionic.edu.entities.Dish;
import com.bionic.edu.entities.DishStatusType;
import com.bionic.edu.entities.OrderDish;
import com.bionic.edu.entities.OrderStatusType;
import com.bionic.edu.entities.Orders;
import com.bionic.edu.entities.Staff;
import com.bionic.edu.entities.StaffRoles;
import com.bionic.edu.service.OrderDishService;
import com.bionic.edu.service.OrderService;



@Component("kitchHome")
@Scope("request")
public class KitchHome implements Serializable{
	private static final long serialVersionUID = 1L;
	List<OrderDish>model;
	OrderDish selectedOrderDish=null;
	@Autowired
	OrderDishService orderDishService;
	@Autowired
	OrderService orderService;
	@Autowired
	Login login;
	public KitchHome(){
	}
	
	@PostConstruct
	public void init() throws IOException{
		if (login.getCurrentUser()==null)
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		else if ((login.getCurrentUser().getCustomer()==null)&&(login.getCurrentUser().getStaff().getAuthorities()!=StaffRoles.Role_KitchenStaff)){
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
		
		model=orderDishService.getListForKitchenSortedByTimeStamp();}
	}
	
	
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<OrderDish> getModel() {
		return model;
	}
	public void setModel(List<OrderDish> model) {
		this.model = model;
	}
	public OrderDish getSelectedOrderDish() {
		return selectedOrderDish;
	}
	public void setSelectedOrderDish(OrderDish selectedOrderDish) {
		this.selectedOrderDish = selectedOrderDish;
	}
public String markPrepared (OrderDish dish){
	//orderDishService.prepareKitchenDishInOrder(dish);
	model.remove(dish);
	System.out.println(dish);
	//orderDishService.prepareKitchenDishInOrder(dish);
	dish.setStatus(DishStatusType.kitchen_made);
	orderDishService.update(dish);
	boolean setDone=true;
	for (OrderDish od: dish.getOrder().getOrderDishes()){
		if (od.getStatus().equals(DishStatusType.kitchen_un_made))setDone=false;}
	if (setDone)orderService.markDone(dish.getOrder());
	return "kitchHome";
}




	
}


