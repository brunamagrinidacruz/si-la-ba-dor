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
		silabas = new ArrayList<JTextField>();
		botoesSilabas = new ArrayList<JButton>();
		
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

		JTextField txtQuantidadeSilabas = new JTextField();
		txtQuantidadeSilabas.setBounds(86, 216, 114, 19);
		contentPane.add(txtQuantidadeSilabas);
		txtQuantidadeSilabas.setColumns(10);
		
		JButton btnCriarSilabas = new JButton("Criar silabas");
		btnCriarSilabas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtQuantidadeSilabas.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite a quantidade de sílabas!", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int quantidadeDeSilabas = 0;
				try {
					quantidadeDeSilabas = Integer.parseInt(txtQuantidadeSilabas.getText());
				} catch (java.lang.NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Digite um valor numérico e inteiro.", "", JOptionPane.ERROR_MESSAGE);
					return;
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "A quantidade de sílabas digitada não é válida.", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(quantidadeDeSilabas <= 1 || quantidadeDeSilabas >= 8) {
					JOptionPane.showMessageDialog(null, "Quantidade de sílabas inválida.", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				setQuantidadeDeSilabas(quantidadeDeSilabas);
				criarSilabas();		
				colocarSilabasNaTela();
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

	private void setQuantidadeDeSilabas(int quantidadeDeSilabas) {
		this.quantidadeDeSilabas = quantidadeDeSilabas;
	}
	
	private void criarSilabas() {
		for(int i = 0; i < this.quantidadeDeSilabas; i++) {
			JTextField silaba = new JTextField();
			silabas.add(silaba);
			
			JButton botaoSilaba = new JButton("Apagar sílaba");
			botaoSilaba.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					silabas.remove(silaba);
					botoesSilabas.remove(botaoSilaba);
					contentPane.remove(silaba);
					contentPane.remove(botaoSilaba);
					colocarSilabasNaTela();
					setQuantidadeDeSilabas(quantidadeDeSilabas - 1);
				}
			});
			botoesSilabas.add(botaoSilaba);
		}
	}

	private void colocarSilabasNaTela() {
		int posicaoY = 250;
		for(JTextField silaba : this.silabas) {
			contentPane.remove(silaba);
			silaba.setBounds(86, posicaoY, 114, 19);
			contentPane.add(silaba);
			posicaoY += 30;
		}
		posicaoY = 250;
		for(JButton botao : this.botoesSilabas) {
			contentPane.remove(botao);
			botao.setBounds(230, posicaoY, 114, 19);
			contentPane.add(botao);
			posicaoY += 30;
		}
		
		repaint();
	}
}
