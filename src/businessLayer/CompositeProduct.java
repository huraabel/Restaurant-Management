package businessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {


	private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	
	public CompositeProduct(String pName, double pPrice,int cantitate,boolean base)
	{
		super(pName,pPrice,cantitate);	
		this.setBase(base); // false
	}
	
	public void addItem(MenuItem mi)
	{
		menuItems.add(mi);
	}

	public void removeItem(int i)
	{
		menuItems.remove(i);
	}
	
	public void getItem(int i)
	{
		menuItems.get(i);
	}
	
	@Override
	public double computePrice() {
		double p=0;
		for(MenuItem i : menuItems)
		{
			p = p +((BaseProduct)i).computePrice();
		}
		return( p + getPrice() )* this.getCantitate();
	}

	public ArrayList<MenuItem> getMenuItems(){
		return menuItems;
	}
	
	
	
}
