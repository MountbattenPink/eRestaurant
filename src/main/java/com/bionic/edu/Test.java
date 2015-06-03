package com.bionic.edu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bionic.edu.service.CategoryService;

public class Test {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
	      ApplicationContext context = new 	ClassPathXmlApplicationContext("spring/application-config.xml");
  CategoryService categoryService =   
	       (CategoryService)context.getBean("categoryServiceImpl");
	    System.out.println(categoryService.getAll());
}
	}
