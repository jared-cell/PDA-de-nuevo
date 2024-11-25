package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorUsuario;
import modelo.NuevoUsuario;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Loging extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreCompleto;
	private JTextField txtCorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loging frame = new Loging();
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
	public Loging() {
	    setTitle("Loging");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 450, 300);
	    
	   
	    setLocationRelativeTo(null);
	    
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    JMenu mnNewMenu = new JMenu("Iniciar Sesion");
	    menuBar.add(mnNewMenu);
	    
	    JMenuItem mntmNewMenuItem = new JMenuItem("Registrarse");
	    mntmNewMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		NuevosUsuarios nu=new NuevosUsuarios();
				nu.setVisible(true);
	    	
	    		Loging.this.dispose();
	    		
	    	}
	    });
	    mnNewMenu.add(mntmNewMenuItem);
	    
	    JMenu mnNewMenu_1 = new JMenu("Consultar");
	    menuBar.add(mnNewMenu_1);
	    
	    JMenuItem mntmNewMenuItem_1 = new JMenuItem("Reportes");
	    mntmNewMenuItem_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ConsultarReporte cr= new ConsultarReporte();
	    		cr.setVisible(true);
	    		Loging.this.dispose();
	    	}
	    });
	    mnNewMenu_1.add(mntmNewMenuItem_1);
	    
	    JMenu mnNewMenu_2 = new JMenu("Administrador");
	    menuBar.add(mnNewMenu_2);
	    
	    JMenuItem mntmNewMenuItem_2 = new JMenuItem("Ingresar");
	    mntmNewMenuItem_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		LogingAdministrador lg=new LogingAdministrador();
	    		lg.setVisible(true);
	    		Loging.this.dispose();
	    	}
	    });
	    mnNewMenu_2.add(mntmNewMenuItem_2);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	    
	    JLabel lblNombreCompleto = new JLabel("Nombre Completo:");
	    lblNombreCompleto.setBounds(10, 11, 104, 14);
	    contentPane.add(lblNombreCompleto);
	    
	    JLabel lblCorreo = new JLabel("Correo:");
	    lblCorreo.setBounds(10, 41, 104, 14);
	    contentPane.add(lblCorreo);
	    
	    txtNombreCompleto = new JTextField();
	    txtNombreCompleto.setBounds(124, 8, 86, 20);
	    contentPane.add(txtNombreCompleto);
	    txtNombreCompleto.setColumns(10);
	    
	    txtCorreo = new JTextField();
	    txtCorreo.setBounds(124, 38, 86, 20);
	    contentPane.add(txtCorreo);
	    txtCorreo.setColumns(10);
	    
	    JButton btnNewButton = new JButton("Aceptar");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	           
	            if (txtNombreCompleto.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty()) {
	                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios. Por favor, completa los datos."); 
	                return; 
	            }
	            ControladorUsuario cu = new ControladorUsuario();
	            NuevoUsuario usu = new NuevoUsuario();
	            usu.setNombre_Completo(txtNombreCompleto.getText());
	            usu.setCorreo(txtCorreo.getText());
	            cu.iniciarsession(usu);
	            Loging.this.dispose();
	          
	        }
	    });
	    btnNewButton.setBounds(41, 150, 89, 23);
	    contentPane.add(btnNewButton);
	    
	    JButton btnNewButton_1 = new JButton("Cerrar");
	    btnNewButton_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Loging.this.dispose();
                JOptionPane.showMessageDialog(null,"Gracias buen dia"); 

	    		
	    		
	    	}
	    });
	    btnNewButton_1.setBounds(179, 150, 89, 23);
	    contentPane.add(btnNewButton_1);
	}
}
