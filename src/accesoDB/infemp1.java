package accesoDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class infemp1 {

	public static void main(String[] args) {
		
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//Microsoft dBASE Driver (*.dbf)
			String database = "jdbc:odbc:DRIVER={Microsoft dBASE Driver (*.dbf)};DefaultDir=C:\\Estudio\\Empresas\\Empresas;";

			Connection conn = null;

			conn = DriverManager.getConnection(database);		

			Statement s = conn.createStatement();

			String selTable = "SELECT * FROM INFEMP1";
		
			System.out.println( selTable );
		
			Statement stmt = null;

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selTable);
			
			while (rs.next()){
				System.out.println(" < " + rs.getInt("NUMERO") + " : " + rs.getString("NOMBRE") + " > ");
				System.out.println("[ " + rs.getString("Literal") + " ]");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
