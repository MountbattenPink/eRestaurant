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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bionic.edu.entities.Category;
import com.bionic.edu.entities.Customer;
import com.bionic.edu.entities.Dish;
import com.bionic.edu.entities.Orders;
import com.bionic.edu.service.OrderDishService;
import com.bionic.edu.service.OrderService;
import com.bionic.edu.service.StaffService;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;



@Component("clientBucket")
@Scope("session")
public class ClientBucket implements Serializable{

	private static final long serialVersionUID = 1L;

	private Customer currentUser;
	private List<BucketEntry> bucketContent;
	private Dish dish;
	private int amount;
	private String client_address;
	private String currentDialog;
	private int wholeAmount;
	private double wholeSum;
	
	
	@Autowired
	StaffService staffService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDishService orderDishService;
	@Autowired
	Login login;
	
	
	public int getWholeAmount() {
		wholeAmount=0;
		if ((bucketContent!=null)||(bucketContent.size()>0))
		{
			for (BucketEntry be:bucketContent)
			{
				wholeAmount+=be.getAmount();
			}
		}
		return wholeAmount;
	}


	public void setWholeAmount(int wholeAmount) {
		this.wholeAmount = wholeAmount;
	}


	public double getWholeSum() {
		wholeSum=0;
		if ((bucketContent!=null)||(bucketContent.size()>0))
		{
			for (BucketEntry be:bucketContent)
			{
				wholeSum+=be.getAmount()*be.getDish().getPrice();
			}
		}
		return wholeSum;
	}


	public void setWholeSum(double wholeSum) {
		this.wholeSum = wholeSum;
	}


	public Login getLogin() {
		return login;
	}


	public void setLogin(Login login) {
		this.login = login;
	}


	public String getClient_address() {
		return client_address;
	}


	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}


	public String getCurrentDialog() {
		System.out.println(currentUser);
		if (bucketContent.isEmpty())
			currentDialog= "emptyBucket";
		else {
				if (login.getCurrentUser()==null)
					currentDialog= "register";
					else currentDialog= "normal";
				
			}
		return currentDialog;
	}


	public void setCurrentDialog(String currentDialog) {
		this.currentDialog = currentDialog;
	}


	public Customer getCurrentUser() {
		return currentUser;
	}


	public void setCurrentUser(Customer currentUser) {
		this.currentUser = currentUser;
	}


	public List<BucketEntry> getBucketContent() {
		return bucketContent;
	}



	public void setBucketContent(List<BucketEntry> bucketContent) {
		this.bucketContent = bucketContent;
	}


	

	public ClientBucket() {
	}

	@PostConstruct
	public void init() throws IOException {
		wholeAmount=0;
		wholeSum=0;
		this.bucketContent = new ArrayList<BucketEntry>();
		this.dish =new Dish();
		this.amount = 1;
		
		if (login.getCurrentUser()!=null)
			{if (login.getCurrentUser().getCustomer()!=null)
		{this.currentUser= login.getCurrentUser().getCustomer();//new Customer("login", "password", "fullname", "address", null, null);
		this.client_address=login.getCurrentUser().getCustomer().getAddress();//currentUser.getAddress();
		}
			else  if (login.getCurrentUser().getStaff()!=null)
				FacesContext.getCurrentInstance().getExternalContext().redirect(
						PageRedirectionList.pagesByRoles.get(login.getCurrentUser().getStaff().getAuthorities()));
			}
		
	
	}



	public void confirm(Dish d){
		System.out.println(d);
		 dish=d;
			
	}
	
	public void addToBucket(){
		   FacesContext context = FacesContext.getCurrentInstance();
		   bucketContent.add(new BucketEntry(dish, amount));
		   dish=new Dish();
		   amount=1;
	        context.addMessage(null, new FacesMessage("Successfully added",  dish.getName()) );
	 //  for (Dish d: bucketContent.keySet()){System.out.println("/ncontent: "+ d + bucketContent.get(d));}
	}
	
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void remove(BucketEntry be){
		bucketContent.remove(be);
	}
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Item Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
	public void clearBucket() {
		if (login.getCurrentUser()!=null)if (login.getCurrentUser().getCustomer()!=null)this.client_address=login.getCurrentUser().getCustomer().getAddress();
		
		if (this.client_address!=null){
			HashMap <Dish, Double> dishesAndAmount=new HashMap<Dish, Double>();
			for (BucketEntry be:bucketContent){
				dishesAndAmount.put(be.getDish(), (double)be.getAmount());
			}
			orderService.submitByCustomer(client_address, dishesAndAmount);
		/*	if (login!=null){
				if (login.getCurrentUser()!=null)
					if (login.getCurrentUser().getCustomer()!=null){
						String from = "strawberry.dream.restaurant";
		        String pass = "44Morning44";
		        String[] to = { login.getCurrentUser().getCustomer().getLogin() }; // list of recipient email addresses
		        String subject = "Strawberry dream thanks you for your order";
		        String body = "Dear , "+login.getCurrentUser().getCustomer().getFullName()+"\n\n\n Your order :\n";
		        for (BucketEntry b:bucketContent){
		        	body+=b.getDish().getName()+" ("+b.getAmount()+" X "+b.getDish().getPrice()+") \n";
		        }
		        body+= "---------------------------\n\nSUMMARY: "+(int)this.wholeSum+" hrn";
		        sendFromGMail(from, pass, to, subject, body);}
		  	
			}*/
			
			bucketContent=new ArrayList<BucketEntry>();}
	}

	
	 private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
	        Properties props = System.getProperties();
	        String host = "smtp.gmail.com";
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", from);
	        props.put("mail.smtp.password", pass);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");

	        Session session = Session.getDefaultInstance(props);
	        MimeMessage message = new MimeMessage(session);

	        try {
	            message.setFrom(new InternetAddress(from));
	            InternetAddress[] toAddress = new InternetAddress[to.length];

	            // To get the array of addresses
	            for( int i = 0; i < to.length; i++ ) {
	                toAddress[i] = new InternetAddress(to[i]);
	            }

	            for( int i = 0; i < toAddress.length; i++) {
	                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	            }

	            message.setSubject(subject);
	            message.setText(body);
	            Transport transport = session.getTransport("smtp");
	            transport.connect(host, from, pass);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	        }
	        catch (AddressException ae) {
	            ae.printStackTrace();
	        }
	        catch (MessagingException me) {
	            me.printStackTrace();
	        }
	    }
	
	
}

		

		

	