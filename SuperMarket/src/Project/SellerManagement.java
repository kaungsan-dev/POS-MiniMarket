package supermarket;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.sql.PreparedStatement;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class SellerManagement extends JFrame {

	private JPanel contentPane;
	private JTextField sellName;
	private JTextField sellId;
	private JTextField sellEmail;
	private JTextField sellPass;

	private static String Url = "jdbc:mysql://localhost:3306/Supermarket?useSSL=false";
	private static String User = "root";
	private static String Password = "232209";
	DefaultTableModel defaultTableModel;
	private JTable table;
	private JTextField sellGender;
	private JTextField sellRole;
	/**
	 * Launch the application.
	 */


	
	public void selectSeller(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting To Database");
			
			Connection conn = DriverManager.getConnection(Url, User, Password);
			System.out.println("Select Data from Table");
			
			String sql = "select * from register";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		
			
			while(rs.next()){
				
					String id = rs.getString("ID");
					String name = rs.getString("Name");
	                String email = rs.getString("Email");
	                String password = rs.getString("Password");
	                String gender = rs.getString("Gender");
	                String role = rs.getString("Role");
	                
	                defaultTableModel.addRow(new Object[]{id, name, email, password, gender, role});//Adding row in Table
	          
				
			}
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			System.out.println("Error" + ex);
		}
		
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellerManagement frame = new SellerManagement();
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
	public SellerManagement() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				selectSeller();
			}
		});
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1002, 805);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(177, 216, 183));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(	118, 185, 71));
		panel.setBounds(15, 82, 144, 651);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton logout = new JButton("Logout");
		logout.setFont(new Font("Arial", Font.PLAIN, 18));
		logout.setBounds(15, 606, 115, 29);
		panel.add(logout);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(163, 82, 802, 651);
		panel_1.setBackground(new Color(148, 201, 115));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSellerId = new JLabel("Name");
		lblSellerId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSellerId.setBounds(15, 71, 110, 20);
		panel_1.add(lblSellerId);
		
		sellName = new JTextField();
		sellName.setFont(new Font("Arial", Font.PLAIN, 18));
		sellName.setBounds(140, 68, 146, 26);
		panel_1.add(sellName);
		sellName.setColumns(10);
		
		JLabel lblSellerEmail = new JLabel("Seller Email");
		lblSellerEmail.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSellerEmail.setBounds(363, 35, 125, 20);
		panel_1.add(lblSellerEmail);
		
		sellId = new JTextField();
		sellId.setFont(new Font("Arial", Font.PLAIN, 18));
		sellId.setColumns(10);
		sellId.setBounds(140, 32, 146, 26);
		panel_1.add(sellId);
		
		JLabel lblSellerPassword = new JLabel("Seller Password");
		lblSellerPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSellerPassword.setBounds(363, 71, 146, 20);
		panel_1.add(lblSellerPassword);
		
		JLabel label_2 = new JLabel("Seller ID");
		label_2.setFont(new Font("Arial", Font.PLAIN, 18));
		label_2.setBounds(15, 35, 69, 20);
		panel_1.add(label_2);
		
		sellEmail = new JTextField();
		sellEmail.setFont(new Font("Arial", Font.PLAIN, 18));
		sellEmail.setColumns(10);
		sellEmail.setBounds(557, 32, 217, 26);
		panel_1.add(sellEmail);
		
		sellPass = new JTextField();
		sellPass.setFont(new Font("Arial", Font.PLAIN, 18));
		sellPass.setColumns(10);
		sellPass.setBounds(557, 68, 217, 26);
		panel_1.add(sellPass);
		
		JButton clear = new JButton("CLEAR");
		clear.setBackground(new Color(0, 139, 139));
		clear.setForeground(Color.white);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellId.setText("");
				sellName.setText("");
				sellEmail.setText("");
				sellPass.setText("");
				sellGender.setText("");
				sellRole.setText("");
			}
		});
		clear.setFont(new Font("Arial", Font.PLAIN, 17));
		clear.setBounds(499, 200, 115, 29);
		panel_1.add(clear);
		
		JButton update = new JButton("UPDATE");
		update.setBackground(new Color(	0, 139, 139));
		update.setForeground(Color.white);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sellId.getText().isEmpty() || sellName.getText().isEmpty() || sellEmail.getText().isEmpty() ||
					sellPass.getText().isEmpty() || sellGender.getText().isEmpty() || sellRole.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Enter the Seller to be updated", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else{
					try {
						String email = Login.getEmail();
	
						java.util.Date date=new java.util.Date();
		                java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		                java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
		                
						Class.forName("com.mysql.jdbc.Driver");
						System.out.println("Connecting To Database");						
						Connection conn = DriverManager.getConnection(Url, User, Password);
						Statement stmt = conn.createStatement();
						String sql = "update register set Name='"+sellName.getText()+"'"+",Email='"+sellEmail.getText()+"'"+",Password='"+sellPass.getText()+"'"+",Gender='"+sellGender.getText()+"'"+",Role='"+sellRole.getText()+"'"+",CreatedDate='"+sqlTime+"'"+",CreatedUser='"+email+"'"+"where ID=" + sellId.getText();
						stmt.executeUpdate(sql);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.setRowCount(0);
						selectSeller();
						JOptionPane.showMessageDialog(null, "Updated Successful", "Seller Deleted", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		update.setFont(new Font("Arial", Font.PLAIN, 17));
		update.setBounds(140, 200, 115, 29);
		panel_1.add(update);
		
		JButton delete = new JButton("DELETE");
		delete.setBackground(new Color(	0, 139, 139));
		delete.setForeground(Color.white);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("Connecting To Database");
					
					Connection conn = DriverManager.getConnection(Url, User, Password);
					if(sellId.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Enter the Seller to be deleted", "Error", JOptionPane.ERROR_MESSAGE);
					}else{
						try {
							Statement stmt = conn.createStatement();
							String sql = "delete from register where ID = "+ sellId.getText();
							stmt.executeUpdate(sql);
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.setRowCount(0);
							selectSeller();
							JOptionPane.showMessageDialog(null, "Deleted Successful", "Seller Deleted", JOptionPane.INFORMATION_MESSAGE);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					System.out.println("Error" + ex);
				}
			}
			
		});
		delete.setFont(new Font("Arial", Font.PLAIN, 17));
		delete.setBounds(315, 200, 115, 29);
		panel_1.add(delete);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(49, 268, 725, 367);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 725, 367);
		panel_2.add(scrollPane);
		defaultTableModel = new DefaultTableModel();
		table = new JTable(defaultTableModel);
		//table.getTableHeader().setFont(new Font("Arial", 10, Font.PLAIN));
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int index = table.getSelectedRow();
				sellId.setText(model.getValueAt(index, 0).toString());
				sellName.setText(model.getValueAt(index, 1).toString());
				sellEmail.setText(model.getValueAt(index, 2).toString());
				sellPass.setText(model.getValueAt(index, 3).toString());
				sellGender.setText(model.getValueAt(index, 4).toString());
				sellRole.setText(model.getValueAt(index, 5).toString());
			}
		});
		scrollPane.setViewportView(table);	
		
		JLabel lblSellerGender = new JLabel("Gender");
		lblSellerGender.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSellerGender.setBounds(15, 113, 110, 20);
		panel_1.add(lblSellerGender);
		
		sellGender = new JTextField();
		sellGender.setFont(new Font("Arial", Font.PLAIN, 18));
		sellGender.setColumns(10);
		sellGender.setBounds(140, 110, 146, 26);
		panel_1.add(sellGender);
		
		sellRole = new JTextField();
		sellRole.setFont(new Font("Arial", Font.PLAIN, 18));
		sellRole.setColumns(10);
		sellRole.setBounds(557, 110, 217, 26);
		panel_1.add(sellRole);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Arial", Font.PLAIN, 18));
		lblRole.setBounds(363, 113, 110, 20);
		panel_1.add(lblRole);
		
 
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Name");
        defaultTableModel.addColumn("Email");
        defaultTableModel.addColumn("Password");
        defaultTableModel.addColumn("Gender");
        defaultTableModel.addColumn("Role");
		
		JLabel lblManageSeller = new JLabel("Manage Seller");
		lblManageSeller.setFont(new Font("Arial", Font.BOLD, 22));
		lblManageSeller.setBounds(389, 16, 200, 36);
		contentPane.add(lblManageSeller);
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    new Login().setVisible(true);
                    
                }
			}
		});
	}
}
