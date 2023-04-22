package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.JDBCUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;

public class DangKy extends JFrame {

	private JPanel contentPane;
	private JTextField txtuser;
	private JTextField txtgmail;
	private JButton btndk;
	private JPasswordField txtpassword;
	private JPasswordField txtconfirmpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangKy frame = new DangKy();
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
	public DangKy() {
		
		this.setTitle("Đăng Ký");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnlogin = new JButton("Login");
		btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangNhap dn = new DangNhap();
				dn.setVisible(true);
				dn.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnlogin.setBounds(239, 221, 104, 34);
		contentPane.add(btnlogin);
		
		JLabel lblNewLabel = new JLabel("Thêm Tài Khoản");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(149, 0, 179, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(94, 48, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gmail");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(94, 94, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(66, 139, 74, 14);
		contentPane.add(lblNewLabel_3);
		
		txtuser = new JTextField();
		txtuser.setBounds(161, 47, 151, 23);
		contentPane.add(txtuser);
		txtuser.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Confirm Password");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(0, 179, 151, 23);
		contentPane.add(lblNewLabel_3_1);
		
		txtgmail = new JTextField();
		txtgmail.setColumns(10);
		txtgmail.setBounds(161, 94, 151, 23);
		contentPane.add(txtgmail);
		
		btndk = new JButton("Đăng Ký");
		btndk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection c = JDBCUtil.openConnection();
					String sql = "Insert into account values(?, ?, ?, ?)";
					PreparedStatement pst = c.prepareStatement(sql);
					pst.setString(1, txtuser.getText());
					pst.setString(2, txtgmail.getText());
					pst.setString(3, txtpassword.getText());
					pst.setString(4, txtconfirmpass.getText());
					
					if(txtuser.getText().equals("") || txtgmail.getText().equals("") ||
							txtpassword.getText().equals("") || txtconfirmpass.getText().equals("")) {
						System.out.println("Ko để trống thông tin");
					}else {
						int n = pst.executeUpdate();
						if(n != 0) {
							System.out.println("Đăng ký thành công");
						}else {
							System.out.println("Đăng ký thất bại");
						}
					}
					JDBCUtil.closeConnection(c);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btndk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btndk.setBounds(66, 221, 105, 34);
		contentPane.add(btndk);
		
		txtpassword = new JPasswordField();
		txtpassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpassword.setBounds(160, 134, 152, 23);
		contentPane.add(txtpassword);
		
		txtconfirmpass = new JPasswordField();
		txtconfirmpass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtconfirmpass.setBounds(160, 179, 152, 23);
		contentPane.add(txtconfirmpass);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	
}