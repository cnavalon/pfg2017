/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Carlos Navalon Urrea
 * Entidad de estudiante
 */
@NamedQueries({
	@NamedQuery(name="findStudentById", query="SELECT x FROM Student x WHERE x.id = :id AND x.enabled = 1"),
	@NamedQuery(name="findAllStudents", query="SELECT x FROM Student x WHERE x.enabled = 1"),
	@NamedQuery(name="findStudentByIdUser", query="SELECT x FROM Student x WHERE x.idUser = :idUser AND x.enabled = 1"),
	@NamedQuery(name="findStudentByCourse", query="SELECT x FROM Student x WHERE x.course = :course AND x.enabled = 1"),
	@NamedQuery(name="findStudentByCourseWithoutGroup", query="SELECT x FROM Student x WHERE x.course = :course AND x.group IS NULL AND x.enabled = 1"),
	@NamedQuery(name="countStudentsByGroup", query="SELECT COUNT(x)FROM Student x WHERE x.group = :group AND x.enabled = 1"),
	@NamedQuery(name="findStudentsByGroup", query="SELECT x FROM Student x WHERE x.group = :group AND x.enabled = 1"),
	@NamedQuery(name="updateGroup", query="UPDATE Student x SET x.group = :group WHERE x.id IN :lstIds"),
	@NamedQuery(name="removeGroup", query="UPDATE Student x SET x.group = NULL WHERE x.group = :group")
})

@Entity
@Table(name="students")
public class Student extends Person implements Serializable {

	private static final long serialVersionUID = -5927105150496985513L;
	
	/** Queries **/
	public static final String Q_FIND_BY_ID = "findStudentById";
	public static final String Q_FIND_ALL = "findAllStudents";
	public static final String Q_FIND_BY_ID_USER = "findStudentByIdUser";
	public static final String Q_FIND_BY_COURSE = "findStudentByCourse";
	public static final String Q_FIND_BY_COURSE_WITHOUT_GROUP = "findStudentByCourseWithoutGroup";
	public static final String Q_COUNT_BY_GROUP = "countStudentsByGroup";
	public static final String Q_FIND_BY_GROUP = "findStudentsByGroup";
	public static final String Q_UPDATE_GROUP = "updateGroup";
	public static final String Q_REMOVE_GROUP = "removeGroup";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_student")
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
	@Column(name="dni_type", length=3)
	private String dniType;	
	@Column(length=9)
	private String dni;	
	@Column(nullable=false, length=1)
	private String sex;
	@Column(nullable=false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthdate;
	@Column(length=255)
	private String address;
	@Column(length=50)
	private String city;
	@Column(length=25)
	private String province;
	@Column(length=5)
	private String cp;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled;
	private Integer course;
	@Column(name="_group")
	private Integer group;
	
	/**
	 * Obtiene el id del estudiante
	 * @return el id del estudiante
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Establece el id de estudiante
	 * @param id id de estudiante
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
	 * @param id id de usuario
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	/**
	 * Obtiene el nombre del usuario
	 * @return el nombre del usuario
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
	 * Obtiene el tipo de documento
	 * @return the tipo de documento
	 */
	public String getDniType() {
		return dniType;
	}
	/**
	 * Establece el tipo de documento
	 * @param dniType tipo de documento
	 */
	public void setDniType(String dniType) {
		this.dniType = dniType;
	}
	/**
	 * Obitiene el dni
	 * @return el dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * Establece el dni
	 * @param dni el dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
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
	 * Obtiene el estado
	 * @return the enabled estado
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * Establece el estado 
	 * @param enabled estado
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * Obtiene el curso
	 * @return el curso
	 */
	public Integer getCourse() {
		return course;
	}
	/**
	 * Establece el curso
	 * @param course el curso
	 */
	public void setCourse(Integer course) {
		this.course = course;
	}
	/**
	 * Obtiene la clase
	 * @return la clase
	 */
	public Integer getGroup() {
		return group;
	}
	/**
	 * Establece la clase
	 * @param group la clase
	 */
	public void setGroup(Integer group) {
		this.group = group;
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
		return "Student [id=" + id + ", idUser=" + idUser + ", name=" + name + ", surname1=" + surname1 + ", surname2="
				+ surname2 + ", email=" + email + ", telephone1=" + telephone1 + ", telephone2=" + telephone2
				+ ", dniType=" + dniType + ", dni=" + dni + ", sex=" + sex + ", birthdate=" + birthdate + ", address="
				+ address + ", city=" + city + ", province=" + province + ", cp=" + cp + ", enabled=" + enabled
				+ ", course=" + course + ", group=" + group + "]";
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
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((cp == null) ? 0 : cp.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((dniType == null) ? 0 : dniType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
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
		Student other = (Student) obj;
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
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (cp == null) {
			if (other.cp != null)
				return false;
		} else if (!cp.equals(other.cp))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (dniType == null) {
			if (other.dniType != null)
				return false;
		} else if (!dniType.equals(other.dniType))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
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
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
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
