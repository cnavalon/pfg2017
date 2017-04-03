/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Carlos Navalon Urrea
 *
 */
public class GroupForm extends Group {

	private static final long serialVersionUID = 6746163241293656802L;
	
	private List<Integer> lstStudents;
	private MultipartFile file;
	private boolean skipStudents;
	private boolean skipSchedule;
	
	/**
	 * Obtiene listado de alumnos 
	 * @return listado de alumnos 
	 */
	public List<Integer> getLstStudents() {
		return lstStudents;
	}
	/**
	 * Establece listado de alumnos 
	 * @param lstStudentsAdd listado de alumnos
	 */
	public void setLstStudents(List<Integer> lstStudents) {
		this.lstStudents = lstStudents;
	}
	/**
	 * Obtiene el fichero de horarios
	 * @return el fichero de horarios
	 */
	public MultipartFile getFile() {
		return file;
	}
	/**
	 * Establece el fichero de horarios
	 * @param file el fichero de horarios
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	/**
	 * Obtiene indicador de guardar alumnos
	 * @return indicador de guardar alumnos
	 */
	public boolean isSkipStudents() {
		return skipStudents;
	}
	/**
	 * Establece indicador de guardar alumnos
	 * @param skipStudents indicador de guardar alumnos
	 */
	public void setSkipStudents(boolean skipStudents) {
		this.skipStudents = skipStudents;
	}
	/**
	 * Obtiene indicador de guardar horario
	 * @return indicador de guardar horario
	 */
	public boolean isSkipSchedule() {
		return skipSchedule;
	}
	/**
	 * Establece indicador de guardar horario
	 * @param skipSchedule indicador de guardar horario
	 */
	public void setSkipSchedule(boolean skipSchedule) {
		this.skipSchedule = skipSchedule;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GroupForm [id=" + getId() + ", letter=" + getLetter() + ", course=" + getCourse()
				+ ", tutor=" + getTutor() + ", scheduleFile=" + getScheduleFile() + ", lstStudents="
				+ lstStudents + ", file=" + file + ", skipStudents=" + skipStudents + ", skipSchedule=" + skipSchedule + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + ((lstStudents == null) ? 0 : lstStudents.hashCode());
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
		GroupForm other = (GroupForm) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (lstStudents == null) {
			if (other.lstStudents != null)
				return false;
		} else if (!lstStudents.equals(other.lstStudents))
			return false;
		return true;
	}
	
	
	
	
}
