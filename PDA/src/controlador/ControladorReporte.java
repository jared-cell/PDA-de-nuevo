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
        boolean guardado = false;
        cn = con.conectar();

        try {
            ps = cn.prepareStatement("INSERT INTO problemas (Describe_el_problema, DireccionCompleta, Localidad, Fecha) VALUES (?, ?, ?, ?)");
            ps.setString(1, rp.getDescribe_el_problema());
            ps.setString(2, rp.getDireccionCompleta());
            ps.setString(3, rp.getLocalidad());
            ps.setDate(4, rp.getFecha());

            guardado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return guardado;
    }

    public boolean actualizarReporte(ReporteUsuario rp, String problemaAnterior) {
        boolean actualizado = false;
        cn = con.conectar();

        try {
            ps = cn.prepareStatement("UPDATE problemas SET Describe_el_problema = ?, DireccionCompleta = ?, Localidad = ?, Fecha = ? WHERE Describe_el_problema = ?");
            ps.setString(1, rp.getDescribe_el_problema());
            ps.setString(2, rp.getDireccionCompleta());
            ps.setString(3, rp.getLocalidad());
            ps.setDate(4, rp.getFecha());
            ps.setString(5, problemaAnterior); 

            actualizado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return actualizado;
    }
}
