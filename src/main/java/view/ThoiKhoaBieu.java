package view;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import MyList.MyDoublyLinkedList;
import dao.GiaoVienDAO;
import database.JDBCUtil;
import model.GiaoVien;
import model.MonHoc;
import model.SapXepTKB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

public class ThoiKhoaBieu extends JFrame {

	private JPanel contentPane;
	private JTable table_tkb;
	public MyDoublyLinkedList<GiaoVien> dsGiaoVien = new MyDoublyLinkedList<GiaoVien>();
	private JComboBox comboBox_tenGV;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ThoiKhoaBieu frame = new ThoiKhoaBieu();
					frame.setVisible(true);
					frame.setSize(1500, 579);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	public static class ProgressDialog extends JDialog {

		private JLabel message;
		private JLabel subMessage;
		private JProgressBar progressBar;

		public ProgressDialog(Component parent, SwingWorker worker) {

			super(parent == null ? null : SwingUtilities.getWindowAncestor(parent));
			setModal(true);

			((JComponent) getContentPane()).setBorder(new EmptyBorder(8, 8, 8, 8));

			message = new JLabel("Đang chờ để sắp xếp và tính toán cho tkb");
			subMessage = new JLabel("Đang chạy....");
			progressBar = new JProgressBar();

			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(2, 2, 2, 2);
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.WEST;
			add(message, gbc);

			gbc.gridy++;
			add(subMessage, gbc);

			gbc.gridy++;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			add(progressBar, gbc);

			pack();

			worker.addPropertyChangeListener(new PropertyChangeHandler());
			switch (worker.getState()) {
			case PENDING:
				worker.execute();
				break;
			}

		}

		public static void showProgress(Component parent, SwingWorker worker) {

			ProgressDialog dialog = new ProgressDialog(parent, worker);
			dialog.setLocationRelativeTo(parent);
			dialog.setVisible(true);

		}

		public class PropertyChangeHandler implements PropertyChangeListener {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("state")) {
					SwingWorker.StateValue state = (SwingWorker.StateValue) evt.getNewValue();
					switch (state) {
					case DONE:
						dispose();
						break;
					}
				} else if (evt.getPropertyName().equals("progress")) {
					progressBar.setValue((int) evt.getNewValue());
				}
			}

		}

	}

	
	
	
	
	
	
	/**
	 * Create the frame.
	 */
	
	public ThoiKhoaBieu() {
		SwingWorker worker = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				for(int index = 0; index <= 1000; index++) {
					Thread.sleep(7);
					setProgress(index);
				}
				return null;
			}

		};
		
		ProgressDialog.showProgress(null, worker);
		
		// Display Process Dialog
		
		setBounds(100, 100, 719, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Thời Khóa Biểu Các Lớp");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(319, 0, 219, 31);
		contentPane.add(lblNewLabel_1_1_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(68, 70, 799, 344);
		contentPane.add(scrollPane_3);
		
		final String timet[][] = {
				{ "1", "", "", "", "", "", "" },
				{ "2", "", "", "", "", "", "" }, 
				{ "3", "", "", "", "", "", "" },
				{ "4", "", "", "", "", "", "" }, 
				{ "5", "", "", "", "", "", "" },
				{ "6", "", "", "", "", "", "" },
				{ "7", "", "", "", "", "", "" }};
		String column[] = { "Tiết", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7"};
		
		table_tkb = new JTable(timet, column)
		{
			public Component prepareRenderer(TableCellRenderer r, int rw, int col) {
				Component c = super.prepareRenderer(r, rw, col);
				c.setBackground(Color.WHITE);
				if(rw == 4 || rw == 5 || rw == 6) {
					c.setBackground(Color.PINK);
				}
				if(rw == 4 && col == 6 || rw == 5 && col == 6 || rw == 6 && col == 6  ) {
					c.setBackground(Color.LIGHT_GRAY);
				}
				return c;
			}
		};
		table_tkb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table_tkb.setBorder(new LineBorder(Color.BLACK, 2));
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		for(int i = 0; i < table_tkb.getColumnCount(); i++) {
			table_tkb.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}

		table_tkb.getColumnModel().getColumn(0).setPreferredWidth(5);
		table_tkb.setRowHeight(45);
		
		scrollPane_3.setViewportView(table_tkb);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Sáng");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(10, 172, 51, 31);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Chiều");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(7, 330, 54, 31);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblMGioVin_1_3_2 = new JLabel("Tên Lớp");
		lblMGioVin_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMGioVin_1_3_2.setBounds(134, 467, 75, 22);
		contentPane.add(lblMGioVin_1_3_2);
		
		
		Map<String, SapXepTKB> Map_SapXep = new HashMap<String, SapXepTKB>();
		Map<String, MonHoc> Map_MonHoc = new HashMap<String, MonHoc>();
//		Map<String, GiaoVien> Map_GiaoVien = new HashMap<String, GiaoVien>();
		
		JComboBox comboBox_tenLop = new JComboBox();
//			comboBox_tenLop.addItem("");
		
		comboBox_tenLop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 6; j++) {
						if((i == 0 && j == 0)) {
							timet[i][j + 1] = "Chào Cờ";
						}else if(i == 1 && j == 0) {
							dsGiaoVien.clear();
							dsGiaoVien = GiaoVienDAO.getInstance().selectAll();
							for(int k = 0; k < dsGiaoVien.size(); k++) {
								GiaoVien gv = dsGiaoVien.get(k);
								try {
									if(gv.getLopChuNhiem().equals(comboBox_tenLop.getSelectedItem().toString())) {
										String tenCuaGV = gv.getTenGiaoVien().substring(gv.getTenGiaoVien().lastIndexOf(" ") + 1);
										timet[i][j + 1] = "SHCN" + " (" + tenCuaGV + ")";
										break;
									}
								} catch (NullPointerException e2) {
									// TODO: handle exception
									timet[i][j + 1] = "SHCN";
								}
							}
							
						}else {
							String tenLopTrongComboBox = comboBox_tenLop.getSelectedItem().toString();
							// lấy ra tên lớp trong comboBox
							timet[i][j + 1] = Map_SapXep.get(tenLopTrongComboBox).a[i][j];
						}
					}
				}
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		
		comboBox_tenLop.setBounds(228, 467, 191, 31);
		contentPane.add(comboBox_tenLop);
		
		JLabel lblMGioVin_1_3_1_1_1 = new JLabel("Giáo Viên");
		lblMGioVin_1_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMGioVin_1_3_1_1_1.setBounds(455, 467, 91, 22);
		contentPane.add(lblMGioVin_1_3_1_1_1);
		
		comboBox_tenGV = new JComboBox();
		comboBox_tenGV.addItem("");
		
		ArrayList<GiaoVien> tenGV = new ArrayList<GiaoVien>();
		try {

			Connection c = JDBCUtil.openConnection();
			
			String sql = "SELECT tenGiaoVien FROM sinhvien.giaovien";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String name = rs.getString("tenGiaoVien");
				GiaoVien gv = new GiaoVien();
				gv.setTenGiaoVien(name);
				tenGV.add(gv);
				
				comboBox_tenGV.addItem(name);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		comboBox_tenGV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 6; j++) {
						if(i == 0 && j == 0) {
							timet[i][j + 1] = "Chào Cờ";
						}else if(i == 1 && j == 0) {
							// Xét lớp chủ nhiệm
							dsGiaoVien.clear();
							dsGiaoVien = GiaoVienDAO.getInstance().selectAll();
							for(int k = 0; k < dsGiaoVien.size(); k++) {
								GiaoVien gv = dsGiaoVien.get(k);
								if(comboBox_tenGV.getSelectedItem().toString().equals(gv.getTenGiaoVien())) {
									if(gv.getLopChuNhiem() != null) {
										timet[i][j + 1] = gv.getLopChuNhiem() + " (SHCN)";
									}else {
										timet[i][j + 1] = "";
									}
								}
							}
						}else {
							String tenGVTrongComboBox = comboBox_tenGV.getSelectedItem().toString();
//							timet[i][j + 1] = Map_GiaoVien.get(tenGVTrongComboBox).a[i][j];
						}
					}
				}
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		comboBox_tenGV.setBounds(546, 467, 191, 31);
		contentPane.add(comboBox_tenGV);
		String sql2 = "SELECT l.tenLop FROM lophoc l \r\n"
				+ "INNER JOIN thisinh t ON t.lopHoc = l.tenLop\r\n"
				+ "GROUP BY lophoc";
		
		try {
			Connection c = JDBCUtil.openConnection();
			PreparedStatement pst = c.prepareStatement(sql2);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String tenMonHocTrongComboBox = "", tenGVTrongComboBox = "", tenLopTrongComboBox = ""; 
				tenLopTrongComboBox = rs.getString("tenLop");
				System.err.println("Lớp "+ tenLopTrongComboBox);
				
				Map_SapXep.put(tenLopTrongComboBox, new SapXepTKB());

				ArrayList<MonHoc> monHoc = MonHoc.getDanhSachMonHoc();
				for (MonHoc mh : monHoc) {
					
					tenMonHocTrongComboBox = mh.getTenMon();
					Map_MonHoc.put(tenMonHocTrongComboBox, new MonHoc());
					Map_MonHoc.get(tenMonHocTrongComboBox).setTenMon(tenMonHocTrongComboBox);
					
					
					if(mh.getTenMon().equals("Toán") || mh.getTenMon().equals("Văn") || mh.getTenMon().equals("Anh")) {
						Map_MonHoc.get(mh.getTenMon()).setSoTiet(4);
					}else if(mh.getTenMon().equals("Lý") || mh.getTenMon().equals("Hóa") || mh.getTenMon().equals("Sinh")) {
						Map_MonHoc.get(mh.getTenMon()).setSoTiet(3);
					}else if(mh.getTenMon().equals("Sử") || mh.getTenMon().equals("Địa") || mh.getTenMon().equals("GDCD")) {
						Map_MonHoc.get(mh.getTenMon()).setSoTiet(1);
					}
					System.out.println("Môn: " + mh);
					
					MonHoc x = Map_MonHoc.get(tenMonHocTrongComboBox); 
					Map_SapXep.get(tenLopTrongComboBox).add(x);

				}
				comboBox_tenLop.addItem(tenLopTrongComboBox);
			}
			
			JDBCUtil.closeConnection(c);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(873, 11, 12, 436);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 0));
		separator_1.setBounds(0, 275, 71, 14);
		contentPane.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBackground(Color.BLACK);
		separator_1_1.setBounds(0, 88, 71, 14);
		contentPane.add(separator_1_1);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBackground(Color.BLACK);
		separator_1_2.setBounds(0, 408, 71, 14);
		contentPane.add(separator_1_2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500, 579);
		
		
		
		
		
	}
}