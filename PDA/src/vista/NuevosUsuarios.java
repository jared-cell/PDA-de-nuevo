package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ControladorUsuario;
import modelo.NuevoUsuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NuevosUsuarios extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNOMBRECOMPLETO;
    private JTextField txtCORREO;
    private JTextField txtNUMEROTELEFONICO;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NuevosUsuarios frame = new NuevosUsuarios();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public NuevosUsuarios() {
    	setTitle("Nuevo Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNombreCompleto = new JLabel("Nombre Completo:");
        lblNombreCompleto.setBounds(40, 14, 104, 14);
        contentPane.add(lblNombreCompleto);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(40, 44, 104, 14);
        contentPane.add(lblCorreo);

        JLabel lblNumeroTelefonico = new JLabel("NumeroTelefonico:");
        lblNumeroTelefonico.setBounds(40, 69, 104, 14);
        contentPane.add(lblNumeroTelefonico);

        txtNOMBRECOMPLETO = new JTextField();
        txtNOMBRECOMPLETO.setColumns(10);
        txtNOMBRECOMPLETO.setBounds(154, 11, 186, 20);
        contentPane.add(txtNOMBRECOMPLETO);

        txtCORREO = new JTextField();
        txtCORREO.setColumns(10);
        txtCORREO.setBounds(154, 41, 186, 20);
        contentPane.add(txtCORREO);

        txtNUMEROTELEFONICO = new JTextField();
        txtNUMEROTELEFONICO.setColumns(10);
        txtNUMEROTELEFONICO.setBounds(154, 66, 186, 20);
        contentPane.add(txtNUMEROTELEFONICO);

        JButton btnNewButton_1 = new JButton("Cerrar");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Loging lg = new Loging();
                lg.setVisible(true);  
                NuevosUsuarios.this.dispose(); 
        	}
        });
        btnNewButton_1.setBounds(209, 153, 89, 23);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton = new JButton("Registrarme");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nuevousuario()) {
                    Reporte rp= new Reporte();
                    rp.setVisible(true);  
                    NuevosUsuarios.this.dispose(); 
                }
            }
        });
        btnNewButton.setBounds(55, 153, 117, 23);
        contentPane.add(btnNewButton);

        txtNOMBRECOMPLETO.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE) {
                    e.consume();
                }
            }
        });

        txtCORREO.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                if (!Character.isLetterOrDigit(c) && c != '@' && c != '.' && c != KeyEvent.VK_SPACE) {
                    e.consume();
                }
            }
        });

        txtNUMEROTELEFONICO.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
                if (txtNUMEROTELEFONICO.getText().length() >= 11) {
                    e.consume();
                }
            }
        });
    }

    public boolean nuevousuario() {
        String nombreCompleto = txtNOMBRECOMPLETO.getText();
        String correo = txtCORREO.getText();
        String numeroTelefonico = txtNUMEROTELEFONICO.getText();

        if (nombreCompleto.isEmpty() || correo.isEmpty() || numeroTelefonico.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios. Por favor, complete todos los campos.");
            return false; 
        }

        if (!validarCorreo(correo)) {
            JOptionPane.showMessageDialog(null, "El correo ingresado no es válido. Por favor, ingrese un correo con formato correcto.");
            return false;
        }

        ControladorUsuario cu = new ControladorUsuario();
        NuevoUsuario nu = new NuevoUsuario();
        nu.setNombre_Completo(nombreCompleto);
        nu.setCorreo(correo);
        nu.setNumero_Telefonico(numeroTelefonico);

        return cu.iniciarsession(nu);
    }

    private boolean validarCorreo(String correo) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
}
