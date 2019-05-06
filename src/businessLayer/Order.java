package businessLayer;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.swing.JTable;

public class Order {
	private Integer orderID;
	private LocalDateTime  date;
	
	
	
	public Integer getOrderID() {
		return orderID;
	}


	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	

	public Order(Integer orderID) {
		super();
		this.orderID = orderID;
		this.date = LocalDateTime.now();
		
		
	}

	
	public String toString()
	{
		return orderID + " " + date.getYear() + "/" + date.getMonth() + "/" + date.getDayOfMonth()
		+ " " + date.getHour() + ":" + date.getMinute() +":" + date.getSecond(); 
		
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) { return true;}
		if (obj == null) { return false; }  
		if (getClass() != obj.getClass()) { return false; } 
		
		Order o = (Order) obj;
		
		for(int i=0; i< this.getMyFields().length; i++)
		{
			if( !Objects.equals(this.getMyFields()[i], o.getMyFields()[i]))
				return false;
		}
		return true;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(getMyFields());
	}
	
	private Object[] getMyFields(){
	    Object[] result = {  orderID };
	    return result;
	  }
	
}
