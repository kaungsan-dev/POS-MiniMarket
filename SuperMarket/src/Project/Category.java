package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;

import net.proteanit.sql.DbUtils;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Category extends JFrame {
	
	private static String Url = "jdbc:mysql://localhost:3306/minimarket?useSSL=false";
	private static String User= "root";
	private static String Pass = "admin";

	private JPanel contentPane;
	private JTextField categoryField;
	JButton addBtn,btnUpdate,btnDelete;
	private JTable categoryTblList;
	private JTextField categoryId;

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
		setBounds(100, 100, 917, 831);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel createCategoryLbl = new JLabel("Create Categories");
		createCategoryLbl.setForeground(Color.BLUE);
		createCategoryLbl.setHorizontalAlignment(SwingConstants.CENTER);
		createCategoryLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		createCategoryLbl.setBounds(134, 11, 564, 61);
		contentPane.add(createCategoryLbl);
		
		JLabel categoryLal = new JLabel("Category Name");
		categoryLal.setForeground(Color.BLUE);
		categoryLal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		categoryLal.setHorizontalAlignment(SwingConstants.CENTER);
		categoryLal.setBounds(60, 103, 164, 37);
		contentPane.add(categoryLal);
		
		categoryField = new JTextField();
		categoryField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		categoryField.setBounds(251, 100, 218, 43);
		contentPane.add(categoryField);
		categoryField.setColumns(10);
		
		
		
		// Create Category Action
		addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String categoryName = categoryField.getText();
				
				if(categoryField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information", "Category",JOptionPane.INFORMATION_MESSAGE);
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
					String sql = "insert into category(category_name,created_date)"
							+ "values ('"+categoryName+"', '"+sqlTime+"')";
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
		addBtn.setBounds(504, 100, 118, 43);
		contentPane.add(addBtn);
		
		// Update Category
		btnUpdate = new JButton("Update");
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
		btnUpdate.setBounds(632, 100, 118, 43);
		contentPane.add(btnUpdate);
		
		// Delete Category
		btnDelete = new JButton("Delete");
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
		btnDelete.setBounds(758, 100, 118, 43);
		contentPane.add(btnDelete);
		
		JLabel allCategoryListLbl = new JLabel("All Category Lists");
		allCategoryListLbl.setHorizontalAlignment(SwingConstants.CENTER);
		allCategoryListLbl.setForeground(Color.BLUE);
		allCategoryListLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		allCategoryListLbl.setBounds(134, 176, 564, 61);
		contentPane.add(allCategoryListLbl);
		
		String []columns={"ID", "CategoryName", "CreatedDate", "CreatedUser"};
		String [][]data={{"1","Fruit","2021-09-17","Admin"}};
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(84, 248, 792, 249);
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
		categoryId.setBounds(70, 137, 74, 18);
		categoryId.setVisible(false);
		contentPane.add(categoryId);
	}
}