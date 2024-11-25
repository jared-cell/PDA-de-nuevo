package vista;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controlador.ControladorReporte;
import modelo.ReporteUsuario;

@SuppressWarnings("serial")
public class Reporte extends JFrame {

    private JPanel contentPane;
    private JTextField txtDESCRIBETUPROBLEMA;
    private JTextField txtCALLES_REFERENCIAS;
    private JTextField txtFECHA;
    private JTextField txtLocalidad1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Reporte frame = new Reporte();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Reporte() {
        setTitle("Reporte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblProblema = new JLabel("Describe tu problema");
        lblProblema.setBounds(10, 30, 150, 14);
        contentPane.add(lblProblema);

        JLabel lblCalles = new JLabel("Calles/Referencias");
        lblCalles.setBounds(10, 94, 150, 14);
        contentPane.add(lblCalles);

        JLabel lblFecha = new JLabel("Fecha (dd/MM/yyyy)");
        lblFecha.setBounds(10, 153, 150, 14);
        contentPane.add(lblFecha);

        JLabel lblLocalidad = new JLabel("Localidad");
        lblLocalidad.setBounds(10, 128, 150, 14);
        contentPane.add(lblLocalidad);

        txtDESCRIBETUPROBLEMA = new JTextField();
        txtDESCRIBETUPROBLEMA.setBounds(170, 27, 250, 53);
        contentPane.add(txtDESCRIBETUPROBLEMA);

        txtCALLES_REFERENCIAS = new JTextField();
        txtCALLES_REFERENCIAS.setBounds(170, 91, 250, 20);
        contentPane.add(txtCALLES_REFERENCIAS);

        txtLocalidad1 = new JTextField();
        txtLocalidad1.setBounds(170, 125, 250, 20);
        contentPane.add(txtLocalidad1);

        txtFECHA = new JTextField();
        txtFECHA.setBounds(170, 150, 250, 20);
        contentPane.add(txtFECHA);

        JButton btnGuardar = new JButton("Siguiente");
        btnGuardar.addActionListener(e -> guardarReporte());
        btnGuardar.setBounds(10, 203, 100, 23);
        contentPane.add(btnGuardar);
    }

    private void guardarReporte() {
        if (txtDESCRIBETUPROBLEMA.getText().trim().isEmpty() ||
            txtCALLES_REFERENCIAS.getText().trim().isEmpty() ||
            txtLocalidad1.getText().trim().isEmpty() ||
            txtFECHA.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return;
        }

        String fechaTexto = txtFECHA.getText().trim();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            Date utilDate = sdf.parse(fechaTexto);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            ReporteUsuario reporte = new ReporteUsuario();
            reporte.setDescribe_el_problema(txtDESCRIBETUPROBLEMA.getText().trim());
            reporte.setDireccionCompleta(txtCALLES_REFERENCIAS.getText().trim());
            reporte.setLocalidad(txtLocalidad1.getText().trim());
            reporte.setFecha(sqlDate);

            ControladorReporte controlador = new ControladorReporte();
            boolean guardado = controlador.nuevoreporte(reporte);

            if (guardado) {
                JOptionPane.showMessageDialog(null, "Reporte guardado exitosamente.");
                new TablaReporte().setVisible(true); // Muestra la ventana de la tabla
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el reporte.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use 'dd/MM/yyyy'.");
        }
    }
}
