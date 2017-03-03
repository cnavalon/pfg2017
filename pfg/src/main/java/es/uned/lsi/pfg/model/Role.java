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

/**
 * Entidad de perfil
 * @author Carlos Navalon Urrea
 */
@Entity
@NamedQueries({
	@NamedQuery(name="findAllRoles", query="SELECT r FROM Role r"),
	@NamedQuery(name="findRollesByListIds", query="SELECT r FROM Role r WHERE r.idRole IN :listIds")
})
@Table(name="roles")
public class Role implements Serializable {
	private static final long serialVersionUID = -1404731829519619871L;
	/** Queries **/
	public static final String Q_FIND_ALL = "findAllRoles";
	public static final String Q_FIND_BY_LIST_IDS = "findRollesByListIds";
	
	@Id
	@Column(name="id_role")
	private String idRole;
	@Column(name="name_role")
	private String name;
	
	/**
	 * Obtiene el id del perfil
	 * @return el id
	 */
	public String getIdRole() {
		return idRole;
	}
	/**
	 * Establece el id perfil
	 * @param idRole el id
	 */
	public void setIdRole(String idRole) {
		this.idRole = idRole;
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
		return "Role [role=" + idRole + ", name=" + name + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
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
		if (idRole == null) {
			if (other.idRole != null)
				return false;
		} else if (!idRole.equals(other.idRole))
			return false;
		return true;
	}
	
}
