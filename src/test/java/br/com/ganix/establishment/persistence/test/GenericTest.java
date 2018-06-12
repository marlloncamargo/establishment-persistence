package br.com.ganix.establishment.persistence.test;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GenericTest {
	protected ApplicationContext applicationContext;
	private DateFormat format;
	
	@Before
	public void load(){
		applicationContext = new ClassPathXmlApplicationContext("/contacts-persistence/Application-Context.xml");
	}

	@After
	public void unload() {
		applicationContext = null;
	}
	
	protected Date getSqlDate(String stringDate) throws ParseException{
		format = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date Data = format.parse(stringDate);
		return new Date(Data.getTime());
	}
}
