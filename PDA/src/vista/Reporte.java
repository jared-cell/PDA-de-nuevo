package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorReporte;
import modelo.ReporteUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class Reporte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDESCRIBETUPROBLEMA;
	private JTextField txtCALLES_REFERENCIAS;
	private JTextField txtFECHA;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;
	private JTextField txtLocalidad1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reporte frame = new Reporte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
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

		JLabel lblNewLabel = new JLabel("Describe tu problema ");
		lblNewLabel.setBounds(10, 30, 121, 14);
		contentPane.add(lblNewLabel);

		JLabel lblCallesreferencias = new JLabel("Calles/Referencias");
		lblCallesreferencias.setBounds(10, 94, 121, 14);
		contentPane.add(lblCallesreferencias);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(10, 153, 121, 14);
		contentPane.add(lblFecha);

		txtDESCRIBETUPROBLEMA = new JTextField();
		txtDESCRIBETUPROBLEMA.setBounds(141, 27, 283, 50);
		contentPane.add(txtDESCRIBETUPROBLEMA);
		txtDESCRIBETUPROBLEMA.setColumns(10);

		txtCALLES_REFERENCIAS = new JTextField();
		txtCALLES_REFERENCIAS.setColumns(10);
		txtCALLES_REFERENCIAS.setBounds(151, 91, 86, 20);
		contentPane.add(txtCALLES_REFERENCIAS);

		txtFECHA = new JTextField();
		txtFECHA.setColumns(10);
		txtFECHA.setBounds(151, 125, 86, 20);
		contentPane.add(txtFECHA);

		JButton btnSiguiente = new JButton("Siguiente ");
		btnSiguiente.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
		        if (txtDESCRIBETUPROBLEMA.getText().trim().isEmpty() ||
		            txtCALLES_REFERENCIAS.getText().trim().isEmpty() ||
		            txtFECHA.getText().trim().isEmpty() ||
		            txtLocalidad1.getText().trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios. Por favor, completa los datos.");
		            return;
		        }

		       
		        String fechaTexto = "23/11/2024";
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		        sdf.setLenient(false);
		        
		        try {
		            java.util.Date utilDate = sdf.parse(fechaTexto);  
		            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());  
		            
		            
		            ReporteUsuario rp = new ReporteUsuario();
		            rp.setDescribe_el_problema(txtDESCRIBETUPROBLEMA.getText());
		            rp.setDireccionCompleta(txtCALLES_REFERENCIAS.getText());
		            rp.setLocalidad(txtLocalidad1.getText());
		            rp.setFecha(sqlDate);  

		            
		            ControladorReporte controlador = new ControladorReporte();
		            boolean guardadoExitoso = controlador.nuevoreporte(rp);
		            
		            if (guardadoExitoso) {
		                
		                TablaReporte ru = new TablaReporte();
		                ru.setVisible(true);
		                Reporte.this.dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Hubo un error al guardar el reporte.");
		            }

		        } catch (java.text.ParseException ex) {
		            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use 'dd/MM/yyyy' (ejemplo: 23/11/2024).");
		        }
		    }
		});

	
		btnSiguiente.setBounds(10, 203, 89, 23);
		contentPane.add(btnSiguiente);

		btnNewButton_1 = new JButton("Actualizar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaReporte ru = new TablaReporte();
				ru.setVisible(true);
				Reporte.this.dispose();
			}
		});
		btnNewButton_1.setBounds(109, 203, 89, 23);
		contentPane.add(btnNewButton_1);

		lblNewLabel_1 = new JLabel("Localidad");
		lblNewLabel_1.setBounds(10, 128, 121, 14);
		contentPane.add(lblNewLabel_1);

		txtLocalidad1 = new JTextField();
		txtLocalidad1.setBounds(151, 156, 86, 20);
		contentPane.add(txtLocalidad1);
		txtLocalidad1.setColumns(10);
	}

	public boolean nuevoreporte(java.sql.Date fecha) {
		ControladorReporte cu = new ControladorReporte();
		ReporteUsuario rp = new ReporteUsuario();
		rp.setDescribe_el_problema(txtDESCRIBETUPROBLEMA.getText());
		rp.setDireccionCompleta(txtCALLES_REFERENCIAS.getText());
		rp.setLocalidad(txtLocalidad1.getText());
		rp.setFecha(fecha);
		return cu.nuevoreporte(rp);
	}
}
