package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FamilySupermarket extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FamilySupermarket frame = new FamilySupermarket();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setTitle("Welcome To Family Supermarket");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FamilySupermarket() {
		getContentPane().setBackground(Color.PINK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 767, 735);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(60, 13, 677, 662);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFamilySupermarket = new JLabel("FAMILY SUPERMARKET");
		lblFamilySupermarket.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 40));
		lblFamilySupermarket.setBounds(83, 13, 503, 38);
		panel.add(lblFamilySupermarket);
		
		JButton btnCategory = new JButton("Category");
		btnCategory.setForeground(Color.BLACK);
		btnCategory.setFont(new Font("Sylfaen", Font.BOLD, 20));
		btnCategory.setBounds(26, 128, 147, 51);
		panel.add(btnCategory);
		
		JButton btnCashier = new JButton("Cashier");
		btnCashier.setForeground(Color.BLACK);
		btnCashier.setFont(new Font("Sylfaen", Font.BOLD, 20));
		btnCashier.setBounds(26, 235, 147, 51);
		panel.add(btnCashier);
		
		JButton button = new JButton("Category");
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Sylfaen", Font.BOLD, 20));
		button.setBounds(266, 128, 147, 51);
		panel.add(button);
		
		JButton button_1 = new JButton("Category");
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Sylfaen", Font.BOLD, 20));
		button_1.setBounds(266, 235, 147, 51);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Category");
		button_2.setForeground(Color.BLACK);
		button_2.setFont(new Font("Sylfaen", Font.BOLD, 20));
		button_2.setBounds(26, 345, 147, 51);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Category");
		button_3.setForeground(Color.BLACK);
		button_3.setFont(new Font("Sylfaen", Font.BOLD, 20));
		button_3.setBounds(266, 345, 147, 51);
		panel.add(button_3);
	}
}
