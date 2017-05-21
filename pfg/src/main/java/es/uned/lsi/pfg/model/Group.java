/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Entidad de clases
 * @author Carlos Navalon Urrea
 *
 */
@NamedQueries({
	@NamedQuery(name="findGroupById", query="SELECT x FROM Group x WHERE x.id = :id AND x.enabled = 1"),
	@NamedQuery(name="findAllGroups", query="SELECT x FROM Group x WHERE x.enabled = 1"),
	@NamedQuery(name="findGroupByCourseAndLetter", query="SELECT x FROM Group x WHERE x.course.idCourse = :course AND x.letter = :letter AND x.enabled = 1"),
	@NamedQuery(name="findGroupByCourse", query="SELECT x FROM Group x WHERE x.course.idCourse = :course AND x.enabled = 1"),
	@NamedQuery(name="findGroupByTutor", query="SELECT x FROM Group x WHERE x.tutor.id = :tutor AND x.enabled = 1")
})
@Entity
@Table(name="groups")
public class Group implements Serializable {

	private static final long serialVersionUID = 6736081775273686987L;
	
	/** Queries **/
	public static final String Q_FIND_BY_ID = "findGroupById";
	public static final String Q_FIND_ALL = "findAllGroups";
	public static final String Q_FIND_BY_COURSE_LETTER = "findGroupByCourseAndLetter";
	public static final String Q_FIND_BY_COURSE = "findGroupByCourse";
	public static final String Q_FIND_BY_TUTOR = "findGroupByTutor";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_group")
	private Integer id;
//	@Column(nullable=false)
//	private Integer course;
	@Column(length=1)
	private String letter;
//	@Column(nullable=false)
//	private Integer tutor;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "course")
	private Course course;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tutor")
	private Teacher tutor;
	@Column(name="schedule_file")
	private String scheduleFile;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean enabled;
	
	/**
	 * Constructor
	 */
	public Group(){
		
	}
	
	/**
	 * Constructor
	 * @param group otra clase
	 */
	public Group(Group group){
		this.id = group.getId();
		this.letter = group.getLetter();
		this.course = group.getCourse();
		this.tutor = group.getTutor();
		this.scheduleFile = group.getScheduleFile();
		
	}
	
	/**
	 * Obtiene el id clase
	 * @return id de clase
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Establece el id de clase
	 * @param id de clase
	 */
	public void setId(Integer id) {
		this.id = id;
	}
//	/**
//	 * Obtiene el id de curso
//	 * @return id de curso
//	 */
//	public Integer getCourse() {
//		return course;
//	}
//	/**
//	 * Establece el id de curso
//	 * @param id de curso
//	 */
//	public void setCourse(Integer course) {
//		this.course = course;
//	}
	/**
	 * Obtiene letra de clase
	 * @return letra de clase
	 */
	public String getLetter() {
		return letter;
	}
	/**
	 * Establece la letra de clase
	 * @param letra de clase
	 */
	public void setLetter(String letter) {
		this.letter = letter;
	}
//	/**
//	 * Obtiene el id del tutor
//	 * @return id del tutor
//	 */
//	public Integer getTutor() {
//		return tutor;
//	}
//	/**
//	 * Establece el id del tutor
//	 * @param tutor id del tutor
//	 */
//	public void setTutor(Integer tutor) {
//		this.tutor = tutor;
//	}
	
	/**
	 * Obtiene el curso
	 * @return el curso
	 */
	public Course getCourse() {
		return course;
	}
	/**
	 * Establece el curso
	 * @param course el curso
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
	/**
	 * Obtiene el tutor
	 * @return el tutor
	 */
	public Teacher getTutor() {
		return tutor;
	}
	/**
	 * Establece el tutor
	 * @param tutor el tutor
	 */
	public void setTutor(Teacher tutor) {
		this.tutor = tutor;
	}
	/**
//	 * Obtiene el listado de alumnos
//	 * @return listado de alumnos
//	 */
//	public List<Student> getStudents() {
//		return students;
//	}
//	/**
//	 * Establece el listado de alumnos
//	 * @param students listado de alumnos
//	 */
//	public void setStudents(List<Student> students) {
//		this.students = students;
//	}
	/**
	 * Obtiene el nombre del fichero de horarios
	 * @return el nombre del fichero de horarios
	 */
	public String getScheduleFile() {
		return scheduleFile;
	}
	/**
	 * Establece el nombre del fichero de horarios
	 * @param scheduleFile el nombre del fichero de horarios
	 */
	public void setScheduleFile(String scheduleFile) {
		this.scheduleFile = scheduleFile;
	}

	/**
	 * Obtiene el campo activo
	 * @return campo activo
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Establece el campo activo
	 * @param enabled campo activo
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", letter=" + letter + ", course=" + course + ", tutor=" + tutor + ", scheduleFile="
				+ scheduleFile + ", enabled=" + enabled + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((letter == null) ? 0 : letter.hashCode());
		result = prime * result + ((scheduleFile == null) ? 0 : scheduleFile.hashCode());
		result = prime * result + ((tutor == null) ? 0 : tutor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (letter == null) {
			if (other.letter != null)
				return false;
		} else if (!letter.equals(other.letter))
			return false;
		if (scheduleFile == null) {
			if (other.scheduleFile != null)
				return false;
		} else if (!scheduleFile.equals(other.scheduleFile))
			return false;
		if (tutor == null) {
			if (other.tutor != null)
				return false;
		} else if (!tutor.equals(other.tutor))
			return false;
		return true;
	}
	
}
