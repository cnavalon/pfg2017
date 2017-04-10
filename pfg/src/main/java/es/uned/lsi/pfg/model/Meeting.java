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
import javax.persistence.IdClass;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entidad de tutoria
 * @author Carlos Navalon Urrea
 *
 */
@Entity(name="meetings")
@IdClass(MeetingId.class)
public class Meeting implements Serializable{

	private static final long serialVersionUID = 2608960416088232147L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_meeting")
	private Integer id;
	@Id
	private Integer step;
	@Column(name="user_student", length=20, nullable=false)
	private String student;
	@Column(name="user_from", length=20, nullable=false)
	private String userFrom;
	@Column(name="email_from", length=70)
	private String emailFrom;
	@Column(name="user_to", length=20, nullable=false)
	private String userTo;
	@Column(name="email_to", length=70)
	private String emailTo;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="date_meeting", nullable=false)
	private Date date;
	@Column(name="hour_meeting", length=15, nullable=false)
	private String hour;
	@Column(length=1, nullable=false)
	private String status;
	private String comments;
	@Column(nullable=false)
	private boolean active;
	@Column(nullable=false)
	private boolean visible;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the step
	 */
	public Integer getStep() {
		return step;
	}
	/**
	 * @param step the step to set
	 */
	public void setStep(Integer step) {
		this.step = step;
	}
	/**
	 * @return the student
	 */
	public String getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(String student) {
		this.student = student;
	}
	/**
	 * @return the userFrom
	 */
	public String getUserFrom() {
		return userFrom;
	}
	/**
	 * @param userFrom the userFrom to set
	 */
	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}
	/**
	 * @return the emailFrom
	 */
	public String getEmailFrom() {
		return emailFrom;
	}
	/**
	 * @param emailFrom the emailFrom to set
	 */
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	/**
	 * @return the userTo
	 */
	public String getUserTo() {
		return userTo;
	}
	/**
	 * @param userTo the userTo to set
	 */
	public void setUserTo(String userTo) {
		this.userTo = userTo;
	}
	/**
	 * @return the emailTo
	 */
	public String getEmailTo() {
		return emailTo;
	}
	/**
	 * @param emailTo the emailTo to set
	 */
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	@Override
	public String toString() {
		return "Meeting [id=" + id + ", step=" + step + ", student=" + student + ", userFrom=" + userFrom
				+ ", emailFrom=" + emailFrom + ", userTo=" + userTo + ", emailTo=" + emailTo + ", date=" + date
				+ ", hour=" + hour + ", status=" + status + ", comments=" + comments + ", active=" + active
				+ ", visible=" + visible + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((emailFrom == null) ? 0 : emailFrom.hashCode());
		result = prime * result + ((emailTo == null) ? 0 : emailTo.hashCode());
		result = prime * result + ((hour == null) ? 0 : hour.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((step == null) ? 0 : step.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		result = prime * result + ((userFrom == null) ? 0 : userFrom.hashCode());
		result = prime * result + ((userTo == null) ? 0 : userTo.hashCode());
		result = prime * result + (visible ? 1231 : 1237);
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
		Meeting other = (Meeting) obj;
		if (active != other.active)
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (emailFrom == null) {
			if (other.emailFrom != null)
				return false;
		} else if (!emailFrom.equals(other.emailFrom))
			return false;
		if (emailTo == null) {
			if (other.emailTo != null)
				return false;
		} else if (!emailTo.equals(other.emailTo))
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (step == null) {
			if (other.step != null)
				return false;
		} else if (!step.equals(other.step))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		if (userFrom == null) {
			if (other.userFrom != null)
				return false;
		} else if (!userFrom.equals(other.userFrom))
			return false;
		if (userTo == null) {
			if (other.userTo != null)
				return false;
		} else if (!userTo.equals(other.userTo))
			return false;
		if (visible != other.visible)
			return false;
		return true;
	}
}
