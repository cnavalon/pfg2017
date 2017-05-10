/**
 * 
 */
package es.uned.lsi.pfg.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entidad de pagos
 * @author Carlos Navalon Urrea
 *
 */
@NamedQueries({
	@NamedQuery(name="findPaymentById", query="SELECT x FROM Payment x WHERE x.id = :id"),
	@NamedQuery(name="findPaymentByStudent", query="SELECT x FROM Payment x WHERE x.student = :student")
})
@Entity
@Table(name="payments")
public class Payment implements Serializable{
	
	private static final long serialVersionUID = 1032507542456830350L;
	
	/** Queries **/
	public static final String Q_FIND_BY_ID = "findPaymentById";
	public static final String Q_FIND_BY_STUDENT = "findPaymentByStudent";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_payment")
	private Integer id;
	@Column(name="id_student", nullable=false)
	private Integer student;
	@Column(name="date_month", nullable=false)
	private Integer month;
	@Column(nullable=false)
	private Integer amount;
	@Column(name= "insert_date", nullable=false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date insertDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStudent() {
		return student;
	}
	public void setStudent(Integer student) {
		this.student = student;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	@Override
	public String toString() {
		return "Payment [id=" + id + ", student=" + student + ", month=" + month + ", amount=" + amount
				+ ", insertDate=" + insertDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((insertDate == null) ? 0 : insertDate.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		Payment other = (Payment) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (insertDate == null) {
			if (other.insertDate != null)
				return false;
		} else if (!insertDate.equals(other.insertDate))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}
	
}
