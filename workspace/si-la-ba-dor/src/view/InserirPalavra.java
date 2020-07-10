package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
				voltar();
			}
		});
		btnVoltar.setBounds(28, 12, 117, 25);
		contentPane.add(btnVoltar);
		
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
		btnCriarSilabas.setBounds(223, 213, 142, 25);
		contentPane.add(btnCriarSilabas);
		
		JTextField txtPalavra = new JTextField();
		txtPalavra.setBounds(86, 145, 114, 19);
		contentPane.add(txtPalavra);
		txtPalavra.setColumns(10);
		
		JLabel lblPalavra = new JLabel("Digite a palavra completa");
		lblPalavra.setBounds(88, 118, 187, 15);
		contentPane.add(lblPalavra);
		
		JLabel lblQuantidadeDeSilabas = new JLabel("Quantidade de silabas:");
		lblQuantidadeDeSilabas.setBounds(86, 189, 401, 15);
		contentPane.add(lblQuantidadeDeSilabas);
		
		JButton btnCadastrarNovaPalavra = new JButton("Cadastrar nova palavra");
		btnCadastrarNovaPalavra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				if(txtPalavra.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite a palavra completa.", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(txtQuantidadeSilabas.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite a quantidade de sílabas!", "", JOptionPane.ERROR_MESSAGE);
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
		btnCadastrarNovaPalavra.setBounds(678, 509, 336, 25);
		contentPane.add(btnCadastrarNovaPalavra);
		
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
	

	/**
	 * Método responsável por colocar os JTextField e os JButton associados na tela.
	 * Irá percorrer o vetor de sílabas e botões e adicionar ao contentPane.
	 * */
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
