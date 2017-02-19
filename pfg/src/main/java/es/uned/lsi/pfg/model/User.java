/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Carlos Navalon Urrea
 * Entidad de usuario
 */
@Entity
@NamedQueries({
	@NamedQuery(name="findUserById", query="SELECT u FROM User u WHERE u.id = :idUser AND u.enabled = 1"),
	@NamedQuery(name="findAllUsers", query="SELECT u FROM User u WHERE u.enabled = 1")
})
@Table(name="users")
public class User implements Serializable{
	private static final long serialVersionUID = 192548048099062875L;
	/** Queries **/
	public static final String Q_FIND_BY_ID = "findUserById";
	public static final String Q_FIND_ALL = "findAllUsers";
	
	
	@Id
	private String id;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private String name;
	@Column(name="surname_1", nullable=false)
	private String surname1;
	@Column(name="surname_2")
	private String surname2;
	@Column(nullable=false)
	private String role;
	@Column(nullable=false)
	private String sex;
	private String email;
	@Column(nullable=false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthdate;
	private String address;
	private String city;
	private String province;
	private String cp;
	@Column(name="telephone_1")
	private String telephone1;
	@Column(name="telephone_2")
	private String telephone2;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled;
	
	
	/**
	 * Obtiene el id del usuario
	 * @return el id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Establece el id del usuario
	 * @param id el id del usuario
	 */
	public void setId(String id) {
		this.id = id;
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
	 * Obtiene el perfil del usuario
	 * @return el perfil
	 */
	public String getRole() {
		return role;
	}
	/**
	 * Establece el perfil del usuario
	 * @param role el perfil
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * Obtiene el sexo del usuario
	 * @return el sexo
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * Estableace el sexo del usuario
	 * @param sex el sexo
	 */
	public void setSex(String sex) {
		this.sex = sex;
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
	 * Obtiene la fecha de nacimiento
	 * @return fecha de nacimiento
	 */
	public Date getBirthdate() {
		return birthdate;
	}
	/**
	 * Establece la fecha de nacimiento
	 * @param birthdate fecha de nacimiento
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	/**
	 * Obtiene la direccion del usuario
	 * @return la direccion
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Establece la direccion del usuario
	 * @param address la direccion
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Obtiene la ciudad del usuario
	 * @return la ciudad
	 */
	public String getCity() {
		return city;
	}
	/**
	 * Establece la ciudad del usuario
	 * @param city la ciudad
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * Obtiene la provincia del usuario
	 * @return la provincia
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * Establece la provincia del usuario
	 * @param province la provincia
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * Obtiene el codigo postal del usuario
	 * @return el codigo postal
	 */
	public String getCp() {
		return cp;
	}
	/**
	 * Establece el codigo postal del usuario
	 * @param cp el codigo postal
	 */
	public void setCp(String cp) {
		this.cp = cp;
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
	 * Obtiene el indicador de habilitacion del usuario
	 * @return <code>true</code> si esta activo, en caso contrario <code>false</code>
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * Establece el indicador de habilitacion del usuario
	 * @param enabled el indicador de habilitación
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", surname1=" + surname1 + ", surname2="
				+ surname2 + ", role=" + role + ", sex=" + sex + ", email=" + email + ", birthdate=" + birthdate
				+ ", address=" + address + ", city=" + city + ", province=" + province + ", cp=" + cp + ", telephone1="
				+ telephone1 + ", telephone2=" + telephone2 + ", enabled=" + enabled + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((cp == null) ? 0 : cp.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
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
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (cp == null) {
			if (other.cp != null)
				return false;
		} else if (!cp.equals(other.cp))
			return false;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
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
