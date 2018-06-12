package br.com.ganix.establishment.persistence.repository;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRepository {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("/establishment-persistence/application-context.xml");
		EstablishmentRepository repository = (EstablishmentRepository) applicationContext.getBean("establishmentRepository");
		
		repository.findAll();
		
		System.out.println("ok");
		
		
	}
}
