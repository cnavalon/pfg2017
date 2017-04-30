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
 * Entidad de calificaciones
 * @author Carlos Navalon Urrea
 *
 */
@NamedQueries({
	@NamedQuery(name="findQualificationById", query="SELECT x FROM Qualification x WHERE x.id = :id AND x.active = 1"),
	@NamedQuery(name="findQualificationByStudent", query="SELECT x FROM Qualification x WHERE x.student = :student AND x.active = 1"),
	@NamedQuery(name="findQualificationByGroupSubject", query="SELECT x FROM Qualification x WHERE x.group = :group AND x.subject = :subject AND x.active = 1"),
})
@Entity
@Table(name="qualifications")
public class Qualification implements Serializable{
	private static final long serialVersionUID = -1746408451387518731L;
	
	/** Queries **/
	public static final String Q_FIND_BY_ID = "findQualificationById";
	public static final String Q_FIND_BY_STUDENT = "findQualificationByStudent";
	public static final String Q_FIND_BY_GROUP_SUBJECT = "findQualificationByGroupSubject";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_qualificaction")
	private Integer id;
	@Column(name="id_group", nullable=false)
	private Integer group;
	@Column(name="id_subject", nullable=false)
	private Integer subject;
	@Column(name="id_student", nullable=false)
	private Integer student;
	@Column(name="qualification_1", length=20)
	private String qualification1;
	@Column(name="qualification_2", length=20)
	private String qualification2;
	@Column(name="qualification_3", length=20)
	private String qualification3;
	@Column(name="qualification_F", length=20)
	private String qualificationF;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean active;
	
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
	public Integer getStudent() {
		return student;
	}
	public void setStudent(Integer student) {
		this.student = student;
	}
	public String getQualification1() {
		return qualification1;
	}
	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}
	public String getQualification2() {
		return qualification2;
	}
	public void setQualification2(String qualification2) {
		this.qualification2 = qualification2;
	}
	public String getQualification3() {
		return qualification3;
	}
	public void setQualification3(String qualification3) {
		this.qualification3 = qualification3;
	}
	public String getQualificationF() {
		return qualificationF;
	}
	public void setQualificationF(String qualificationF) {
		this.qualificationF = qualificationF;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Qualification [id=" + id + ", group=" + group + ", subject=" + subject + ", student=" + student
				+ ", qualification1=" + qualification1 + ", qualification2=" + qualification2 + ", qualification3="
				+ qualification3 + ", qualificationF=" + qualificationF + ", active=" + active + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((qualification1 == null) ? 0 : qualification1.hashCode());
		result = prime * result + ((qualification2 == null) ? 0 : qualification2.hashCode());
		result = prime * result + ((qualification3 == null) ? 0 : qualification3.hashCode());
		result = prime * result + ((qualificationF == null) ? 0 : qualificationF.hashCode());
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
		Qualification other = (Qualification) obj;
		if (active != other.active)
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
		if (qualification1 == null) {
			if (other.qualification1 != null)
				return false;
		} else if (!qualification1.equals(other.qualification1))
			return false;
		if (qualification2 == null) {
			if (other.qualification2 != null)
				return false;
		} else if (!qualification2.equals(other.qualification2))
			return false;
		if (qualification3 == null) {
			if (other.qualification3 != null)
				return false;
		} else if (!qualification3.equals(other.qualification3))
			return false;
		if (qualificationF == null) {
			if (other.qualificationF != null)
				return false;
		} else if (!qualificationF.equals(other.qualificationF))
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
