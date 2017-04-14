/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.util.List;

/**
 * Entidad de Reunión con asistentes
 * @author Carlos Navalon Urrea
 *
 */
public class MeetingFull extends Meeting {

	private static final long serialVersionUID = 5218365119004893471L;
	
	private List<Attendee> lstAttendee;

	public List<Attendee> getLstAttendee() {
		return lstAttendee;
	}

	public void setLstAttendee(List<Attendee> lstAttendee) {
		this.lstAttendee = lstAttendee;
	}

	@Override
	public String toString() {
		return "MeetingFull [meeting= " + super.toString() + ", lstAttendee=" + lstAttendee + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lstAttendee == null) ? 0 : lstAttendee.hashCode());
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
		return true;
	}
	
	
	
	
	

}
