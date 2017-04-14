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

import org.hibernate.annotations.Type;

/**
 * Entidad de asistentes
 * @author Carlos Navalon Urrea
 *
 */
@NamedQueries({
	@NamedQuery(name="findAttendeeByMeter", query="SELECT x FROM Attendee x WHERE x.meeting = :meeting"),
	@NamedQuery(name="findAttendeeByUser", query="SELECT x FROM Attendee x WHERE x.user = :user")
})
@Entity
@Table(name="meetings_attendees")
public class Attendee implements Serializable {
	private static final long serialVersionUID = 2334865156915397664L;
	
	/** Queries **/
	public static final String Q_FIND_BY_MEETING = "findAttendeeByMeter";
	public static final String Q_FIND_BY_USER = "findAttendeeByUser";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_attendee")
	private Integer id;
	@Column(name="id_meeting", nullable=false)
	private Integer meeting;
	@Column(name="id_user", length=20, nullable=false)
	private String user;
	@Column(name="id_student", nullable=false)
	private Integer student;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean confirm;
	private String comments;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean active;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMeeting() {
		return meeting;
	}
	public void setMeeting(Integer meeting) {
		this.meeting = meeting;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Integer getStudent() {
		return student;
	}
	public void setStudent(Integer student) {
		this.student = student;
	}
	public Boolean getConfirm() {
		return confirm;
	}
	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Attendee [id=" + id + ", meeting=" + meeting + ", user=" + user + ", student=" + student + ", confirm="
				+ confirm + ", comments=" + comments + ", active=" + active + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((confirm == null) ? 0 : confirm.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		result = prime * result + ((meeting == null) ? 0 : meeting.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Attendee other = (Attendee) obj;
		if (active != other.active)
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (confirm == null) {
			if (other.confirm != null)
				return false;
		} else if (!confirm.equals(other.confirm))
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
		if (meeting == null) {
			if (other.meeting != null)
				return false;
		} else if (!meeting.equals(other.meeting))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
