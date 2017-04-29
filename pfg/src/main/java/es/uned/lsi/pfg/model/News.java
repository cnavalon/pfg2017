/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entidad de noticias
 * @author Carlos Navalon Urrea
 *
 */
@NamedQueries({
	@NamedQuery(name="findNewsById", 
			query="SELECT x FROM News x "
					+ "WHERE x.id = :id "
					+ "AND x.active = 1"),
	@NamedQuery(name="findNewsByGroupSubject", 
			query="SELECT x FROM News x "
					+ "WHERE x.group = :group "
					+ "AND x.subject = :subject "
					+ "AND x.active = 1 "
					+ "ORDER BY x.date DESC"),
	@NamedQuery(name="countNewsByGroupSubject", 
			query="SELECT COUNT(x)FROM News x "
					+ "WHERE x.group = :group "
					+ "AND x.subject = :subject "
					+ "AND x.active = 1 "),
})
@Entity
@Table(name="news")
public class News implements Serializable {
	private static final long serialVersionUID = 8568295677153566177L;
	
	/** Queries **/
	public static final String Q_FIND_BY_ID = "findNewsById";
	public static final String Q_FIND_BY_GROUP_SUBJECT = "findNewsByGroupSubject";
	public static final String Q_COUNT_BY_GROUP_SUBJECT = "countNewsByGroupSubject";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_news")
	private Integer id;
	@Column(name="id_group", nullable=false)
	private Integer group;
	@Column(name="id_subject", nullable=false)
	private Integer subject;
	@Column(name="creation_user", length=20, nullable=false)
	private String user;
	@Column(name="creation_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date date;
	private String title;
	@Column(name="news_text")
	private String text;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean active;
	
	@Transient
	private Person person;
	@Transient
	private List<Comment> comments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Integer getSubject() {
		return subject;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", group=" + group + ", subject=" + subject + ", user=" + user + ", date=" + date
				+ ", title=" + title + ", text=" + text + ", active=" + active + ", person=" + person + ", comments="
				+ comments + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		News other = (News) obj;
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
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
