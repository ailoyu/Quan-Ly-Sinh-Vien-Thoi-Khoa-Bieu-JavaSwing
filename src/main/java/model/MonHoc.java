package model;

import java.util.ArrayList;
import java.util.Objects;

public class MonHoc {
	private int id;
	private String tenMon;
	
	private int soTiet;
	private ArrayList<String> giaoVien = new ArrayList<String>();
	
	public MonHoc() {
		
	}
	
	public MonHoc(String tenMon) {
		this.tenMon = tenMon;
	}

	public MonHoc(int id, String tenMon) {
		this.id = id;
		this.tenMon = tenMon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenMon() {
		return tenMon;
	}

	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}

	public int getSoTiet() {
		return soTiet;
	}

	public void setSoTiet(int soTiet) {
		this.soTiet = soTiet;
	}
	
	public void addGiaoVien(String s) {
		this.giaoVien.add(s);
	}

	int getGiaoVienSize() {
		return this.giaoVien.size();
	}

	String getGiaoVienItem(int d) {
		return this.giaoVien.get(d);
	}


	@Override
	public String toString() {
		return tenMon;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tenMon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonHoc other = (MonHoc) obj;
		return id == other.id && Objects.equals(tenMon, other.tenMon);
	}
	
	public static ArrayList<MonHoc> getDanhSachMonHoc(){
		String [] arr_MonHoc = {"Toán", "Văn" , "Anh", 
								"Lý", "Hóa", "Sinh", 
								"Sử", "Địa","GDCD"};
		int i = 0;
		ArrayList<MonHoc> list_MonHoc = new ArrayList<MonHoc>();
		for (String tenMon : arr_MonHoc) {
			MonHoc mh = new MonHoc(i, tenMon);
			list_MonHoc.add(mh);
		}
		return list_MonHoc;
	}
	
	public static MonHoc getMonHocById(int id) {
		return MonHoc.getDanhSachMonHoc().get(id);
	}
	
	public static MonHoc getMonHocByTen (String tenMon) {
		ArrayList<MonHoc> list_MonHoc = MonHoc.getDanhSachMonHoc();
		for (MonHoc monHoc : list_MonHoc) {
			if(monHoc.tenMon.equals(tenMon)) {
				return monHoc;
			}
		}
		return null;
	}
	
}
