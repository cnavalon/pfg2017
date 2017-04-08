/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.util.Arrays;

/**
 * Tabla de horario
 * @author Carlos Navalon Urrea
 *
 */
public class ScheduleTable {

	private ScheduleRow[] rows;
	
	public ScheduleTable(int size, int columns){
		this.rows = new ScheduleRow[size];
		for(int i = 0; i < size; i++){
			rows[i] = new ScheduleRow(columns);
		}
	}

	public ScheduleRow[] getRows() {
		return rows;
	}

	public void setRows(ScheduleRow[] rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "ScheduleTable [rows=" + Arrays.toString(rows) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(rows);
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
		ScheduleTable other = (ScheduleTable) obj;
		if (!Arrays.equals(rows, other.rows))
			return false;
		return true;
	}
	
	
	
}
