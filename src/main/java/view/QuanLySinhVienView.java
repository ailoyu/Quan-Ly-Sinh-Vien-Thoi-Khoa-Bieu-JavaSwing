package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import MyList.MyDoublyLinkedList;
import controller.QuanLySinhVienController;
import dao.GiaoVienDAO;
import dao.ThiSinhDAO;
import database.JDBCUtil;
import model.GiaoVien;
import model.MonHoc;
import model.ThiSinh;
import model.Tinh;

public class QuanLySinhVienView extends JFrame {

	private JPanel contentPane;
//	public QuanLySinhVienModel model;
	public JTextField textFieldMaSinhVien_timKiem;
	public JTable table;
	public JTextField textFieldID;
	public JTextField textFieldHoVaTen;
	public JTextField textFieldNgaySinh;
	public JTextField textFieldMon1;
	public JTextField textFieldMon2;
	public JTextField textFieldMon3;
	public ButtonGroup buttonGroup_GioiTinh;
	public JComboBox comboBoxQueQuan_timKiem;
	public JComboBox comboBoxQueQuan;
	public JRadioButton radioButonNam;
	public JRadioButton radioButtonNu;
	MyDoublyLinkedList<ThiSinh> dsSinhVien = new MyDoublyLinkedList<ThiSinh>();
	public MyDoublyLinkedList<GiaoVien> dsGiaoVien = new MyDoublyLinkedList<GiaoVien>();
	public JPopupMenu jPopupMenu;
	public JComboBox comboBoxTenLop;
	public JComboBox comboBoxMonHoc;
	public JTable tableGV;
	public JTextField textFieldMaGiaoVien;
	public JTextField textFieldTenGiaoVien;
	public JComboBox comboBoxLopChuNhiem;
	public JTextField textFieldMaGiaoVien_timKiem;
	public ButtonGroup buttonGroup_Option;
	public JRadioButton radioButonChuNhiem;
	public JRadioButton radioButtonKoChuNhiem;
	private JTable table_thongke;
	private JLabel lblHocSinhGioi;
	private JLabel lblHocSinhKha;
	private JLabel lblHocSinhTrungBinh;
	private JLabel lblHocSinhYeu;
	private JLabel lblNewLabel_2_3_1;
	private JLabel lblSoLuongHocSinh;
	private JLabel lblSoLuongGiaoVien;
	private JTable table_sinhVien;
	private JTable table_GV;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // Runnable là lớp mở giao diện
			public void run() {
				try {
					QuanLySinhVienView frame = new QuanLySinhVienView();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public QuanLySinhVienView() { // constructor
//		this.model = new QuanLySinhVienModel();
		setTitle("Quản Lý Sinh Viên");
		QuanLySinhVienController qlsvc = new QuanLySinhVienController(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 739);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuItemSignOut = new JMenu("File");
		menuItemSignOut.addActionListener(qlsvc);
		menuItemSignOut.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuItemSignOut);

		JMenuItem menuItemOpen = new JMenuItem("Open");
		menuItemOpen.addActionListener(qlsvc);
		menuItemOpen.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuItemSignOut.add(menuItemOpen);

		JMenuItem menuItemSave = new JMenuItem("Save");
		menuItemSave.addActionListener(qlsvc);
		menuItemSave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuItemSignOut.add(menuItemSave);

		JSeparator separator = new JSeparator();
		menuItemSignOut.add(separator);

		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(qlsvc);
		menuItemSignOut.add(menuItemExit);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sign Out");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmNewMenuItem.addActionListener(qlsvc);
		menuItemSignOut.add(mntmNewMenuItem);
		menuItemExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		

		JMenu menuAbout = new JMenu("About");
		menuAbout.addActionListener(qlsvc);
		menuAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuAbout);

		JMenuItem menuItemAboutMe = new JMenuItem("About me");
		menuItemAboutMe.addActionListener(qlsvc);
		menuItemAboutMe.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuAbout.add(menuItemAboutMe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1360, 667);
		contentPane.add(tabbedPane);
		
		JPanel sinhvien = new JPanel();
		tabbedPane.addTab("Sinh Viên", null, sinhvien, null);
		sinhvien.setLayout(null);
		
		JLabel labelQueQuan = new JLabel("Quê Quán");
		labelQueQuan.setFont(new Font("Tahoma", Font.PLAIN, 19));
		labelQueQuan.setBounds(383, 27, 93, 32);
		sinhvien.add(labelQueQuan);
		
		JLabel lblMaSinhVinhVien = new JLabel("MSV & Họ Và Tên");
		lblMaSinhVinhVien.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMaSinhVinhVien.setBounds(657, 27, 166, 32);
		sinhvien.add(lblMaSinhVinhVien);
		
		textFieldMaSinhVien_timKiem = new JTextField();
		textFieldMaSinhVien_timKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				dsSinhVien.clear();
				int maThiSinh = 0;
				try {
					Connection c = JDBCUtil.openConnection();
					String sql = "";
					if (textFieldMaSinhVien_timKiem.getText().isEmpty()) {
						sql = "select * from thisinh where queQuan like N'%"
								+ (String) comboBoxQueQuan_timKiem.getSelectedItem() + "%'";
					} else {
						if (textFieldMaSinhVien_timKiem.getText().chars().allMatch(Character::isDigit)) {
							// nếu như nhập chuỗi thì ko nhận, nhập số thì ms nhận
							maThiSinh = Integer.parseInt(textFieldMaSinhVien_timKiem.getText());
							sql = "select * from thisinh where queQuan like N'%"
									+ (String) comboBoxQueQuan_timKiem.getSelectedItem() + "%'" + " and maThiSinh LIKE N'%"
									+ maThiSinh + "%'";
						} else {
							sql = "select * from thisinh where queQuan like N'%"
									+ (String) comboBoxQueQuan_timKiem.getSelectedItem() + "%'"
									+ " and tenThiSinh like N'%" + textFieldMaSinhVien_timKiem.getText() + "%'";
						}
					}
					System.out.println(sql);
					Statement st = c.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {
						int maThiSinh1 = rs.getInt("maThiSinh");
						String tenThiSinh = rs.getString("tenThiSinh");
						String queQuan = rs.getString("queQuan");
						Tinh tinh = Tinh.getTinhByTen(queQuan);
						java.sql.Date ngaySinh = rs.getDate("ngaySinh");
						boolean gioiTinh = true;
						if (rs.getInt("gioiTinh") == 1) {
							gioiTinh = true;
						} else if (rs.getInt("gioiTinh") == 0) {
							gioiTinh = false;
						}
						float diemMon1 = rs.getFloat("diemMon1");
						float diemMon2 = rs.getFloat("diemMon2");
						float diemMon3 = rs.getFloat("diemMon3");
						String lopHoc = rs.getString("lopHoc");
						
						
						dsSinhVien.add(new ThiSinh(maThiSinh1, tenThiSinh, tinh, ngaySinh, gioiTinh, diemMon1, diemMon2, diemMon3, lopHoc));

					}

					JDBCUtil.closeConnection(c);
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setRowCount(0);
					for(int i = 0; i < dsSinhVien.size(); i++) {
						ThiSinh ts = dsSinhVien.get(i);
						tableModel.addRow(ts.createObjects());
						
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		textFieldMaSinhVien_timKiem.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textFieldMaSinhVien_timKiem.setColumns(10);
		textFieldMaSinhVien_timKiem.setBounds(812, 27, 189, 32);
		sinhvien.add(textFieldMaSinhVien_timKiem);
		
		JButton btnTimKiem = new JButton("Tìm");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnTimKiem.setBounds(1011, 11, 120, 34);
		btnTimKiem.addActionListener(qlsvc);
		sinhvien.add(btnTimKiem);
		
		comboBoxQueQuan_timKiem = new JComboBox();
		// Add item tỉnh vào comboBox
		ArrayList<Tinh> listTinh = Tinh.getDanhSachTinh();
		comboBoxQueQuan_timKiem.addItem("");
		for (Tinh tinh : listTinh) {
			comboBoxQueQuan_timKiem.addItem(tinh.getTenTinh());
		}
		comboBoxQueQuan_timKiem.setBounds(480, 27, 167, 32);
		sinhvien.add(comboBoxQueQuan_timKiem);

		
		JLabel lblDanh = new JLabel("Danh Sách Thí Sinh");
		lblDanh.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblDanh.setBounds(10, 70, 174, 32);
		sinhvien.add(lblDanh);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 Sinh Vi\u00EAn", "H\u1ECD V\u00E0 T\u00EAn", "Qu\u00EA Qu\u00E1n",
						"Ng\u00E0y Sinh", "Gi\u1EDBi T\u00EDnh", "Lớp Học", "Điểm Trung Bình", "Học Lực" }));

		table.setRowHeight(25);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 113, 1335, 242);
		sinhvien.add(scrollPane);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(366, 366, 748, 8);
		sinhvien.add(separator_1_1);
		
		JLabel lblThngTin = new JLabel("Thông tin thí sinh");
		lblThngTin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblThngTin.setBounds(366, 369, 157, 32);
		sinhvien.add(lblThngTin);
		
		JLabel lblId = new JLabel("Mã Thí Sinh");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblId.setBounds(366, 412, 100, 23);
		sinhvien.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textFieldID.setColumns(10);
		textFieldID.setBounds(483, 412, 166, 29);
		sinhvien.add(textFieldID);
		
		JLabel lblHVTn = new JLabel("Họ Và Tên");
		lblHVTn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblHVTn.setBounds(367, 460, 88, 23);
		sinhvien.add(lblHVTn);
		
		textFieldHoVaTen = new JTextField();
		textFieldHoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textFieldHoVaTen.setColumns(10);
		textFieldHoVaTen.setBounds(482, 460, 166, 29);
		sinhvien.add(textFieldHoVaTen);
		
		JLabel lblQueQuan = new JLabel("Quê Quán");
		lblQueQuan.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblQueQuan.setBounds(367, 503, 85, 23);
		sinhvien.add(lblQueQuan);
		
		comboBoxQueQuan = new JComboBox();
		// Add item các tỉnh vào comboBox
		comboBoxQueQuan.addItem("");
		ArrayList<Tinh> listTinh2 = Tinh.getDanhSachTinh();
		for (Tinh tinh : listTinh2) {
			comboBoxQueQuan.addItem(tinh.getTenTinh());
		}
		comboBoxQueQuan.setBounds(483, 507, 167, 22);
		sinhvien.add(comboBoxQueQuan);
		
		JLabel lblNgySinh = new JLabel("Ngày Sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNgySinh.setBounds(367, 546, 86, 23);
		sinhvien.add(lblNgySinh);
		
		textFieldNgaySinh = new JTextField();
		textFieldNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textFieldNgaySinh.setColumns(10);
		textFieldNgaySinh.setBounds(483, 546, 166, 29);
		sinhvien.add(textFieldNgaySinh);
		
		JLabel lblGioiTinh = new JLabel("Giới Tính");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblGioiTinh.setBounds(755, 381, 88, 23);
		sinhvien.add(lblGioiTinh);
		
		radioButonNam = new JRadioButton("Nam");
		radioButonNam.setFont(new Font("Tahoma", Font.PLAIN, 19));
		radioButonNam.setBounds(874, 381, 77, 23);
		sinhvien.add(radioButonNam);
		
		radioButtonNu = new JRadioButton("Nữ");
		radioButtonNu.setFont(new Font("Tahoma", Font.PLAIN, 19));
		radioButtonNu.setBounds(972, 381, 77, 23);
		sinhvien.add(radioButtonNu);
		
		JLabel lbl_Mon1 = new JLabel("Môn 1");
		lbl_Mon1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_Mon1.setBounds(755, 460, 88, 23);
		sinhvien.add(lbl_Mon1);
		
		textFieldMon1 = new JTextField();
		textFieldMon1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textFieldMon1.setColumns(10);
		textFieldMon1.setBounds(870, 460, 166, 29);
		sinhvien.add(textFieldMon1);
		
		JLabel lbl_Mon2 = new JLabel("Môn 2");
		lbl_Mon2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_Mon2.setBounds(755, 503, 88, 23);
		sinhvien.add(lbl_Mon2);
		
		textFieldMon2 = new JTextField();
		textFieldMon2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textFieldMon2.setColumns(10);
		textFieldMon2.setBounds(870, 503, 166, 29);
		sinhvien.add(textFieldMon2);
		
		JLabel lbl_Mon3 = new JLabel("Môn 3");
		lbl_Mon3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_Mon3.setBounds(755, 546, 88, 23);
		sinhvien.add(lbl_Mon3);
		
		textFieldMon3 = new JTextField();
		textFieldMon3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textFieldMon3.setColumns(10);
		textFieldMon3.setBounds(870, 546, 166, 29);
		sinhvien.add(textFieldMon3);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(366, 580, 736, 8);
		sinhvien.add(separator_1_2);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnXoa.setBounds(745, 596, 93, 32);
		btnXoa.addActionListener(qlsvc);
		sinhvien.add(btnXoa);
		
		JButton btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCapNhat.setBounds(568, 596, 116, 32);
		btnCapNhat.addActionListener(qlsvc);
		sinhvien.add(btnCapNhat);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnLuu.setBounds(433, 596, 93, 32);
		btnLuu.addActionListener(qlsvc);
		sinhvien.add(btnLuu);
		
		JButton btnHuyBo = new JButton("Hủy Bỏ");
		btnHuyBo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnHuyBo.setBounds(878, 596, 109, 32);
		btnHuyBo.addActionListener(qlsvc);
		sinhvien.add(btnHuyBo);
		
		JButton btnHuyTim = new JButton("Hủy Tìm");
		btnHuyTim.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnHuyTim.setBounds(1011, 56, 120, 34);
		btnHuyTim.addActionListener(qlsvc);
		sinhvien.add(btnHuyTim);
		
		JLabel lbl_tenLop = new JLabel("Tên Lớp");
		lbl_tenLop.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_tenLop.setBounds(755, 420, 88, 23);
		sinhvien.add(lbl_tenLop);
		
		JPanel hocluc = new JPanel();
		tabbedPane.addTab("Giáo Viên", null, hocluc, null);
		hocluc.setLayout(null);
		
		JLabel lblDanhSachGiaoVien = new JLabel("Danh Sách Giáo Viên");
		lblDanhSachGiaoVien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDanhSachGiaoVien.setBounds(282, 76, 188, 28);
		hocluc.add(lblDanhSachGiaoVien);
		
		comboBoxMonHoc = new JComboBox();
		ArrayList<MonHoc> listMonHoc = MonHoc.getDanhSachMonHoc();
		comboBoxMonHoc.addItem("");
		for (MonHoc monHoc : listMonHoc) {
			comboBoxMonHoc.addItem(monHoc.getTenMon());
		}
		comboBoxMonHoc.setBounds(477, 518, 166, 22);
		hocluc.add(comboBoxMonHoc);
		
		tableGV = new JTable(){
	         @Override
	        public boolean editCellAt(int row, int column, EventObject e) {
	        	// TODO Auto-generated method stub
	        	return false;
	        }
	       };
		tableGV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tableGV.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mã Giáo Viên", "Tên Giáo Viên", "Môn Dạy Học", "Lớp Chủ Nhiệm"}));
		tableGV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount() == 2) {
					DefaultTableModel tableModel = (DefaultTableModel) tableGV.getModel();
					int selectedRow = tableGV.getSelectedRow();
					int idGV = Integer.parseInt(tableModel.getValueAt(selectedRow, 0) + "");
					GiaoVienWindow gv;
					try {
						gv = new GiaoVienWindow(idGV);
						gv.setVisible(true);
						gv.setLocationRelativeTo(null);
						gv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		
		tableGV.setRowHeight(25);
		tableGV.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableGV.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableGV.getColumnModel().getColumn(2).setPreferredWidth(100);

		
		JScrollPane scrollPane_1 = new JScrollPane(tableGV);
		scrollPane_1.setBounds(286, 115, 748, 242);
		hocluc.add(scrollPane_1);
		
		JLabel lblNewLabel = new JLabel("Thông tin giáo viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(345, 368, 172, 22);
		hocluc.add(lblNewLabel);
		
		JComboBox comboBoxGVChuNhiem = new JComboBox();
		comboBoxGVChuNhiem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxGVChuNhiem.addItem("");
		comboBoxGVChuNhiem.addItem("Giáo viên có lớp chủ nhiệm");
		comboBoxGVChuNhiem.addItem("Giáo viên không có lớp chủ nhiệm");
		comboBoxGVChuNhiem.setBounds(325, 36, 288, 29);
		hocluc.add(comboBoxGVChuNhiem);
		
		JLabel lblMGioVin = new JLabel("Mã Giáo Viên");
		lblMGioVin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMGioVin.setBounds(646, 38, 119, 22);
		hocluc.add(lblMGioVin);
		
		JLabel lblMGioVin_1 = new JLabel("Mã Giáo Viên");
		lblMGioVin_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMGioVin_1.setBounds(345, 414, 110, 22);
		hocluc.add(lblMGioVin_1);
		
		JLabel lblMGioVin_1_1 = new JLabel("Tên Giáo Viên");
		lblMGioVin_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMGioVin_1_1.setBounds(345, 463, 125, 22);
		hocluc.add(lblMGioVin_1_1);
		
		textFieldMaGiaoVien = new JTextField();
		textFieldMaGiaoVien.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textFieldMaGiaoVien.setColumns(10);
		textFieldMaGiaoVien.setBounds(477, 411, 166, 29);
		hocluc.add(textFieldMaGiaoVien);
		
		textFieldTenGiaoVien = new JTextField();
		textFieldTenGiaoVien.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textFieldTenGiaoVien.setColumns(10);
		textFieldTenGiaoVien.setBounds(477, 463, 166, 29);
		hocluc.add(textFieldTenGiaoVien);
		
		JLabel lblMGioVin_1_2 = new JLabel("Môn Học");
		lblMGioVin_1_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMGioVin_1_2.setBounds(371, 518, 84, 22);
		hocluc.add(lblMGioVin_1_2);
		
		JLabel lblMGioVin_1_1_1 = new JLabel("Lớp Chủ Nhiệm");
		lblMGioVin_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMGioVin_1_1_1.setBounds(704, 463, 141, 22);
		hocluc.add(lblMGioVin_1_1_1);
		
		comboBoxLopChuNhiem = new JComboBox();
		comboBoxLopChuNhiem.setEnabled(false);
		
		
		comboBoxLopChuNhiem.setBounds(836, 463, 166, 22);
		hocluc.add(comboBoxLopChuNhiem);
		
		
		textFieldMaGiaoVien_timKiem = new JTextField();
		textFieldMaGiaoVien_timKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				dsGiaoVien.clear();
//				String sql = "";
//				int maGiaoVien = 0;
//				try {
//					Connection c = JDBCUtil.openConnection();
//					if(textFieldMaGiaoVien_timKiem.getText().isEmpty()) {
//						if(comboBoxGVChuNhiem.getSelectedIndex() == 1) {
//							sql = "select * from giaovien where lopChuNhiem is not null";
//						}else if (comboBoxGVChuNhiem.getSelectedIndex() == 2) {
//							sql = "select * from giaovien where lopChuNhiem is null";
//						}else {
//							sql = "select * from giaovien";
//						}
//					}else {
//						maGiaoVien = Integer.valueOf(textFieldMaGiaoVien_timKiem.getText());
//						if(comboBoxGVChuNhiem.getSelectedIndex() == 1) {
//							sql = "select * from giaovien where lopChuNhiem is not null"
//									+ " and maGiaoVien LIKE N'%"+maGiaoVien+"%'";
//						}else if (comboBoxGVChuNhiem.getSelectedIndex() == 2) {
//							sql = "select * from giaovien where lopChuNhiem is null"
//									+ " and maGiaoVien LIKE N'%"+maGiaoVien+"%'";
//						}else {
//							sql = "select * from giaoVien where maGiaoVien LIKE N'%"
//									+maGiaoVien+"%'";
//						}
//					}
//					System.out.println(sql);
//					Statement st = c.createStatement();
//					ResultSet rs = st.executeQuery(sql);
//					while (rs.next()) {
//						int maGiaoVien1 = rs.getInt("maGiaoVien");
//						String tenGiaoVien = rs.getString("tenGiaoVien");
//						String tenMonHoc = rs.getString("monHoc");
//						MonHoc monHoc = MonHoc.getMonHocByTen(tenMonHoc);
//						String tenLopChuNhiem = rs.getString("lopChuNhiem");
//						
//						GiaoVien gv = new GiaoVien(maGiaoVien1, tenGiaoVien, tenLopChuNhiem, monHoc);
//						dsGiaoVien.add(gv);
//						
//					}
//
//					JDBCUtil.closeConnection(c);
//					DefaultTableModel tableModel = (DefaultTableModel) tableGV.getModel();
//					tableModel.setRowCount(0);
//					for (GiaoVien gv : dsGiaoVien) {
//						tableModel.addRow(gv.createObjects());
//					}
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
				
				

				//// ********************
				DefaultTableModel tableModel = (DefaultTableModel) tableGV.getModel();
				
				
				dsGiaoVien = GiaoVienDAO.getInstance().selectAll();
				
				
				if(!textFieldMaGiaoVien_timKiem.getText().isEmpty()) {
					if(textFieldMaGiaoVien_timKiem.getText().chars().allMatch(Character::isDigit)) {
						ArrayList<Integer> idGV = GiaoVienDAO.getInstance().selectAllId();
						int idCanTim = Integer.valueOf(textFieldMaGiaoVien_timKiem.getText());
						int viTriTimKiem = timKiemNhiPhan(idGV, 0, idGV.size() - 1, idCanTim);
						if(viTriTimKiem != -1) {
							GiaoVien gv = dsGiaoVien.get(viTriTimKiem);
							tableModel.setRowCount(0);
							tableModel.addRow(new Object[] {gv.getId(), gv.getTenGiaoVien(), gv.getMonHoc().getTenMon(), gv.getLopChuNhiem()});
						}else {
							tableModel.setRowCount(0);
						}
					}else {
						tableModel.setRowCount(0);
						String tenCanTim = textFieldMaGiaoVien_timKiem.getText().toLowerCase();			
						

//						for(GiaoVien gv : dsGiaoVien) {
//							if(gv.getTenGiaoVien().toLowerCase().contains(tenCanTim.toLowerCase())) {
//								tableModel.addRow(new Object[] {gv.getId(), gv.getTenGiaoVien(), gv.getMonHoc().getTenMon(), gv.getLopChuNhiem()});
//							}
//						}
						
					// ******************** Tìm tên theo tìm kiếm nhị phân (Chữ cái đầu)
						ArrayList<GiaoVien> dsGiaoVienTheoTen = GiaoVienDAO.getInstance().selectAllByName();
						ArrayList<String> tenGV = GiaoVienDAO.getInstance().selectAllName();
						int viTriTimKiem = timKiemNhiPhan2(tenGV, 0, tenGV.size() - 1, tenCanTim);
						if(viTriTimKiem != -1) {
							GiaoVien gv = dsGiaoVienTheoTen.get(viTriTimKiem);
							tableModel.setRowCount(0);
							tableModel.addRow(new Object[] {gv.getId(), gv.getTenGiaoVien(), gv.getMonHoc().getTenMon(), gv.getLopChuNhiem()});
						}else {
							tableModel.setRowCount(0);
						}
						
						
					}
				}else {
					showGiaoVienFromDatabase();
				}
				
				
				
			}
		});
		textFieldMaGiaoVien_timKiem.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textFieldMaGiaoVien_timKiem.setColumns(10);
		textFieldMaGiaoVien_timKiem.setBounds(762, 36, 166, 29);
		hocluc.add(textFieldMaGiaoVien_timKiem);
		
		JButton btnTimKiem_1 = new JButton("Tìm");
		btnTimKiem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				dsGiaoVien.clear();
//				int maGiaoVien = 0;
//				String sql = "";
//				try {
//					Connection c = JDBCUtil.openConnection();
//					if(textFieldMaGiaoVien_timKiem.getText().isEmpty()) {
//						if(comboBoxGVChuNhiem.getSelectedIndex() == 1) {
//							sql = "SELECT * FROM giaovien WHERE lopChuNhiem IS not null";
//						}else if (comboBoxGVChuNhiem.getSelectedIndex() == 2) {
//							sql = "SELECT * FROM giaovien WHERE lopChuNhiem IS null";
//						}
//					}else {
//						if (textFieldMaSinhVien_timKiem.getText().chars().allMatch(Character::isDigit)) {
//							maGiaoVien = Integer.valueOf(textFieldMaGiaoVien_timKiem.getText());
//							if(comboBoxGVChuNhiem.getSelectedIndex() == 1) {
//								sql = "select * from giaovien where lopChuNhiem is not null"
//										+ " and maGiaoVien LIKE N'%"+maGiaoVien+"%'";
//							}else if (comboBoxGVChuNhiem.getSelectedIndex() == 2) {
//								sql = "select * from giaovien where lopChuNhiem is null"
//										+ " and maGiaoVien LIKE N'%"+maGiaoVien+"%'";
//							}
//						}
//					}
//					System.out.println(sql);
//					Statement st = c.createStatement();
//					ResultSet rs = st.executeQuery(sql);
//					while (rs.next()) {
//						int maGiaoVien1 = rs.getInt("maGiaoVien");
//						String tenGiaoVien = rs.getString("tenGiaoVien");
//						String tenMonHoc = rs.getString("monHoc");
//						MonHoc monHoc = MonHoc.getMonHocByTen(tenMonHoc);
//						String tenLopChuNhiem = rs.getString("lopChuNhiem");
//
//						
//						GiaoVien gv = new GiaoVien(maGiaoVien1, tenGiaoVien, tenLopChuNhiem, monHoc);
//						dsGiaoVien.add(gv);
//						
//					}
//
//					JDBCUtil.closeConnection(c);
//					
//					
//					DefaultTableModel tableModel = (DefaultTableModel) tableGV.getModel();
//					tableModel.setRowCount(0);
//					for (GiaoVien gv : dsGiaoVien) {
//						tableModel.addRow(gv.createObjects());
//					}
//					
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
				

				//// ********************
				DefaultTableModel tableModel = (DefaultTableModel) tableGV.getModel();
				
				
				dsGiaoVien = GiaoVienDAO.getInstance().selectAll();
				
				
				if(!textFieldMaGiaoVien_timKiem.getText().isEmpty()) {
					if(textFieldMaGiaoVien_timKiem.getText().chars().allMatch(Character::isDigit)) {
						ArrayList<Integer> idGV = GiaoVienDAO.getInstance().selectAllId();
						int idCanTim = Integer.valueOf(textFieldMaGiaoVien_timKiem.getText());
						int viTriTimKiem = timKiemNhiPhan(idGV, 0, idGV.size() - 1, idCanTim);
						if(viTriTimKiem != -1) {
							GiaoVien gv = dsGiaoVien.get(viTriTimKiem);
							tableModel.setRowCount(0);
							tableModel.addRow(new Object[] {gv.getId(), gv.getTenGiaoVien(), gv.getMonHoc().getTenMon(), gv.getLopChuNhiem()});
						}else {
							tableModel.setRowCount(0);
						}
					}else {
						ArrayList<GiaoVien> dsGiaoVienTheoTen = GiaoVienDAO.getInstance().selectAllByName();
						ArrayList<String> tenGV = GiaoVienDAO.getInstance().selectAllName();
						tableModel.setRowCount(0);
						String tenCanTim = textFieldMaGiaoVien_timKiem.getText().toLowerCase();
						for(GiaoVien gv : dsGiaoVienTheoTen) {
							if(gv.getTenGiaoVien().toLowerCase().contains(tenCanTim.toLowerCase())) {
								tableModel.addRow(new Object[] {gv.getId(), gv.getTenGiaoVien(), gv.getMonHoc().getTenMon(), gv.getLopChuNhiem()});
							}
						}
					}
				}else {
					showGiaoVienFromDatabase();
				}
				
			}
		});
		btnTimKiem_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnTimKiem_1.setBounds(938, 11, 120, 34);
		hocluc.add(btnTimKiem_1);
		
		JButton btnHuyTim_1 = new JButton("Hủy Tìm");
		btnHuyTim_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showGiaoVienFromDatabase();
			}
		});
		btnHuyTim_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnHuyTim_1.setBounds(938, 56, 120, 34);
		hocluc.add(btnHuyTim_1);
		
		JButton btnLuu_1 = new JButton("Lưu");
		btnLuu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf(textFieldMaGiaoVien.getText());
				String tenGiaoVien = textFieldTenGiaoVien.getText();
				boolean option = false;
				String lopChuNhiem = "";
				if(radioButonChuNhiem.isSelected()) {
					option = true;
					lopChuNhiem = comboBoxLopChuNhiem.getSelectedItem().toString();
				}else if(radioButtonKoChuNhiem.isSelected()) {
					option = false;
					lopChuNhiem = "";
				}
				
				int maMonHoc = comboBoxMonHoc.getSelectedIndex();
				MonHoc monHoc = MonHoc.getMonHocById(maMonHoc - 1);
				
				GiaoVien gvAdd = new GiaoVien(id, tenGiaoVien, option, lopChuNhiem, monHoc);
				
				
				dsGiaoVien.clear();
				dsGiaoVien = GiaoVienDAO.getInstance().selectAll();
				HashSet<Integer> idGV = new HashSet<Integer>();
				for(int i = 0; i < dsGiaoVien.size(); i++) {
					GiaoVien gv = dsGiaoVien.get(i);
					idGV.add(gv.getId());
				}
				
					
				if(!idGV.contains(id)) {
					try {
						GiaoVienDAO.getInstance().insert(gvAdd);
						showGiaoVienFromDatabase();
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(QuanLySinhVienView.this, "Lớp " + lopChuNhiem + " đã có GV chủ nhiệm");
					}
					
				}else if(idGV.contains(id)) {
					try {
						GiaoVienDAO.getInstance().update(gvAdd);
						showGiaoVienFromDatabase();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(QuanLySinhVienView.this, "Lớp " + lopChuNhiem + " đã có GV chủ nhiệm");
					}
				}
			}
		});
		btnLuu_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnLuu_1.setBounds(345, 596, 93, 32);
		hocluc.add(btnLuu_1);
		
		JButton btnCapNhat_1 = new JButton("Cập Nhật");
		btnCapNhat_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dsGiaoVien.clear();
				dsGiaoVien = GiaoVienDAO.getInstance().selectAll();
				
				DefaultTableModel tableModel = (DefaultTableModel) tableGV.getModel();
				int rowSelected = tableGV.getSelectedRow();
				
				GiaoVien gv = dsGiaoVien.get(rowSelected);
				
				textFieldMaGiaoVien.setText(tableModel.getValueAt(rowSelected, 0) + "");
				textFieldTenGiaoVien.setText(tableModel.getValueAt(rowSelected, 1) + "");
				comboBoxMonHoc.setSelectedItem(tableModel.getValueAt(rowSelected, 2) + "");
				
				
				
				String lopChuNhiem = null;
				try { // Nếu lopChuNhiem != null (Nếu giáo viên có lớp chủ nhiệm)
					lopChuNhiem = tableModel.getValueAt(rowSelected, 3).toString();
					radioButonChuNhiem.setSelected(true);
					comboBoxLopChuNhiem.setEnabled(true);
					comboBoxLopChuNhiem.setSelectedItem(lopChuNhiem);
					
				} catch (NullPointerException e2) {
					// Nếu lopChuNhiem == null (Nếu giáo viên ko có lớp chủ nhiệm)
					radioButtonKoChuNhiem.setSelected(true);
					comboBoxLopChuNhiem.setEnabled(false);
					comboBoxLopChuNhiem.setSelectedIndex(-1);
				}
				
			}
		});
		btnCapNhat_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCapNhat_1.setBounds(481, 596, 116, 32);
		hocluc.add(btnCapNhat_1);
		
		JButton btnXoa_1 = new JButton("Xóa");
		btnXoa_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int luaChon = JOptionPane.showConfirmDialog(QuanLySinhVienView.this, "Bạn có muốn xóa giáo viên này không?", "Delete",
						JOptionPane.YES_NO_OPTION);
				if (luaChon == JOptionPane.YES_OPTION) {
					int rowSelected = tableGV.getSelectedRow();
					if (rowSelected >= 0) {
						DefaultTableModel tableModel = (DefaultTableModel) tableGV.getModel();
						int id = Integer.valueOf(tableModel.getValueAt(rowSelected, 0).toString());
						GiaoVienDAO.getInstance().delete(id);

						showGiaoVienFromDatabase();
					} else {
						JOptionPane.showMessageDialog(QuanLySinhVienView.this, "Ko tìm thấy giáo viên để xóa!");
					}
				}
			}
		});
		btnXoa_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnXoa_1.setBounds(629, 596, 93, 32);
		hocluc.add(btnXoa_1);
		
		JLabel lblGioiTinh_1 = new JLabel("Lựa Chọn");
		lblGioiTinh_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblGioiTinh_1.setBounds(704, 413, 88, 23);
		hocluc.add(lblGioiTinh_1);
		
		String sql1 = "select * from lopHoc";
		try {
			Connection c = JDBCUtil.openConnection();
			PreparedStatement pst = c.prepareStatement(sql1);
			ResultSet rs = pst.executeQuery();
			comboBoxLopChuNhiem.addItem("");
			while(rs.next()) {
				comboBoxLopChuNhiem.addItem(rs.getString("tenLop"));
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		radioButonChuNhiem = new JRadioButton("Chủ Nhiệm");
		radioButonChuNhiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxLopChuNhiem.setEnabled(true);
			}
		});
		radioButonChuNhiem.setFont(new Font("Tahoma", Font.PLAIN, 19));
		radioButonChuNhiem.setBounds(788, 413, 119, 23);
		hocluc.add(radioButonChuNhiem);
		
		radioButtonKoChuNhiem = new JRadioButton("Không Chủ Nhiệm");
		radioButtonKoChuNhiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comboBoxLopChuNhiem.setEnabled(false);
				comboBoxLopChuNhiem.setSelectedItem("");
			}
		});
		radioButtonKoChuNhiem.setFont(new Font("Tahoma", Font.PLAIN, 19));
		radioButtonKoChuNhiem.setBounds(908, 413, 179, 23);
		hocluc.add(radioButtonKoChuNhiem);
		
		buttonGroup_Option = new ButtonGroup();
		buttonGroup_Option.add(radioButonChuNhiem);
		buttonGroup_Option.add(radioButtonKoChuNhiem);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(286, 363, 748, 8);
		hocluc.add(separator_1_1_1);
		
		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(286, 589, 736, 8);
		hocluc.add(separator_1_2_1);
		

		buttonGroup_GioiTinh = new ButtonGroup();
		buttonGroup_GioiTinh.add(radioButonNam);
		buttonGroup_GioiTinh.add(radioButtonNu);

		jPopupMenu = new JPopupMenu();

		JMenuItem jMenu_Reset = new JMenuItem("Refresh");
		jMenu_Reset.addActionListener(qlsvc);
		JMenuItem jMenuItem_Xoa = new JMenuItem("Xóa");
		jMenuItem_Xoa.addActionListener(qlsvc);
		JMenuItem jMenuItem_CapNhat = new JMenuItem("Cập Nhật");
		jMenuItem_CapNhat.addActionListener(qlsvc);

		jPopupMenu.add(jMenu_Reset);
		jPopupMenu.add(jMenuItem_Xoa);
		jPopupMenu.add(jMenuItem_CapNhat);

//		this.add(jPopupMenu);
//		contentPane.add(jPopupMenu);
//		scrollPane.add(jPopupMenu);
		table.add(jPopupMenu);
		
		comboBoxTenLop = new JComboBox();
		String sql = "select * from lopHoc";
		
		try {
			Connection c = JDBCUtil.openConnection();
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			comboBoxTenLop.addItem("");
			while(rs.next()) {
				comboBoxTenLop.addItem(rs.getString("tenLop"));
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		comboBoxTenLop.setBounds(870, 424, 166, 22);
		sinhvien.add(comboBoxTenLop);
		
		JPanel thongKe = new JPanel();
		tabbedPane.addTab("Thống Kê", null, thongKe, null);
		thongKe.setLayout(null);
		
		JButton btnNewButton = new JButton("Thống Kê");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection c = JDBCUtil.openConnection();
					String sql = "SELECT \r\n"
							+ "COUNT(if((thisinh.diemMon1 + thisinh.diemMon2 + thisinh.diemMon3) / 3 >= 8, 1, NULL)) AS hocSinhGioi,\r\n"
							+ "COUNT(if((thisinh.diemMon1 + thisinh.diemMon2 + thisinh.diemMon3) / 3 BETWEEN 6.5 AND 7.9, 1, NULL)) AS hocSinhKha,\r\n"
							+ "COUNT(if((thisinh.diemMon1 + thisinh.diemMon2 + thisinh.diemMon3) / 3 BETWEEN 3.5 AND 6.49, 1, NULL)) AS hocSinhTrungBinh,\r\n"
							+ "COUNT(if((thisinh.diemMon1 + thisinh.diemMon2 + thisinh.diemMon3) / 3 BETWEEN 0 AND 3.49, 1, NULL)) AS hocSinhYeu,\r\n"
							+ "COUNT(*) AS soLuongHocSinh,\r\n"
							+ "(SELECT COUNT(*) FROM giaovien) AS soLuongGiaoVien\r\n"
							+ "FROM thisinh";
					PreparedStatement pst = c.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						lblHocSinhGioi.setText(rs.getInt("hocSinhGioi")+"");
						lblHocSinhKha.setText(rs.getInt("hocSinhKha")+"");
						lblHocSinhTrungBinh.setText(rs.getInt("hocSinhTrungBinh")+"");
						lblHocSinhYeu.setText(rs.getInt("hocSinhYeu")+"");
						lblSoLuongHocSinh.setText(rs.getInt("soLuongHocSinh")+"");
						lblSoLuongGiaoVien.setText(rs.getInt("soLuongGiaoVien")+"");
					}
					String sql_2 = "SELECT DISTINCT thisinh.queQuan, COUNT(thisinh.queQuan) AS soLuong FROM sinhvien.thisinh WHERE (diemMon1 + diemMon2 + diemMon3) / 3 >= 8\r\n"
							+ "GROUP BY thisinh.queQuan\r\n"
							+ "ORDER BY COUNT(thisinh.queQuan) desc\r\n"
							+ "LIMIT 3";
					pst = c.prepareStatement(sql_2);
					rs = pst.executeQuery();
					DefaultTableModel tableModel = (DefaultTableModel) table_thongke.getModel();
					tableModel.setRowCount(0);
					while(rs.next()) {
						String queQuan = rs.getString("queQuan");
						int soLuong = rs.getInt("soLuong");
						
						tableModel.addRow(new Object[] {queQuan, soLuong});
					}
					String sql_3 = "SELECT l.id_GV, g.tenGiaoVien, COUNT(l.id_GV) AS soLuongLopDangDay from lophoc_giaovien AS l \r\n"
							+ "INNER JOIN giaovien AS g ON g.maGiaoVien = l.id_GV\r\n"
							+ "GROUP BY id_GV ORDER BY COUNT(id_GV) DESC LIMIT 3";
					pst = c.prepareStatement(sql_3);
					rs = pst.executeQuery();
					DefaultTableModel tableModel1 = (DefaultTableModel) table_1.getModel();
					tableModel1.setRowCount(0);
					while(rs.next()) {
						int id_GV = rs.getInt("id_GV");
						String tenGiaoVien = rs.getString("tenGiaoVien");
						int soLuongLopDangDay = rs.getInt("soLuongLopDangDay");
						tableModel1.addRow(new Object[] {id_GV, tenGiaoVien, soLuongLopDangDay});
					}
					JDBCUtil.closeConnection(c);
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(35, 597, 121, 31);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Hiển Thị Thống Kê");
		lblNewLabel_1.setBounds(28, 11, 173, 31);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		thongKe.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Học Sinh Giỏi:");
		lblNewLabel_2.setBounds(88, 66, 113, 20);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Học Sinh Khá:");
		lblNewLabel_2_1.setBounds(88, 97, 113, 20);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Học Sinh Trung Bình:");
		lblNewLabel_2_2.setBounds(28, 128, 173, 20);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Học Sinh Yếu:");
		lblNewLabel_2_3.setBounds(88, 159, 113, 20);
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(lblNewLabel_2_3);
		
		lblHocSinhGioi = new JLabel("");
		lblHocSinhGioi.setBounds(205, 66, 113, 20);
		lblHocSinhGioi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(lblHocSinhGioi);
		
		lblHocSinhKha = new JLabel("");
		lblHocSinhKha.setBounds(205, 97, 113, 20);
		lblHocSinhKha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(lblHocSinhKha);
		
		lblHocSinhTrungBinh = new JLabel("");
		lblHocSinhTrungBinh.setBounds(205, 128, 113, 20);
		lblHocSinhTrungBinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(lblHocSinhTrungBinh);
		
		lblHocSinhYeu = new JLabel("");
		lblHocSinhYeu.setBounds(205, 159, 113, 20);
		lblHocSinhYeu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(lblHocSinhYeu);
		
		lblNewLabel_2_3_1 = new JLabel("Tổng số lượng học sinh:");
		lblNewLabel_2_3_1.setBounds(10, 190, 200, 20);
		lblNewLabel_2_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(lblNewLabel_2_3_1);
		
		lblSoLuongHocSinh = new JLabel("");
		lblSoLuongHocSinh.setBounds(205, 190, 113, 20);
		lblSoLuongHocSinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(lblSoLuongHocSinh);
		
		JLabel lblNewLabel_2_3_2 = new JLabel("Số lượng Giáo Viên:");
		lblNewLabel_2_3_2.setBounds(37, 221, 164, 20);
		lblNewLabel_2_3_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(lblNewLabel_2_3_2);
		
		lblSoLuongGiaoVien = new JLabel("");
		lblSoLuongGiaoVien.setBounds(205, 221, 113, 20);
		lblSoLuongGiaoVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thongKe.add(lblSoLuongGiaoVien);
		
		table_thongke = new JTable();
		table_thongke.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table_thongke.setBounds(28, 359, 310, 191);
		table_thongke.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Khu Vực", "Số Lượng Học Sinh Giỏi"
			}
		));
		table_thongke.setRowHeight(25);
		table_thongke.getColumnModel().getColumn(1).setPreferredWidth(20);
		
		JScrollPane scrollPane_2 = new JScrollPane(table_thongke);
		scrollPane_2.setBounds(28, 318, 471, 102);
		thongKe.add(scrollPane_2);
		
		JLabel lblNewLabel_2_3_2_1 = new JLabel("3 khu vực có nhiều học sinh giỏi (sắp xếp giảm dần):");
		lblNewLabel_2_3_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_3_2_1.setBounds(28, 279, 471, 28);
		thongKe.add(lblNewLabel_2_3_2_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(509, 11, 12, 617);
		thongKe.add(separator_1);
		
		JScrollPane scrollPane_2_1 = new JScrollPane((Component) null);
		scrollPane_2_1.setBounds(531, 81, 402, 547);
		thongKe.add(scrollPane_2_1);
		
		table_sinhVien = new JTable();
		table_sinhVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table_sinhVien.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Họ Và Tên", "Giới Tính", "Lớp Học"
			}
		));
		table_sinhVien.setRowHeight(25);
		table_sinhVien.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_sinhVien.getColumnModel().getColumn(1).setPreferredWidth(300);
		scrollPane_2_1.setViewportView(table_sinhVien);
		
		JLabel lblNewLabel_1_1 = new JLabel("Danh Sách Học Sinh:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(531, 55, 200, 31);
		thongKe.add(lblNewLabel_1_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(943, 81, 402, 547);
		thongKe.add(scrollPane_3);
		
		table_GV = new JTable();
		table_GV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table_GV.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Tên Giáo Viên", "Môn Học", "Lớp Học"
			}
		));
		table_GV.setRowHeight(25);
		table_GV.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_GV.getColumnModel().getColumn(1).setPreferredWidth(300);
		scrollPane_3.setViewportView(table_GV);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Danh Sách Giáo Viên:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(943, 49, 200, 31);
		thongKe.add(lblNewLabel_1_1_1);
		
		JComboBox comboBox_Lop = new JComboBox();
		comboBox_Lop.setBounds(1022, 14, 193, 31);
		thongKe.add(comboBox_Lop);
		
		String sql2 = "select * from lopHoc";
		try {
			Connection c = JDBCUtil.openConnection();
			PreparedStatement pst = c.prepareStatement(sql2);
			ResultSet rs = pst.executeQuery();
			comboBox_Lop.addItem("");
			
			JButton btnTimKiem_1_1 = new JButton("Thống Kê");
			btnTimKiem_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String tenLop = comboBox_Lop.getSelectedItem().toString();
					if(!tenLop.equals("")) {
						Connection c = JDBCUtil.openConnection();
						String sql1 = "SELECT @STT := @STT + 1 STT, g.tenGiaoVien, g.monHoc, l.tenLop \r\n"
								+ "FROM lophoc_giaovien AS l\r\n"
								+ "	INNER JOIN giaovien AS g ON  g.maGiaoVien = l.id_GV,\r\n"
								+ "	(SELECT @STT := 0) m\r\n"
								+ "WHERE tenLop LIKE '" + tenLop +"'";
						String sql2 = "SELECT @STT := @STT + 1 STT,\r\n"
								+ "      tenThiSinh, gioiTinh, lophoc\r\n"
								+ "  FROM thisinh, (SELECT @STT := 0) m\r\n"
								+ "  WHERE lophoc LIKE '" + tenLop +"'\r\n"
								+ " ORDER BY tenThiSinh, gioiTinh, lopHoc";
						
						try {
							PreparedStatement pst = c.prepareStatement(sql1);
							ResultSet rs = pst.executeQuery();
							
							DefaultTableModel tableModel_GiaoVien = (DefaultTableModel) table_GV.getModel();
							tableModel_GiaoVien.setRowCount(0);
							while(rs.next()) {
								int STT = rs.getInt("STT");
								String tenGiaoVien = rs.getString("tenGiaoVien");
								String monHoc = rs.getString("monHoc");
								String tenLop1 = rs.getString("tenLop");
								tableModel_GiaoVien.addRow(new Object[] {STT, tenGiaoVien, monHoc, tenLop1});
							}
							pst = c.prepareStatement(sql2);
							rs = pst.executeQuery();
							DefaultTableModel tableModel_SinhVien = (DefaultTableModel) table_sinhVien.getModel();
							tableModel_SinhVien.setRowCount(0);
							while(rs.next()) {
								int STT = rs.getInt("STT");
								String tenThiSinh = rs.getString("tenThiSinh");
								String gioiTinh = "";
								if(rs.getInt("gioiTinh") == 1) {
									gioiTinh = "Nam";
								}else if(rs.getInt("gioiTinh") == 0) {
									gioiTinh = "Nữ";
								}
								String lopHoc = rs.getString("lopHoc");
								tableModel_SinhVien.addRow(new Object[] {STT, tenThiSinh, gioiTinh, lopHoc});
							}
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JDBCUtil.closeConnection(c);
					}
				}
			});
			btnTimKiem_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
			btnTimKiem_1_1.setBounds(1225, 11, 120, 34);
			thongKe.add(btnTimKiem_1_1);
			
			JLabel lblNewLabel_1_1_2 = new JLabel("Thông tin tất cả những Học Sinh và Giáo Viên dạy lớp: ");
			lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel_1_1_2.setBounds(531, 14, 498, 31);
			thongKe.add(lblNewLabel_1_1_2);
			
			JLabel lblNewLabel_2_3_2_1_1 = new JLabel("3 giáo viên dạy nhiều lớp nhất (sắp xếp giảm dần):");
			lblNewLabel_2_3_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_2_3_2_1_1.setBounds(28, 428, 471, 28);
			thongKe.add(lblNewLabel_2_3_2_1_1);
			
			JScrollPane scrollPane_2_2 = new JScrollPane((Component) null);
			scrollPane_2_2.setBounds(28, 467, 471, 102);
			thongKe.add(scrollPane_2_2);
			
			table_1 = new JTable();
			table_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã Giáo Viên" , "T\u00EAn Gi\u00E1o Vi\u00EAn", "S\u1ED1 l\u01B0\u1EE3ng l\u1EDBp \u0111ang d\u1EA1y"
				}
			));
			table_1.setRowHeight(25);
			table_1.getColumnModel().getColumn(0).setPreferredWidth(5);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(200);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(70);
			scrollPane_2_2.setViewportView(table_1);
			while(rs.next()) {
				comboBox_Lop.addItem(rs.getString("tenLop"));
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JPanel thoiKhoaBieu = new JPanel();
		tabbedPane.addTab("Thời Khóa Biểu", null, thoiKhoaBieu, null);
		thoiKhoaBieu.setLayout(null);
		
		
		JButton btnTaoThoiKhoaBieu = new JButton("Tạo Thời Khóa Biểu");
		btnTaoThoiKhoaBieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThoiKhoaBieu dn = new ThoiKhoaBieu();
				dn.setVisible(true);
				dn.setLocationRelativeTo(null);
				dn.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnTaoThoiKhoaBieu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTaoThoiKhoaBieu.setBounds(129, 69, 462, 64);
		thoiKhoaBieu.add(btnTaoThoiKhoaBieu);
		

		MouseListener mml = new QuanLySinhVienController(this);
//		this.addMouseListener(mml);
//		contentPane.addMouseListener(mml);
		scrollPane.addMouseListener(mml);
		table.addMouseListener(mml);

		this.setVisible(true);
		this.setSize(1386, 737);

		showStudentFromDatabase();
		
		showGiaoVienFromDatabase();
	}
	
	public int timKiemNhiPhan(ArrayList<Integer> gv, int l, int r, int x) {
		if(l > r) {
			return -1;
		}else {
			int m = (l + r) / 2;
			if(gv.get(m) == x) {
				return m;
			}else if(x < gv.get(m)) {
				return timKiemNhiPhan(gv, l, m - 1, x);
			}else {
				return timKiemNhiPhan(gv, m + 1, r, x);	
			}
		}
	}
	
	public int timKiemNhiPhan2(ArrayList<String> gv, int l, int r, String x) {
		if(l > r) {
			return -1;
		}else {
			int m = (l + r) / 2;
			if(gv.get(m).toLowerCase().charAt(0) == (x.toLowerCase().charAt(0))) {
				return m;
			}else if (x.toLowerCase().charAt(0) < gv.get(m).toLowerCase().charAt(0)) {
				return timKiemNhiPhan2(gv, l, m - 1, x);
			}else {
				return timKiemNhiPhan2(gv, m + 1, r, x);	
			}
		}
	}
	

	public void xoaForm() {
		textFieldID.setText("");
		textFieldHoVaTen.setText("");
		textFieldMaSinhVien_timKiem.setText("");
		textFieldNgaySinh.setText("");
		textFieldMon1.setText("");
		textFieldMon2.setText("");
		textFieldMon3.setText("");
		comboBoxTenLop.setSelectedIndex(-1);
		comboBoxQueQuan.setSelectedIndex(-1);
		buttonGroup_GioiTinh.clearSelection();
	}

	public void thucHienLuuSinhVien() {
		try { // phải try catch, vì user có thể nhập sai dữ liệu từ
			int maThiSinh = Integer.valueOf(this.textFieldID.getText());
			String tenThiSinh = this.textFieldHoVaTen.getText().toString();
			int maTinh = this.comboBoxQueQuan.getSelectedIndex();
			// lấy các con số trong comboBox ra
			Tinh queQuan = Tinh.getTinhById(maTinh - 1);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = df.parse(this.textFieldNgaySinh.getText());
			java.sql.Date ngaySinh = new java.sql.Date(date.getTime());

			boolean gioiTinh = true;
			
			if (this.radioButonNam.isSelected()) {
				gioiTinh = true;
			} else if (this.radioButtonNu.isSelected()) {
				gioiTinh = false;
			}
			// lấy các con số trong comboBox ra
			String tenLop = comboBoxTenLop.getSelectedItem().toString();
			float diemMon1 = Float.valueOf(this.textFieldMon1.getText());
			float diemMon2 = Float.valueOf(this.textFieldMon2.getText());
			float diemMon3 = Float.valueOf(this.textFieldMon3.getText());

			ThiSinh tsAdd = new ThiSinh(maThiSinh, tenThiSinh, queQuan, ngaySinh, gioiTinh, diemMon1, diemMon2, diemMon3, tenLop);
			
			dsSinhVien.clear();
			dsSinhVien = ThiSinhDAO.getInstance().selectAll();
			
			Set<Integer> idThiSinh = new HashSet<Integer>();
			for(int i = 0; i < dsSinhVien.size(); i++) {
				ThiSinh tsId = dsSinhVien.get(i);
				idThiSinh.add(tsId.getMaThiSinh());
			}
			
			if (!idThiSinh.contains(maThiSinh)) {
				ThiSinhDAO.getInstance().insert(tsAdd);
				showStudentFromDatabase();
			} else {
				ThiSinhDAO.getInstance().update(tsAdd);
				showStudentFromDatabase();
			}
			

		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
		}

	}

	public void hienThiThongTinThiSinhDaChon() {
		// TODO Auto-generated method stub
		

		int rowSelected = table.getSelectedRow();

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

		this.textFieldID.setText(tableModel.getValueAt(rowSelected, 0) + "");
		this.textFieldHoVaTen.setText(tableModel.getValueAt(rowSelected, 1) + "");
		this.comboBoxQueQuan.setSelectedItem(tableModel.getValueAt(rowSelected, 2) + "");
//		SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
		// date to String
		String dateToString = tableModel.getValueAt(rowSelected, 3) + "";
		this.textFieldNgaySinh.setText(dateToString);
		String gioiTinh = tableModel.getValueAt(rowSelected, 4).toString();
		if (gioiTinh.equals("Nam")) { 
			radioButonNam.setSelected(true);
		} else if(gioiTinh.equals("Nữ")) { 
			radioButtonNu.setSelected(true);
		}
		
		try {
			String tenLop = tableModel.getValueAt(rowSelected, 5).toString();
				
				this.comboBoxTenLop.setSelectedItem(tenLop);
		} catch (NullPointerException e) {
			this.comboBoxTenLop.setSelectedIndex(0);;
		}
		
		// Lấy điểm 3 môn
		dsSinhVien.clear();
		dsSinhVien = ThiSinhDAO.getInstance().selectAll();
		int id = Integer.valueOf(tableModel.getValueAt(rowSelected, 0) + "");
		for(int i = 0; i < dsSinhVien.size(); i++) {
			ThiSinh ts = dsSinhVien.get(i);
			if(ts.getMaThiSinh() == id) {
				this.textFieldMon1.setText(ts.getDiemMon1() + "");
				this.textFieldMon2.setText(ts.getDiemMon2() + "");
				this.textFieldMon3.setText(ts.getDiemMon3() + "");
				break;
			}
		}
		
	}

	public void thucHienXoa() {

		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa sinh viên này không?", "Delete",
				JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			int rowSelected = table.getSelectedRow();
			if (rowSelected >= 0) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				int id = Integer.valueOf(tableModel.getValueAt(rowSelected, 0).toString());
				ThiSinhDAO.getInstance().delete(id);

				showStudentFromDatabase();
			} else {
				JOptionPane.showMessageDialog(this, "Ko tìm thấy sinh viên để xóa!");
			}
		}

	}

	public void thucHienTimKiem() {
//		loadDataToArrayList();
		dsSinhVien.clear();
		try {
			Connection c = JDBCUtil.openConnection();
			String sql = "";
			if (this.textFieldMaSinhVien_timKiem.getText().isEmpty()) {
				sql = "select * from thisinh where queQuan like N'%"
						+ (String) comboBoxQueQuan_timKiem.getSelectedItem() + "%'";
			} else {
				if (textFieldMaSinhVien_timKiem.getText().chars().allMatch(Character::isDigit)) {
					// nếu như nhập chuỗi thì ko nhận, nhập số thì ms nhận
					int maThiSinh = Integer.parseInt(textFieldMaSinhVien_timKiem.getText());
					sql = "select * from thisinh where queQuan like N'%"
							+ (String) comboBoxQueQuan_timKiem.getSelectedItem() + "%'" + " and maThiSinh Like N'%"
							+ maThiSinh + "%'";
				} else {
					sql = "select * from thisinh where queQuan like N'%"
							+ (String) comboBoxQueQuan_timKiem.getSelectedItem() + "%'" + " and tenThiSinh like N'%"
							+ this.textFieldMaSinhVien_timKiem.getText() + "%'";
				}
				// 
			}
			System.out.println(sql);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int maThiSinh1 = rs.getInt("maThiSinh");
				String tenThiSinh = rs.getString("tenThiSinh");
				String queQuan = rs.getString("queQuan");
				Tinh tinh = Tinh.getTinhByTen(queQuan);
				java.sql.Date ngaySinh = rs.getDate("ngaySinh");
				boolean gioiTinh = true;
				if (rs.getInt("gioiTinh") == 1) {
					gioiTinh = true;
				} else if (rs.getInt("gioiTinh") == 0) {
					gioiTinh = false;
				}
				float diemMon1 = rs.getFloat("diemMon1");
				float diemMon2 = rs.getFloat("diemMon2");
				float diemMon3 = rs.getFloat("diemMon3");
				String tenLop = rs.getString("tenLop");
				ThiSinh ts = new ThiSinh(maThiSinh1, tenThiSinh, tinh, ngaySinh, gioiTinh, diemMon1, diemMon2, diemMon3, tenLop);
				dsSinhVien.add(ts);

			}

			JDBCUtil.closeConnection(c);
//			loadDataFromArrayListToTable(model.dsThiSinh);
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setRowCount(0);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			for(int i = 0; i < dsSinhVien.size(); i++) {
				ThiSinh ts = dsSinhVien.get(i);
				tableModel.addRow(ts.createObjects());
				
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

	
	
	public void showStudentFromDatabase() {
		dsSinhVien = ThiSinhDAO.getInstance().selectAll();
		
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		
		tableModel.setRowCount(0);

		// addRow theo vòng forEach của từ arrayList dsThiSinh trong model
		for(int i = 0; i < dsSinhVien.size(); i++) {
			ThiSinh ts = dsSinhVien.get(i);
			tableModel.addRow(ts.createObjects());
		}
	}
	
	public void showGiaoVienFromDatabase() {
		dsGiaoVien = GiaoVienDAO.getInstance().selectAll();
		
		DefaultTableModel tableModel = (DefaultTableModel) tableGV.getModel();
		
		tableModel.setRowCount(0);
		
		for(int i = 0; i < dsGiaoVien.size(); i++) {
			GiaoVien gv = dsGiaoVien.get(i);
			tableModel.addRow(gv.createObjects());
		}
	}

	public void hienThiAbout() {
		JOptionPane.showMessageDialog(this, "Phần mềm quản lý Sinh Viên 1.0");
	}

	public void thoatKhoiChuongTrinh() {
		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thoát khỏi chương trình hay không ?",
				"Exit", JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void luuFileKieuText(String path) {

		// lưu File
		System.out.println("Bắt đầu lưu");
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(path);
			// true là lần sau tự thêm vào
			// Lưu dữ liệu
			for(int i = 0; i < dsSinhVien.size(); i++) {
				ThiSinh thiSinh = dsSinhVien.get(i);
				//
				String line = thiSinh.getThongTinThiSinh();
				// chuyển string to byte
				byte[] b = line.getBytes("utf8");
				// Save (Ghi lại cái byte)
				fos.write(b);
			}
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void luuFileKieuDoiTuong(String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for(int i = 0; i < dsSinhVien.size(); i++) {
				ThiSinh ts = dsSinhVien.get(i);
				oos.writeObject(ts);
			}
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void thucHienSaveFile() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String tenFile = file.getAbsolutePath();
			if (tenFile.endsWith(".txt")) {
				luuFileKieuText(tenFile);
			} else {
				luuFileKieuDoiTuong(tenFile);
			}

		}

	}

	public void docFileKieuDoiTuong(File file) {
		String tenFile = file.getAbsolutePath();
		ArrayList<ThiSinh> ds = new ArrayList<ThiSinh>();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		try {
			FileInputStream fis = new FileInputStream(tenFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ThiSinh ts = (ThiSinh) ois.readObject();
			while (ts != null) {
				ds.add(ts);
				System.out.println(ts.toString());
			}

//			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//			tableModel.addRow(new Object[] {ts.getMaThiSinh(), ts.getTenThiSinh(), ts.getQueQuan().getTenTinh(),
//					df.format(ts.getNgaySinh()), (ts.isGioiTinh() ? "Nam" : "Nữ"), ts.getDiemMon1(), ts.getDiemMon2(),
//					ts.getDiemMon3() });
			ois.close();
			fis.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
//		this.model.setDsThiSinh(ds);
	}

	public void docFileKieuText(File file) {
		ThiSinh ts2 = null;

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.getDataVector().removeAllElements(); // remove all dòng

		try {
			FileReader fr = new FileReader(file.getAbsolutePath());
			BufferedReader br = new BufferedReader(fr);
			String line = ""; // Từng dòng trong file
			while (true) { // đọc dữ liệu từng dòng
				line = br.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);

				String txt[] = line.split("\\s\\|\\s");

				int maThiSinh = Integer.parseInt(txt[0]);
				String tenThiSinh = txt[1];
				Tinh queQuan = Tinh.getTinhByTen(txt[2]);
				java.util.Date d1;
				d1 = new SimpleDateFormat("yyyy-MM-dd").parse(txt[3]);
				java.sql.Date d2 = new java.sql.Date(d1.getTime());
				boolean gioiTinh = true;
				if (txt[4].equals("Nam")) {
					gioiTinh = true;
				} else if (txt[4].equals("Nữ")) {
					gioiTinh = false;
				}
				String tenLop = txt[5];
				float diemMon1 = Float.valueOf(txt[6]);
				float diemMon2 = Float.valueOf(txt[7]);
				float diemMon3 = Float.valueOf(txt[8]);
//				float diemTrungBinh = Float.valueOf(txt[9]);
//				String hocLuc = txt[10];

				ts2 = new ThiSinh(maThiSinh, tenThiSinh, queQuan, d2, gioiTinh, diemMon1, diemMon2, diemMon3,
					 tenLop);
				

				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				tableModel.addRow(ts2.createObjects());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void thucHienOpenFile() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if (file.getAbsolutePath().endsWith(".txt")) {
				docFileKieuText(file);
			} else {
				docFileKieuDoiTuong(file);
//				thucHienLoadLaiDuLieu2();
			}

		}
	}

	public void signOut() {
		int luaChon = JOptionPane.showConfirmDialog(this, 
	"Bạn có chắc chắn muốn đăng xuất không?", "Sign Out", JOptionPane.YES_NO_OPTION);
		if(luaChon == JOptionPane.YES_OPTION) {
			DangNhap dn = new DangNhap();
			dn.setVisible(true);
			dn.setLocationRelativeTo(null);
			this.dispose();
		}
	}
}
