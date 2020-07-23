package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Niveis;
import control.ManipulaAdministradores;
import exception.OperacoesException;
import model.Administrador;
import model.Jogador;
import model.Usuario;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String[] levels = {"Nivel 1", "Nivel 2", "Nivel 3"};
	
	private static Menu menu;

	private Usuario usuario;
	
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
		
		JLabel background;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon img = new ImageIcon("assets/background_menu_silabador.png");

		JComboBox<Object> cbLevel = new JComboBox<Object>(levels);
		cbLevel.setBackground(new Color(255, 215, 0));
		cbLevel.setForeground(new Color(255, 69, 0));
		cbLevel.setFont(new Font("Cooper Black", Font.BOLD, 30));
		cbLevel.setBounds(520, 383, 235, 53);
		cbLevel.setSelectedIndex(0);
		contentPane.add(cbLevel);
		
		JLabel lblSi = new JLabel("SI-LA-BA-DOR");
		lblSi.setForeground(new Color(0, 0, 128));
		lblSi.setFont(new Font("Cooper Black", Font.BOLD, 80));
		lblSi.setBounds(330, 11, 685, 191);
		contentPane.add(lblSi);
		
	    JTextField txtName = new JTextField();
	    txtName.setForeground(new Color(0, 0, 128));
		txtName.setBackground(Color.WHITE);
		txtName.setFont(new Font("Cooper Black", Font.BOLD, 40));
		txtName.setToolTipText("Seu nome");
		txtName.setBounds(645, 290, 305, 45);
		contentPane.add(txtName);
		txtName.setColumns(10);
		txtName.setFocusable(true);
		
		JLabel lblDigiteSeuNome = new JLabel("Digite seu nome:");
		lblDigiteSeuNome.setForeground(new Color(255, 69, 0));
		lblDigiteSeuNome.setBackground(new Color(240, 240, 240));
		lblDigiteSeuNome.setFont(new Font("Cooper Black", Font.BOLD, 30));
		lblDigiteSeuNome.setBounds(330, 282, 305, 66);
		contentPane.add(lblDigiteSeuNome);
		
		JLabel lblBemVindo = new JLabel("Seja bem-vindo(a)!");
		lblBemVindo.setForeground(new Color(255, 69, 0));
		lblBemVindo.setFont(new Font("Cooper Black", Font.BOLD, 40));
		lblBemVindo.setBounds(416, 150, 435, 72);
		contentPane.add(lblBemVindo);
		
		JButton btnPlay = new JButton("Jogar");
		btnPlay.setBackground(new Color(255, 215, 0));
		btnPlay.setForeground(new Color(0, 0, 128));
		btnPlay.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira seu nome!", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				ManipulaAdministradores operacoesArquivo = new ManipulaAdministradores();
				boolean administrador = false;
				try {
					administrador = operacoesArquivo.verificarAdmistrador(txtName.getText());
				} catch (OperacoesException e) {
					e.printStackTrace();
				}

				/*!< Se o usuário for administrador, a tela de gerenciamento deve ser aberta. Se não, a tela de jogo. */
				if(administrador) {
					usuario = new Administrador(txtName.getText());
					Gerenciamento gerenciamento = new Gerenciamento(menu, usuario);
					gerenciamento.setLocationRelativeTo(null);
					gerenciamento.setVisible(true);
				} else {
					usuario = new Jogador(txtName.getText());
					/*!< Inicializando a variavel de nivel como nivel 1 e caso seja diferente conforme o comboBox, o valor é alterado */
					Niveis nivel = Niveis.NIVEL1;
					switch(cbLevel.getSelectedIndex()) {
						case 0:
							break;
						case 1: 
							nivel = Niveis.NIVEL2;
							break;
						case 2:
							nivel = Niveis.NIVEL3;
							break;
						default:
							System.out.println("Houve um problema com o nivel: " + cbLevel.getSelectedIndex());
					}
						
					Jogo jogo = new Jogo(menu, usuario, nivel);
					jogo.setLocationRelativeTo(null);
					jogo.setVisible(true);
				}
				setVisible(false);
			}
		});
		
		btnPlay.setBounds(544, 559, 194, 66);
		contentPane.add(btnPlay);
		
		background = new JLabel("", img,JLabel.CENTER);
		background.setBackground(new Color(51, 204, 204));
		background.setBounds(0, 0, 1262, 673);
		getContentPane().add(background);

	}
}
