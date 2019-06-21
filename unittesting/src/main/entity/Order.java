package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`order`")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int orid;

	@Column(name = "CUSTOMER_ID")
	private int cid;

	@Column(name = "PRODUCT_ID")
	private int pid;

	@Column(name = "QUANTITY")
	private int prquant;
	

	@Column(name = "CONF_NO")
	private int confirmationno;

	public Order() {
	}

	public Order(int i, int cid, int pid, int qi, int confirmationno) {
		this.orid = i;
		this.cid = cid;
		this.pid = pid;
		this.prquant = qi;
		this.confirmationno = confirmationno;
	}

	public int getPrquant() {
		return prquant;
	}

	public void setPrquant(int quantity) {
		this.prquant = quantity;
	}
	public int getConfirmationno() {
		return confirmationno;
	}

	public void setConfirmationno(int confirmationno) {
		this.confirmationno = confirmationno;
	}

	public int getOrid() {
		return orid;
	}

	public void setOrid(int orid) {
		this.orid = orid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "Order: [id=" + orid + ", customer id=" + cid + ", product id=" + pid + ", quantity=" + prquant + "]";
	}

}
