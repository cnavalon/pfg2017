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

/**
 * Entidad de horario
 * @author Carlos Navalon Urrea
 *
 */
@NamedQueries({
	@NamedQuery(name="findAllSchedule", query="SELECT x FROM Schedule x"),
	@NamedQuery(name="findScheduleByGroup", query="SELECT x FROM Schedule x WHERE x.group = :group"),
	@NamedQuery(name="findScheduleByTeacher", query="SELECT x FROM Schedule x WHERE x.teacher = :teacher"),
	@NamedQuery(name="removeScheduleByGroup", query="DELETE FROM Schedule x WHERE x.group = :group"),
	@NamedQuery(name="updateScheduleByTeacher", query="UPDATE Schedule x SET x.teacher = NULL WHERE x.teacher = :teacher"),
	@NamedQuery(name="findMaxHourByGroup", query="SELECT MAX(x.hour) FROM Schedule x WHERE x.group = :group"),
	@NamedQuery(name="findMaxHourByTeacher", query="SELECT MAX(x.hour) FROM Schedule x WHERE x.teacher = :teacher"),
	@NamedQuery(name="findScheduleBySubject", query="SELECT x FROM Schedule x WHERE x.subject = :subject")
})
@Entity
@Table(name="schedules")
public class Schedule implements Serializable {

	private static final long serialVersionUID = 5309209055982349929L;
	
	/** Queries **/
	public static final String Q_FIND_ALL = "findAllSchedule";
	public static final String Q_FIND_BY_GROUP = "findScheduleByGroup";
	public static final String Q_FIND_BY_TEACHER = "findScheduleByTeacher";
	public static final String Q_REMOVE_BY_GROUP = "removeScheduleByGroup";
	public static final String Q_UPDATE_TEACHER = "updateScheduleByTeacher";
	public static final String Q_FIND_MAX_HOUR_BY_GROUP = "findMaxHourByGroup";
	public static final String Q_FIND_MAX_HOUR_BY_TEACHER = "findMaxHourByTeacher";
	public static final String Q_FIND_BY_SUBJECT= "findScheduleBySubject";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_schedule")
	private Integer id;
	@Column(name="id_group", nullable=false)
	private Integer group;
	@Column(nullable=false)
	private Integer day;
	@Column(nullable=false)
	private Integer hour;
	@Column(nullable=false, length=20)
	private String subject;
	private Integer teacher;
	@Column(name="init_hour")
	private String initHour;
	@Column(name="end_hour")
	private String endHour;
	
	/**
	 * Constructor
	 */
	public Schedule(){
	}
	
	/**
	 * Constructor
	 * @param id id
	 * @param group clase
	 * @param day dia
	 * @param hour hora
	 * @param subject asignatura
	 * @param teacher profesor
	 * @param initHour hora de inicio
	 * @param endHour hora de fin
	 */
	public Schedule(Integer id, Integer group, Integer day, Integer hour, String subject, Integer teacher,
			String initHour, String endHour) {
		super();
		this.id = id;
		this.group = group;
		this.day = day;
		this.hour = hour;
		this.subject = subject;
		this.teacher = teacher;
		this.initHour = initHour;
		this.endHour = endHour;
	}

	/**
	 * Obtiene el id de horario
	 * @return id de horario
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Establece el id de horario
	 * @param id id de horario
	 */
	public void setId(Integer id) {
		this.id = id;
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
	/**
	 * Obtiene el dia
	 * @return el dia
	 */
	public Integer getDay() {
		return day;
	}
	/**
	 * Establece el dia
	 * @param day el dia
	 */
	public void setDay(Integer day) {
		this.day = day;
	}
	/**
	 * Obtiene la hora
	 * @return la hora
	 */
	public Integer getHour() {
		return hour;
	}
	/**
	 * Establece la hora
	 * @param hour la hora
	 */
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	/**
	 * Obtiene la asignatura
	 * @return la asignatura
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * Establece la asignatura
	 * @param subject la asignatura
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * Obtiene el profesor
	 * @return el profesor
	 */
	public Integer getTeacher() {
		return teacher;
	}
	/**
	 * Establece el profesor
	 * @param teacher el profesor
	 */
	public void setTeacher(Integer teacher) {
		this.teacher = teacher;
	}
	/**
	 * Obtiene la hora de inicio
	 * @return hora de inicio
	 */
	public String getInitHour() {
		return initHour;
	}
	/**
	 * Establece la hora de inicio
	 * @param initHour hora de inicio
	 */
	public void setInitHour(String initHour) {
		this.initHour = initHour;
	}
	/**
	 * Obtiene la hora de fin
	 * @return hora de fin
	 */
	public String getEndHour() {
		return endHour;
	}
	/**
	 * Establece la hora de fin
	 * @param endHour hora de fin
	 */
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", group=" + group + ", day=" + day + ", hour=" + hour + ", subject=" + subject
				+ ", teacher=" + teacher + ", initHour=" + initHour + ", endHour=" + endHour + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((endHour == null) ? 0 : endHour.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((hour == null) ? 0 : hour.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((initHour == null) ? 0 : initHour.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
		Schedule other = (Schedule) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (endHour == null) {
			if (other.endHour != null)
				return false;
		} else if (!endHour.equals(other.endHour))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (hour == null) {
			if (other.hour != null)
				return false;
		} else if (!hour.equals(other.hour))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (initHour == null) {
			if (other.initHour != null)
				return false;
		} else if (!initHour.equals(other.initHour))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}
	
}
