package accesoDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	public static Connection getConexion( String pathin ){
		
		Connection conn = null;
		String dflPath;
		if ( pathin.isEmpty() ){
			dflPath = "C:\\Estudio";
		} else {
			dflPath = pathin;
		}
		
		try {
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//Microsoft dBASE Driver (*.dbf)
			String database = "jdbc:odbc:DRIVER={Microsoft dBASE Driver (*.dbf)};DefaultDir="+dflPath+";";

			conn = DriverManager.getConnection(database);	

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}
