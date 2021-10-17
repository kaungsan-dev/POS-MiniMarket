package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Product extends JFrame {
	
	private static String Url = "jdbc:mysql://localhost:3306/minimarket?useSSL=false";
	private static String User= "root";
	private static String Pass = "admin";

	private JPanel contentPane;
	private JTextField productNameTxtField;
	private JTextField priceTxtField;
	private JTextField qtyTxtField;
	private JTable productTbl;
	private JComboBox productCatComboBox;
	private JTextField productIDTxtField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product frame = new Product();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void CategoryComboBox() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Open Connection
			System.out.println("Connecting Database....");
			Connection conn = DriverManager.getConnection(Url,User,Pass);
			
			Statement stmt = conn.createStatement();
			ResultSet Rs = stmt.executeQuery("select * from category");
			
			while(Rs.next()) {
				productCatComboBox.addItem(Rs.getString("category_name"));
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	// To Show Category Data in Table
				public void SelectProduct() {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						
						//Open Connection
						System.out.println("Connecting Database....");
						Connection conn = DriverManager.getConnection(Url,User,Pass);
						
						Statement stmt = conn.createStatement();
						ResultSet Rs = stmt.executeQuery("select * from product");
						productTbl.setModel(DbUtils.resultSetToTableModel(Rs));
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}

	/**
	 * Create the frame.
	 */
	public Product() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				SelectProduct();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(240, 11, 364, 29);
		contentPane.add(lblNewLabel);
		
		JLabel productLabel = new JLabel("Product Name");
		productLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productLabel.setBounds(37, 61, 147, 29);
		contentPane.add(productLabel);
		
		productNameTxtField = new JTextField();
		productNameTxtField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productNameTxtField.setBounds(199, 61, 207, 29);
		contentPane.add(productNameTxtField);
		productNameTxtField.setColumns(10);
		
		JLabel priceLbl = new JLabel("Price");
		priceLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceLbl.setBounds(428, 61, 147, 29);
		contentPane.add(priceLbl);
		
		priceTxtField = new JTextField();
		priceTxtField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceTxtField.setColumns(10);
		priceTxtField.setBounds(505, 61, 207, 29);
		contentPane.add(priceTxtField);
		
		JLabel productCatLbl = new JLabel("Category");
		productCatLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productCatLbl.setBounds(37, 108, 147, 29);
		contentPane.add(productCatLbl);
		
		productCatComboBox = new JComboBox();
		productCatComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productCatComboBox.setBounds(199, 101, 207, 37);
		contentPane.add(productCatComboBox);
		CategoryComboBox();
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuantity.setBounds(428, 101, 147, 29);
		contentPane.add(lblQuantity);
		
		qtyTxtField = new JTextField();
		qtyTxtField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qtyTxtField.setColumns(10);
		qtyTxtField.setBounds(505, 101, 207, 29);
		contentPane.add(qtyTxtField);
		
		// Create Product
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ProductName = productNameTxtField.getText();
				String CatCombo = (String) productCatComboBox.getItemAt(productCatComboBox.getSelectedIndex());
				String Price = priceTxtField.getText();
				String Qty = qtyTxtField.getText();
				
				if(productNameTxtField.getText().isEmpty() || priceTxtField.getText().isEmpty() || qtyTxtField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information", "Product",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					try{
						java.util.Date date=new java.util.Date();
				        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
				        java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						
						//Open Connection
						System.out.println("Connecting Database....");
						Connection conn = DriverManager.getConnection(Url,User,Pass);
						
						//Execute Query
						System.out.println("Insert Data To Table .....");
						
						// Data Fetch for FK to get Category_id
						//select minimarket.category.category_id from minimarket.category where minimarket.category.category_name="Fruits";
						//String sql2 = "select category_id from category where category_name ='"+CatCombo+"'";
						String sql2 = "select category_id from category where category_name ='"+CatCombo+"'";
						
						// To Insert
						String sql = "insert into product(product_name,category_id,price,qty,created_date)"
								+ "values ('"+ProductName+"','"+sql2+"','"+Price+"','"+Qty+"','"+sqlTime+"')";
						
						Statement stmt = conn.createStatement();
						int result = stmt.executeUpdate(sql);
						if(result == 1) {
							JOptionPane.showMessageDialog(null, "Insert Successful!", "Product Created",JOptionPane.INFORMATION_MESSAGE);
							productNameTxtField.setText("");
							priceTxtField.setText("");
							qtyTxtField.setText("");
							SelectProduct();
						}
					}
					catch(Exception ex) {
						System.out.println("Error " + ex);
					}
				}
				
			}
		});
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addBtn.setBounds(90, 148, 147, 29);
		contentPane.add(addBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateBtn.setBounds(310, 148, 147, 29);
		contentPane.add(updateBtn);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		deleteBtn.setBounds(527, 148, 147, 29);
		contentPane.add(deleteBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Products List");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(240, 194, 364, 29);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 233, 686, 241);
		contentPane.add(scrollPane);
		
		productTbl = new JTable();
		productTbl.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product ID", "Product Name", "Product Category", "Price", "Quantity", "Created Date", "Created User"
			}
		));
		productTbl.getColumnModel().getColumn(1).setPreferredWidth(91);
		productTbl.getColumnModel().getColumn(2).setPreferredWidth(103);
		scrollPane.setViewportView(productTbl);
		
		productIDTxtField = new JTextField();
		productIDTxtField.setBounds(199, 30, 86, 20);
		contentPane.add(productIDTxtField);
		productIDTxtField.setColumns(10);
		productIDTxtField.setVisible(false);
	}
}
