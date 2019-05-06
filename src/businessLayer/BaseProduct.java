package businessLayer;

import java.util.Objects;

public class BaseProduct extends MenuItem {


	
	public BaseProduct(String bpName, double bpPrice,int cantitate,boolean base) {
		super(bpName,bpPrice,cantitate);	
		this.setBase(base); // true
	}


	@Override
	public double computePrice()
	{
		return this.getPrice() * this.getCantitate();
	}
	
	
	
	
	
}
