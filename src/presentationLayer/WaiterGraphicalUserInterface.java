package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import businessLayer.RestaurantProcessing;

public class WaiterGraphicalUserInterface extends JFrame implements RestaurantProcessing{
	
	private int make=1;
	private Restaurant r;
	private JPanel contentPane;
	private static JTable menuTable;
	private JTable orderTable;
	private static DefaultTableModel ordDTM;
	private JTable itemsTable;
	private JTextField numeTextField;
	
	public String getNumeTextField() {
		return numeTextField.getText();
	}

	public void setNumeTextField(String numeTextField) {
		this.numeTextField.setText( numeTextField);
	}

	public String getPriceTextField() {
		return priceTextField.getText();
	}

	public void setPriceTextField(String priceTextField) {
		this.priceTextField.setText( priceTextField);
	}

	public String getBaseTextField() {
		return baseTextField.getText();
	}

	public void setBaseTextField(String baseTextField) {
		this.baseTextField.setText(baseTextField);
	}

	private JTextField priceTextField;
	private JTextField baseTextField;
	private JTextField orderIdTextField;
	
	public String getOrderIdTextField() {
		return orderIdTextField.getText();
	}

	public void setOrderIdTextField(String orderIdTextField) {
		this.orderIdTextField.setText(orderIdTextField);
	}

	public static DefaultTableModel getOrdDTM() {
		return ordDTM;
	}
	
	public static void setOrdDTM(DefaultTableModel m)
	{
		ordDTM = m;
	}
	
	
	

	/**
	 * Create the frame.
	 */
	public WaiterGraphicalUserInterface(Restaurant r, JTable menuTable) {
		this.r =r;
		setTitle("Waiter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Place Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				make = 1;
				itemsTable.setModel(new DefaultTableModel());
				
				createNewOrder(r.getCurrentOrder());
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnCreateBill = new JButton("Create Bill");
		btnCreateBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Order o = new Order(Integer.parseInt(getOrderIdTextField()));
				createBill(o);
				
			}
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		orderTable = new JTable();
		orderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = orderTable.getSelectedRow();
		        TableModel model =orderTable.getModel();
		        setOrderIdTextField(model.getValueAt(i,0).toString());
			}
		});
		scrollPane_1.setViewportView(orderTable);
		
		this.menuTable = menuTable ;//new JTable();
		menuTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				menuTableCLicked(evt);
			}
		});
		scrollPane.setViewportView(menuTable);
		
		itemsTable = new JTable();
		scrollPane_2.setViewportView(itemsTable);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		        DefaultTableModel model2 = ( DefaultTableModel)itemsTable.getModel();
		        if(make == 1)
		        {
		        	model2.addColumn("Name");
		        	model2.addColumn("Price");
		        	model2.addColumn("Base");
		        	make=0;
		        }
		        Object[] ob = new Object[3];
		        ob[0] = getNumeTextField();
		        ob[1] = getPriceTextField();
		        ob[2] = getBaseTextField();
		        MenuItem m ;
		        if(getBaseTextField().equals("true"))
		        {
		        	m = new BaseProduct(getNumeTextField(),Double.parseDouble(getPriceTextField()),1,true);
		        }
		        else
		        {
		        	m = new CompositeProduct(getNumeTextField(),Double.parseDouble(getPriceTextField()),1,false);
		        }
		        r.addToCurrentOrder(m);
		  
		        model2.addRow( ob);
		       
		        
		        model2.fireTableDataChanged();
		        itemsTable.setModel(model2);
		        
			}
		});
		
		numeTextField = new JTextField();
		numeTextField.setColumns(10);
		
		priceTextField = new JTextField();
		priceTextField.setColumns(10);
		
		baseTextField = new JTextField();
		baseTextField.setColumns(10);
		
		orderIdTextField = new JTextField();
		orderIdTextField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(71)
							.addComponent(btnAddItem)
							.addGap(51)
							.addComponent(btnNewButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(17)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(numeTextField, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
											.addGap(26)
											.addComponent(priceTextField, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(baseTextField, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
											.addGap(20)))))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(126)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(orderIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCreateBill, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(numeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(baseTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(priceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(orderIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreateBill)
						.addComponent(btnNewButton)
						.addComponent(btnAddItem)))
		);
		
		
		contentPane.setLayout(gl_contentPane);
	}
	

	public void menuTableCLicked(java.awt.event.MouseEvent evt) {
		int i = menuTable.getSelectedRow();
        TableModel model =menuTable.getModel();
        setNumeTextField(model.getValueAt(i,0).toString());
        setPriceTextField(model.getValueAt(i,1).toString());
        setBaseTextField(model.getValueAt(i,2).toString());
	}
	
	
	public static void setMenuTableModel(DefaultTableModel m)
	{
		menuTable.setModel(m);
	}
	public void setOrderTableModel()
	{
		orderTable.setModel(ordDTM);
	}
	
	private void remakeOrderTable()
	{
		setOrdDTM(null);
		DefaultTableModel d = new DefaultTableModel();
		d.addColumn("OrderID");
		d.addColumn("Date");
		//d.addColumn("Base");
		
		Iterator i =r.getOrderList().iterator();
		while(i.hasNext())
		{
			Order m = (Order)(i.next());
			Object[] ob = new Object[2];
			ob[0] = m.getOrderID();
			ob[1] = m.getDate();
			d.addRow(ob);
		}
		
		d.fireTableDataChanged();
		setOrdDTM(d);
		setOrderTableModel();
		
	}

	@Override
	public void createNewMenuItem(String name, double price, boolean base) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMenuItem(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean editMenuItem(Object o, String name, double price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createNewOrder(ArrayList<MenuItem> list) {
		
		r.createNewOrder(list);
		remakeOrderTable();
		r.flushCurrentOrder();
	}

	@Override
	public void createBill(Order o) {
		r.createBill(o);
		r.getOrderList().remove(o);
		r.getOrders().remove(o);
		r.flushCurrentOrder();
		JOptionPane.showMessageDialog(new JFrame(), "Bill Created!", null, JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	public double computePrice(Order o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
