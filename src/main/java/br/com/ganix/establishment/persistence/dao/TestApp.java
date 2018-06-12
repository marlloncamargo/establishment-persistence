package br.com.ganix.establishment.persistence.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApp {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/establishment-persistence/Application-Context.xml");
		
		EstablishmentDAO ed  = (EstablishmentDAO) applicationContext.getBean("establishmentDAO");
		ed.getListEstablishmentMap();
	}
}
