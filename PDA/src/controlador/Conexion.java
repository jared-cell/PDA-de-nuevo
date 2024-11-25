package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
	Connection cn;
	PreparedStatement ps;
	
	public Connection conectar() {
		try {
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/admin","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No conectado"+e);
		}
		return cn;
		
	}

}
