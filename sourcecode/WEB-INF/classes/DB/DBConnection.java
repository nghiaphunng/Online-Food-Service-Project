package DB;

import java.sql.*;

public class DBConnection 
{
	public static Connection CreateConnection()
	{
		Connection conn = null;;
		
		String url = "jdbc:mysql://localhost:3306/onlinefoodserviceproject?useUnicode=true&characterEncoding=utf8";
		String username = "root"; 
		String password = "1234512345m";


//		String url = "jdbc:mysql://localhost:3306/kghbajlo_studywithnDB?useUnicode=true&characterEncoding=UTF-8";
//		String username = "kghbajlo_root"; 
//		String password = "studywithn@";
		 
		try 
		{
			//load driver
			Class.forName("com.mysql.jdbc.Driver");
			//create connection
			conn = DriverManager.getConnection(url,username,password);
			
		} 
		catch (ClassNotFoundException e) 
		{

			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		
		return conn;
	}
}
