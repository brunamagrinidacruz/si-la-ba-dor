package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String[] levels = {"Nivel 1", "Nivel 2", "Nivel 3"};
	
	private static Menu menu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu = new Menu();
					menu.setVisible(true);
					menu.setLocationRelativeTo(null);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<Object> cbLevel = new JComboBox<Object>(levels);
		cbLevel.setBounds(125, 143, 198, 24);
		cbLevel.setSelectedIndex(0);
		contentPane.add(cbLevel);
		
		JLabel lblSi = new JLabel("SI-LA-BA-DOR");
		lblSi.setFont(new Font("Dialog", Font.BOLD, 26));
		lblSi.setBounds(125, 27, 212, 25);
		contentPane.add(lblSi);
		
	    JTextField txtName = new JTextField();
		txtName.setBackground(Color.WHITE);
		txtName.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtName.setToolTipText("Seu nome");
		txtName.setBounds(135, 106, 168, 25);
		contentPane.add(txtName);
		txtName.setColumns(10);
		txtName.setFocusable(true);
		
		JLabel lblDigiteSeuNome = new JLabel("Digite seu nome");
		lblDigiteSeuNome.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDigiteSeuNome.setBounds(147, 75, 166, 30);
		contentPane.add(lblDigiteSeuNome);
		JButton btnPlay = new JButton("Jogar");
		
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira seu nome!", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				menu.setVisible(false);
				Jogo jogo = new Jogo(txtName.getText(), cbLevel.getSelectedIndex());
				jogo.setLocationRelativeTo(null);
				jogo.setVisible(true);
			}
		});
		btnPlay.setBounds(161, 209, 117, 25);
		contentPane.add(btnPlay);
	}
}
