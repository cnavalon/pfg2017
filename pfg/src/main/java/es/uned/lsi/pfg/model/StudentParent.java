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
 * Entidad de la tabla relacional student_parent
 * @author Carlos Navalon Urrea
 *
 */
@Entity
@Table(name="student_parent")
public class StudentParent implements Serializable {
	private static final long serialVersionUID = -2688649159105943154L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_student_parent")
	private Integer id;
	@Column(name="id_student", nullable=false)
	private Integer student;
	@Column(name="id_parent", nullable=false)
	private Integer parent;
	
	/**
	 * Constructor
	 */
	public StudentParent(){
		
	}
	
	/**
	 * Constructor
	 * @param student id alumno
	 * @param parent id padre
	 */
	public StudentParent(Integer student, Integer parent){
		this.student = student;
		this.parent = parent;
	}
	
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
	 * Obtiene el id de alumno
	 * @return id de alumno
	 */
	public Integer getStudent() {
		return student;
	}
	/**
	 * Establece el id de alumno
	 * @param student id de alumno
	 */
	public void setStudent(Integer student) {
		this.student = student;
	}
	/**
	 * Obitiene el id de padre
	 * @return id de padre
	 */
	public Integer getParent() {
		return parent;
	}
	/**
	 * Establece el id de padre
	 * @param parent id de padre
	 */
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentParent [id=" + id + ", student=" + student + ", parent=" + parent + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
		StudentParent other = (StudentParent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

}
