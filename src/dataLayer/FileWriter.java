package dataLayer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import businessLayer.MenuItem;
import businessLayer.Order;

public class FileWriter {
	
	public static void serialize(RestaurantSerializator rs, String filename)
	{
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			out.writeObject(rs);
			out.close();
			file.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static RestaurantSerializator deserialize(String filename)
	{
		RestaurantSerializator rs = null;
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			
			 rs = (RestaurantSerializator) in.readObject();
			
			in.close();
			file.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rs;
	}

	


}
