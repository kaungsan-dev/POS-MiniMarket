package Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
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

public class Category extends JFrame {
	
	private static String Url = "jdbc:mysql://localhost:3306/minimarket?useSSL=false";
	private static String User= "root";
	private static String Pass = "admin";

	private JPanel contentPane;
	private JTextField categoryField;
	JButton addBtn,btnUpdate,btnDelete;
	private JTable categoryTblList;
	private JTextField categoryId;
	private JButton menuProductBtn_1;
	private JButton menuProductBtn_2;
	private JButton menuProductBtn_3;
	private JButton btnSelllist;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Category frame = new Category();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// To Show Category Data in Table
			public void SelectCategory() {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					//Open Connection
					System.out.println("Connecting Database....");
					Connection conn = DriverManager.getConnection(Url,User,Pass);
					
					Statement stmt = conn.createStatement();
					ResultSet Rs = stmt.executeQuery("select * from category");
					categoryTblList.setModel(DbUtils.resultSetToTableModel(Rs));
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

	/**
	 * Create the frame.
	 */
	public Category() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				SelectCategory();
			}
		});
		// To Show Category List in table
		//SelectCategory();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(177, 216, 183));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel createCategoryLbl = new JLabel("Create Categories");
		createCategoryLbl.setForeground(Color.BLACK);
		createCategoryLbl.setHorizontalAlignment(SwingConstants.CENTER);
		createCategoryLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		createCategoryLbl.setBounds(218, 11, 564, 61);
		contentPane.add(createCategoryLbl);
		
		JLabel categoryLal = new JLabel("Category Name");
		categoryLal.setForeground(Color.BLACK);
		categoryLal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		categoryLal.setHorizontalAlignment(SwingConstants.CENTER);
		categoryLal.setBounds(271, 108, 164, 25);
		contentPane.add(categoryLal);
		
		categoryField = new JTextField();
		categoryField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		categoryField.setBounds(478, 106, 218, 28);
		contentPane.add(categoryField);
		categoryField.setColumns(10);
		
		
		
		// Create Category Action
		addBtn = new JButton("Add");
		addBtn.setBackground(new Color(	0, 139, 139));
		addBtn.setForeground(Color.white);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String categoryName = categoryField.getText();
				
				if(categoryField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information", "Category",JOptionPane.INFORMATION_MESSAGE);
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
					String sql = "insert into category(category_name,created_date,created_user)"
							+ "values ('"+categoryName+"', '"+sqlTime+"', '"+Email+"' )";
					Statement stmt = conn.createStatement();
					int result = stmt.executeUpdate(sql);
					if(result == 1) {
						JOptionPane.showMessageDialog(null, "Insert Successful!", "Category Created",JOptionPane.INFORMATION_MESSAGE);
						categoryField.setText("");
						SelectCategory();
					}
				}
				catch(Exception ex) {
					System.out.println("Error " + ex);
				}
			}
				
			}
		});
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addBtn.setBounds(329, 144, 86, 31);
		contentPane.add(addBtn);
		
		// Update Category
		btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(	0, 139, 139));
		btnUpdate.setForeground(Color.white);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//int index = categoryTblList.getSelectedRow();
				//categoryField.setText(categoryTblList.getModel().getValueAt(index, 0).toString());
				String categoryid = categoryId.getText();
				String categoryName = categoryField.getText();
				
				try {
					//System.out.println(categoryid);
					
					java.util.Date date=new java.util.Date();
			        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			        java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					//Open Connection
					System.out.println("Connecting Database....");
					Connection conn = DriverManager.getConnection(Url,User,Pass);
					
					//Execute Query
					System.out.println("Insert Data To Table....");
					
					String sql = "update category set category_name='"+categoryName+"',created_date='"+sqlTime+"'where category_id = "+categoryid;
					
					
					Statement stmt = conn.createStatement();
					int result = stmt.executeUpdate(sql);
					DefaultTableModel model = (DefaultTableModel) categoryTblList.getModel();
					model.setRowCount(0);
					SelectCategory();
					
					if(result == 1) {
						JOptionPane.showMessageDialog(null, "Update Successful!", "Category Updated",JOptionPane.INFORMATION_MESSAGE);
						categoryField.setText("");
					}
				}
				catch(Exception ex) {
					System.out.println("Error " + ex);
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(436, 144, 93, 31);
		contentPane.add(btnUpdate);
		
		// Delete Category
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(	0, 139, 139));
		btnDelete.setForeground(Color.white);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(categoryId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information", "Category",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					try {
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						
						//Open Connection
						System.out.println("Connecting Database....");
						Connection conn = DriverManager.getConnection(Url,User,Pass);
						
						String Cid = categoryId.getText();
						
						//Execute Query
						System.out.println("Insert Data To Table....");
						
						String sql = "delete from category where category_id = "+Cid;
						
						
						Statement stmt = conn.createStatement();
						int result = stmt.executeUpdate(sql);
						DefaultTableModel model = (DefaultTableModel) categoryTblList.getModel();
						model.setRowCount(0);
						SelectCategory();
						
						if(result == 1) {
							JOptionPane.showMessageDialog(null, "Delete Successful!", "Category Deleted",JOptionPane.INFORMATION_MESSAGE);
							categoryField.setText("");
						}
					}
					catch(Exception ex) {
						System.out.println("Error " + ex);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(549, 144, 86, 31);
		contentPane.add(btnDelete);
		
		JLabel allCategoryListLbl = new JLabel("All Category Lists");
		allCategoryListLbl.setHorizontalAlignment(SwingConstants.CENTER);
		allCategoryListLbl.setForeground(Color.BLACK);
		allCategoryListLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		allCategoryListLbl.setBounds(246, 196, 564, 61);
		contentPane.add(allCategoryListLbl);
		
		String []columns={"ID", "CategoryName", "CreatedDate", "CreatedUser"};
		String [][]data={{"1","Fruit","2021-09-17","Admin"}};
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(174, 268, 717, 261);
		contentPane.add(scrollPane_1);
		
		categoryTblList = new JTable();
		categoryTblList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)categoryTblList.getModel();
				int index = categoryTblList.getSelectedRow();
				categoryId.setText(model.getValueAt(index, 0).toString());
				categoryField.setText(model.getValueAt(index, 1).toString());
			}
		});
		categoryTblList.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Category Name", "Created User", "Created Date"
			}
		));
		categoryTblList.getColumnModel().getColumn(1).setPreferredWidth(112);
		categoryTblList.getColumnModel().getColumn(2).setPreferredWidth(91);
		categoryTblList.getColumnModel().getColumn(3).setPreferredWidth(110);
		scrollPane_1.setViewportView(categoryTblList);
		
		categoryId = new JTextField();
		categoryId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		categoryId.setColumns(10);
		categoryId.setBounds(708, 111, 74, 18);
		categoryId.setVisible(false);
		contentPane.add(categoryId);
		
		// Do Report
		JButton btnReport = new JButton("Report");
		btnReport.setBackground(new Color(	0, 139, 139));
		btnReport.setForeground(Color.white);
		btnReport.addActionListener(new ActionListener() {
			private JasperPrint jprint;
			
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					//Open Connection
					System.out.println("Connecting Database....");
					Connection conn = DriverManager.getConnection(Url,User,Pass);
					String sql = "select * from category";
					
					JasperDesign jdesign = JRXmlLoader.load("D:\\Dont_Delete\\KBTC\\JavaFinalProject\\SuperMarket\\src\\Project\\ReportCategory.jrxml");
					
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
		btnReport.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReport.setBounds(650, 145, 86, 30);
		contentPane.add(btnReport);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(	118, 185, 71));
		panel.setBounds(0, 90, 164, 691);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Redirect To Product Page
		menuProductBtn_1 = new JButton("Products");
		menuProductBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Product().setVisible(true);
			}
		});
		menuProductBtn_1.setFont(new Font("Arial", Font.PLAIN, 18));
		menuProductBtn_1.setBounds(10, 80, 144, 33);
		panel.add(menuProductBtn_1);
		
		menuProductBtn_2 = new JButton("Logout");
		menuProductBtn_2.setForeground(Color.WHITE);
		menuProductBtn_2.addMouseListener(new MouseAdapter() {
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
		menuProductBtn_2.setFont(new Font("Arial", Font.PLAIN, 18));
		menuProductBtn_2.setBounds(10, 414, 144, 33);
		menuProductBtn_2.setBackground(new Color(0, 139, 139));
		panel.add(menuProductBtn_2);
		
		menuProductBtn_3 = new JButton("Seller");
		menuProductBtn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SellerManagement().setVisible(true);
			}
		});
		menuProductBtn_3.setFont(new Font("Arial", Font.PLAIN, 18));
		menuProductBtn_3.setBounds(10, 36, 144, 33);
		panel.add(menuProductBtn_3);
		
		// Redirect To SellList Page
		btnSelllist = new JButton("SellList");
		btnSelllist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SellList().setVisible(true);
			}
		});
		btnSelllist.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSelllist.setBounds(10, 124, 144, 33);
		panel.add(btnSelllist);
		
		
	}
}