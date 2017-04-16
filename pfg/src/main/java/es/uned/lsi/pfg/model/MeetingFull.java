/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.util.List;
import java.util.Map;

/**
 * Entidad de Reunión con asistentes
 * @author Carlos Navalon Urrea
 *
 */
public class MeetingFull extends Meeting {

	private static final long serialVersionUID = 5218365119004893471L;
	
	private List<Attendee> lstAttendee;
	private Map<String, Person> mapPerson;

	public List<Attendee> getLstAttendee() {
		return lstAttendee;
	}

	public void setLstAttendee(List<Attendee> lstAttendee) {
		this.lstAttendee = lstAttendee;
	}

	@Override
	public String toString() {
		return "MeetingFull [lstAttendee=" + lstAttendee + ", mapPerson=" + mapPerson + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lstAttendee == null) ? 0 : lstAttendee.hashCode());
		result = prime * result + ((mapPerson == null) ? 0 : mapPerson.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingFull other = (MeetingFull) obj;
		if (lstAttendee == null) {
			if (other.lstAttendee != null)
				return false;
		} else if (!lstAttendee.equals(other.lstAttendee))
			return false;
		if (mapPerson == null) {
			if (other.mapPerson != null)
				return false;
		} else if (!mapPerson.equals(other.mapPerson))
			return false;
		return true;
	}

}
