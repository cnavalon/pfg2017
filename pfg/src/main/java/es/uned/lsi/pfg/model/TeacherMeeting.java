/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.util.List;
import java.util.Map;

/**
 * Entidad de profersor de tutoria
 * @author Carlos Navalon Urrea
 *
 */
public class TeacherMeeting {

	private Teacher teacher;
	private Map<Integer,List<String>> mapDayHour;
	
	public TeacherMeeting(){}
	
	public TeacherMeeting(Teacher teacher, Map<Integer,List<String>> mapDayHour){
		this.teacher = teacher;
		this.mapDayHour = mapDayHour;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Map<Integer, List<String>> getMapDayHour() {
		return mapDayHour;
	}

	public void setMapDayHour(Map<Integer, List<String>> mapDayHour) {
		this.mapDayHour = mapDayHour;
	}

	@Override
	public String toString() {
		return "TeacherMeeting [teacher=" + teacher + ", mapDayHour=" + mapDayHour + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mapDayHour == null) ? 0 : mapDayHour.hashCode());
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
		TeacherMeeting other = (TeacherMeeting) obj;
		if (mapDayHour == null) {
			if (other.mapDayHour != null)
				return false;
		} else if (!mapDayHour.equals(other.mapDayHour))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}
}
