package com.bionic.edu;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.criteria.Order;

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
import com.bionic.edu.service.StaffService;


@Component("delivHomeAndMyOrders")
@Scope("request")
public class DelivHomeAndMyOrders implements Serializable{
	private static final long serialVersionUID = 1L;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDishService orderDishService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private Login login;
	private List<Orders>model;
	private List<Orders>modelMyOrders;
	private String[] ordersStatuses={OrderStatusType.ready_for_shipment.toString(),OrderStatusType.delivering.toString()};
	private List<Orders>filteredOrders;
	private  SelectItem[] ordersStatusesOptions;
	
	Orders selectedOrder=null;
	Staff currentStaff;
	
	@PostConstruct
	public void init() throws IOException{
		
		if (login.getCurrentUser()==null)
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		else if ((login.getCurrentUser().getCustomer()==null)&&(login.getCurrentUser().getStaff().getAuthorities()!=StaffRoles.Role_DeliveryStaff)){
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
		
		
		
		currentStaff=login.getCurrentUser().getStaff();
		model=orderService.getListForDeliverySortedByTimeStamp();
		for (Orders o:model)o.setOrderDishes(orderDishService.getAllInOrder(o.getOrder_id()));
		ordersStatusesOptions=this.createFilterOptions(ordersStatuses);
		//////////////////////////////////////////////////////
		modelMyOrders=orderService.getListTakenByStaff(currentStaff);}
		//////////////////////////////////////////////////////
	}
	
	 private SelectItem[] createFilterOptions(String[] data)  {  
	        SelectItem[] options = new SelectItem[data.length + 1];  
	  
	        options[0] = new SelectItem("", "Select");  
	        for(int i = 0; i < data.length; i++) {  
	            options[i + 1] = new SelectItem(data[i], data[i]);  
	        }  
	  
	        return options;  
	    } 
	public DelivHomeAndMyOrders(){
	}


	
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Orders> getModel() {
		return model;
	}
	public void setModel(List<Orders> model) {
		this.model = model;
	}
	public List<Orders> getModelMyOrders() {
		return modelMyOrders;
	}
	public void setModelMyOrders(List<Orders> modelMyOrders) {
		this.modelMyOrders = modelMyOrders;
	}
	public String[] getOrdersStatuses() {
		return ordersStatuses;
	}
	public void setOrdersStatuses(String[] ordersStatuses) {
		this.ordersStatuses = ordersStatuses;
	}
	public List<Orders> getFilteredOrders() {
		return filteredOrders;
	}
	public void setFilteredOrders(List<Orders> filteredOrders) {
		this.filteredOrders = filteredOrders;
	}
	public SelectItem[] getOrdersStatusesOptions() {
		return ordersStatusesOptions;
	}
	public void setOrdersStatusesOptions(SelectItem[] ordersStatusesOptions) {
		this.ordersStatusesOptions = ordersStatusesOptions;
	}
	public Orders getSelectedOrder() {
		return selectedOrder;
	}
	public void setSelectedOrder(Orders selectedOrder) {
		this.selectedOrder = selectedOrder;
	}
	public Staff getCurrentStaff() {
		return currentStaff;
	}
	public void setCurrentStaff(Staff currentStaff) {
		this.currentStaff = currentStaff;
	}
	public String takeForDelivering(Orders order)
	{
		model.remove(order);
		modelMyOrders.add(order);
		order.setStatus(OrderStatusType.delivering);
		order.setDeliveredBy(currentStaff);
		orderService.update(order);
		return "delivMyOrders";
	}
	
	public String reportDelivered(Orders order){
		modelMyOrders.remove(order);
		order.setStatus(OrderStatusType.delivered);
		modelMyOrders.add(order);
		orderService.update(order);
		return "delivMyOrders";
	}

}



