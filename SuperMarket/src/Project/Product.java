package Project;

import java.awt.BorderLayout;
import java.awt.Color;
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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JTextField productID;

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
		contentPane.setBackground(new Color(177, 216, 183));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(240, 11, 364, 29);
		contentPane.add(lblNewLabel);
		
		JLabel productLabel = new JLabel("Product Name");
		productLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productLabel.setBounds(188, 61, 100, 29);
		contentPane.add(productLabel);
		
		productNameTxtField = new JTextField();
		productNameTxtField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productNameTxtField.setBounds(298, 61, 156, 29);
		contentPane.add(productNameTxtField);
		productNameTxtField.setColumns(10);
		
		JLabel priceLbl = new JLabel("Price");
		priceLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceLbl.setBounds(464, 61, 74, 29);
		contentPane.add(priceLbl);
		
		priceTxtField = new JTextField();
		priceTxtField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceTxtField.setColumns(10);
		priceTxtField.setBounds(548, 61, 162, 29);
		contentPane.add(priceTxtField);
		
		JLabel productCatLbl = new JLabel("Category");
		productCatLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productCatLbl.setBounds(202, 101, 86, 29);
		contentPane.add(productCatLbl);
		
		productCatComboBox = new JComboBox();
		productCatComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productCatComboBox.setBounds(298, 97, 156, 29);
		contentPane.add(productCatComboBox);
		CategoryComboBox();
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuantity.setBounds(464, 101, 79, 29);
		contentPane.add(lblQuantity);
		
		qtyTxtField = new JTextField();
		qtyTxtField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qtyTxtField.setColumns(10);
		qtyTxtField.setBounds(548, 101, 162, 29);
		contentPane.add(qtyTxtField);
		
		// Create Product
		JButton addBtn = new JButton("Add");
		addBtn.setBackground(new Color(	0, 139, 139));
		addBtn.setForeground(Color.white);
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
						String Email = Login.getEmail();
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
						//String sql2 = "select category_id from category where category_name ='"+CatCombo+"'";
						
						// To Insert
						String sql = "insert into product(product_name,category_name,price,qty,created_date,created_user)"
								+ "values ('"+ProductName+"','"+CatCombo+"','"+Price+"','"+Qty+"','"+sqlTime+"','"+Email+"')";
						
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
		addBtn.setBounds(188, 148, 100, 29);
		contentPane.add(addBtn);
		
		// Update Product
		JButton updateBtn = new JButton("Update");
		updateBtn.setBackground(new Color(	0, 139, 139));
		updateBtn.setForeground(Color.white);
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productId = productID.getText();
				String productName = productNameTxtField.getText();
				String CatCombo = (String) productCatComboBox.getItemAt(productCatComboBox.getSelectedIndex());
				String productPrice = priceTxtField.getText();
				String productQty = qtyTxtField.getText();
				
				try {
					java.util.Date date=new java.util.Date();
			        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			        java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					//Open Connection
					System.out.println("Connecting Database....");
					Connection conn = DriverManager.getConnection(Url,User,Pass);
					
					//Execute Query
					System.out.println("Insert Data To Table....");
					
					String sql = "update product set product_name='"+productName+"', category_name='"+CatCombo+"', price='"+productPrice+"', qty='"+productQty+"', created_date='"+sqlTime+"'where product_id = "+productId;
					
					
					Statement stmt = conn.createStatement();
					int result = stmt.executeUpdate(sql);
					DefaultTableModel model = (DefaultTableModel) productTbl.getModel();
					model.setRowCount(0);
					SelectProduct();
					
					if(result == 1) {
						JOptionPane.showMessageDialog(null, "Update Successful!", "Category Updated",JOptionPane.INFORMATION_MESSAGE);
						productNameTxtField.setText("");
						priceTxtField.setText("");
						qtyTxtField.setText("");
					}
				} catch (Exception ex) {
					System.out.println("Error " + ex);
				}
			}
		});
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateBtn.setBounds(327, 148, 100, 29);
		contentPane.add(updateBtn);
		
		// Delete Product
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setBackground(new Color(	0, 139, 139));
		deleteBtn.setForeground(Color.white);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(productID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information", "Product",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					try {
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						
						//Open Connection
						System.out.println("Connecting Database....");
						Connection conn = DriverManager.getConnection(Url,User,Pass);
						
						String Pid = productID.getText();
						
						//Execute Query
						System.out.println("Insert Data To Table....");
						
						String sql = "delete from product where product_id = "+Pid;
						
						
						Statement stmt = conn.createStatement();
						int result = stmt.executeUpdate(sql);
						DefaultTableModel model = (DefaultTableModel) productTbl.getModel();
						model.setRowCount(0);
						SelectProduct();
						
						if(result == 1) {
							JOptionPane.showMessageDialog(null, "Delete Successful!", "Category Deleted",JOptionPane.INFORMATION_MESSAGE);
							productNameTxtField.setText("");
							priceTxtField.setText("");
							qtyTxtField.setText("");
						}
					}
					catch(Exception ex) {
						System.out.println("Error " + ex);
					}
				}
			}
		});
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		deleteBtn.setBounds(454, 148, 108, 29);
		contentPane.add(deleteBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Products List");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(240, 194, 364, 29);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 233, 605, 241);
		contentPane.add(scrollPane);
		
		//Select Row Table
		productTbl = new JTable();
		productTbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)productTbl.getModel();
				int index = productTbl.getSelectedRow();
				productID.setText(model.getValueAt(index, 0).toString());
				productNameTxtField.setText(model.getValueAt(index, 1).toString());
				productCatComboBox.setSelectedItem(model.getValueAt(index, 2));
				priceTxtField.setText(model.getValueAt(index, 3).toString());
				qtyTxtField.setText(model.getValueAt(index, 4).toString());
			}
		});
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
		
		productID = new JTextField();
		productID.setBounds(188, 36, 86, 20);
		contentPane.add(productID);
		productID.setColumns(10);
		productID.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(	118, 185, 71));
		panel.setBounds(0, 50, 143, 435);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Redirect To Category Page
		JButton menuProductBtn = new JButton("Categories");
		menuProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Category().setVisible(true);
			}
		});
		menuProductBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		menuProductBtn.setBounds(10, 55, 123, 33);
		panel.add(menuProductBtn);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.addMouseListener(new MouseAdapter() {
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
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 18));
		btnLogout.setBounds(10, 391, 123, 33);
		btnLogout.setBackground(new Color(0, 139, 139));
		panel.add(btnLogout);
		
		// Redirect To Manage Seller
		JButton btnSeller = new JButton("Seller");
		btnSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SellerManagement().setVisible(true);
			}
		});
		btnSeller.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSeller.setBounds(10, 11, 123, 33);
		panel.add(btnSeller);
		
		// Redirect To SellList Page
		JButton menuProductBtn_1_1 = new JButton("SellList");
		menuProductBtn_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SellList().setVisible(true);
			}
		});
		menuProductBtn_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		menuProductBtn_1_1.setBounds(10, 99, 123, 33);
		panel.add(menuProductBtn_1_1);
		
		JButton btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			private JasperPrint jprint;
			
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					//Open Connection
					System.out.println("Connecting Database....");
					Connection conn = DriverManager.getConnection(Url,User,Pass);
					String sql = "select * from product";
					
					JasperDesign jdesign = JRXmlLoader.load("D:\\Dont_Delete\\KBTC\\JavaFinalProject\\SuperMarket\\src\\Project\\StockReport.jrxml");
					
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(sql);
					
					jdesign.setQuery(updateQuery);
					
					JasperReport Jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jasperPrint = JasperFillManager.fillReport(Jreport, null, conn);
					
					JasperViewer.viewReport(jasperPrint, false);
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnReport.setForeground(Color.WHITE);
		btnReport.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReport.setBackground(new Color(0, 139, 139));
		btnReport.setBounds(592, 148, 108, 29);
		contentPane.add(btnReport);
	}
}
