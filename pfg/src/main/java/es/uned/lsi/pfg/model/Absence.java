/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entidad de faltas de asistencia
 * @author Carlos Navalon Urrea
 *
 */
@NamedQueries({
	@NamedQuery(name="findAbsenceById", query="SELECT x FROM Absence x WHERE x.id = :id AND x.active = 1"),
	@NamedQuery(name="findAbsenceByStudent", query="SELECT x FROM Absence x WHERE x.student.id = :student AND x.active = 1"),
	@NamedQuery(name="findAbsenceByScheduleDay", query="SELECT x FROM Absence x WHERE x.schedule.id = :schedule AND x.day = :day AND x.active = 1"),
	@NamedQuery(name="findAbsenceByStudentScheduleDay", 
		query="SELECT x FROM Absence x "
				+ "WHERE x.student.id = :student "
				+ "AND x.schedule.id = :schedule "
				+ "AND x.day = :day "
				+ "AND x.active = 1"),
})
@Entity
@Table(name="absences")
public class Absence implements Serializable {
	
	private static final long serialVersionUID = 3430413083275652990L;
	
	/** Queries **/
	public static final String Q_FIND_BY_ID = "findAbsenceById";
	public static final String Q_FIND_BY_STUDENT = "findAbsenceByStudent";
	public static final String Q_FIND_BY_SCHEDULE_DAY = "findAbsenceByScheduleDay";
	public static final String Q_FIND_BY_STUDENT_SCHEDULE_DAY = "findAbsenceByStudentScheduleDay";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_absence")
	private Integer id;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_student")
	private Student student;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_schedule")
	private Schedule schedule;
	@Column(name= "date_day", nullable=false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date day;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean active;
	
	@Transient
	private Subject subject;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Absence [id=" + id + ", student=" + student + ", schedule=" + schedule + ", day=" + day + ", active="
				+ active + ", subject=" + subject + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		Absence other = (Absence) obj;
		if (active != other.active)
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (schedule == null) {
			if (other.schedule != null)
				return false;
		} else if (!schedule.equals(other.schedule))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
	
	
}
