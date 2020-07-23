package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class InserirPalavra extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JFrame telaAnterior;
	
	private int quantidadeDeSilabas;
	
	/*!< Silabas das palavras presente na tela */
	private List<JTextField> silabas;
	private List<JButton> botoesSilabas;
	
	public InserirPalavra(JFrame telaAnterior) {
		
		this.telaAnterior = telaAnterior;
		
		initGUI();
	}
	private void initGUI() {
		
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
		btnVoltar.setBackground(new Color(255, 215, 0));
		btnVoltar.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		btnVoltar.setForeground(new Color(255, 69, 0));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				voltar();
			}
		});
		btnVoltar.setBounds(1105, 25, 117, 25);
		contentPane.add(btnVoltar);
		
		JTextField txtQuantidadeSilabas = new JTextField();
		txtQuantidadeSilabas.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		txtQuantidadeSilabas.setBounds(654, 142, 232, 38);
		contentPane.add(txtQuantidadeSilabas);
		txtQuantidadeSilabas.setColumns(10);
		
		JButton btnCriarSilabas = new JButton("Criar silabas");
		btnCriarSilabas.setBackground(new Color(255, 215, 0));
		btnCriarSilabas.setForeground(new Color(0, 0, 139));
		btnCriarSilabas.setFont(new Font("Cooper Black", Font.PLAIN, 20));
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
		btnCriarSilabas.setBounds(535, 216, 225, 51);
		contentPane.add(btnCriarSilabas);
		
		JTextField txtPalavra = new JTextField();
		txtPalavra.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		txtPalavra.setBounds(654, 72, 232, 38);
		contentPane.add(txtPalavra);
		txtPalavra.setColumns(10);
		
		JLabel lblPalavra = new JLabel("Digite a palavra completa:");
		lblPalavra.setForeground(new Color(255, 69, 0));
		lblPalavra.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		lblPalavra.setBounds(343, 76, 301, 30);
		contentPane.add(lblPalavra);
		
		JLabel lblQuantidadeDeSilabas = new JLabel("Quantidade de silabas:");
		lblQuantidadeDeSilabas.setForeground(new Color(255, 69, 0));
		lblQuantidadeDeSilabas.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		lblQuantidadeDeSilabas.setBounds(343, 149, 276, 25);
		contentPane.add(lblQuantidadeDeSilabas);
		
		JButton btnCadastrarNovaPalavra = new JButton("Cadastrar nova palavra");
		btnCadastrarNovaPalavra.setBackground(new Color(255, 215, 0));
		btnCadastrarNovaPalavra.setForeground(new Color(0, 0, 128));
		btnCadastrarNovaPalavra.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		btnCadastrarNovaPalavra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				if(txtPalavra.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite a palavra completa.", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(txtQuantidadeSilabas.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite a quantidade de s�labas!", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!silabasSaoValidas(txtPalavra.getText())) 
					return;
				
				JOptionPane.showMessageDialog(null, "Palavra cadastrada com sucesso.", "", JOptionPane.INFORMATION_MESSAGE);
				
				/*!< Limpando os botões e campos de sílabas */
				removerSilabaDaTela();
				/*!< Limpando os campos da tela */
				txtPalavra.setText("");
				txtQuantidadeSilabas.setText("");
			}
		});
		btnCadastrarNovaPalavra.setBounds(491, 540, 295, 51);
		contentPane.add(btnCadastrarNovaPalavra);
		
		ImageIcon img = new ImageIcon("assets/background_gerenciamento.png");
		JLabel background = new JLabel("", img, JLabel.CENTER);
		background.setBounds(0, 0, 1262, 673);
		contentPane.add(background);
	}
	
	/*!< Método para retornar a página anterior */
	private void voltar() {
		telaAnterior.setVisible(true);
		dispose();
	}
	
	/*!< Método para setar a quantidade de silabas */
	private void setQuantidadeDeSilabas(int quantidadeDeSilabas) {
		this.quantidadeDeSilabas = quantidadeDeSilabas;
	}
	
	/**
	 * Método responsável por limpar a tela.
	 * Os JTextField de sílabas e os respectivos botões associados serão removidos da tela.
	 * Além disso, os vetores de silabas e botões são limpos.
	 * */
	private void removerSilabaDaTela() {
		for(JTextField silaba : this.silabas) 
			contentPane.remove(silaba);
		for(JButton botao : this.botoesSilabas) 
			contentPane.remove(botao);
		this.silabas.clear();
		this.botoesSilabas.clear();
		repaint();
	}
	
	/**
	 *  Método responsável por criar os JTextField e os JButton associados.
	 *	Armazena os objetos numa lista de JTextField e em uma de JButton.
	 * */
	private void criarSilabas() {
		removerSilabaDaTela(); /*!< Limpando a tela */
		for(int i = 0; i < this.quantidadeDeSilabas; i++) {
			JTextField silaba = new JTextField();
			silabas.add(silaba);
			
			JButton botaoSilaba = new JButton("Apagar s�laba");
			botaoSilaba.setBackground(new Color(255, 215, 0));
			botaoSilaba.setForeground(new Color(255, 69, 0));
			botaoSilaba.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		
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
	

	/**
	 * Método responsável por colocar os JTextField e os JButton associados na tela.
	 * Irá percorrer o vetor de sílabas e botões e adicionar ao contentPane.
	 * */
	private void colocarSilabasNaTela() {
		int posicaoY = 300;
		for(JTextField silaba : this.silabas) {
			contentPane.remove(silaba);
			silaba.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			silaba.setBounds(450, posicaoY, 114, 30);
			contentPane.add(silaba);
			posicaoY += 50;
		}
		posicaoY = 300;
		for(JButton botao : this.botoesSilabas) {
			contentPane.remove(botao);
			botao.setBounds(600, posicaoY, 300, 30);
			contentPane.add(botao);
			posicaoY += 50;
		}
		repaint();
	}
	
	/** 
	 *  Método responsável por verificar se todas as silabas são válidas
	 *  Recebe como parametro a palavra 
	 * */
	private boolean silabasSaoValidas(String palavra) {
		/*!< Se a quantidade de silabas é menor que 1 ou maior que 8, não é válido */
		if(this.silabas.size() <= 1 || this.silabas.size() >= 8) {
			JOptionPane.showMessageDialog(null, "Quantidade de sílabas inválida.", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		/*!< Se a quantidade de botões associados é diferente da quantidade de sílabas, algo deu errado */
		if(this.botoesSilabas.size() != this.silabas.size())
			return false;
		
		int quantidadeDeLetras = 0;
		/*!< Verificando se alguma das sílabas é vazia */
		for(JTextField silaba : this.silabas) {
			quantidadeDeLetras += silaba.getText().length();
			if(silaba.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Preencha todas as sílabas.", "", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		/*!< A palavra deve ter a mesma quantidade de letras que a silaba */
		if(palavra.length() != quantidadeDeLetras) {
			JOptionPane.showMessageDialog(null, "As sílabas e palavras não coincidem.", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
							
		return true;
	}
}
