package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	public static Connection getConexion() {
		Connection cn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/forumproject?useSSL=false&useTimezone=true&serverTimezone=America/Lima";
			String usr = "root";
			String psw = "mysql";
			cn = DriverManager.getConnection(url, usr, psw);
		} catch (ClassNotFoundException e) {
			System.out.println("Error >> Driver no Instalado!!" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error >> de conexi�n con la BD" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error >> general : " + e.getMessage());
		}
		return cn;
	}

	public static void closeConexion(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Problemas al cerrar la conexion");
		}
	}

}
