package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogingAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUSUARIO;
	private JTextField txtCONTRASEÑA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogingAdministrador frame = new LogingAdministrador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogingAdministrador() {
		setTitle("Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(10, 36, 87, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña:");
		lblNewLabel_1.setBounds(10, 61, 87, 14);
		contentPane.add(lblNewLabel_1);
		
		txtUSUARIO = new JTextField();
		txtUSUARIO.setBounds(107, 33, 86, 20);
		contentPane.add(txtUSUARIO);
		txtUSUARIO.setColumns(10);
		
		txtCONTRASEÑA = new JTextField();
		txtCONTRASEÑA.setColumns(10);
		txtCONTRASEÑA.setBounds(107, 58, 86, 20);
		contentPane.add(txtCONTRASEÑA);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtUSUARIO.getText().trim().isEmpty() || txtCONTRASEÑA.getText().trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios. Por favor, completa los datos."); 
		            return; 
		        }
				Administrador ad=new Administrador();
				ad.setVisible(true);
				LogingAdministrador.this.dispose();
			}
		});
		btnNewButton.setBounds(107, 108, 89, 23);
		contentPane.add(btnNewButton);
	}

}
