/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.util.Arrays;

/**
 * Fila de horario
 * @author Carlos Navalon Urrea
 *
 */
public class ScheduleRow {
	
	private String initHour;
	private String endHour;
	private ScheduleCell[] cells;
	
	public ScheduleRow(int size){
		this.cells = new ScheduleCell[size];
	}
	
	public String getInitHour() {
		return initHour;
	}
	public void setInitHour(String initHour) {
		this.initHour = initHour;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public ScheduleCell[] getCells() {
		return cells;
	}
	public void setCells(ScheduleCell[] cells) {
		this.cells = cells;
	}
	@Override
	public String toString() {
		return "ScheduleRow [initHour=" + initHour + ", endHour=" + endHour + ", cells=" + Arrays.toString(cells) + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cells);
		result = prime * result + ((endHour == null) ? 0 : endHour.hashCode());
		result = prime * result + ((initHour == null) ? 0 : initHour.hashCode());
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
		ScheduleRow other = (ScheduleRow) obj;
		if (!Arrays.equals(cells, other.cells))
			return false;
		if (endHour == null) {
			if (other.endHour != null)
				return false;
		} else if (!endHour.equals(other.endHour))
			return false;
		if (initHour == null) {
			if (other.initHour != null)
				return false;
		} else if (!initHour.equals(other.initHour))
			return false;
		return true;
	} 

}
