package view;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.JDBCUtil;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GiaoVienWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBoxLopHoc;
	private int idGV;
	private JLabel lblTenGiaoVien;
	private JLabel lbl_MonHoc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoVienWindow frame = new GiaoVienWindow(5);
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
	 * @throws SQLException 
	 */
	
	public GiaoVienWindow(int idGV) throws SQLException {
		this.idGV = idGV;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTnGioVin = new JLabel("Tên Giáo Viên:");
		lblTnGioVin.setBounds(165, 32, 136, 22);
		lblTnGioVin.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblTnGioVin);
		
		lblTenGiaoVien = new JLabel("");
		lblTenGiaoVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTenGiaoVien.setBounds(302, 32, 252, 22);
		contentPane.add(lblTenGiaoVien);
		
		lbl_MonHoc = new JLabel("");
		lbl_MonHoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_MonHoc.setBounds(219, 59, 128, 22);
		contentPane.add(lbl_MonHoc);
		
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"L\u1EDBp"
			}
		));
		table.setRowHeight(25);
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(175, 125, 292, 413);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		
		hienThiThongTin();
		
		
		
		JLabel lblDanhSchLp = new JLabel("Danh Sách Lớp:");
		lblDanhSchLp.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDanhSchLp.setBounds(165, 92, 175, 22);
		contentPane.add(lblDanhSchLp);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection c = JDBCUtil.openConnection();
				String tenLop = comboBoxLopHoc.getSelectedItem().toString() ;
				String sql = "INSERT INTO sinhvien.`lophoc_giaovien` (tenLop, id_GV) VALUES ('"+ tenLop +"', '" + idGV +"')";
				PreparedStatement pst;
				try {
					pst = c.prepareStatement(sql);
					pst.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					if(tenLop.equals("")) {
						JOptionPane.showMessageDialog(GiaoVienWindow.this, "Vui lòng chọn lớp");
					}else {
						JOptionPane.showMessageDialog(GiaoVienWindow.this, "Lớp "+tenLop +" đã được thêm trước đó!");
					}
				}
				JDBCUtil.closeConnection(c);
				hienThiThongTin();
			}
		});
		btnNewButton.setBounds(502, 323, 145, 37);
		contentPane.add(btnNewButton);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection c = JDBCUtil.openConnection();
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				int rowSelected = table.getSelectedRow();
				String tenLop = tableModel.getValueAt(rowSelected, 0) + "";
				String sql = "DELETE FROM sinhvien.`lophoc_giaovien` WHERE  tenLop`='" + tenLop +"' AND id_GV`= " + idGV;
				try {
					PreparedStatement pst = c.prepareStatement(sql);
					pst.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				hienThiThongTin();
			}
		});
		btnXa.setBounds(502, 390, 145, 37);
		contentPane.add(btnXa);
		
		comboBoxLopHoc = new JComboBox();
		String sql1 = "select * from lophoc";
		Connection c1 = JDBCUtil.openConnection();
		PreparedStatement pst = c1.prepareStatement(sql1);
		ResultSet rs = pst.executeQuery();
		comboBoxLopHoc.addItem("");
		while(rs.next()) {
			comboBoxLopHoc.addItem(rs.getString("tenLop"));
		}
		comboBoxLopHoc.setBounds(502, 242, 145, 30);
		contentPane.add(comboBoxLopHoc);
		
		JLabel lblMn = new JLabel("Môn:");
		lblMn.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMn.setBounds(165, 59, 53, 22);
		contentPane.add(lblMn);
		
		
	}
	
	public void hienThiThongTin() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		Connection c = JDBCUtil.openConnection();
		String sql = "SELECT giaovien.tenGiaoVien, giaovien.monHoc FROM giaovien WHERE giaovien.maGiaoVien = "+idGV;
		String sql2 = "SELECT lophoc_giaovien.tenLop FROM lophoc_giaovien WHERE id_GV = " +idGV;
		try {
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				lbl_MonHoc.setText(rs.getString("monHoc"));
				lblTenGiaoVien.setText(rs.getString("tenGiaoVien"));
			}
			pst = c.prepareStatement(sql2);
			rs = pst.executeQuery();
			tableModel.setRowCount(0);
			while(rs.next()) {
				tableModel.addRow(new Object[] {rs.getString("tenLop")});
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}