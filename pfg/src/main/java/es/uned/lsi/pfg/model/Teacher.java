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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Entidad de profesores
 * @author Carlos Navalon Urrea
 */
@NamedQueries({
	@NamedQuery(name="findTeacherById", query="SELECT x FROM Teacher x WHERE x.id = :id AND x.enabled = 1"),
	@NamedQuery(name="findAllTeachers", query="SELECT x FROM Teacher x WHERE x.enabled = 1"),
	@NamedQuery(name="findTeacherByIdUser", query="SELECT x FROM Teacher x WHERE x.idUser = :idUser AND x.enabled = 1")
})

@Entity
@Table(name="teachers")
public class Teacher extends Person implements Serializable {

	private static final long serialVersionUID = -7657513873476928238L;
	
	/** Queries **/
	public static final String Q_FIND_BY_ID = "findTeacherById";
	public static final String Q_FIND_ALL = "findAllTeachers";
	public static final String Q_FIND_BY_ID_USER = "findTeacherByIdUser";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_teacher")
	private Integer id;
	@Column(name="id_user", length=20)
	private String idUser;
	@Column(nullable=false, length=50)
	private String name;
	@Column(name="surname_1", nullable=false, length=50)
	private String surname1;
	@Column(name="surname_2", length=50)
	private String surname2;
	@Column(length=70)
	private String email;
	@Column(name="telephone_1", length=9)
	private String telephone1;
	@Column(name="telephone_2", length=9)
	private String telephone2;		
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled;
	
	/**
	 * Obtiene el id del profesor
	 * @return id del profesor
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Establece el id del profesor
	 * @param id id del profesor
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Obtiene el id del usuario
	 * @return el id del usuario
	 */
	public String getIdUser() {
		return idUser;
	}
	
	/**
	 * Establece el id de usuario
	 * @param id el id de usuario
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	/**
	 * Obtiene el nombre del usuario
	 * @return el nombre
	 */
	public String getName() {
		return name;
	}
	/**
	 * Establece el nombre del usuario
	 * @param name el nombre
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Obtiene el primer apellido del usuario
	 * @return el primer apellido
	 */
	public String getSurname1() {
		return surname1;
	}
	/**
	 * Establece el primer apellido del usuario
	 * @param surname1 el primer apellido
	 */
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}
	/**
	 * Obtiene el segundo apellido del usuario
	 * @return el segundo apellido
	 */
	public String getSurname2() {
		return surname2;
	}
	/**
	 * Establece el segundo apellido del usuario
	 * @param surname2 el segundo apellido
	 */
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}
	/**
	 * Obtiene el correo electronico del usuario
	 * @return el correo electronico
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Establece el correo electronico del usuario
	 * @param email el correo electronico
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Obtiene el primer numero de telefono de contacto del usuario
	 * @return el numero de telefono
	 */
	public String getTelephone1() {
		return telephone1;
	}
	/**
	 * Establece el primer numero de telefono de contacto del usuario
	 * @param telephone1 el numero de telefono
	 */
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	/**
	 * Obtiene el segundo numero de telefono de contacto del usuario
	 * @return el numero de telefono
	 */
	public String getTelephone2() {
		return telephone2;
	}
	/**
	 * Establece el segundo numero de telefono de contacto del usuario
	 * @param telephone2 el numero de telefono
	 */
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}	
		/**
	 * Obtiene el estado
	 * @return the enabled estado
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * Establece el  estado 
	 * @param enabled estado
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public String getQueryFindById() {
		return Q_FIND_BY_ID;
	}
	@Override
	public String getQueryFindAll() {
		return Q_FIND_ALL;
	}
	@Override
	public String getQueryFindByIdUser() {
		return Q_FIND_BY_ID_USER;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Teacher [idTeacher=" + id + ", idUser=" + idUser + ", name=" + name + ", surname1=" + surname1
				+ ", surname2=" + surname2 + ", email=" + email + ", telephone1=" + telephone1 + ", telephone2="
				+ telephone2 + ", enabled=" + enabled + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname1 == null) ? 0 : surname1.hashCode());
		result = prime * result + ((surname2 == null) ? 0 : surname2.hashCode());
		result = prime * result + ((telephone1 == null) ? 0 : telephone1.hashCode());
		result = prime * result + ((telephone2 == null) ? 0 : telephone2.hashCode());
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
		Teacher other = (Teacher) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname1 == null) {
			if (other.surname1 != null)
				return false;
		} else if (!surname1.equals(other.surname1))
			return false;
		if (surname2 == null) {
			if (other.surname2 != null)
				return false;
		} else if (!surname2.equals(other.surname2))
			return false;
		if (telephone1 == null) {
			if (other.telephone1 != null)
				return false;
		} else if (!telephone1.equals(other.telephone1))
			return false;
		if (telephone2 == null) {
			if (other.telephone2 != null)
				return false;
		} else if (!telephone2.equals(other.telephone2))
			return false;
		return true;
	}
	
}
