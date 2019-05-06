package presentationLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import businessLayer.RestaurantProcessing;
import dataLayer.RestaurantSerializator;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdministratorGraphicalUserInterface extends JFrame implements RestaurantProcessing {
	
	private Restaurant r;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField priceTextField;
	private JTable menuTable;
	private JRadioButton baseItemRadioButton;
	private static DefaultTableModel dtm = new DefaultTableModel();
	private JTextField newNameTextField;
	private JTextField newPriceTextField;
	
	
	/**
	 * Create the frame.
	 */
	public AdministratorGraphicalUserInterface(Restaurant r) {
		this.r = r;
		setTitle("Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 734, 398);
		contentPane.add(panel);
		
		JButton deleteMenuItemButton = new JButton("Delete Menu Item");
		deleteMenuItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = menuTable.getSelectedRow();
		        TableModel model =menuTable.getModel();
		        String s = model.getValueAt(i,2).toString();
		        MenuItem m;
		        boolean b;
				if(s == "true")
					b = true;
				else
					b = false;
				if(b == true)
				{
					m = new BaseProduct( model.getValueAt(i,0).toString(), Double.parseDouble( model.getValueAt(i,1).toString()),
							0,b);
				}
				else
				{
					m = new CompositeProduct( model.getValueAt(i,0).toString(), Double.parseDouble( model.getValueAt(i,1).toString()),
							0,b);
				}
				deleteMenuItem(m);
			}
		});
		
		JLabel label = new JLabel("Name:");
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("Price:");
		
		priceTextField = new JTextField();
		priceTextField.setColumns(10);
		
		baseItemRadioButton = new JRadioButton("Base Element");
		
		JButton addMenuItemButton = new JButton("Add New Menu Item");
		addMenuItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(baseItemRadioButton.isSelected())
				{
					double d = Double.parseDouble(getPriceTextField());
					createNewMenuItem(getNameTextField(), d , true);
					
				}
				else
				{
					double d = Double.parseDouble(getPriceTextField());
					createNewMenuItem(getNameTextField(), d , false);
					
				}
				
			}
			
		});
		
		JButton editMenuItemButton = new JButton("Edit Menu Item");
		editMenuItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i = menuTable.getSelectedRow();
		        TableModel model =menuTable.getModel();
		        String s = model.getValueAt(i,2).toString();
		        MenuItem m;
		        boolean b;
				if(s == "true")
					b = true;
				else
					b = false;
				if(b == true)
				{
					m = new BaseProduct( model.getValueAt(i,0).toString(), Double.parseDouble( model.getValueAt(i,1).toString()),
							0,b);
				}
				else
				{
					m = new CompositeProduct( model.getValueAt(i,0).toString(), Double.parseDouble( model.getValueAt(i,1).toString()),
							0,b);
				}
				editMenuItem(m, newNameTextField.getText(), Double.parseDouble(newPriceTextField.getText()));
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		JLabel lblNewName = new JLabel("New Name:");
		
		JLabel lblNewPrice = new JLabel("New Price:");
		
		newNameTextField = new JTextField();
		newNameTextField.setColumns(10);
		
		newPriceTextField = new JTextField();
		newPriceTextField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(10)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
										.addGap(14)
										.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
										.addGap(14)
										.addComponent(priceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(21)
										.addComponent(baseItemRadioButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
								.addGap(42))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(deleteMenuItemButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(editMenuItemButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(addMenuItemButton, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewName)
								.addComponent(lblNewPrice))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(newPriceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(newNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(43)))
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(label))
						.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1))
						.addComponent(priceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addComponent(baseItemRadioButton)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewName)
						.addComponent(newNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewPrice)
						.addComponent(newPriceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addComponent(addMenuItemButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(editMenuItemButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(deleteMenuItemButton)
					.addGap(31))
		);
		
		menuTable = new JTable();
		menuTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Price", "Base"
			}
		));
		menuTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				menuTableCLicked(evt);
			}
		});
		scrollPane.setViewportView(menuTable);
		panel.setLayout(gl_panel);
	}
	
	public String getNameTextField() {
		return nameTextField.getText();
	}

	public void setNameTextField(String t) {
		this.nameTextField.setText(t); 
	}

	public String getPriceTextField() {
		return priceTextField.getText();
	}

	public void setPriceTextField(String t) {
		this.priceTextField.setText(t); 
	}

	public void setMenuTableModel()
	{
		menuTable.setModel(dtm);
	}
	
	public static DefaultTableModel getDTM() {
		return dtm;
	}
	
	public static void setDTM(DefaultTableModel m)
	{
		dtm = m;
	}
	
	
	public void menuTableCLicked(java.awt.event.MouseEvent evt) {
		int i = menuTable.getSelectedRow();
        TableModel model =menuTable.getModel();
        setNameTextField(model.getValueAt(i,0).toString());
        setPriceTextField(model.getValueAt(i,1).toString());
      
	}
	
	public void addToTable(MenuItem m)
	{
		DefaultTableModel d =getDTM();
		
		Object[] ob = new Object[3];
		ob[0] = m.getName();
		ob[1] = (Double)m.getPrice();
		ob[2] = (Boolean)m.isBase();
			
		d.addRow(ob);
		
		d.fireTableDataChanged();
		setDTM(d);
		setMenuTableModel();
		WaiterGraphicalUserInterface.setMenuTableModel(d);
	}

	@Override
	public void createNewMenuItem(String name, double price, boolean base) {
		MenuItem m;
		if(baseItemRadioButton.isSelected())
		{
			m = new BaseProduct(name,price,0,base);
		}
		else
		{
			m = new CompositeProduct(name,price,0,base);
		}
		boolean b=r.getMenu().add(m);
		if(b == true)
			addToTable(m);
		createBackUp();
	}
	
	public void createBackUp() {
		RestaurantSerializator rs = new RestaurantSerializator();
		HashSet hash = (HashSet) r.getMenu().clone();
		rs.setRestaurantMenu(hash);
		dataLayer.FileWriter.serialize(rs, "serial.txt");
		
	}
	
	private void remakeMenuTable()
	{
		setDTM(null);
		DefaultTableModel d = new DefaultTableModel();
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
		setDTM(d);
		setMenuTableModel();
		WaiterGraphicalUserInterface.setMenuTableModel(d);
	}
	
	@Override
	public void deleteMenuItem(Object o) {
		
		r.deleteMenuItem(o);
		remakeMenuTable();
		createBackUp();
	}

	@Override
	public boolean editMenuItem(Object o, String name, double price) {
		
		boolean b =r.editMenuItem(o, name, price);
		remakeMenuTable();
		createBackUp();
		return b;
	}

	@Override
	public void createNewOrder(ArrayList<MenuItem> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createBill(Order o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double computePrice(Order o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public JTable getMenuTableClone()
	{
		JTable j = new JTable();
		j.setModel(menuTable.getModel());
		return j;
	}
}
