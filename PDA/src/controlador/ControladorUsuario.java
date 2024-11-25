package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import modelo.NuevoUsuario;


public class ControladorUsuario {
    private Connection cn;
    private PreparedStatement ps;
    private Conexion con = new Conexion();	

    public boolean iniciarsession(NuevoUsuario nu) {
        boolean iniciar = false;
        cn = con.conectar();  

        try {
            
            ps = cn.prepareStatement("INSERT INTO tablaadministrador (NombreCompleto, Correo, NumeroTelefonico) VALUES (?, ?, ?)");
            
            
            ps.setString(1, nu.getNombre_Completo());
            ps.setString(2, nu.getCorreo());
            ps.setString(3, nu.getNumero_Telefonico());

            
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
