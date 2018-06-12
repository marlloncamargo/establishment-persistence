package br.com.ganix.establishment.persistence.exceptions;

public class GanixEstablishmentPersistenceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2620662864519905611L;
	
	public GanixEstablishmentPersistenceException(String message)
	{
		super("-- GANIX EXCEPTION -- " + message);
	}
}
