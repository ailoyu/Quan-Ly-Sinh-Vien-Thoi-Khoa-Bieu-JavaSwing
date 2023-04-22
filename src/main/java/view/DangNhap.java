package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.JDBCUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class DangNhap extends JFrame {

	private JPanel contentPane;
	private JTextField txtdn;
	private JPasswordField jfpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
					frame.setLocationRelativeTo(null);
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
	public DangNhap() {
		
		this.setTitle("Đăng Nhập");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng Nhập");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(178, 21, 108, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(49, 71, 89, 28);
		contentPane.add(lblNewLabel_1);
		
		txtdn = new JTextField();
		txtdn.setBounds(170, 75, 140, 27);
		contentPane.add(txtdn);
		txtdn.setColumns(10);
		
		jfpass = new JPasswordField();
		jfpass.setBounds(170, 144, 140, 28);
		contentPane.add(jfpass);
		
		JButton btndangky = new JButton("Đăng Ký");
		btndangky.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangKy dk = new DangKy();
				dk.setVisible(true);
				dispose();
			}
		});
		btndangky.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btndangky.setBounds(246, 211, 108, 23);
		contentPane.add(btndangky);
		
		JButton btndangnhap = new JButton("Đăng Nhập");
		btndangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection c = JDBCUtil.openConnection();
					String sql = "select * from account where username = ? "
							+ "and pass = ?";
					PreparedStatement pst = c.prepareCall(sql);
					pst.setString(1, txtdn.getText());
					pst.setString(2, jfpass.getText());
					ResultSet rs = pst.executeQuery();
					
					
					
					// Sql injection: 
					// 105' OR 1=1;-- 
					// ' or ''='
					
//					java.sql.Statement st = c.createStatement();
//					String sql = "select * from account where username = '" + txtdn.getText() 
//					+"' and pass = '" + jfpass.getText() + "'";
//					
//					ResultSet rs = st.executeQuery(sql);
					
					if(txtdn.getText().equals("") || jfpass.getText().equals("")) {
						System.out.println("Chưa nhập username hoặc password");
					}else {
						if(rs.next()) {
							System.out.println("Đăng nhập thành công");
							QuanLySinhVienView qlsv = new QuanLySinhVienView();
							qlsv.setVisible(true);
							qlsv.setLocationRelativeTo(null);
							dispose();
						}else {
							System.out.println("Đăng nhập thất bại");
							System.out.println("Username or pass bị sai!");
						}
					}
					JDBCUtil.closeConnection(c);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btndangnhap.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btndangnhap.setBounds(79, 211, 129, 23);
		contentPane.add(btndangnhap);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(49, 141, 89, 28);
		contentPane.add(lblNewLabel_1_1);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
}