package model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;


public class SapXepTKB extends Timetable {
	static Random rand = new Random();
	static ArrayList<Integer> listI = new ArrayList<>();
	static ArrayList<Integer> listJ = new ArrayList<>();
	

	public boolean add(MonHoc x) {
		int i, j, k, m, n;
		listI.clear();
		listJ.clear();

		for (i = 0; i < 7; i++) {
			for (j = 0; j < 6; j++) {
				if (this.a[i][j] == null) {
					listI.add(i);
					listJ.add(j);
				}
			}
		}
		
		for(Integer s : listI) {
			System.out.print(s+ " ");
		}
		System.out.println();
		for(Integer s : listJ) {
			System.out.print(s+ " ");
		}
		System.out.println("\n" + listI.size());
		
		if(listI.size() - 5 >= x.getSoTiet()) {
			for(i = 0; i < x.getSoTiet(); i++) {
				k = rand.nextInt(listI.size());
				m = listI.get(k);
				n = listJ.get(k);
				while((m == 0 && n == 0) || (m == 1 && n == 0) ||
						(m == 4 && n == 5) || (m == 5 && n == 5) || (m == 6 && n == 5)) {
					k = rand.nextInt(listI.size());
					m = listI.get(k);
					n = listJ.get(k);
				}
				listI.remove(k);
				listJ.remove(k);
				this.a[m][n] = x.getTenMon();
//						+ " (" + y.getTenGiaoVien().substring(y.getTenGiaoVien().lastIndexOf(" ") + 1) + ")";
//				y.a[m][n] = this.getTenLopTimetable() + " (" + x.getTenMon() + ")";
			}
			return true;
		}
		return false;
		
		
	}
	
}
