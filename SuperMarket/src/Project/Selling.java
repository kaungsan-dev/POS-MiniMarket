package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;

public class Selling extends JFrame {

	private JPanel contentPane;
	private JTextField productQuantity;
	private JTextField productName;
	private JTextField ID;
	private JLabel TotalAmount;
	private int avaliableQuantity;
	private int price;
	private JTextArea BillText;
//	private String quantity;
	private int total = 0;
	private int overallTotalAmount = 0;
	private static String Url = "jdbc:mysql://localhost:3306/minimarket?useSSL=false";
	private static String User = "root";
	private static String Password = "admin";
	DefaultTableModel defaultTableModel;
	DefaultTableModel defaultTableModel1;
	private JTable productList;

	/**
	 * Launch the application.
	 * @return 
	 */
	

	public void selectProduct(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting To Database");
			
			Connection conn = DriverManager.getConnection(Url, User, Password);
			System.out.println("Select Data from Table");
			
			String sql = "select * from product";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		
			
			while(rs.next()){
				
					String id = rs.getString("product_id");
					String productName = rs.getString("product_name");
	                String categoryName = rs.getString("category_name");
	                String price = rs.getString("price");
	                String quantity = rs.getString("qty");
	          
	                
	                defaultTableModel.addRow(new Object[]{id, productName, categoryName, price, quantity});//Adding row in Table
	          
				
			}
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			System.out.println("Error" + ex);
		}
		
	}
	
	public void update(){
		TotalAmount.setText("$: " + overallTotalAmount);
		int updateStock = avaliableQuantity - Integer.valueOf(productQuantity.getText());
		System.out.println(updateStock);
		int id = Integer.parseInt(ID.getText());
		System.out.println(id);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting To Database");
			
			Connection conn = DriverManager.getConnection(Url, User, Password);
			System.out.println("Select Data from Table");
			Statement stmt = conn.createStatement();
			String sql = "update product set qty='"+updateStock+"'where product_id="+ id;
			stmt.executeUpdate(sql);
			DefaultTableModel model = (DefaultTableModel) productList.getModel();
			model.setRowCount(0);
			selectProduct();
			
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Selling frame = new Selling();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Selling() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				selectProduct();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1087, 799);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(177, 216, 183));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(118, 185, 71));
		panel_3.setBounds(15, 29, 144, 714);
		contentPane.add(panel_3);
		
		JButton logout = new JButton("Logout");
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    new Login().setVisible(true);
                    
                }
			}
			
		});
	
		logout.setBackground(new Color(0, 139, 139));
		logout.setForeground(Color.white);
		logout.setFont(new Font("Arial", Font.PLAIN, 18));
		logout.setBounds(10, 674, 124, 29);
		panel_3.add(logout);
		
		JPanel panel = new JPanel();
		panel.setBounds(174, 29, 901, 714);
		panel.setBackground(new Color(	118, 185, 71));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(15, 89, 353, 168);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(15, 16, 69, 20);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quantity: ");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(15, 85, 102, 20);
		panel_1.add(lblNewLabel_1);
		
		productQuantity = new JTextField();
		productQuantity.setFont(new Font("Arial", Font.PLAIN, 16));
		productQuantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
			      if (!((c >= '0') && (c <= '9') ||
			         (c == KeyEvent.VK_BACK_SPACE) ||
			         (c == KeyEvent.VK_DELETE))) {
			        getToolkit().beep();
			        e.consume();
			      }
			    }
		
		});
		productQuantity.setBounds(150, 79, 182, 33);
		panel_1.add(productQuantity);
		productQuantity.setColumns(10);
		
		productName = new JTextField();
		productName.setFont(new Font("Arial", Font.PLAIN, 16));
		productName.setColumns(10);
		productName.setBounds(150, 10, 182, 33);
		panel_1.add(productName);
		
		ID = new JTextField();
		ID.setBounds(15, 135, 47, 26);
		panel_1.add(ID);
		ID.setVisible(false);
		ID.setColumns(10);
		
		JButton btnAddToBill = new JButton("Add To Bill");
		btnAddToBill.addMouseListener(new MouseAdapter() {
			int i=0;
			@Override
			public void mouseClicked(MouseEvent e) {
				if(productQuantity.getText().isEmpty() || productName.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Enter the Products to buy", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(ID.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Pease choose products from product table", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(avaliableQuantity <=0){
					JOptionPane.showMessageDialog(null, "Stock currently Run out ", "Sorry", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(Integer.valueOf(productQuantity.getText())==0){
					JOptionPane.showMessageDialog(null, "Please fill quantity more than 0", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(avaliableQuantity <= Integer.valueOf(productQuantity.getText())){
					JOptionPane.showMessageDialog(null, "Not Enough in stock", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					
					i++;
					total = price * Integer.valueOf(productQuantity.getText().toString());
					overallTotalAmount += total;
					System.out.println(overallTotalAmount);
					
					java.util.Date date=new java.util.Date();
	                java.sql.Date sqlDate=new java.sql.Date(date.getTime());
	                java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
					try {
						String Email = Login.getEmail();
						Class.forName("com.mysql.jdbc.Driver");
						System.out.println("Connecting To Database");
						
						Connection conn = DriverManager.getConnection(Url, User, Password);
						System.out.println("Inserting Data into Selling Table");
						
						String sql = "insert into selling(ProductName, Price, Quantity, Total, CreatedDate, CreatedUser)"
								+ " values ('"+productName.getText()+"','"+price+"','"+productQuantity.getText()+"', '"+total+"', '"+sqlTime+"', '"+Email+"')";
						Statement stmt = conn.createStatement();
						int result = stmt.executeUpdate(sql);
						
						if(result == 1){
							
						if(i==1){
							BillText.setText(BillText.getText()+"========Family Point========\n"+"     NUM    	 Product    	   Price    	 Quantity   	  Total\n"+
											 "     "+i+"    	 " + productName.getText()+"  	   "+price+"    	 "+productQuantity.getText()+"    	 "+ total+"\n");
						
						}
						else{
							BillText.setText(BillText.getText()+ "     " +i+"   	 " + productName.getText()+"  	   "+price+"  	 "+productQuantity.getText()+"  	 "+total+"\n");
						
						}
					
					
					}
					}catch(Exception ex){
						System.out.println("Error" + ex);
					}
					update();
				
			}
			}
		});
		btnAddToBill.setForeground(Color.WHITE);
		btnAddToBill.setFont(new Font("Arial", Font.PLAIN, 18));
		btnAddToBill.setBackground(new Color(0, 139, 139));
		btnAddToBill.setBounds(45, 273, 126, 29);
		panel.add(btnAddToBill);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productName.setText("");
				productQuantity.setText("");
				ID.setText("");
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Arial", Font.PLAIN, 18));
		btnClear.setBackground(new Color(0, 139, 139));
		btnClear.setBounds(247, 273, 88, 29);
		panel.add(btnClear);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(409, 89, 470, 286);
		panel.add(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 470, 286);
		panel_2.add(scrollPane);
		defaultTableModel = new DefaultTableModel();
		productList = new JTable(defaultTableModel);
		productList.setFont(new Font("Arial", Font.PLAIN, 14));
		productList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) productList.getModel();
				int index = productList.getSelectedRow();
				avaliableQuantity = Integer.valueOf(model.getValueAt(index, 4).toString());
				ID.setText(model.getValueAt(index, 0).toString());
				productQuantity.setText("");
				productName.setText(model.getValueAt(index, 1).toString());
				price = Integer.valueOf(model.getValueAt(index, 3).toString());
				
			}
		});
		productList.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(productList);
		defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Product Name");
        defaultTableModel.addColumn("Category Name");
        defaultTableModel.addColumn("Price");
        defaultTableModel.addColumn("Quantity");
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(409, 391, 470, 191);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 470, 254);
		panel_4.add(scrollPane_1);
		
		BillText = new JTextArea();
		BillText.setFont(new Font("Arial", Font.PLAIN, 13));
		scrollPane_1.setViewportView(BillText);
		defaultTableModel1 = new DefaultTableModel();
		defaultTableModel1.addColumn("ID");
        defaultTableModel1.addColumn("productName");
        defaultTableModel1.addColumn("price");
        defaultTableModel1.addColumn("quantity");
        defaultTableModel1.addColumn("total");
		
		TotalAmount = new JLabel("$");
		TotalAmount.setFont(new Font("Arial", Font.PLAIN, 16));
		TotalAmount.setBounds(586, 609, 69, 20);
		panel.add(TotalAmount);
		
		JButton print = new JButton("Print");
		print.setForeground(Color.WHITE);
		print.setFont(new Font("Arial", Font.PLAIN, 18));
		print.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					BillText.print();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		print.setBackground(new Color(0, 139, 139));
		print.setBounds(576, 645, 115, 29);
		panel.add(print);
		
		JLabel lblNewLabel_2 = new JLabel("Product List");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_2.setBounds(480, 30, 163, 29);
		panel.add(lblNewLabel_2);
		
	}
}
