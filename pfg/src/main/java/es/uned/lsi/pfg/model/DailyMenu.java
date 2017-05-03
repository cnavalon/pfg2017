package es.uned.lsi.pfg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bean para representacion de menus
 * @author Carlos Navalon Urrea
 *
 */
public class DailyMenu {

	private List<Map<String,Menu>> data;
	
	public DailyMenu() {
		data = new ArrayList<Map<String,Menu>>();
		for(int i = 0; i < 3; i++ ){
			data.add(new HashMap<String,Menu>());
		}
	}

	public List<Map<String, Menu>> getData() {
		return data;
	}

	public void setData(List<Map<String, Menu>> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DailyMenu [data=" + data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		DailyMenu other = (DailyMenu) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
}
