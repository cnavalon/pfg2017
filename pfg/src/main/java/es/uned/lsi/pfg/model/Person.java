package es.uned.lsi.pfg.model;

import javax.persistence.Transient;

/**
 * Clase abastracta que representa una persona
 * @author Carlos Navalon Urrea
 *
 */
public abstract class Person {
	
	@Transient
	protected User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public abstract String getQueryFindById();
	public abstract String getQueryFindAll();
	public abstract String getQueryFindByIdUser();
	
	public abstract Integer getId();
	public abstract String getName();
	public abstract String getSurname1();
	public abstract String getSurname2();
	public abstract String getIdUser();
	public abstract String getEmail();
	public abstract void setEnabled(boolean enable);
	public abstract boolean isEnabled();
	
	
	
}
