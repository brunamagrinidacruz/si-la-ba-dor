package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String[] levels = {"Nivel 1", "Nivel 2", "Nivel 3"};
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		JLabel background;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon img = new ImageIcon("background_menu_silabador.png");
		
		JButton btnPlay = new JButton("Jogar");
		btnPlay.setForeground(new Color(0, 0, 128));
		btnPlay.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPlay.setBounds(606, 539, 194, 66);
		contentPane.add(btnPlay);
		
		JComboBox cbLevel = new JComboBox(levels);
		cbLevel.setForeground(new Color(0, 0, 128));
		cbLevel.setFont(new Font("Cooper Black", Font.BOLD, 30));
		cbLevel.setBounds(577, 366, 235, 53);
		cbLevel.setModel(new DefaultComboBoxModel(new String[] {"N\u00EDvel 1", "N\u00EDvel 2", "N\u00EDvel 3"}));
		cbLevel.setSelectedIndex(0);
		contentPane.add(cbLevel);
		
		JLabel lblSi = new JLabel("SI-LA-BA-DOR");
		lblSi.setForeground(new Color(0, 0, 128));
		lblSi.setFont(new Font("Cooper Black", Font.BOLD, 80));
		lblSi.setBounds(318, 13, 685, 191);
		contentPane.add(lblSi);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Cooper Black", Font.BOLD, 40));
		txtName.setToolTipText("Seu nome");
		txtName.setBounds(555, 285, 305, 45);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblDigiteSeuNome = new JLabel("Digite seu nome:");
		lblDigiteSeuNome.setForeground(new Color(0, 0, 128));
		lblDigiteSeuNome.setBackground(new Color(240, 240, 240));
		lblDigiteSeuNome.setFont(new Font("Cooper Black", Font.BOLD, 30));
		lblDigiteSeuNome.setBounds(253, 277, 350, 66);
		contentPane.add(lblDigiteSeuNome);
		
		background = new JLabel("", img,JLabel.CENTER);
		background.setBackground(new Color(51, 204, 204));
		background.setBounds(0, 0, 1262, 673);
		getContentPane().add(background);
		
		JLabel lblBemVindo = new JLabel("Seja bem-vindo(a)!");
		lblBemVindo.setForeground(new Color(0, 0, 128));
		lblBemVindo.setFont(new Font("Cooper Black", Font.BOLD, 40));
		lblBemVindo.setBounds(425, 216, 435, 72);
		contentPane.add(lblBemVindo);
	}
}
