/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

/**
 * Entidad de usuario
 * @author Carlos Navalon Urrea
 */
@NamedQueries({
	@NamedQuery(name="findUserById", query="SELECT u FROM User u WHERE u.idUser = :id AND u.enabled = 1"),
	@NamedQuery(name="findAllUsers", query="SELECT u FROM User u WHERE u.enabled =1")
})
@Entity
@Table(name="users")
public class User implements Serializable{
	private static final long serialVersionUID = 192548048099062875L;
	/** Queries **/
	public static final String Q_FIND_BY_ID = "findUserById";
	public static final String Q_FIND_ALL = "findAllUsers";
	
	@Id
	@Column(name="id_user")
	private String idUser;
	@Column(nullable=false)
	private String password;
	@Column(name="id_role", nullable=false)
	private String role;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled;
	
	@Transient
	private String lastPassword;
	
	/**
	 * Constructor
	 */
	public User(){
		this.enabled = true;
	}
	
	/**
	 * Obtiene el id de usuario
	 * @return id de usuario
	 */
	public String getIdUser() {
		return idUser;
	}
	/**
	 * Establece el id de usuario
	 * @param id el id nombre de usuario
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	/**
	 * Obtiene la contraseña
	 * @return la contraseña
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Establece la contraseña
	 * @param password la contraseña
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Obtiene el ID perfil
	 * @return ID perfil
	 */
	public String getRole() {
		return role;
	}
	/**
	 * Establece el ID perfil
	 * @param role ID perfil
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * Obtiene el estado
	 * @return estado
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * Establece el estado 
	 * @param enabled estado
	 */
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}
	/**
	 * Obtiene contraseña anterior
	 * @return contraseña anterior
	 */
	public String getLastPassword() {
		return lastPassword;
	}

	/**
	 * Establece contraseña anterior
	 * @param lastPassword contraseña anterior
	 */
	public void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", password=" + password + ", role=" + role + ", enabled=" + enabled
				+ ", lastPassword=" + lastPassword + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((lastPassword == null) ? 0 : lastPassword.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (enabled != other.enabled)
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		if (lastPassword == null) {
			if (other.lastPassword != null)
				return false;
		} else if (!lastPassword.equals(other.lastPassword))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

}


