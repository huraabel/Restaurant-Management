package businessLayer;

import java.util.ArrayList;

public interface RestaurantProcessing {
	
	//ADMIN
	public void createNewMenuItem(String name, double price, boolean base);
	public void deleteMenuItem(Object o);
	public boolean editMenuItem(Object o,String name, double price);
	
	
	//WAITER
	public void createNewOrder(ArrayList<MenuItem> list);
	public void createBill(Order o);
	public double computePrice(Order o);
}
