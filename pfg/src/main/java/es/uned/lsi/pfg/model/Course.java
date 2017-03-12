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

@NamedQueries({
	@NamedQuery(name="findAllCourses", query="SELECT x FROM Course x"),
	@NamedQuery(name="findCourseById", query="SELECT x FROM Course x WHERE x.idCourse = :id")
})

/**
 * Entidad de curso
 * @author Carlos Navalon Urrea
 *
 */

@Entity
@Table(name="courses")
public class Course implements Serializable {

	private static final long serialVersionUID = 6380289958885448566L;
	
	/** Queries **/
	public static final String Q_FIND_BY_ID = "findCourseById";
	public static final String Q_FIND_ALL = "findAllCourses";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_course")
	private Integer idCourse;
	@Column(nullable=false, length=50)
	private String stage;
	@Column(nullable=false)
	private Integer level;
	@Column(nullable=false, length=255)
	private String name;
	
	/**
	 * Obtiene el id del curso
	 * @return id del curso
	 */
	public Integer getIdCourse() {
		return idCourse;
	}
	/**
	 * Establece el id del curso
	 * @param idCourse id del curso
	 */
	public void setIdCourse(Integer idCourse) {
		this.idCourse = idCourse;
	}
	/**
	 * Obtiene la etapa del curso
	 * @return la etapa del curso
	 */
	public String getStage() {
		return stage;
	}
	/**
	 * Establece la etapa del curso
	 * @param stage la etapa del curso
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}
	/**
	 * Obtiene el nivel del curso
	 * @return el nivel del curso
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * Establece el nivel del curso
	 * @param level el nivel del curso
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * Obtiene el nombre del curso
	 * @return el nombre del curso
	 */
	public String getName() {
		return name;
	}
	/**
	 * Establece el nombre del curso
	 * @param name el nombre del curso
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Course [idCourse=" + idCourse + ", stage=" + stage + ", level=" + level + ", name=" + name + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCourse == null) ? 0 : idCourse.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
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
		Course other = (Course) obj;
		if (idCourse == null) {
			if (other.idCourse != null)
				return false;
		} else if (!idCourse.equals(other.idCourse))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		return true;
	}
	
}
