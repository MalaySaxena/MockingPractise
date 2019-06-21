package entity;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id")	
	private int prid;


	@Column(name="PR_NAME")	
	private String prtext;
	
	@Column(name="PRICE")	
	private int prprice;
	
	@Column(name="QUANTITY")
	private int prquant;
	
	
	public Product() {}
	public Product(String text, int price, int quantity )
	{

		this.prtext = text;
		this.prprice = price;
		this.prquant = quantity;
	}
	
	public void setname(String t)
	{
		this.prtext=t;
	}
	public void setprice(int p)
	{
		this.prprice=p;
	}
	
	public int getQuantity() {
    	return prquant;
    }
    public void setQuantity(int quantity) {
    	prquant = quantity;
    }
    public int getPrice() {
    	return  prprice;
    }
    public void setPrice(int price) {
    	prprice = price;
    }
    public int getPrId() {
    	return prid;
    }
    public void setPrId(int prId) {
    	prid = prId;
    }
    public String getPrName() {
    	return prtext;
    }
    public void setPrName(String prName) {
    	prtext = prName;
    }

    
	
	@Override
	public String toString() {
		return "Product: [id=" + prid + ", text=" + prtext + ", price="+prprice+", quantity="+ prquant+"]";
	}	
	
}
