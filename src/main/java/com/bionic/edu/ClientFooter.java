package com.bionic.edu;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.context.annotation.Scope;


@Named("clientFooter")
@Scope("session")
public class ClientFooter implements Serializable{

	private static final long serialVersionUID = 1L;
	 private MapModel simpleModel;
	  
	    @PostConstruct
	    public void init() {
	        simpleModel = new DefaultMapModel();
	          
	        //Shared coordinates
	        LatLng coord1 = new LatLng(50.459499, 30.495612);
	          
	        //Basic marker
	        simpleModel.addOverlay(new Marker(coord1, "Main filial"));
	    }
	  
	    public MapModel getSimpleModel() {
	        return simpleModel;
	    }
}

		

		

	