package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.QuanLySinhVienView;

public class QuanLySinhVienController implements ActionListener, MouseListener {
	private QuanLySinhVienView view;
	
	public QuanLySinhVienController(QuanLySinhVienView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();

		
		if (src.equals("Lưu")) {
			this.view.thucHienLuuSinhVien();
		} else if (src.equals("Cập Nhật")) {
			this.view.hienThiThongTinThiSinhDaChon();
		} else if (src.equals("Xóa")) {
			this.view.thucHienXoa();
		} else if (src.equals("Hủy Bỏ")) {
			this.view.xoaForm();
			
			
		} else if (src.equals("Tìm")) {
			this.view.thucHienTimKiem();
		} else if (src.equals("Hủy Tìm")) {
			this.view.showStudentFromDatabase();
		} else if (src.equals("About me")) {
			this.view.hienThiAbout();
		} else if (src.equals("Exit")) {
			this.view.thoatKhoiChuongTrinh();
		} else if (src.equals("Save")) {
			this.view.thucHienSaveFile();
		} else if (src.equals("Open")) {
			this.view.thucHienOpenFile();
		} else if (src.equals("Refresh")) {
			this.view.showStudentFromDatabase();
		} else if (src.equals("Xóa")) {
			this.view.thucHienXoa();
		} else if (src.equals("Sign Out")) {
			this.view.signOut();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.isPopupTrigger()) { // nhấn chuột phải
			this.view.jPopupMenu.show(e.getComponent(), e.getX(), e.getY());;
			// hiện popupMenu chuột phải
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.isPopupTrigger()) { 
			// sau khi nhấn chuột phải, phải thả ra mới hiện popupMenu
			this.view.jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}