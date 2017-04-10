/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.io.Serializable;

/**
 * Clave primaria compuesta de entidad {@link Meeting}
 * @author Carlos Navalon Urrea
 *
 */
public class MeetingId implements Serializable{
	private static final long serialVersionUID = -5401602766830431887L;
	
	private Integer id;
	private Integer step;
	
	public MeetingId() {
	}
	
	public MeetingId(Integer id, Integer step) {
		this.id = id;
		this.step = step;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	@Override
	public String toString() {
		return "TutorialId [id=" + id + ", step=" + step + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((step == null) ? 0 : step.hashCode());
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
		MeetingId other = (MeetingId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (step == null) {
			if (other.step != null)
				return false;
		} else if (!step.equals(other.step))
			return false;
		return true;
	}
	
}
