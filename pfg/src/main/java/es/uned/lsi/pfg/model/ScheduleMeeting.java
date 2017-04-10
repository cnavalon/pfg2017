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
	@NamedQuery(name="findScheduleMeetingById", query="SELECT x FROM ScheduleMeeting x WHERE x.id = :id"),
	@NamedQuery(name="findScheduleMeetingByTeacher", query="SELECT x FROM ScheduleMeeting x WHERE x.teacher = :teacher")
})
/**
 * Entidad de horarios de tutoria
 * @author Carlos Navalon Urrea
 *
 */
@Entity
@Table(name="schedules_meeting")
public class ScheduleMeeting implements Serializable {

	private static final long serialVersionUID = -6199785948942408574L;

	/** Queries **/
	public static final String Q_FIND_BY_ID = "findScheduleMeetingById";
	public static final String Q_FIND_BY_TEACHER = "findScheduleMeetingByTeacher";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sch_meeting")
	private Integer id;
	@Column(nullable=false)
	private Integer day;
	@Column(nullable=false)
	private Integer teacher;
	@Column(name="init_hour",length=15,nullable=false)
	private String initHour;
	@Column(name="end_hour",length=15,nullable=false)
	private String endHour;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getTeacher() {
		return teacher;
	}

	public void setTeacher(Integer teacher) {
		this.teacher = teacher;
	}

	public String getInitHour() {
		return initHour;
	}

	public void setInitHour(String initHour) {
		this.initHour = initHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	@Override
	public String toString() {
		return "ScheduleMeeting [id=" + id + ", day=" + day + ", teacher=" + teacher + ", initHour=" + initHour
				+ ", endHour=" + endHour + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((endHour == null) ? 0 : endHour.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((initHour == null) ? 0 : initHour.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
		ScheduleMeeting other = (ScheduleMeeting) obj;
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
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}
	
	
	

	
}
