/**
 * 
 */
package es.uned.lsi.pfg.model;

/**
 * Celda de horario
 * @author Carlos Navalon Urrea
 *
 */
public class ScheduleCell {

	private Integer id;
	private Subject subject;
	private Teacher teacher;
	private Group group;
	
	public ScheduleCell() {
	}

	public ScheduleCell(Integer id, Subject subject, Teacher teacher, Group group) {
		this.id = id;
		this.subject = subject;
		this.teacher = teacher;
		this.group = group;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "ScheduleCell [id=" + id + ", subject=" + subject + ", teacher=" + teacher + ", group=" + group + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		ScheduleCell other = (ScheduleCell) obj;
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
