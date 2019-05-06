package businessLayer;

import java.io.*;
import java.util.*;
import presentationLayer.ChefGraphicalUserInterface;

public class Restaurant extends Observable implements RestaurantProcessing, Serializable {
	private String restaurantName;
	private HashSet<MenuItem> restaurantMenu = new HashSet<MenuItem>();
	private static ArrayList<MenuItem> tempItems = new ArrayList<MenuItem>();
	private ArrayList<Order> orderList = new ArrayList<Order>();
	private HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<Order, ArrayList<MenuItem>>();
	public ArrayList<ChefGraphicalUserInterface> observers = new ArrayList<ChefGraphicalUserInterface>();
	public ArrayList<Order> getOrderList(){
		return orderList;
	}
	public void attach(Observer o) {
		observers.add((ChefGraphicalUserInterface) o);
	}
	public void detach(Observer o) {
		observers.remove(o);
	}
	public void notifyChef() {
		int ok = 0;
		for (MenuItem j : getCurrentOrder()) {
			if (j instanceof CompositeProduct) {
				ok = 1;}}
		if (ok == 1) {
			for (int i = 0; i < observers.size(); i++) {
				observers.get(i).update(this,  getCurrentOrder());
			}
		}
	}
	public static ArrayList<MenuItem> getTempItems(){
		return tempItems;
	}
	public Restaurant(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public static void addToCurrentOrder(MenuItem m) {
		tempItems.add(m);
	}
	public Order getOrderFromList() {
		return orderList.get(orderList.size() - 1);
	}
	public static ArrayList<MenuItem> getCurrentOrder() {
		return tempItems;
	}
	public static void flushCurrentOrder() {
		while (tempItems.size() > 0)
			tempItems.remove(0);
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public HashSet<MenuItem> getMenu() {
		return restaurantMenu;
	}
	public void setMenu(HashSet<MenuItem> menu) {
		restaurantMenu = menu;
	}
	public HashMap<Order, ArrayList<MenuItem>> getOrders() {
		return orders;
	}
	public boolean wellFormed() {
		Iterator i = restaurantMenu.iterator();
		while(i.hasNext())
		{	MenuItem m = (MenuItem)i.next();
			if (m.getName() == null || m.getPrice() <= 0 ) return false;
		}
		return true;
	}

	/**
	 * Adds a new MenuItem to the restaurantMenu HashSet
	 * @invariant wellFormed
	 * @pre name!=null && price > 0 
	 * @post restaurantMenu.size() == restaurantMenu.size()@pre + 1
	 */
	@Override
	public void createNewMenuItem(String name, double price, boolean base) {
		assert name!=null : "name is null";
		assert price > 0 : "price less than 0"; assert wellFormed() == true;
		int size = restaurantMenu.size();
		if (base == true)
			restaurantMenu.add(new BaseProduct(name, price, 0, true));
		else
			restaurantMenu.add(new CompositeProduct(name, price, 0, false));
		assert restaurantMenu.size() == size + 1 : "restaurantMenu didnt increment"; assert wellFormed() == true;
	}
	
	/**
	 * Deletes a MenuItem from restaurantMenu HashSet
	 * @invariant wellFormed
	 * @pre o != null && o instanceof MenuItem && wellFormed
	 * @post restaurantMenu.size() == restaurantMenu.size()@pre - 1 && wellFormed
	 */
	@Override
	public void deleteMenuItem(Object o) {
		assert o != null;
		assert o instanceof MenuItem : " not a MenuItem"; assert wellFormed() == true;
		int size = restaurantMenu.size();
		restaurantMenu.remove(((MenuItem) o));
		assert restaurantMenu.size() == size - 1 : "restaurantMenu didnt decrement"; assert wellFormed() == true;
	}
	
	/**
	 * Takes out a MenuItem and replaces it with a new one, returns true if Object o was added successfully, false otherwise
	 * @invariant wellFormed
	 * @pre o instanceof MenuItem && o != null & name!=null && price >0 && wellFormed
	 * @post restaurantMenu.size() == restaurantMenu.size() @pre && wellFormed
	 */
	@Override
	public boolean editMenuItem(Object o, String name, double price) {
		assert o != null : " o is null";
		assert o instanceof MenuItem : " o is not a MenuItem";
		assert name != null : "name is null";
		assert price > 0 : "price less than 0"; assert wellFormed() == true;
		int size = restaurantMenu.size();
		MenuItem m = (MenuItem) o;
		if (restaurantMenu.contains(m) == true) {
			restaurantMenu.remove(m);
			m.setName(name);
			m.setPrice(price);
			restaurantMenu.add(m);
			assert size == restaurantMenu.size() ;
			return true;
		}
		assert size == restaurantMenu.size() ;assert wellFormed() == true;
		return false;
	}

	/**
	 * Creates a new Order, puts it in the orderList, and maps it to a ArrayList<MenuItem>
	 * @pre list is instanceof ArrayList<?> && list != null
	 * @post orderList.size() == orderList.size() @ pre && orders.size() == orders.size() @pre
	 */
	@Override
	public void createNewOrder(ArrayList<MenuItem> list) {
		assert list != null : "list is null";
		assert list instanceof ArrayList<?> : " list not a ArrayList  ";
		int size1 = orderList.size();
		int size2 = orders.size();
		ArrayList<MenuItem> copyList = (ArrayList<MenuItem>) list.clone();
		Order o = new Order((Integer) ((int) (Math.random() * (1000 - 1))) + 1);
		orderList.add(o);
		orders.put(o, copyList);
		notifyChef();
		assert orderList.size() == size1 + 1;
		assert orders.size() == size2 + 1;
	}
	
	/**
	 * Creates a bill for the Order o
	 * @pre o!=null
	 * @post @nochange
	 */
	@Override
	public void createBill(Order o) {
		assert o != null : " o is null";
		try {
			BufferedWriter writer = null;
			ArrayList<MenuItem> list = orders.get(o);
			File logFile = new File("Bill_" + o.getOrderID() + ".txt");
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write(o.toString() + "\n");
			writer.write(list.toString() + "\n");
			writer.write("Total:" + computePrice(o));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Computes price for the items in the Order o
	 * @pre o != null
	 * @post @result > 0
	 */
	@Override
	public double computePrice(Order o) {
		assert o != null : " o is null";
		ArrayList<MenuItem> list = orders.get(o);
		double p = 0;
		for (MenuItem i : list) {
			p = p + i.computePrice();
		}
		assert p > 0 : " price is wrong";
		return p;
	}
}
