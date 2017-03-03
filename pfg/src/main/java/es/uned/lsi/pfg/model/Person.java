package es.uned.lsi.pfg.model;

/**
 * Clase abastracta que representa una persona
 * @author Carlos Navalon Urrea
 *
 */
public abstract class Person {
	
	public abstract String getQueryFindById();
	public abstract String getQueryFindAll();
	public abstract String getQueryFindByIdUser();
	
	public abstract Integer getId();
	public abstract String getName();
	public abstract String getSurname1();
	public abstract String getSurname2();
	public abstract String getIdUser();
	public abstract void setEnabled(boolean enable);
	public abstract boolean isEnabled();
	
	
}
