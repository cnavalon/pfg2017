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

/**
 * Entidad de clases
 * @author Carlos Navalon Urrea
 *
 */
@NamedQueries({
	@NamedQuery(name="findGroupById", query="SELECT x FROM Group x WHERE x.id = :id"),
	@NamedQuery(name="findAllGroups", query="SELECT x FROM Group x"),
	@NamedQuery(name="findGroupByCourseAndLetter", query="SELECT x FROM Group x WHERE x.course.idCourse = :course AND x.letter = :letter"),
	@NamedQuery(name="findGroupByCourse", query="SELECT x FROM Group x WHERE x.course.idCourse = :course"),
	@NamedQuery(name="findGroupByTutor", query="SELECT x FROM Group x WHERE x.tutor.id = :tutor")
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

	/**
	 * Constructor
	 */
	public Group(){
		
	}
	
	/**
	 * Constructor
	 * @param group otra clases
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
}
