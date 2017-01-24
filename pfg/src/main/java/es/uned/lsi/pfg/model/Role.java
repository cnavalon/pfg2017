/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Carlos Navalon Urrea
 * Entidad de perfil
 */
@Entity
@NamedQueries({
	@NamedQuery(name="findAll", query="SELECT r FROM Role r")
})
@Table(name="roles")
public class Role implements Serializable {
	private static final long serialVersionUID = -1404731829519619871L;
	/** Queries **/
	public static final String Q_FIND_ALL = "findAll";
	
	@Id
	private String role;
	private String name;
	
	/**
	 * Obtiene el id del perfil
	 * @return el id
	 */
	public String getRole() {
		return role;
	}
	/**
	 * Establece el id perfil
	 * @param role: el id
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * Obtiene el nombre del perfil
	 * @return el nombre
	 */
	public String getName() {
		return name;
	}
	/**
	 * Establece el nombre del perfil
	 * @param name: el nombre
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [role=" + role + ", name=" + name + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	
}
