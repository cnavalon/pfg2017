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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entidad de reunion
 * @author Carlos Navalon Urrea
 *
 */
@NamedQueries({
	@NamedQuery(name="findMeetingById", query="SELECT x FROM Meeting x WHERE x.id = :id"),
	@NamedQuery(name="findMeetingByUser", query="SELECT x FROM Meeting x WHERE x.user = :user")
})
@Entity
@Table(name="meetings")
public class Meeting implements Serializable{

	private static final long serialVersionUID = 2608960416088232147L;
	
	/** Queries **/
	public static final String Q_FIND_BY_ID = "findMeetingById";
	public static final String Q_FIND_BY_USER = "findMeetingByUser";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_meeting")
	private Integer id;
	@Column(name="type_meeting", length=1, nullable=false)
	private String type;
	@Column(name="create_user", length=20, nullable=false)
	private String user;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="date_meeting", nullable=false)
	private Date date;
	@Column(name="hour_meeting", length=15, nullable=false)
	private String hour;
	private String comments;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean active;
	
	public Meeting() {	}
	
	public Meeting(Integer id, String type, String user, Date date, String hour, String comments, boolean active) {
		this.id = id;
		this.type = type;
		this.user = user;
		this.date = date;
		this.hour = hour;
		this.comments = comments;
		this.active = active;
	}

	public Meeting(Meeting meeting){
		this.id = meeting.getId();
		this.type = meeting.getType();
		this.user = meeting.getUser();
		this.date = meeting.getDate();
		this.hour = meeting.getHour();
		this.comments = meeting.getComments();
		this.active = meeting.isActive();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
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
		return "Meeting [id=" + id + ", type=" + type + ", user=" + user + ", date=" + date + ", hour=" + hour
				+ ", comments=" + comments + ", active=" + active + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((hour == null) ? 0 : hour.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
