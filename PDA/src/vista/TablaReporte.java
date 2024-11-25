package vista;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TablaReporte extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaReporteUsuario;
    private JButton btnActualizarReporte;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TablaReporte frame = new TablaReporte();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TablaReporte() {
        setTitle("Reporte Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        setLocationRelativeTo(null); // Centra la ventana
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnActualizarReporte = new JButton("Actualizar");
        btnActualizarReporte.setBounds(150, 350, 120, 30);
        btnActualizarReporte.addActionListener(e -> abrirVentanaActualizar());
        contentPane.add(btnActualizarReporte);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 570, 300);
        contentPane.add(scrollPane);

        // Configuración inicial de la tabla con filas vacías
        tablaReporteUsuario = new JTable();
        tablaReporteUsuario.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Descripcion del problema", "Calles/Referencias", "Localidad", "Fecha"
            }
        ));
        scrollPane.setViewportView(tablaReporteUsuario);

        JButton btnGenerarPdf = new JButton("Generar PDF");
        btnGenerarPdf.setBounds(10, 350, 120, 30);
        btnGenerarPdf.addActionListener(e -> generarPDF());
        contentPane.add(btnGenerarPdf);

        cargarDatos(); // Carga los datos al inicializar la ventana
    }

    private void cargarDatos() {
        DefaultTableModel modelo = (DefaultTableModel) tablaReporteUsuario.getModel();
        modelo.setRowCount(0); // Limpia la tabla

        try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "");
             PreparedStatement ps = cn.prepareStatement("SELECT Describe_el_problema, DireccionCompleta, Localidad, Fecha FROM problemas");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                modelo.addRow(new Object[] {
                    rs.getString("Describe_el_problema"),
                    rs.getString("DireccionCompleta"),
                    rs.getString("Localidad"),
                    rs.getString("Fecha")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage());
        }
    }

    private void generarPDF() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home") + "/Desktop/Reporte_Usuarios.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();

            PdfPTable tabla = new PdfPTable(tablaReporteUsuario.getColumnCount());
            for (int i = 0; i < tablaReporteUsuario.getColumnCount(); i++) {
                tabla.addCell(tablaReporteUsuario.getColumnName(i));
            }

            DefaultTableModel modelo = (DefaultTableModel) tablaReporteUsuario.getModel();
            for (int i = 0; i < modelo.getRowCount(); i++) {
                for (int j = 0; j < modelo.getColumnCount(); j++) {
                    tabla.addCell(modelo.getValueAt(i, j) != null ? modelo.getValueAt(i, j).toString() : "");
                }
            }

            documento.add(tabla);
            JOptionPane.showMessageDialog(this, "PDF generado exitosamente en el escritorio.");

        } catch (FileNotFoundException | DocumentException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + e.getMessage());
        } finally {
            documento.close();
        }
    }

    private void abrirVentanaActualizar() {
        JOptionPane.showMessageDialog(this, "Funcionalidad no implementada aún.");
    }
}
