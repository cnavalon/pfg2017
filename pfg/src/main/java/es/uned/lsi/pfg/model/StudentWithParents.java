/**
 * 
 */
package es.uned.lsi.pfg.model;

/**
 * Entidad de estudiante y padres
 * @author Carlos Navalon Urrea
 *
 */
public class StudentWithParents {
	
	private Student student;
	private Parent parent1;
	private Parent parent2;
	
	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	/**
	 * @return the parent1
	 */
	public Parent getParent1() {
		return parent1;
	}
	/**
	 * @param parent1 the parent1 to set
	 */
	public void setParent1(Parent parent1) {
		this.parent1 = parent1;
	}
	/**
	 * @return the parent2
	 */
	public Parent getParent2() {
		return parent2;
	}
	/**
	 * @param parent2 the parent2 to set
	 */
	public void setParent2(Parent parent2) {
		this.parent2 = parent2;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentParents [student=" + student + ", parent1=" + parent1 + ", parent2=" + parent2 + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parent1 == null) ? 0 : parent1.hashCode());
		result = prime * result + ((parent2 == null) ? 0 : parent2.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		StudentWithParents other = (StudentWithParents) obj;
		if (parent1 == null) {
			if (other.parent1 != null)
				return false;
		} else if (!parent1.equals(other.parent1))
			return false;
		if (parent2 == null) {
			if (other.parent2 != null)
				return false;
		} else if (!parent2.equals(other.parent2))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}
	
}
