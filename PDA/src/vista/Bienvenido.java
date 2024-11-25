package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;
public class Bienvenido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bienvenido frame = new Bienvenido();
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
	public Bienvenido() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Bienvenido.class.getResource("/vista/logo3.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("bienvenido");
		lblNewLabel.setForeground(new Color(240, 255, 255));
		lblNewLabel.setBackground(new Color(135, 206, 250));
		lblNewLabel.setFont(new Font("Engravers MT", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel.setBounds(0, 0, 265, 109);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setFont(new Font("Lucida Fax", Font.ITALIC, 13));
		btnNewButton.setBackground(new Color(135, 206, 250));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loging vl=new Loging();
				vl.setVisible(true);
				Bienvenido.this.dispose();
			}
		});
		btnNewButton.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Alumno.SC3PC34.000\\Pictures\\Screenshots\\diseño2.png"));
		lblNewLabel_1.setBounds(0, 0, 434, 261);
		contentPane.add(lblNewLabel_1);
	}
}
