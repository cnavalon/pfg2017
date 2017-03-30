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
 * Entidad de horario
 * @author Carlos Navalon Urrea
 *
 */
@Entity
@Table(name="schedules")
public class Schedule implements Serializable {

	private static final long serialVersionUID = 5309209055982349929L;
	
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
