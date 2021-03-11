package com.example.howtodoinjava.springbootsoapservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class Config extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/service/*");
	}

	@Bean(name = "studentDetailsWsdl")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("StudentDetailsPort");
		wsdl11Definition.setLocationUri("/service/student-details");
		wsdl11Definition.setTargetNamespace("http://www.howtodoinjava.com/xml/school");
		wsdl11Definition.setSchema(schema);
		return wsdl11Definition;
	}
	@Bean
	public XsdSchema studentSchema() {
		return new SimpleXsdSchema(new ClassPathResource("school.xsd"));
	}
	
	
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the  specified in
		// pom.xml
		marshaller.setContextPath("studentCompare.wsdl");
		return marshaller;
	}

	@Bean
	public StudentClient studentClient(Jaxb2Marshaller marshaller) {
		StudentClient client = new StudentClient();
		client.setDefaultUri("http://localhost:8090/service/student-details");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}