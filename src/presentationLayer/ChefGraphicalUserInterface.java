package presentationLayer;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Restaurant;

public class ChefGraphicalUserInterface extends JFrame implements Observer {

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Restaurant)
		{
			Restaurant r = (Restaurant)arg0;
			ArrayList<MenuItem> list = (ArrayList<MenuItem>)arg1;
			processOrder(list);
		}
		
		
	}
	
	public void processOrder(ArrayList<MenuItem> list)
	{
		for(MenuItem i : list)
		{
			if(i instanceof CompositeProduct)
			{
				
				area.append(" Cooking " + i + "\n");
			}
		}
		area.append("\n\n");
	}
	
	private JPanel contentPane;
	private JTextArea area;
	
	public ChefGraphicalUserInterface()
	{
		setTitle("Chef");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 471);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    area = new JTextArea();
		contentPane.add(area, BorderLayout.CENTER);
		setContentPane(contentPane);
	}

}
