package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class ErrorMessage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorMessage frame = new ErrorMessage();
					frame.setVisible(true);
					frame.setTitle("Error Message");
					frame.setResizable(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ErrorMessage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ErrorMessage.class.getResource("/images/error.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\error.png"));
		label.setBounds(0, 0, 50, 48);
		contentPane.add(label);
		
		JLabel lblPleaseEnterUsername = new JLabel("Please Enter Correct Username or Password!");
		lblPleaseEnterUsername.setFont(new Font("Sylfaen", Font.BOLD, 16));
		lblPleaseEnterUsername.setBounds(49, 13, 358, 27);
		contentPane.add(lblPleaseEnterUsername);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Sylfaen", Font.BOLD, 10));
		btnOk.setBounds(355, 53, 65, 40);
		contentPane.add(btnOk);
	}
}
