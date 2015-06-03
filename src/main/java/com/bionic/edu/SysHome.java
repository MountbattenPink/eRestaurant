package com.bionic.edu;
import javax.annotation.PostConstruct;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.bionic.edu.entities.Category;
import com.bionic.edu.entities.ReportData;
import com.bionic.edu.entities.StaffRoles;
import com.bionic.edu.service.OrderService;
 

@Named("sysHome")
@Scope("request")
public class SysHome implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private java.util.Date startUtil;
	private java.util.Date finishUtil;

	@Autowired
	private OrderService orderService;
	private List<ReportData> reportModel;
 
	@Autowired
	private Login login;
	
	
	
	private Date start;
	private Date finish;
	private ArrayList<ReportData> reportTableData=new ArrayList<ReportData>();
	private LineChartModel dateModel;
	private LineChartModel dateModelSumm;
	private BarChartModel areaModel;
	private BarChartModel areaModelAmount;
	 private PieChartModel pieModelAmount;
	    private PieChartModel pieModeSum;
	   private int wholeAmount=0;
	   private int wholeSum=0;
	 
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public int getWholeAmount() {
		return wholeAmount;
	}

	public void setWholeAmount(int wholeAmount) {
		this.wholeAmount = wholeAmount;
	}

	public int getWholeSum() {
		return wholeSum;
	}

	public void setWholeSum(int wholeSum) {
		this.wholeSum = wholeSum;
	}

	public PieChartModel getPieModelAmount() {
			return pieModelAmount;
		}

		public void setPieModelAmount(PieChartModel pieModelAmount) {
			this.pieModelAmount = pieModelAmount;
		}

		public PieChartModel getPieModeSum() {
			return pieModeSum;
		}

		public void setPieModeSum(PieChartModel pieModeSum) {
			this.pieModeSum = pieModeSum;
		}

	public BarChartModel getAreaModelAmount() {
		return areaModelAmount;
	}

	public void setAreaModelAmount(BarChartModel areaModelAmount) {
		this.areaModelAmount = areaModelAmount;
	}

	public SysHome() {
		
	}
	
	 @PostConstruct
	 public void init() throws IOException {
		 startUtil=Date.valueOf(LocalDate.now().minusWeeks(3));
		 finishUtil=new Date(System.currentTimeMillis());
		 finish=Date.valueOf(LocalDate.now());
		 start=Date.valueOf(LocalDate.now().minusWeeks(3));
		 reportModel=orderService.makeReport(start, finish);
		 System.out.println("init");
		 if (reportModel.size()>0){
		  createDateModel();
	     createDateModelSumm();
	     createWholeModel();
	     createWholeModelAmount();
	     createCategoryModelSum();
	     createCategoryModelAmount();
	     cteateTableData();
	 	 }
		 else {
				FacesMessage msg = new FacesMessage("No data during this period!");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		 }
			
	 if (login.getCurrentUser()==null)
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		else if ((login.getCurrentUser().getCustomer()==null)&&(login.getCurrentUser().getStaff().getAuthorities()!=StaffRoles.Role_SysAnalytic)){
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
	 }}

	private void createCategoryModelAmount() {
		pieModelAmount= new PieChartModel();
		 Map<String, Number>mapAmount=new HashMap<String, Number>();
	     for (ReportData rd:reportModel){
	    	if (!mapAmount.containsKey(rd.getCategory())){
	    		mapAmount.put(rd.getCategory().getName(), rd.getAmount());
	    	}else 
	    		{
	    		mapAmount.replace(rd.getCategory().getName(), mapAmount.get(rd.getCategory().getName()).intValue()+rd.getAmount());
	    		
	    		}
	    }
	     for (String k:mapAmount.keySet()){
	    	 pieModelAmount.set(k+": "+mapAmount.get(k), mapAmount.get(k));
	     }
	    System.out.println(mapAmount);
	   // pieModelAmount.setData(mapAmount);
	    pieModelAmount.setTitle("Amount");
	    pieModelAmount.setLegendPosition("ne");
	    pieModelAmount.setMouseoverHighlight(true);
	    pieModelAmount.setShowDataLabels(true);

		
	}

	private void createCategoryModelSum() {
		pieModeSum= new PieChartModel();
		 Map<String, Number>mapSum=new HashMap<String, Number>();
	     for (ReportData rd:reportModel){
	    	if (!mapSum.containsKey(rd.getCategory())){
	    		mapSum.put(rd.getCategory().getName(), rd.getSum());
	    	}else 
	    		{
	    		mapSum.replace(rd.getCategory().getName(), mapSum.get(rd.getCategory().getName()).intValue()+rd.getSum());
	    		
	    		}
	    }
	     for (String k:mapSum.keySet()){
	    	 pieModeSum.set(k+": "+mapSum.get(k), mapSum.get(k));
	     }
	    System.out.println(mapSum);
	   // pieModeSum.setData(mapSum);
	    pieModeSum.setTitle("Sum of Income");
	    pieModeSum.setLegendPosition("ne");
	    pieModeSum.setMouseoverHighlight(true);
	    pieModeSum.setShowDataLabels(true);
	    
	    
	}

	public void cteateTableData(){
		wholeAmount=0;
		wholeSum=0;
		String currentCategory="";
		reportTableData=new ArrayList<ReportData>();
		for (ReportData r:reportModel){
			if (!currentCategory.equals(r.getCategory().getName())){
				reportTableData.add(new ReportData(r.getCategory()));
				reportTableData.add(new ReportData(r.getTimestamp(),r.getAmount(),r.getSum()));		
				wholeAmount+=r.getAmount();
				wholeSum+=r.getSum();
				currentCategory=r.getCategory().getName();
				
			}
			else {
				reportTableData.add(new ReportData(r.getTimestamp(),r.getAmount(),r.getSum()));		
				wholeAmount+=r.getAmount();
				wholeSum+=r.getSum();
				
			}
		}
		
	}
	
	
	public BarChartModel getAreaModel() {
		return areaModel;
	}

	public void setAreaModel(BarChartModel areaModel) {
		this.areaModel = areaModel;
	}

	public ArrayList<ReportData> getReportTableData() {
		return reportTableData;
	}


	public void setReportTableData(ArrayList<ReportData> reportTableData) {
		this.reportTableData = reportTableData;
	}


	public java.util.Date getStartUtil() {
		return startUtil;
	}

	public void setStartUtil(java.util.Date startUtil) {
		this.startUtil = startUtil;
	}

	public java.util.Date getFinishUtil() {
		return finishUtil;
	}

	public void setFinishUtil(java.util.Date finishUtil) {
		this.finishUtil = finishUtil;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getFinish() {
		return finish;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}
	

public List<ReportData> getReportModel() {
	return reportModel;
}

public void setReportModel(List<ReportData> reportModel) {
	this.reportModel = reportModel;
}

 


 public LineChartModel getDateModel() {
	   return dateModel;
 }

 
 
 public LineChartModel getDateModelSumm() {
	return dateModelSumm;
}

 private void createWholeModel() {
	areaModel= new BarChartModel();
	 ChartSeries sum = new ChartSeries();
	  sum.setLabel("Sum");
     Map<Object, Number>mapSum=new TreeMap<Object, Number>();
     for (ReportData rd:reportModel){
    	if (!mapSum.containsKey(rd.getTimestamp().toString())){
    		mapSum.put(rd.getTimestamp().toString(), rd.getSum());
    	}else 
    		{
    		mapSum.replace(rd.getTimestamp().toString(), mapSum.get(rd.getTimestamp().toString()).intValue()+rd.getSum());
    		
    		}
    }
    System.out.println(mapSum);
    sum.setData(mapSum);
     areaModel.addSeries(sum);
    areaModel.setTitle("Sum of Income");
    areaModel.setLegendPosition("ne");
    areaModel.setStacked(true);
    areaModel.setShowPointLabels(true);
     
    Axis xAxis = new CategoryAxis("Dates");
    areaModel.getAxes().put(AxisType.X, xAxis);
    xAxis.setMax(finish.toString());
    xAxis.setTickAngle(-70);
    Axis yAxis = areaModel.getAxis(AxisType.Y);
    yAxis.setLabel("Sum");
    yAxis.setMin(0);
    Number maxn=0;
    for (Number n:mapSum.values())if (maxn.doubleValue()+100<n.doubleValue())maxn=n;
    yAxis.setMin(0);
    yAxis.setMax(maxn);

}
 
 
 
 private void createWholeModelAmount() {
	areaModelAmount= new BarChartModel();
	 ChartSeries amount = new ChartSeries();	    
      amount.setLabel("Amount");   
     Map<Object, Number>mapAmount=new TreeMap<Object, Number>();
    for (ReportData rd:reportModel){
    	if (!mapAmount.containsKey(rd.getTimestamp().toString())){
    		mapAmount.put(rd.getTimestamp().toString(), rd.getAmount().intValue());
    	}else 
    		{
    		mapAmount.replace(rd.getTimestamp().toString(), mapAmount.get(rd.getTimestamp().toString()).intValue()+rd.getAmount().intValue());
    		
    		}
    }
    System.out.println(mapAmount);
    amount.setData(mapAmount);
    areaModelAmount.addSeries(amount);
    areaModelAmount.setTitle("Amount of sold dishes");
    areaModelAmount.setLegendPosition("ne");
    areaModelAmount.setStacked(true);
    areaModelAmount.setShowPointLabels(true);
     
    Axis xAxis = new CategoryAxis("Dates");
    areaModelAmount.getAxes().put(AxisType.X, xAxis);
    xAxis.setMax(finish.toString());
    xAxis.setTickAngle(-70);
    Axis yAxis = areaModelAmount.getAxis(AxisType.Y);
    yAxis.setLabel("Amount");
    yAxis.setMin(0);
    Number maxn=0;
    for (Number n:mapAmount.values())if (maxn.intValue()+10<n.intValue())maxn=n.intValue()+10;
    yAxis.setMax(maxn);

}
 
 
private void createDateModel() {
	 
	 dateModel = new LineChartModel();
     ArrayList <LineChartSeries> series=new ArrayList<LineChartSeries>();
     LineChartSeries serie = new LineChartSeries();
	 serie.setLabel(reportModel.get(0).getCategory().getName());
	 serie.set(reportModel.get(0).getTimestamp().toString(), reportModel.get(0).getAmount());
	 series.add(serie);
     for (int i=1;i<reportModel.size();i++)
     {
    	  if (reportModel.get(i).getCategory().getName().equals(series.get(series.size()-1).getLabel()))
    		 {
    			 series.get(series.size()-1)
    			 .set(reportModel.get(i).getTimestamp().toString(), reportModel.get(i).getAmount());
    		 }
    	 
    	 else{
    		  serie = new LineChartSeries();
    	 serie.setLabel(reportModel.get(i).getCategory().getName());
    	 serie.set(reportModel.get(i).getTimestamp().toString(), reportModel.get(i).getAmount());
    	 series.add(serie);}
     }
     
     for (LineChartSeries s:series){
    	 dateModel.addSeries(s);
     }
       
     dateModel.setTitle("Amount of sold dishes");
     dateModel.setZoom(true);
     dateModel.getAxis(AxisType.Y).setLabel("Amount");
     dateModel.setLegendPosition("ne");
     DateAxis axis = new DateAxis("Dates");
     axis.setTickAngle(-70);
     axis.setMax(finish.toString());
     axis.setTickFormat("%#d %b  %y");
      
     dateModel.getAxes().put(AxisType.X, axis);

}



private void createDateModelSumm() {
	 
	 dateModelSumm = new LineChartModel();
    ArrayList <LineChartSeries> series=new ArrayList<LineChartSeries>();
    LineChartSeries serie = new LineChartSeries();
	 serie.setLabel(reportModel.get(0).getCategory().getName());
	 serie.set(reportModel.get(0).getTimestamp().toString(), reportModel.get(0).getSum());
	 series.add(serie);
    for (int i=1;i<reportModel.size();i++)
    {
   	  if (reportModel.get(i).getCategory().getName().equals(series.get(series.size()-1).getLabel()))
   		 {
   			 series.get(series.size()-1)
   			 .set(reportModel.get(i).getTimestamp().toString(), (double)reportModel.get(i).getSum());
   		 }
   	 
   	 else{
   		  serie = new LineChartSeries();
   	 serie.setLabel(reportModel.get(i).getCategory().getName());
   	serie.set(reportModel.get(i).getTimestamp().toString(), reportModel.get(i).getSum());
   	 series.add(serie);}
    }
    
    for (LineChartSeries s:series){
    
   	 dateModelSumm.addSeries(s);
    }
      
    dateModelSumm.setTitle("Summ of income");
    dateModelSumm.setZoom(true);
    dateModelSumm.getAxis(AxisType.Y).setLabel("Summ, hrn");
    dateModelSumm.setLegendPosition("ne");
    DateAxis axis = new DateAxis("Dates");
    axis.setTickAngle(-70);
    axis.setMax(finish.toString());
    axis.setTickFormat("%#d %b  %y");
     
    dateModelSumm.getAxes().put(AxisType.X, axis);
}

public void init1(){
	start=new java.sql.Date(startUtil.getTime());
	finish=new java.sql.Date(finishUtil.getTime());
	reportModel=orderService.makeReport(start, finish);
	System.out.println(start + " "+ finish);
	 if (reportModel.size()>0){ 
	createDateModel();
     createDateModelSumm();
     createWholeModel();
     createWholeModelAmount();
     createCategoryModelSum();
     createCategoryModelAmount();
     cteateTableData();
     RequestContext.getCurrentInstance().update("table:tbl");
     
     for (ReportData rd:reportModel){
    	 System.out.println(rd);
     }} else {
			FacesMessage msg = new FacesMessage("No data during this period!");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		
	 }
     
}

}