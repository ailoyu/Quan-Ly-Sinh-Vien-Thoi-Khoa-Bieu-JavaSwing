package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

import com.mysql.jdbc.Driver;


public class JDBCUtil {
	
	public static Connection openConnection() {
		Connection c = null;
		try {
			DriverManager.registerDriver(new Driver());
			String url = "jdbc:mySQL://localhost:3306/sinhvien";
			String username = "root";
			String password = "";
			c = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	
	
	public static void closeConnection(Connection c) {
		if(c != null) {
			try {
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
}
