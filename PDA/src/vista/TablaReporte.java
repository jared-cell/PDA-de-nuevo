package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TablaReporte extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaReporteUsuario;
    private JButton btnActualizarReporte;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TablaReporte frame = new TablaReporte();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @SuppressWarnings("serial")
	public TablaReporte() {
        setTitle("Reporte Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 531, 404);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnActualizarReporte = new JButton("Actualizar");
        btnActualizarReporte.setBounds(150, 297, 105, 23);
        btnActualizarReporte.addActionListener(e -> {
            Reporte vr = new Reporte();
            vr.setVisible(true);
            TablaReporte.this.dispose();
        });
        contentPane.add(btnActualizarReporte);

        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 495, 186);
        contentPane.add(scrollPane);

        tablaReporteUsuario = new JTable();
        tablaReporteUsuario.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String[] {
                "Descripcion del problema", "Calles/Referencias", "Localidad", "Fecha"
            }
        ) {
            boolean[] columnEditables = new boolean[] { false, false, false, false };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });

        tablaReporteUsuario.getTableHeader().setReorderingAllowed(false);
        tablaReporteUsuario.getColumnModel().getColumn(0).setResizable(false);
        tablaReporteUsuario.getColumnModel().getColumn(1).setResizable(false);
        tablaReporteUsuario.getColumnModel().getColumn(2).setResizable(false);
        tablaReporteUsuario.getColumnModel().getColumn(3).setResizable(false);

        scrollPane.setViewportView(tablaReporteUsuario);
        
        JButton btnGenerarPdf = new JButton("Generar Pdf");
        btnGenerarPdf.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		generarPDF();
        	}
        });
        btnGenerarPdf.setBounds(10, 297, 105, 23);
        contentPane.add(btnGenerarPdf);
    }

    private void generarPDF() {
        Document documento = new Document();
        try {
        	String ruta = System.getProperty("user.home") + "/Desktop/Reporte_Usuarios.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Usuarios.pdf"));
            documento.open();

            PdfPTable tabla = new PdfPTable(4);
            tabla.addCell("Descripcion del problema");
            tabla.addCell("Calles/Referencias");
            tabla.addCell("Localidad");
            tabla.addCell("Fecha");

            try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Registros", "root", "");
                 PreparedStatement ps = cn.prepareStatement("SELECT * FROM registros");
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                }

                documento.add(tabla);
                JOptionPane.showMessageDialog(null, "Reporte creado exitosamente en el escritorio.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al consultar la base de datos: " + e.getMessage());
            }

        } catch (FileNotFoundException | DocumentException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
        } finally {
            documento.close();
        }
    }
}
