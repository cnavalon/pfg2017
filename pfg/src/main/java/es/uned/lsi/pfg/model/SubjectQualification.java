/**
 * 
 */
package es.uned.lsi.pfg.model;

/**
 * Bean de relacion de asignatura y calificacion
 * @author Carlos Navalon Urrea
 *
 */
public class SubjectQualification {

	private Subject subject;
	private Qualification qualification;
	
	public SubjectQualification() {
	}
	
	public SubjectQualification(Subject subject, Qualification qualification) {
		this.subject = subject;
		this.qualification = qualification;
	}

	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	@Override
	public String toString() {
		return "SubjectQualification [subject=" + subject + ", qualification=" + qualification + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qualification == null) ? 0 : qualification.hashCode());
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
		SubjectQualification other = (SubjectQualification) obj;
		if (qualification == null) {
			if (other.qualification != null)
				return false;
		} else if (!qualification.equals(other.qualification))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
	
	
}
