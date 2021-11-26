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
import javax.swing.RowFilter;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SellList extends JFrame {
	
	private static String Url = "jdbc:mysql://localhost:3306/minimarket?useSSL=false";
	private static String User = "root";
	private static String Password = "admin";

	private JPanel contentPane;
	private JTextField searchTxtField;
	private JTable sellList;
	private JTextField searchTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellList frame = new SellList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void SelectSellList() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Open Connection
			System.out.println("Connecting Database....");
			Connection conn = DriverManager.getConnection(Url,User,Password);
			
			Statement stmt = conn.createStatement();
			ResultSet Rs = stmt.executeQuery("select * from selling");
			sellList.setModel(DbUtils.resultSetToTableModel(Rs));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	String[][] data;
	private JPanel panel_1;
	private JButton menuProductBtn;
	private JButton logout;
	private JButton btnProduct;
	private JButton menuProductBtn_2;
	public SellList() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//SelectSellList();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 975, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(177, 216, 183));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(213, 141, 698, 405);
		contentPane.add(scrollPane);
		
		
		String[] column = {"ID", "Product Name", "Price", "Quantity", "Total", "Created Date", "Created User"};
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Open Connection
			System.out.println("Connecting Database....");
			Connection conn = DriverManager.getConnection(Url,User,Password);
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount from selling");
			rs.next();
			int count = rs.getInt("rowcount");
			System.out.println(count);
			
			data = new String[count][7];
			
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/minimarket?useSSL=false","root","admin");  
			Statement st=con.createStatement();  
			
			ResultSet res=st.executeQuery("SELECT * FROM selling"); 
		// Looping to store result in returning array data // 
			int i=0;
			while(res.next())  {
			 for(int j=0;j<7;j++) {
			 //System.out.print(rs.getString(j+1));
			 data[i][j]=res.getString(j+1);
			 }
			 //System.out.println();
			 i=i+1;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		sellList = new JTable(data,column);
		
		sellList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(sellList.getModel());
		sellList.setRowSorter(rowSorter);
		
		scrollPane.setViewportView(sellList);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 24, 153, 538);
		panel.setBackground(new Color(118, 185, 71));
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Redirect To SellerManagement Page
		menuProductBtn = new JButton("Seller");
		menuProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SellerManagement().setVisible(true);
			}
		});
		menuProductBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		menuProductBtn.setBounds(10, 11, 133, 33);
		panel.add(menuProductBtn);
		
		logout = new JButton("Logout");
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
		logout.setForeground(Color.WHITE);
		logout.setFont(new Font("Arial", Font.PLAIN, 18));
		logout.setBackground(new Color(0, 139, 139));
		logout.setBounds(10, 498, 133, 29);
		panel.add(logout);
		
		btnProduct = new JButton("Product");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Product().setVisible(true);
			}
		});
		btnProduct.setFont(new Font("Arial", Font.PLAIN, 18));
		btnProduct.setBounds(10, 53, 133, 33);
		panel.add(btnProduct);
		
		menuProductBtn_2 = new JButton("Category");
		menuProductBtn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Category().setVisible(true);
			}
		});
		menuProductBtn_2.setFont(new Font("Arial", Font.PLAIN, 18));
		menuProductBtn_2.setBounds(10, 97, 133, 33);
		panel.add(menuProductBtn_2);
		
		panel_1 = new JPanel();
		panel_1.setBounds(195, 24, 736, 538);
		panel_1.setBackground(new Color(	118, 185, 71));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search");
		lblNewLabel.setBounds(199, 58, 60, 25);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		searchTxt = new JTextField();
		searchTxt.setBounds(290, 60, 135, 29);
		panel_1.add(searchTxt);
		searchTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = searchTxt.getText();
				if(text.trim().length()==0){
					rowSorter.setRowFilter(null);
				}
			else{
				rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
		});
		searchTxt.setColumns(10);
		
		// Daily Sale Report
		JButton btnNewButton = new JButton("Report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					//Open Connection
					System.out.println("Connecting Database....");
					Connection conn = DriverManager.getConnection(Url,User,Password);
					String sql = "select * from selling";
					
					JasperDesign jdesign = JRXmlLoader.load("D:\\Dont_Delete\\KBTC\\JavaFinalProject\\SuperMarket\\src\\Project\\Report.jrxml");
					
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
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(500, 57, 89, 32);
		panel_1.add(btnNewButton);
		
	}
}
