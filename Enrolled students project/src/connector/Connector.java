package connector;
import java.sql.*;
public class Connector {
	public static Connection startConnection()  {  
		 Connection conn = null;
		     try{ 
		     
		          String connectionUrl = "jdbc:sqlserver://10.211.55.3:1433;" + 
                          				"Database=Databas_uppgift1;" + 
                          				"user=sa;" + 
                          				"password=hejhej123"; 
		          
		          conn = DriverManager.getConnection(connectionUrl);
		          
		     }catch (Exception e){
		    	 System.out.println("Could not connect, see message: " + e);;
		     }return conn;
		     
	 }
	     
}

	

