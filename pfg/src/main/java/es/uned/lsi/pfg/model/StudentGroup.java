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
 * Entidad de la tabla relacional estudiante-clase
 * @author Carlos Navalon Urrea
 *
 */
@Entity
@Table(name="student_group")
public class StudentGroup implements Serializable {

	private static final long serialVersionUID = -8219442500732735339L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_student_group")
	private Integer id;
	@Column(name="id_student", nullable=false)
	private Integer student;
	@Column(name="id_group", nullable=false)
	private Integer group;
	
	/**
	 * Obtiene el id
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Establece el id
	 * @param id id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Obtiene el id de estudiante
	 * @return id de estudiante
	 */
	public Integer getStudent() {
		return student;
	}
	/**
	 * Establece el id de estudiante
	 * @param student id de estudiante
	 */
	public void setStudent(Integer student) {
		this.student = student;
	}
	/**
	 * Obtiene el id de clase
	 * @return id de clase
	 */
	public Integer getGroup() {
		return group;
	}
	/**
	 * Establece el id de clase
	 * @param group id de clase
	 */
	public void setGroup(Integer group) {
		this.group = group;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentGroup [id=" + id + ", student=" + student + ", group=" + group + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		StudentGroup other = (StudentGroup) obj;
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
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

}
