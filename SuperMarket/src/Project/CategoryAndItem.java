package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class CategoryAndItem extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryAndItem frame = new CategoryAndItem();
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
	public CategoryAndItem() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CategoryAndItem.class.getResource("/images/iconimage.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 753);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 814, 336);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblManageProducts = new JLabel("Manage Products");
		lblManageProducts.setForeground(Color.BLUE);
		lblManageProducts.setFont(new Font("Sylfaen", Font.BOLD, 40));
		lblManageProducts.setBounds(233, 0, 335, 54);
		panel.add(lblManageProducts);
		
		JLabel lblItemid = new JLabel("ItemID");
		lblItemid.setBackground(Color.PINK);
		lblItemid.setForeground(Color.BLUE);
		lblItemid.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		lblItemid.setBounds(12, 65, 74, 22);
		panel.add(lblItemid);
		
		textField = new JTextField();
		textField.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		textField.setBounds(140, 65, 171, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblItemname = new JLabel("Item_Name");
		lblItemname.setForeground(Color.BLUE);
		lblItemname.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		lblItemname.setBounds(12, 129, 116, 22);
		panel.add(lblItemname);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(140, 126, 171, 30);
		panel.add(textField_1);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(Color.BLUE);
		lblCategory.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		lblCategory.setBounds(12, 175, 87, 22);
		panel.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Fruits");
		comboBox.addItem("Drinks");
		comboBox.addItem("Snacks");
		comboBox.addItem("Beverage");
		comboBox.setBounds(140, 169, 171, 30);
		panel.add(comboBox);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setForeground(Color.BLUE);
		btnAdd.setBackground(Color.GRAY);
		btnAdd.setFont(new Font("Sylfaen", Font.BOLD, 25));
		btnAdd.setBounds(80, 238, 97, 40);
		panel.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.BLUE);
		btnUpdate.setBackground(Color.GRAY);
		btnUpdate.setFont(new Font("Sylfaen", Font.BOLD, 19));
		btnUpdate.setBounds(272, 238, 97, 40);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.GRAY);
		btnDelete.setForeground(Color.BLUE);
		btnDelete.setFont(new Font("Sylfaen", Font.BOLD, 19));
		btnDelete.setBounds(476, 240, 97, 38);
		panel.add(btnDelete);
		
		JButton btnClear = new JButton("Clear\r\n");
		btnClear.setBackground(Color.GRAY);
		btnClear.setForeground(Color.BLUE);
		btnClear.setFont(new Font("Sylfaen", Font.BOLD, 19));
		btnClear.setBounds(654, 238, 97, 40);
		panel.add(btnClear);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.BLUE);
		lblPrice.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		lblPrice.setBounds(479, 68, 74, 22);
		panel.add(lblPrice);
		
		textField_2 = new JTextField();
		textField_2.setBounds(561, 65, 171, 30);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setForeground(Color.BLUE);
		lblQty.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		lblQty.setBounds(479, 132, 74, 22);
		panel.add(lblQty);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(561, 129, 87, 30);
		panel.add(textField_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 338, 814, 368);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 792, 294);
		panel_1.add(scrollPane);
		
		
		
		String []columns={"ID","Name","Price","Qty","Category"};
		String [][]data={{"1","Ice Cream","1000","100","Drinks"}};
		table = new JTable(data,columns);
		scrollPane.setViewportView(table);
		
		JLabel lblProductList = new JLabel("Product List");
		lblProductList.setForeground(Color.BLUE);
		lblProductList.setFont(new Font("Sylfaen", Font.BOLD, 40));
		lblProductList.setBounds(269, 13, 250, 44);
		panel_1.add(lblProductList);
	}
}
