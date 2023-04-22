package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import MyList.MyDoublyLinkedList;

public interface DAOInterface<T> {
	public void insert(T t) throws SQLException;
	
	public void update(T t) throws SQLException;
	
	public void delete(int maSo);
	
	public MyDoublyLinkedList<T> selectAll();
	
	
}
