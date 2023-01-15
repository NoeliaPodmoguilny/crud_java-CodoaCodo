package ar.com.codoacodo.db;
import java.sql.Connection;
import java.sql.DriverManager;

import ar.com.codoacodo.db.AdministradorDeConexiones;

public class AdministradorDeConexiones {
	

	public static Connection getConnection() {
		String hosts = "127.0.0.1"; 
		String port = "3306";
		String password = "";
		String username = "root" ;
		String nombredb = "desafio";
		
		String driveClassName = "com.mysql.cj.jdbc.Driver";
		
		Connection connection ;
		try {
		
			Class.forName(driveClassName);
			String url ="jdbc:mysql://"+hosts+":"+port+"/"+nombredb+"?serverTimeZone=UTC&useSSL=false";
			connection = DriverManager.getConnection(url, username, password);
		}
		catch(Exception e){
			connection = null;
		}
		return connection;
	}
  
	public static void main(String[] args) {
		Connection con = AdministradorDeConexiones.getConnection();//f5
		if(con != null) {
			System.out.println("Conexion ok");
		}else {
			System.err.println("Conexion fail");
		}
	}
}
