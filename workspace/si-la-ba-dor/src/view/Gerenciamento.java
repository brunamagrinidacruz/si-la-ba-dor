package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Gerenciamento extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	/*!< Objeto do menu do jogo */
	private JFrame menu;
	
	private int quantidadeDeSilabas;
	
	/*!< Silabas das palavras presente na tela */
	private List<JTextField> silabas;
	private List<JButton> botoesSilabas;

	public Gerenciamento(JFrame menu, String string) {
		this.menu = menu;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1280, 720);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				voltarParaMenu();
			}
		});
		btnVoltar.setBounds(29, 85, 117, 25);
		contentPane.add(btnVoltar);
		
		JButton btnInserirPalavra = new JButton("Inserir Palavra");
		btnInserirPalavra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				inserirPalavra();
			}
		});
		btnInserirPalavra.setBounds(167, 85, 117, 24);
		contentPane.add(btnInserirPalavra);
		
		JLabel lblAdministrador = new JLabel("Administrador: " + string);
		lblAdministrador.setBounds(29, 11, 155, 34);
		contentPane.add(lblAdministrador);
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
