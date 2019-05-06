package dataLayer;


import java.io.Serializable;
import java.util.HashSet;

import businessLayer.MenuItem;
import businessLayer.Restaurant;
public class RestaurantSerializator implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashSet<MenuItem> restaurantMenu = new HashSet<MenuItem>();
	
	public HashSet<MenuItem> getRestaurantMenu()
	{
		return  restaurantMenu;
	}
	
	public void setRestaurantMenu(HashSet<MenuItem> r)
	{
		this.restaurantMenu = r;
	}
	
}
 