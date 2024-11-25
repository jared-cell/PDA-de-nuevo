package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.ReporteUsuario;


public class ControladorReporte {
    private Connection cn;
    private PreparedStatement ps;
    private Conexion con = new Conexion();

    public boolean nuevoreporte(ReporteUsuario rp) {
        boolean iniciar = false;
        cn = con.conectar();      
        try {
        	ps = cn.prepareStatement("INSERT INTO tablaadministrador (descripcionproblema, direccioncompleta, localidad, fecha) VALUES (?, ?, ?, ?)");
            ps.setString(1, rp.getDescribe_el_problema()); 
            ps.setString(2, rp.getDireccionCompleta());   
            ps.setString(3, rp.getLocalidad());
            ps.setDate(4, rp.getFecha());
            int guardado = ps.executeUpdate();
            if (guardado > 0) {
                iniciar = true;
                JOptionPane.showMessageDialog(null, "Guardado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se guardó correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return iniciar;
    }
}


