package businessLayer;

import java.io.Serializable;
import java.util.Objects;

public abstract class MenuItem implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private int cantitate;
	private String name;
	private double price;
	private boolean base;
	
	public MenuItem(String name, double price) {
		cantitate = 0;
		this.name =name;
		this.price = price;
	}
	
	public MenuItem(String name, double price,int cant) {
		cantitate = cant;
		this.name =name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	
	public int getCantitate() {
		return cantitate;
	}
	
	
	
	
	public boolean isBase() {
		return base;
	}

	public void setBase(boolean base) {
		this.base = base;
	}

	public abstract double computePrice();
	
	
	public String toString() {
		if (base == true)
			return name + " " + price +" "+cantitate+ " base";
		else
			return name + " " + price +" "+cantitate+  " composite";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj) { return true;}
		if (obj == null) { return false; }  
		if (getClass() != obj.getClass()) { return false; } 
		
		BaseProduct o;
		CompositeProduct co;
		
		if( obj instanceof BaseProduct)
			 {o = (BaseProduct) obj;
			 
			 if(o.getName().equals(this.getName()))
					{return true;}
			 
				return false;			 	
			 }
		else if ( obj instanceof CompositeProduct)
			 {co = (CompositeProduct) obj;
			if(co.getName().equals(this.getName()))
				return true;
			
			return false;
			 }
		
		return false;
	}
	
	
}
