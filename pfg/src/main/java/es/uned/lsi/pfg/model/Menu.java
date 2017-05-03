/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entidad de menu
 * @author Carlos Navalon Urrea
 *
 */
@NamedQueries({
	@NamedQuery(name="findMenuById", query="SELECT x FROM Menu x WHERE x.id = :id"),
	@NamedQuery(name="findMenuByMonth", query="SELECT x FROM Menu x WHERE x.month = :month"),
	@NamedQuery(name="findMenuByMonthDay", query="SELECT x FROM Menu x WHERE x.month = :month AND x.day = :day"),
	@NamedQuery(name="findMenuByMonthDayTypeOrder", query="SELECT x FROM Menu x WHERE x.month = :month AND x.day = :day AND x.type = :type AND x.order = :order"),
	@NamedQuery(name="removeMenuByMonthDay", query="DELETE FROM Menu x WHERE x.month = :month AND x.day = :day")
})
@Entity
@Table(name="menus")
public class Menu implements Serializable {

 static final long serialVersionUID = -7109872296348626617L;

	/** Queries **/
	public static final String Q_FIND_BY_ID = "findMenuById";
	public static final String Q_FIND_BY_MONTH = "findMenuByMonth";
	public static final String Q_FIND_BY_MONTH_DAY = "findMenuByMonthDay";
	public static final String Q_FIND_BY_MONTH_DAY_TYPE_ORDER = "findMenuByMonthDayTypeOrder";
	public static final String Q_REMOVE_BY_MONTH_DAY = "removeMenuByMonthDay";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_menu")
	private Integer id;
	@Column(name="date_month", nullable=false)
	private Integer month;
	@Column(name="date_day", nullable=false)
	private Integer day;
	@Column(name="order_menu", nullable=false)
	private Integer order;
	@Column(name="type_menu", nullable=false, length=5)
	private String type;
	@Column(nullable=false, length=255)
	private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", month=" + month + ", day=" + day + ", order=" + order + ", type=" + type
				+ ", description=" + description + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Menu other = (Menu) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
