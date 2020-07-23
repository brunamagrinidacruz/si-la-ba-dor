package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Administrador;
import model.Usuario;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
//import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Gerenciamento extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	/*!< Objeto do menu do jogo */
	private JFrame menu;

	Administrador usuario;

	public Gerenciamento(JFrame menu, Usuario usuario) {
		this.menu = menu;
		this.usuario = (Administrador) usuario;
		
		JLabel lblAdministrador = new JLabel("Administrador: " + usuario.getNome());
		initGUI();
	}
	private void initGUI() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1280, 720);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(new Color(255, 215, 0));
		btnVoltar.setForeground(new Color(255, 69, 0));
		btnVoltar.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				voltarParaMenu();
			}
		});
		btnVoltar.setBounds(1091, 32, 131, 35);
		contentPane.add(btnVoltar);
		
		JButton btnInserirPalavra = new JButton("Inserir Palavra");
		btnInserirPalavra.setBackground(new Color(255, 215, 0));
		btnInserirPalavra.setForeground(new Color(255, 69, 0));
		btnInserirPalavra.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		btnInserirPalavra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				inserirPalavra();
			}
		});
		btnInserirPalavra.setBounds(552, 327, 281, 76);
		contentPane.add(btnInserirPalavra);
		// Essas duas linhas estao com erro
		//lblAdministrador.setBounds(29, 11, 155, 34);
		//contentPane.add(lblAdministrador);
		ImageIcon img = new ImageIcon("assets/background_gerenciamento.png");
		JLabel background = new JLabel("", img, JLabel.CENTER);
		background.setBounds(0, 0, 1264, 681);
		contentPane.add(background);
	}
	
	/**
	 * Retorna o usuário para a tela de menu
	 * */
	private void voltarParaMenu() {
		menu.setVisible(true);
		dispose();
	}
	
	private void inserirPalavra() {
		InserirPalavra ip = new InserirPalavra(this);
		ip.setLocationRelativeTo(null);
		ip.setVisible(true);
	}
}
