package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class Stock extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock frame = new Stock();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setTitle("FAMILY SUPERMARKET");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Stock() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 641);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(179, 292, 663, 289);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setForeground(Color.BLUE);
		lblStock.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblStock.setBounds(12, 13, 69, 22);
		panel_1.add(lblStock);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(Color.BLUE);
		lblCategory.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblCategory.setBounds(12, 50, 99, 34);
		panel_1.add(lblCategory);
		
		textField = new JTextField();
		textField.setBounds(123, 51, 116, 34);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 110, 639, 179);
		panel_1.add(scrollPane);
		
		table = new JTable();
		String []columns={"Item_ID","Item_Name","Category","Serial No","Buying Price","Selling Price","No of Items"};
		String [][]data={{"","","","","","","",""}};
		table = new JTable(data,columns);
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.PINK);
		panel_2.setBounds(0, 0, 180, 594);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblSell = new JLabel("SELL & STOCKS");
		lblSell.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblSell.setBounds(12, 13, 156, 65);
		panel_2.add(lblSell);
		
		JPanel panel = new JPanel();
		panel.setBounds(179, 13, 663, 274);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSell_1 = new JLabel("Sell");
		lblSell_1.setForeground(Color.BLUE);
		lblSell_1.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblSell_1.setBounds(12, 13, 69, 22);
		panel.add(lblSell_1);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(Color.BLUE);
		lblDate.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblDate.setBounds(12, 35, 69, 34);
		panel.add(lblDate);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(80, 36, 116, 34);
		panel.add(textField_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 92, 639, 169);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		String []col={"Item_ID","Item_Name","Category","Serial No","Buying Price","Selling Price","No of Items"};
		String [][]da={{"","","","","","","",""}};
		table_1= new JTable(da,col);
		scrollPane_1.setViewportView(table_1);
	}
}
