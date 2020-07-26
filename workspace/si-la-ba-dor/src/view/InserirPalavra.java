package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.ManipulaSilabas;
import control.Niveis;
import exception.ArquivoException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class InserirPalavra extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel texto1;
	private JLabel texto2;
	private JFrame telaAnterior;
	
	private int quantidadeDeSilabas;
	/*!< Silabas das palavras presente na tela */
	private List<JTextField> silabas;
	
	private Niveis nivel = null;
	private int quantidadeDeSilabasExtras;
	/*!< Silabas EXTRAS presente na tela */
	private List<JTextField> silabasExtras;
	
	public InserirPalavra(JFrame telaAnterior) {
		
		JLabel imagem1;
		JLabel imagem2;
		
		ImageIcon img = new ImageIcon("assets/imagem1_tela_jogo.png");
		ImageIcon img2 = new ImageIcon("assets/imagem2_tela_jogo.png");
		
		this.telaAnterior = telaAnterior;
		
		silabas = new ArrayList<JTextField>();
		
		silabasExtras = new ArrayList<JTextField>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1280, 720);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		texto1 = new JLabel("Digite as silabas:");
		texto2 = new JLabel("Digite as silabas extras:");
		texto1.setForeground(new Color(0, 0, 128));
		texto2.setForeground(new Color(0, 0, 128));
		texto1.setFont(new Font("Cooper Black", Font.PLAIN, 18));
		texto1.setBounds(500, 200, 300, 20);
		texto2.setFont(new Font("Cooper Black", Font.PLAIN, 18));
		texto2.setBounds(700, 200, 300, 20);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(new Color(255, 215, 0));
		btnVoltar.setForeground(new Color(255, 69, 0));
		btnVoltar.setFont(new Font("Cooper Black", Font.PLAIN, 18));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				voltar();
			}
		});
		btnVoltar.setBounds(1100, 12, 117, 25);
		contentPane.add(btnVoltar);
		
		JTextField txtQuantidadeSilabas = new JTextField();
		txtQuantidadeSilabas.setBounds(200, 233, 200, 30);
		txtQuantidadeSilabas.setFont(new Font("Cooper Black", Font.PLAIN, 18));
		contentPane.add(txtQuantidadeSilabas);
		txtQuantidadeSilabas.setColumns(10);
		
		String[] levels = {"Selecione um nivel", "Nivel 1", "Nivel 2", "Nivel 3"};
		JComboBox<Object> cbLevel = new JComboBox<Object>(levels);
		cbLevel.setBackground(new Color(255, 215, 0));
		cbLevel.setForeground(new Color(255, 69, 0));
		cbLevel.setFont(new Font("Cooper Black", Font.PLAIN, 18));
		cbLevel.setBounds(200, 273, 200, 53);
		cbLevel.setSelectedIndex(0);
		contentPane.add(cbLevel);
		
		imagem1 = new JLabel("", img, JLabel.CENTER);
		imagem2 = new JLabel("", img2, JLabel.CENTER);
		
		imagem1.setBounds(0, 0, 1256, 670);
		imagem2.setBounds(120, 0, 1144, 670);
		
		contentPane.add(imagem1);
		contentPane.add(imagem2);
		
		JButton btnCriarSilabas = new JButton("Criar silabas");
		btnCriarSilabas.setBackground(new Color(255, 215, 0));
		btnCriarSilabas.setForeground(new Color(255, 69, 0));
		btnCriarSilabas.setFont(new Font("Cooper Black", Font.PLAIN, 18));
		btnCriarSilabas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtQuantidadeSilabas.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite a quantidade de silabas!", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int quantidadeDeSilabas = 0;
				try {
					quantidadeDeSilabas = Integer.parseInt(txtQuantidadeSilabas.getText());
				} catch (java.lang.NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Digite um valor numerico e inteiro.", "", JOptionPane.ERROR_MESSAGE);
					return;
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "A quantidade de silabas digitada nao e valida.", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(quantidadeDeSilabas <= 1 || quantidadeDeSilabas >= 6) {
					JOptionPane.showMessageDialog(null, "Quantidade de silabas invalida. Ela deve ser maior ou igual a 1 e menor ou igual a 6 .", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(nivel == null) {
					JOptionPane.showMessageDialog(null, "Selecione um nivel.", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
								
				int numeroMaxSilabas = 6;
				if(nivel == Niveis.NIVEL3)
					numeroMaxSilabas = 8; /*!< acima de 4 silabas e sera apresentado 8 silabas para escolha */		
				
				/**
				 * Cada nivel possui um numero de silabas de palavra maximo.
				 * Nï¿½vel 1: de 2 silabas ate 3.
				 * Nivel 2: de 2 silabas ate 4.
				 * Nivel 3: de 5 silabas ate 7.
				 * Verificando se a quantidade de sï¿½labas passada corresponde corretamente ao intervalo do nï¿½vel selecionado.
				 * */
				switch(nivel) {
					case NIVEL1:
						if(quantidadeDeSilabas < 2 || quantidadeDeSilabas > 3) {
							JOptionPane.showMessageDialog(null, "A quantidade de silabas e invalida. Digite um numero menor ou aumente o nivel da palavra.", "", JOptionPane.ERROR_MESSAGE);
							cbLevel.setSelectedIndex(0);
							return;
						}
						break;
					case NIVEL2:
						if(quantidadeDeSilabas < 2 || quantidadeDeSilabas > 4) {
							JOptionPane.showMessageDialog(null, "A quantidade de silabas e invalida. Digite um numero menor ou aumente o nivel da palavra.", "", JOptionPane.ERROR_MESSAGE);
							cbLevel.setSelectedIndex(0);
							return;
						}
						break;
					case NIVEL3:
						if(quantidadeDeSilabas < 5 || quantidadeDeSilabas > 7) {
							JOptionPane.showMessageDialog(null, "A quantidade de silabas e invalida. Digite um numero menor ou aumente o nivel da palavra.", "", JOptionPane.ERROR_MESSAGE);
							cbLevel.setSelectedIndex(0);
							return;
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "Selecione um nivel.", "", JOptionPane.ERROR_MESSAGE);
						return;
				}
				
				/**
				 * A quantidade de sílabas total (silabas de palavra + silabas extra) tem um limite.
				 * Aqui a quantidade de sílabas extra vai ser atualizada com base no tamanho da palavra a ser inserida.
				 * Desta forma, ao somar a quantidade de silabas de palavra + silabas extra teremos a quantidade total de silabas.
				 * No nível 1 e 2: 6 sílabas serao mostradas na tela (silabas de palavra e silabas extra).
				 * No nível 3: 8 sílabas serão mostradas na tela (silabas de palavra e silabas extra).
				 **/
				int quantidadeSilabasExtras = numeroMaxSilabas - quantidadeDeSilabas;
				if(quantidadeSilabasExtras <= 0) {
					JOptionPane.showMessageDialog(null, "A quantidade de silabas e invalida. Digite um numero menor ou aumente o nivel da palavra.", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				/*!< Colocando os campos e botões da sílaba da palavra na tela */
				setQuantidadeDeSilabas(quantidadeDeSilabas);
				criarSilabas();		
				colocarSilabasNaTela();
				
				/*!< Colocando os campos da sílaba EXTRA na tela */
				setQuantidadeDeSilabasExtras(quantidadeSilabasExtras);
				criarSilabasExtras();
				colocarSilabasExtrasNaTela();
			}
		});
		btnCriarSilabas.setBounds(200, 351, 200, 25);
		contentPane.add(btnCriarSilabas);
		
		/*!< Evento quando muda o nível selecionado */
		cbLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setNivel(cbLevel.getSelectedIndex());
			}
		});
		
		JTextField txtPalavra = new JTextField();
		txtPalavra.setBounds(200, 144, 200, 30);
		txtPalavra.setFont(new Font("Cooper Black", Font.PLAIN, 18));
		contentPane.add(txtPalavra);
		txtPalavra.setColumns(10);
		
		JLabel lblPalavra = new JLabel("Digite a palavra completa:");
		lblPalavra.setForeground(new Color(0, 0, 128));
		lblPalavra.setFont(new Font("Cooper Black", Font.PLAIN, 18));
		lblPalavra.setBounds(200, 114, 300, 25);
		contentPane.add(lblPalavra);
		
		JLabel lblQuantidadeDeSilabas = new JLabel("Quantidade de silabas:");
		lblQuantidadeDeSilabas.setForeground(new Color(0, 0, 128));
		lblQuantidadeDeSilabas.setFont(new Font("Cooper Black", Font.PLAIN, 18));
		lblQuantidadeDeSilabas.setBounds(200, 203, 401, 25);
		contentPane.add(lblQuantidadeDeSilabas);
		
		JButton btnCadastrarNovaPalavra = new JButton("Cadastrar nova palavra");
		btnCadastrarNovaPalavra.setBackground(new Color(255, 215, 0));
		btnCadastrarNovaPalavra.setForeground(new Color(255, 69, 0));
		btnCadastrarNovaPalavra.setFont(new Font("Cooper Black", Font.PLAIN, 18));
		
		btnCadastrarNovaPalavra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				if(txtPalavra.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite a palavra completa.", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(txtQuantidadeSilabas.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite a quantidade de silabas!", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(nivel == null) {
					JOptionPane.showMessageDialog(null, "Selecione um nivel.", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				/*!< Verificando se as silabas são válidas */
				if(!silabasSaoValidas(txtPalavra.getText())) 
					return;
				if(!silabasExtrasSaoValidas())
					return;
				
				try {
					String[] silabasPalavra = new String[silabas.size()];
					String[] extras = new String[silabasExtras.size()];
					
					/*!< Criando o vetor com as sílabas da palavra */
					for(int i = 0; i < silabas.size(); i++) 
						silabasPalavra[i] = silabas.get(i).getText();
					/*!< Criando o vetor com as sílabas EXTRAS */
					for(int i = 0; i < silabasExtras.size(); i++) 
						extras[i] = silabasExtras.get(i).getText();
					
					ManipulaSilabas manipulaSilabas = new ManipulaSilabas(nivel);
					if(manipulaSilabas.inserePalavra(silabasPalavra, extras)) {
						JOptionPane.showMessageDialog(null, "Palavra cadastrada com sucesso.", "", JOptionPane.INFORMATION_MESSAGE);
						
						/*!< Limpando os botões e campos de sílabas */
						removerSilabaDaTela();
						removerSilabaExtraDaTela();
						
						/*!< Limpando os campos da tela */
						txtPalavra.setText("");
						txtQuantidadeSilabas.setText("");
						cbLevel.setSelectedIndex(0);
					} else {
						JOptionPane.showMessageDialog(null, "A palavra já estão cadastrada.", "", JOptionPane.ERROR_MESSAGE);
					}
						
				} catch (ArquivoException e) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro com a aplicacao.", "", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
					
				
			}
		});
		btnCadastrarNovaPalavra.setBounds(562, 562, 336, 25);
		contentPane.add(btnCadastrarNovaPalavra);
		
	}
	
	/*!< Método para retornar a página anterior */
	private void voltar() {
		telaAnterior.setVisible(true);
		dispose();
	}
	
	private void setNivel(int nivel) {
		switch(nivel) {
			case 1: 
				this.nivel = Niveis.NIVEL1;
				break;
			case 2: 
				this.nivel = Niveis.NIVEL2;
				break;
			case 3:
				this.nivel = Niveis.NIVEL3;
				break;
			default:
				this.nivel = null;
		}
	}
	
	/*!< Método para setar a quantidade de silabas */
	private void setQuantidadeDeSilabas(int quantidadeDeSilabas) {
		this.quantidadeDeSilabas = quantidadeDeSilabas;
	}
	
	/*!< Método para setar a quantidade de silabas EXTRAS */
	private void setQuantidadeDeSilabasExtras(int quantidadeDeSilabasExtras) {
		this.quantidadeDeSilabasExtras = quantidadeDeSilabasExtras;
	}
	
	/**
	 * Método responsável por limpar a tela.
	 * Os JTextField de sílabas e os respectivos botões associados serão removidos da tela.
	 * Além disso, os vetores de silabas e botões são limpos.
	 * */
	private void removerSilabaDaTela() {
		for(JTextField silaba : this.silabas) 
			contentPane.remove(silaba);
		this.silabas.clear();
		contentPane.remove(texto1);
		contentPane.remove(texto2);
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
		}
	}
	

	/**
	 * Método responsável por colocar os JTextField e os JButton associados na tela.
	 * Irão percorrer o vetor de sílabas e botões e adicionar ao contentPane.
	 * */
	private void colocarSilabasNaTela() {
		contentPane.add(texto1);
		contentPane.add(texto2);
		int posicaoY = 230;
		for(JTextField silaba : this.silabas) {
			contentPane.remove(silaba);
			silaba.setFont(new Font("Cooper Black", Font.PLAIN, 18));
			silaba.setBounds(500, posicaoY, 114, 30);
			contentPane.add(silaba);
			posicaoY += 35;
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
			JOptionPane.showMessageDialog(null, "Quantidade de silabas invalida.", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		int quantidadeDeLetras = 0;
		/*!< Verificando se alguma das sílabas é vazia */
		for(JTextField silaba : this.silabas) {
			quantidadeDeLetras += silaba.getText().length();
			if(silaba.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Preencha todas as silabas.", "", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			/*!< Verificando se a String só possui letras */
			if(!silaba.getText().matches("[a-zA-Z]+")) {
				JOptionPane.showMessageDialog(null, "A silaba so pode conter letras.", "", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		}
		
		/*!< A palavra deve ter a mesma quantidade de letras que a silaba */
		if(palavra.length() != quantidadeDeLetras) {
			JOptionPane.showMessageDialog(null, "As silabas e palavras nao coincidem.", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
							
		return true;
	}
	
	/**
	 * Método responsável por limpar a tela.
	 * Os JTextField de sílabas EXTRAS e os respectivos botões associados serão removidos da tela.
	 * Além disso, os vetores de silabas e botões EXTRAS são limpos.
	 * */
	private void removerSilabaExtraDaTela() {
		for(JTextField silaba : this.silabasExtras) 
			contentPane.remove(silaba);
		this.silabasExtras.clear();
		repaint();
	}
	
	/**
	 *  Método responsável por criar os JTextField e os JButton associados das sílabas EXTRAS.
	 *  Nota: Sílabas extras são as sílabas que irão aparecer na tela além das sílabas da palavra.
	 *	Armazena os objetos numa lista de JTextField e em uma de JButton.
	 * */
	private void criarSilabasExtras() {		
		removerSilabaExtraDaTela(); /*!< Limpando a tela */
		
		for(int i = 0; i < this.quantidadeDeSilabasExtras; i++) {
			JTextField silabaExtra = new JTextField();
			silabaExtra.setFont(new Font("Cooper Black", Font.PLAIN, 18));
			silabasExtras.add(silabaExtra);
		}
	}
	

	/**
	 * Método responsável por colocar os JTextField e os JButton associados na tela.
	 * Irá percorrer o vetor de sílabas e botões EXTRAS e adicionar ao contentPane.
	 * */
	private void colocarSilabasExtrasNaTela() {
		int posicaoY = 230;
		for(JTextField silaba : this.silabasExtras) {
			contentPane.remove(silaba);
			silaba.setBounds(700, posicaoY, 200, 30);
			contentPane.add(silaba);
			posicaoY += 35;
		}
		posicaoY = 230;
		repaint();
	}
	
	/*!< Método responsável por verificar se todas as silabas EXTRAS são válidas */ 
	private boolean silabasExtrasSaoValidas() {
		/*!< Verificando se há sílabas extras */
		if(this.quantidadeDeSilabasExtras < 1) {
			JOptionPane.showMessageDialog(null, "E necessario pelo menos uma silaba extra.", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		/**
		 * O nível 1 deve possuir 6 sílabas.
		 * O nível 2 deve possuir 6 sílabas.
		 * O nível 3 deve possuir 8 sílabas.
		 **/
		int quantidadeSilabaQueDeveTer = 6;
		if(this.nivel == Niveis.NIVEL3)
			quantidadeSilabaQueDeveTer = 8;
		
		/*!< Cada nível deve possuir um número certo de sílabas extras + sílabas de palavra */
		if(quantidadeDeSilabas + quantidadeDeSilabasExtras != quantidadeSilabaQueDeveTer) {
			JOptionPane.showMessageDialog(null, "Sao necessarias " + quantidadeSilabaQueDeveTer + " silabas ao total.", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
			
		/*!< Verificando se alguma das sílabas é vazia */
		for(JTextField silaba : this.silabasExtras) {
			String texto = silaba.getText();
			if(texto.equals("")) {
				JOptionPane.showMessageDialog(null, "Preencha todas as silabas extras.", "", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(texto.length() >= 4) {
				JOptionPane.showMessageDialog(null, "Silaba invalida.", "", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			/*!< Verificando se a String só possui letras */
			if(!texto.matches("[a-zA-Z]+")) {
				JOptionPane.showMessageDialog(null, "A silaba so pode conter letras.", "", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		return true;
	}
}
