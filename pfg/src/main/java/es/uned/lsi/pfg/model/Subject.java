/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad de asignatura
 * @author Carlos Navalon Urrea
 *
 */
@Entity
@Table(name="subjects")
public class Subject implements Serializable {

	private static final long serialVersionUID = -8081470506622326605L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_subject")
	private Integer id;
	@Column(nullable=false, length=20)
	private String code;
	@Column(nullable=false)
	private String name;
	
	/**
	 * Obtiene el id de asignatura
	 * @return id de asignatura
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Establece el id de asignatura
	 * @param id id de asignatura
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Obtiene el codigo de asignatura
	 * @return codigo de asignatura
	 */
	public String getCode() {
		return code;
	}
	/**
	 * Establece el codigo de asignatura
	 * @param code codigo de asignatura
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * Obtiene el nombre de asignatura
	 * @return nombre de asignatura
	 */
	public String getName() {
		return name;
	}
	/**
	 * Establece el nombre de asignatura
	 * @param name nombre de asignatura
	 */
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Subject [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Subject other = (Subject) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
