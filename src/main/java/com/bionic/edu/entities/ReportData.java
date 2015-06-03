package com.bionic.edu.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.temporal.ChronoField;

public class ReportData {
	private Category category;
	private Date timestamp;
	private Long amount;
	private Double sum;
	
	public ReportData(Category category, long amount, double sum) {
		super();
		this.category = category;
		this.amount = amount;
		this.sum = sum;
	}
	
	
	public ReportData(Category category) {
		super();
		this.category = category;
	}


	public ReportData(Category category, Date timestamp, long amount,
			double sum) {
		super();
		this.category = category;
		this.timestamp = timestamp;
		this.amount = amount;
		this.sum = sum;
	}

	public ReportData(Date timestamp, long amount,
			double sum) {
		super();
		this.timestamp = timestamp;
		this.amount = amount;
		this.sum = sum;
	}

	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public ReportData(long amount, double sum){
		this.amount = amount;
		this.sum = sum;
	}
	
	

	public ReportData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	@Override
	public String toString() {
		return "ReportData [category="  +category + " TimeStamp= "+timestamp 
				+ ", amount=" + amount + ", sum=" + sum + "]";
	}

	
	
}

