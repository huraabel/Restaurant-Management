package presentationLayer;

import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Restaurant;
import dataLayer.FileWriter;
import dataLayer.RestaurantSerializator;

public class App {

	public static void main(String[] args) {
		
		
				Restaurant r = new Restaurant("Abel's");
				ChefGraphicalUserInterface chefFrame = new ChefGraphicalUserInterface();
				r.attach(chefFrame);
				chefFrame.setVisible(true);
				
				RestaurantSerializator rs = (FileWriter.deserialize("serial.txt"));
				r.setMenu(rs.getRestaurantMenu());
				
				AdministratorGraphicalUserInterface adminFrame = new AdministratorGraphicalUserInterface(r);
				
				DefaultTableModel d = adminFrame.getDTM();
				
				d.addColumn("Name");
				d.addColumn("Price");
				d.addColumn("Base");
				
				Iterator i = r.getMenu().iterator();
				while(i.hasNext())
				{
					MenuItem m = (MenuItem)(i.next());
					Object[] ob = new Object[3];
					ob[0] = m.getName();
					ob[1] = (Double)m.getPrice();
					ob[2] = (Boolean)m.isBase();
					
					d.addRow(ob);
				}
				d.fireTableDataChanged();
				adminFrame.setDTM(d);
				adminFrame.setMenuTableModel();
				
				adminFrame.setVisible(true);
				
				JTable j = adminFrame.getMenuTableClone();
				WaiterGraphicalUserInterface waiterframe = new WaiterGraphicalUserInterface(r,j);
				waiterframe.setVisible(true);
				
			
				
		
	}

}
