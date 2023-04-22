package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import MyList.MyDoublyLinkedList;
import database.JDBCUtil;
import model.ThiSinh;
import model.Tinh;

public class ThiSinhDAO implements DAOInterface<ThiSinh>{
	
	public static ThiSinhDAO getInstance() {
		return new ThiSinhDAO();
	}

	@Override
	public void insert(ThiSinh t) {
		try {
			Connection c = JDBCUtil.openConnection();
			
			
			Statement st = c.createStatement();
			
			if(!t.getLopHoc().equals("")) {
				String sql = "Insert into thisinh(maThiSinh, tenThiSinh, queQuan, ngaySinh, gioiTinh, lopHoc, diemMon1, diemMon2, diemMon3)"+
						" Values('"+t.getMaThiSinh()+"', '"+t.getTenThiSinh()+"', '"
					+t.getQueQuan().getTenTinh()+"', '"+t.getNgaySinh()+"', "
					+(t.isGioiTinh()?"1":"0") +", '" + t.getLopHoc() + "', '" +t.getDiemMon1()+"', '"
					+t.getDiemMon2()+"', '"+t.getDiemMon3()+"')";
				System.out.println(sql);
			
				st.executeUpdate(sql);
			}else {
				String sql = "Insert into thisinh(maThiSinh, tenThiSinh, queQuan, ngaySinh, gioiTinh, diemMon1, diemMon2, diemMon3)"+
						" Values('"+t.getMaThiSinh()+"', '"+t.getTenThiSinh()+"', '"
					+t.getQueQuan().getTenTinh()+"', '"+t.getNgaySinh()+"', "
					+(t.isGioiTinh()?"1":"0") +", '" +t.getDiemMon1()+"', '"
					+t.getDiemMon2()+"', '"+t.getDiemMon3()+"')";
				System.out.println(sql);
			
				st.executeUpdate(sql);
			}
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ThiSinh t) {
		try {
			Connection c = JDBCUtil.openConnection();
			if(!t.getLopHoc().equals("")) {
				String sql = "Update thisinh"+
						" set"+
						" tenThiSinh = ?,"+
						" queQuan = ?,"+
						" ngaySinh = ?,"+
						" gioiTinh = ?,"+
						" lopHoc = ?,"+
						" diemMon1 = ?,"+
						" diemMon2 = ?,"+
						" diemMon3 = ?"+
						" where maThiSinh = ?";
				PreparedStatement pst = c.prepareStatement(sql);
				pst.setString(1, t.getTenThiSinh());
				pst.setString(2, t.getQueQuan().getTenTinh());
				pst.setDate(3, t.getNgaySinh());
				pst.setBoolean(4, t.isGioiTinh());
				pst.setString(5, t.getLopHoc());
				pst.setFloat(6, t.getDiemMon1());
				pst.setFloat(7, t.getDiemMon2());
				pst.setFloat(8, t.getDiemMon3());
				pst.setInt(9, t.getMaThiSinh());
				System.out.println(sql);
				
				pst.executeUpdate();
			}else {
				String sql = "Update thisinh"+
						" set"+
						" tenThiSinh = ?,"+
						" queQuan = ?,"+
						" ngaySinh = ?,"+
						" gioiTinh = ?,"+
						" lopHoc = ?,"+
						" diemMon1 = ?,"+
						" diemMon2 = ?,"+
						" diemMon3 = ?"+
						" where maThiSinh = ?";
				PreparedStatement pst = c.prepareStatement(sql);
				pst.setString(1, t.getTenThiSinh());
				pst.setString(2, t.getQueQuan().getTenTinh());
				pst.setDate(3, t.getNgaySinh());
				pst.setBoolean(4, t.isGioiTinh());
				pst.setString(5, null);
				pst.setFloat(6, t.getDiemMon1());
				pst.setFloat(7, t.getDiemMon2());
				pst.setFloat(8, t.getDiemMon3());
				pst.setInt(9, t.getMaThiSinh());
				System.out.println(sql);
				
				pst.executeUpdate();
			}
			
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int matThiSinh) {
		try {
			Connection c = JDBCUtil.openConnection();
			
			String sql = "delete from thisinh"+
						" where maThiSinh = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, matThiSinh);
			
			pst.executeUpdate();
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public MyDoublyLinkedList<ThiSinh> selectAll() {
		MyDoublyLinkedList<ThiSinh> list = new MyDoublyLinkedList<ThiSinh>();
		String sql = "select * from thisinh";
		
		try {
			Connection c = JDBCUtil.openConnection();
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				ThiSinh ts = new ThiSinh();
				ts.setMaThiSinh(Integer.valueOf(rs.getString("maThiSinh")));
				ts.setTenThiSinh(rs.getString("tenThiSinh"));
				Tinh queQuan = new Tinh(rs.getString("queQuan"));
				ts.setQueQuan(queQuan);
				ts.setNgaySinh(rs.getDate("ngaySinh"));
				if(rs.getInt("gioiTinh") == 1) {
					ts.setGioiTinh(true);
				}else if(rs.getInt("gioiTinh") == 0) {
					ts.setGioiTinh(false);
				}
				ts.setLopHoc(rs.getString("lopHoc"));
				ts.setDiemMon1(rs.getFloat("diemMon1"));
				ts.setDiemMon2(rs.getFloat("diemMon2"));
				ts.setDiemMon3(rs.getFloat("diemMon3"));
				list.add(ts);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
}
