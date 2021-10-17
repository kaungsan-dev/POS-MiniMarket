package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cashier extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTable table_1;
	private JTextField textField_10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cashier frame = new Cashier();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setTitle("Cash & Billing System");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void Date(){
	
	}

	/**
	 * Create the frame.
	 */
	public Cashier() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cashier.class.getResource("/images/iconimage.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 656);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 643, 187);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblProductCode = new JLabel("Product Code");
		lblProductCode.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		lblProductCode.setBounds(12, 13, 108, 31);
		panel.add(lblProductCode);
		
		textField = new JTextField();
		textField.setBounds(12, 57, 116, 31);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		lblProductName.setBounds(154, 13, 116, 31);
		panel.add(lblProductName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(154, 57, 116, 31);
		panel.add(textField_1);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		lblQty.setBounds(298, 13, 42, 31);
		panel.add(lblQty);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		lblPrice.setBounds(389, 13, 84, 31);
		panel.add(lblPrice);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(389, 57, 116, 31);
		panel.add(textField_2);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		lblTotal.setBounds(530, 13, 84, 31);
		panel.add(lblTotal);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(517, 57, 116, 31);
		panel.add(textField_3);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBackground(Color.LIGHT_GRAY);
		btnAdd.setFont(new Font("Sylfaen", Font.BOLD, 20));
		btnAdd.setBounds(530, 102, 84, 72);
		panel.add(btnAdd);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(291, 57, 73, 31);
		panel.add(textField_10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 228, 643, 418);
		contentPane.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		
		table = new JTable();
		table.setBounds(43, 79, 1, 1);
		panel_1.add(table);
		
		JLabel lblDate = new JLabel("");
		lblDate.setFont(new Font("Sylfaen", Font.BOLD, 15));
		lblDate.setBounds(461, 0, 170, 29);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
        Date dateobj = new Date();
        String date=df.format(dateobj);
		lblDate.setText("Today : "+date);
		panel_1.add(lblDate);
		
		
		String [] column={"Product Code","Product Name","Qty","Price","Sub_Total","Total"};
		String [][]data={{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "},
				{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "}
				,{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "},
				{" "," "," "," "," "," "},{" "," "," "," "," "," "},{" "," "," "," "," "," "}};
		table_1 = new JTable(data,column);
		scrollPane.setViewportView(table_1);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(654, 33, 238, 499);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblToday = new JLabel("");
		lblToday.setBounds(109, 33, 129, 23);
		panel_2.add(lblToday);
        String today=df.format(dateobj);
        lblToday.setText("Today : "+date);
        
        JLabel lblFamilySupermarket = new JLabel("Family Supermarket");
        lblFamilySupermarket.setBounds(12, 69, 123, 16);
        panel_2.add(lblFamilySupermarket);
        
        JLabel lblContactNoxxxxxxxxx = new JLabel("Contact No. 09XXXXXXXXX");
        lblContactNoxxxxxxxxx.setBounds(12, 90, 139, 16);
        panel_2.add(lblContactNoxxxxxxxxx);
        
        JLabel lblAddresscompanyfamily = new JLabel("Address::Company.Family");
        lblAddresscompanyfamily.setBounds(12, 109, 159, 16);
        panel_2.add(lblAddresscompanyfamily);
        
        JLabel label = new JLabel("************************");
        label.setBounds(27, 138, 199, 16);
        panel_2.add(label);
        
        JLabel lblItemqty = new JLabel("Item/Qty");
        lblItemqty.setBounds(12, 167, 66, 16);
        panel_2.add(lblItemqty);
        
        JLabel lblPrice_1 = new JLabel("Price");
        lblPrice_1.setBounds(172, 167, 66, 16);
        panel_2.add(lblPrice_1);
        
        JLabel lblTotal_1 = new JLabel("Total:");
        lblTotal_1.setForeground(new Color(0, 0, 0));
        lblTotal_1.setBounds(105, 443, 66, 16);
        panel_2.add(lblTotal_1);
        
        JButton btnPrint = new JButton("PRINT");
        btnPrint.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        btnPrint.setBounds(788, 545, 92, 54);
        contentPane.add(btnPrint);
        
        JLabel lblDate_1 = new JLabel("");
        lblDate_1.setBounds(504, 199, 139, 16);
        contentPane.add(lblDate_1);
        lblDate_1.setText("Today : "+date);
	}
}
