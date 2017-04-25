package com.gfk.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebIntializer implements WebApplicationInitializer  {

	public void onStartup(ServletContext ctx) throws ServletException {
		WebApplicationContext context = getContext();
		ctx.addListener(new ContextLoaderListener(context));
		
		ServletRegistration.Dynamic dispatcher = ctx.addServlet("DispatcherServlet",
									new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		/*dispatcher.addMapping("*.html");
		dispatcher.addMapping("*.json");
		dispatcher.addMapping("*.jsp");*/
	}
	
	private AnnotationConfigWebApplicationContext getContext(){
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.setConfigLocation("com.gfk.config.WebConfig");
		return ctx;
	}
}
