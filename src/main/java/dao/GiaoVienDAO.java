package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import MyList.MyDoublyLinkedList;
import database.JDBCUtil;
import model.GiaoVien;
import model.MonHoc;
import model.ThiSinh;
import model.Tinh;

public class GiaoVienDAO implements DAOInterface<GiaoVien>{
	
	public static GiaoVienDAO getInstance() {
		return new GiaoVienDAO();
	}

	@Override
	public void insert(GiaoVien t) throws SQLException {
			Connection c = JDBCUtil.openConnection();
			
			
			Statement st = c.createStatement();
			if(!t.getLopChuNhiem().equals("")) {
				String sql = "Insert into giaovien(maGiaoVien, tenGiaoVien, monHoc, lopChuNhiem)"+
						" Values('"+t.getId()+"', '"+t.getTenGiaoVien()+"', '"
					+t.getMonHoc().getTenMon() + "', '"
					+t.getLopChuNhiem()+"')";
				System.out.println(sql);
				
				st.executeUpdate(sql);
			}else {
				String sql = "Insert into giaovien(maGiaoVien, tenGiaoVien, monHoc)"+
						" Values('"+t.getId()+"', '"+t.getTenGiaoVien()+"', '"
					+t.getMonHoc().getTenMon() + "')";
				System.out.println(sql);
				
				st.executeUpdate(sql);
			}
			
			JDBCUtil.closeConnection(c);
	}

	@Override
	public void update(GiaoVien t) throws SQLException {
			Connection c = JDBCUtil.openConnection();
			
			if(!t.getLopChuNhiem().equals("")) {
				String sql = "Update giaovien"+
						" set"+
						" tenGiaoVien = ?,"+
						" monHoc = ?,"+
						" lopChuNhiem = ?"+
						" where maGiaoVien = ?";
				PreparedStatement pst = c.prepareStatement(sql);
				pst.setString(1, t.getTenGiaoVien());
				pst.setString(2, t.getMonHoc().getTenMon());
				pst.setString(3, t.getLopChuNhiem());
				pst.setInt(4, t.getId());
				System.out.println(sql);
				pst.executeUpdate();
			}else {
				String sql = "Update giaovien"+
						" set"+
						" tenGiaoVien = ?,"+
						" monHoc = ?,"+
						" lopChuNhiem = ?"+
						" where maGiaoVien = ?";
				PreparedStatement pst = c.prepareStatement(sql);
				pst.setString(1, t.getTenGiaoVien());
				pst.setString(2, t.getMonHoc().getTenMon());
				pst.setString(3, null);
				pst.setInt(4, t.getId());
				System.out.println(sql);
				pst.executeUpdate();
			}
			JDBCUtil.closeConnection(c);
	}

	@Override
	public void delete(int maGiaoVien) {
		try {
			Connection c = JDBCUtil.openConnection();
			
			String sql = "delete from giaovien"+
						" where maGiaoVien = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, maGiaoVien);
			
			pst.executeUpdate();
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public MyDoublyLinkedList<GiaoVien> selectAll() {
		MyDoublyLinkedList<GiaoVien> list = new MyDoublyLinkedList<GiaoVien>();
		String sql = "select * from giaovien ORDER by maGiaoVien asc";
		
		try {
			Connection c = JDBCUtil.openConnection();
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				GiaoVien ts = new GiaoVien();
				
				ts.setId(Integer.valueOf(rs.getString("maGiaoVien")));
				ts.setTenGiaoVien(rs.getString("tenGiaoVien"));
				MonHoc monHoc = new MonHoc(rs.getString("monHoc"));
				ts.setMonHoc(monHoc);
				ts.setLopChuNhiem(rs.getString("lopChuNhiem"));
				list.add(ts);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public ArrayList<Integer> selectAllId(){
		ArrayList<Integer> idGV = new ArrayList<>();
		String sql = "SELECT maGiaoVien FROM giaovien ORDER by maGiaoVien asc";
		try {
			Connection c = JDBCUtil.openConnection();
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				idGV.add(Integer.valueOf(rs.getString("maGiaoVien")));
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idGV;
	}
	
	public ArrayList<String> selectAllName(){
		ArrayList<String> tenGV = new ArrayList<>();
		String sql = "SELECT tenGiaoVien FROM giaovien ORDER BY tenGiaoVien asc";
		try {
			Connection c = JDBCUtil.openConnection();
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				tenGV.add(rs.getString("tenGiaoVien"));
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tenGV;
	}
	
	public ArrayList<GiaoVien> selectAllByName() {
		ArrayList<GiaoVien> list = new ArrayList<GiaoVien>();
		String sql = "select * from giaovien ORDER by tenGiaoVien asc";
		
		try {
			Connection c = JDBCUtil.openConnection();
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				GiaoVien ts = new GiaoVien();
				
				ts.setId(Integer.valueOf(rs.getString("maGiaoVien")));
				ts.setTenGiaoVien(rs.getString("tenGiaoVien"));
				MonHoc monHoc = new MonHoc(rs.getString("monHoc"));
				ts.setMonHoc(monHoc);
				ts.setLopChuNhiem(rs.getString("lopChuNhiem"));
				list.add(ts);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
}
