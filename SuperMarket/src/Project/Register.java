package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private JLabel Gender;
	private JLabel Role;
	private JPanel panel1;
	private JButton btnClear;
	private JRadioButton radioMale;
	private JRadioButton radioFemale;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox comboBox;
	private JLabel Title;
	private JButton btnSignIn;
	private JPasswordField txtPassword;
	
	private static String Url = "jdbc:mysql://localhost:3306/minimarket?useSSL=false";
	private static String User = "root";
	private static String Password = "admin";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 716);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(41,70,91));
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(53, 79, 614, 375);
		contentPane.add(panel);
		panel.setBorder(null);
		panel.setBackground(new Color(41,70,91));
		panel.setLayout(new GridLayout(6, 2, 5, 8));
		
		JLabel Name = new JLabel("Name");
		Name.setFont(new Font("Arial", Font.BOLD, 18));
		Name.setForeground(Color.WHITE);
		panel.add(Name);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Arial", Font.PLAIN, 18));
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel Email = new JLabel("Email");
		Email.setFont(new Font("Arial", Font.BOLD, 18));
		Email.setForeground(Color.WHITE);
		panel.add(Email);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 18));
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel Password1 = new JLabel("Password");
		Password1.setFont(new Font("Arial", Font.BOLD, 18));
		Password1.setForeground(Color.WHITE);
		panel.add(Password1);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		panel.add(txtPassword);
		
		Gender = new JLabel("Gender");
		Gender.setFont(new Font("Arial", Font.BOLD, 18));
		Gender.setForeground(Color.WHITE);
		panel.add(Gender);
		
		panel1 = new JPanel();
		panel.add(panel1);
		
		radioMale = new JRadioButton("Male");
		buttonGroup.add(radioMale);
		radioMale.setFont(new Font("Arial", Font.PLAIN, 18));
		panel1.add(radioMale);
		
		radioFemale = new JRadioButton("Female");
		buttonGroup.add(radioFemale);
		radioFemale.setFont(new Font("Arial", Font.PLAIN, 18));
		panel1.add(radioFemale);
		
		Role = new JLabel("Role");
		Role.setFont(new Font("Arial", Font.BOLD, 18));
		Role.setForeground(Color.WHITE);
		panel.add(Role);
		
		String[] role = {"Admin", "Seller"};
		comboBox = new JComboBox(role);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
		panel.add(comboBox);
		
		Title = new JLabel("Register");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		Title.setBounds(302, 30, 119, 29);
		Title.setForeground(Color.WHITE);
		contentPane.add(Title);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String email =txtEmail.getText();
				String password = txtPassword.getText();
				String gender = "";
				if(radioMale.isSelected()){
					gender = "Male";
				}
				else{
					gender = "Female";
				}
				String role = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("Connecting To Database");
					
					Connection conn = DriverManager.getConnection(Url, User, Password);
					System.out.println("Inserting Data into Table");
					
					String sql = "insert into register(Name, Email, Password, Gender, Role)"
							+ " values ('"+name+"','"+email+"','"+password+"','"+gender+"', '"+role+"')";
					Statement stmt = conn.createStatement();
					int result = stmt.executeUpdate(sql);
					
					if(result == 1){
						//int result1 = Integer.parseInt(JOptionPane.showMessageDialog(null, "Insert Successful", "Student Inserted", JOptionPane.INFORMATION_MESSAGE));
						int n = JOptionPane.showOptionDialog(new JFrame(), "Message", 
						        "Title", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
						        null, new Object[] {"Yes", "No"}, JOptionPane.YES_OPTION);
						if (n == JOptionPane.YES_OPTION) {
							dispose();
			                 new Login().setVisible(true);
				        } 
						buttonGroup.clearSelection();
						txtName.setText("");
						txtEmail.setText("");
						txtPassword.setText("");
					}
					
					
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					System.out.println("Error" + ex);
				}
				
			}
		});
		btnRegister.setFont(new Font("Arial", Font.PLAIN, 18));
		btnRegister.setBounds(364, 470, 115, 29);
		contentPane.add(btnRegister);
		
		btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtName.setText("");
				txtEmail.setText("");
				txtPassword.setText("");
				buttonGroup.clearSelection();
				
			}
		});
		btnClear.setFont(new Font("Arial", Font.PLAIN, 18));
		btnClear.setBounds(553, 470, 115, 29);
		contentPane.add(btnClear);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(53, 533, 621, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel = new JLabel("If you have an account,");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(302, 551, 212, 20);
		lblNewLabel.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel);
		
		btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
                 new Login().setVisible(true);
			}
		});
		btnSignIn.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSignIn.setBackground(new Color(41,70,91));
		btnSignIn.setBounds(335, 587, 115, 29);
		btnSignIn.setForeground(new Color(154,254,255));
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnSignIn.setBorder(emptyBorder);
		contentPane.add(btnSignIn);
	}
}