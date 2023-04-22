package model;

import java.util.ArrayList;
import java.util.Objects;


public class GiaoVien extends Timetable{
	private int id;
	private String tenGiaoVien;
	private boolean option;
	private String lopChuNhiem;
	private MonHoc monHoc;
	private ArrayList<String> dsMonHoc = new ArrayList<String>();
	
	
	public void addDsMonHoc(String s) {
		this.dsMonHoc.add(s);
	}
	
	public GiaoVien() {
		
	}
	
	public GiaoVien(int id) {
		this.id = id;
	}

	public GiaoVien(int id, String tenGiaoVien, boolean option, String lopChuNhiem, MonHoc monHoc) {
		super();
		this.id = id;
		this.tenGiaoVien = tenGiaoVien;
		this.option = option;
		this.lopChuNhiem = lopChuNhiem;
		this.monHoc = monHoc;
	}
	
	public GiaoVien(int id, String tenGiaoVien, String lopChuNhiem, MonHoc monHoc) {
		this.id = id;
		this.tenGiaoVien = tenGiaoVien;
		this.lopChuNhiem = lopChuNhiem;
		this.monHoc = monHoc;
	}

	public Object[] createObjects() {
		return new Object[] {getId(), getTenGiaoVien(), getMonHoc().getTenMon(), getLopChuNhiem()};
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenGiaoVien() {
		return tenGiaoVien;
	}

	public boolean isOption() {
		return option;
	}

	public void setOption(boolean option) {
		this.option = option;
	}

	public void setTenGiaoVien(String tenGiaoVien) {
		this.tenGiaoVien = tenGiaoVien;
	}

	public String getLopChuNhiem() {
		return lopChuNhiem;
	}

	public void setLopChuNhiem(String lopChuNhiem) {
		this.lopChuNhiem = lopChuNhiem;
	}

	

	public MonHoc getMonHoc() {
		return monHoc;
	}

	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}

	@Override
	public String toString() {
		return "GiaoVien [id=" + id + ", tenGiaoVien=" + tenGiaoVien + ", option=" + option + ", lopChuNhiem="
				+ lopChuNhiem + ", monHoc=" + monHoc + ", dsMonHoc=" + dsMonHoc + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dsMonHoc, id, lopChuNhiem, monHoc, option, tenGiaoVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiaoVien other = (GiaoVien) obj;
		return Objects.equals(dsMonHoc, other.dsMonHoc) && id == other.id
				&& Objects.equals(lopChuNhiem, other.lopChuNhiem) && Objects.equals(monHoc, other.monHoc)
				&& option == other.option && Objects.equals(tenGiaoVien, other.tenGiaoVien);
	}


	
}
