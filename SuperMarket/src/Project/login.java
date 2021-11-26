package Project;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JPasswordField;
import javax.swing.JSeparator;

public class Login extends JFrame {
	private JPanel contentPane;
	private JTextField email;
	
	private static String Url = "jdbc:mysql://localhost:3306/minimarket?useSSL=false";
	private static String User = "root";
	private static String Password = "admin";
	private JPasswordField password;
	private JComboBox Role;
	
	private static String Email;
	
    public static String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	Connection con;
    Statement stmt;
    ResultSet rs;
	void Clear(){
		email.setText(null);
		password.setText(null);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Login frame = new Login();
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
	public Login() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/images/iconimage.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 559);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(41,70,91));
		panel_1.setBounds(351, 0, 440, 509);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(188, 5, 0, 0);
		panel_1.add(label);
		
		JLabel lblLogIn = new JLabel("LOGIN");
		lblLogIn.setFont(new Font("Sylfaen", Font.BOLD, 27));
		lblLogIn.setForeground(Color.WHITE);
		lblLogIn.setBounds(155, 11, 105, 29);
		panel_1.add(lblLogIn);
		
		JLabel txtEmail = new JLabel("EMAIL");
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 18));
		txtEmail.setForeground(Color.WHITE);
		txtEmail.setBounds(15, 151, 119, 29);
		panel_1.add(txtEmail);
		
		JLabel txtPassword = new JLabel("PASSWORD\r\n");
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		txtPassword.setForeground(Color.WHITE);
		txtPassword.setBounds(15, 191, 119, 29);
		panel_1.add(txtPassword);
		
		JPanel panel = new JPanel();
		panel.setBounds(-355, 18, 355, 539);
		panel_1.add(panel);
		JButton btnLogin = new JButton("Log in\r\n");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Role.getSelectedItem().toString().equals("Admin")){
				try {
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("Connecting To Database");
					
					Connection conn = DriverManager.getConnection(Url, User, Password);
					String Query = "select * from register where email ='"+email.getText()+"' and password='"+password.getText()+"'";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(Query);
					if(rs.next()){
						Email = email.getText();
						new SellerManagement().setVisible(true);
						dispose();
						
					}else{
						JOptionPane.showMessageDialog(null, "Wrong Username or Password");
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			else{
				try {
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("Connecting To Database");
					
					Connection conn = DriverManager.getConnection(Url, User, Password);
					String Query = "select * from register where email ='"+email.getText()+"' and password='"+password.getText()+"'";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(Query);
					if(rs.next()){
						Email = email.getText();
						new Selling().setVisible(true);
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "Wrong Username or Password");
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		});
		
		btnLogin.setForeground(Color.BLACK);
			
		btnLogin.setBackground(new Color(173, 239, 209));
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 18));
		btnLogin.setBounds(60, 256, 113, 72);
		panel_1.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clear();
			}
		});
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 18));
		btnCancel.setBackground(new Color(173, 239, 209));;
		btnCancel.setBounds(251, 256, 113, 72);
		panel_1.add(btnCancel);
		
		email = new JTextField();
		email.setFont(new Font("Arial", Font.PLAIN, 18));
		email.setColumns(10);
		email.setBounds(172, 147, 192, 36);
		panel_1.add(email);
		
		Role = new JComboBox();
		Role.setFont(new Font("Arial", Font.PLAIN, 18));
		Role.addItem("Admin");
		Role.addItem("Seller");
		Role.setBounds(172, 100, 192, 36);
		panel_1.add(Role);
		
		password = new JPasswordField();
		password.setFont(new Font("Arial", Font.PLAIN, 18));
		password.setBounds(172, 194, 192, 36);
		panel_1.add(password);
		
		JLabel lblNewLabel = new JLabel("If you haven't account!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(62, 360, 342, 29);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Register");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Register().setVisible(true);
			}
			
		});
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(Color.CYAN);
		lblNewLabel_1.setBounds(188, 388, 92, 29);
		panel_1.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 348, 420, 14);
		panel_1.add(separator);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(173, 239, 209));
		panel_2.setBounds(0, 0, 351, 509);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
	
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("D:\\Dont_Delete\\KBTC\\JavaFinalProject\\login.png"));
		label_1.setBounds(15, 155, 464, 196);
		panel_2.add(label_1);
	}
}