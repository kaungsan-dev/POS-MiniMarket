package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JComboBox;
import java.awt.SystemColor;

public class login extends JFrame {
	 private static  String Url = "jdbc:mysql://localhost:3306";
	 private static  String User="root";
	 private static  String Pw="107449";
	private JTextField txtPassword;
    Connection con;
    Statement stmt;
    ResultSet rs;
    private JTextField txtName;
	void Clear(){
		txtName.setText(null);
		txtPassword.setText(null);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					login frame = new login();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setTitle("FAMILY SUPERMARKET");
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/images/iconimage.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 499);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.PINK);
		panel_1.setBounds(351, 0, 376, 452);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(188, 5, 0, 0);
		panel_1.add(label);
		
		JLabel lblLogIn = new JLabel("LOGIN");
		lblLogIn.setFont(new Font("Sylfaen", Font.BOLD, 27));
		lblLogIn.setBounds(127, 5, 105, 29);
		panel_1.add(lblLogIn);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		lblUsername.setBounds(12, 194, 119, 29);
		panel_1.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD\r\n");
		lblPassword.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		lblPassword.setBounds(12, 256, 119, 29);
		panel_1.add(lblPassword);
		
		JPanel panel = new JPanel();
		panel.setBounds(-355, 18, 355, 539);
		panel_1.add(panel);
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(172, 249, 192, 36);
		panel_1.add(txtPassword);
		JButton btnLogin = new JButton("Log in\r\n");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn=DriverManager.getConnection(Url, User, Pw);
					String Name=txtName.getText().trim(); 
			        String Password=txtPassword.getText().trim(); 
			        String sql="SELECT * FROM login where name='"+Name+"' and password='"+Password+"'"; 
			        stmt=conn.createStatement();
			        rs=stmt.executeQuery(sql);
			            if(rs.next()) 
			            {   
			                new FamilySupermarket().setVisible(true);
			            }
			        else 
			            { 
			                new ErrorMessage().setVisible(true);
			                txtName.requestFocus();
			            }         
				}
				catch(Exception q){
					q.printStackTrace();
				}
				
			}
		});
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 20));
		btnLogin.setBounds(44, 352, 113, 72);
		panel_1.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clear();
			}
		});
		btnCancel.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 20));
		btnCancel.setBackground(Color.LIGHT_GRAY);
		btnCancel.setBounds(226, 352, 113, 72);
		panel_1.add(btnCancel);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(172, 187, 192, 36);
		panel_1.add(txtName);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.addItem("Admin");
		comboBox.addItem("User");
		comboBox.setBounds(172, 138, 192, 36);
		panel_1.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(0, 0, 351, 452);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\wel2.png"));
		label_2.setBounds(22, 72, 302, 81);
		panel_2.add(label_2);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(login.class.getResource("/images/FamilyLogo.png")));
		label_1.setBounds(22, 187, 302, 222);
		panel_2.add(label_1);
	}
}
