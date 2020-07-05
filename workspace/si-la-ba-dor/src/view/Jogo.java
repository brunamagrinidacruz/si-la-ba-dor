package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ManipulaArquivo;
import control.Niveis;
import exception.ArquivoException;
import model.Palavra;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class Jogo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/*!< Objeto do menu do jogo */
	private JFrame menu;

	/*!< JLabel utilizado para informar situação do usuário durante o jogo */
	private JLabel lblMensagem;
	
	private String usuario;
	private Niveis nivel;
	private Palavra palavra;
	
	/*!< Representa as silabas na tela */
	JLabel silabasNaTela[];
	/*!< Representa o número de silabas que a pessoa colocou na tela. Este valor não pode ultrapassar o número de silabas da palavra */
	private int numeroSilabasNaTela = 0; 
	
	private final int ESPACAMENTO = 200;

	private int xProximoBotao = 100;
	private int xProximaSilaba = 67;
	private int xProximaBarra = 65;
	
//	private JLabel background;
	
	public Jogo(JFrame menu, String usuario, Niveis nivel){
		this.menu = menu;
		this.usuario = usuario;
		this.nivel = nivel;
		
		ManipulaArquivo manipulaArquivo = null;
		
		try {
			manipulaArquivo = new ManipulaArquivo(nivel);
		} catch (ArquivoException e) {
			e.printStackTrace();
		}
		
		this.palavra = manipulaArquivo.recebePalavra();		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 363);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(1280, 720);
		
		contentPane = new JPanel();
		
		//contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		inicializarSilabas();
		
		JLabel lblNivel = new JLabel("Nivel: " + this.nivel.getNivel());

		lblNivel.setForeground(new Color(0, 0, 128));
		lblNivel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		lblNivel.setBounds(29, 8, 113, 40);
		contentPane.add(lblNivel);
		
		JLabel lblJogador = new JLabel("Jogador: " + this.usuario);
		lblJogador.setForeground(new Color(0, 0, 128));
		lblJogador.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblJogador.setBounds(143, 8, 134, 40);
//		contentPane.add(lblJogador);
				
		lblJogador.setFont(new Font("Cooper Black", Font.PLAIN, 50));
		lblJogador.setBounds(277, 72, 747, 71);
		contentPane.add(lblJogador);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltarParaMenu();
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnVoltar.setBounds(1013, 17, 94, 24);
		contentPane.add(btnVoltar);
		
		JButton btnLimparSilabas = new JButton("Limpar sílabas");
		btnLimparSilabas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < numeroSilabasNaTela; i++) {
					/*!< Removendo silabas da tela e do vetor */
					contentPane.remove(silabasNaTela[i]);
					silabasNaTela[i] = null;
				}
				/*!< Voltando x ao x inicial das silabas na tela */
				xProximaSilaba = 67;
				numeroSilabasNaTela = 0;
				lblMensagem.setText("");
				repaint();
			}
		});
		btnLimparSilabas.setBounds(921, 608, 220, 25);
		contentPane.add(btnLimparSilabas);
//		background.add(btnLimparSilabas);
		
		lblMensagem = new JLabel("");
		lblMensagem.setForeground(SystemColor.activeCaption);
		lblMensagem.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblMensagem.setBounds(411, 44, 477, 40);
		contentPane.add(lblMensagem);
			
	}
	
	
	/**
	 * Retorna o usuário para a tela de menu
	 * */
	private void voltarParaMenu() {
		menu.setVisible(true);
		dispose();
	}
	
	/**
	 * Cria os botões com as sílabas e coloca na tela.
	 * Cria os campos de preenchimento para o usuário colocar as sílabas.
	 * */
	private void inicializarSilabas() {
		/*!< Inicializando o vetor que irá armazenar as silabas que estão na tela com o tamanho da silaba correta */
		silabasNaTela = new JLabel[palavra.getNumSilabas()];
		
		/*!< Criando os botões de sílabas */
		for(int i = 0; i < palavra.getNumTotalSilabas(); i++) {
			JButton btnSilaba = new JButton(palavra.getSilabasCompleto()[i]);
			
			btnSilaba.setBackground(new Color(0, 0, 128));
			btnSilaba.setForeground(new Color(255, 204, 102));
			btnSilaba.setFont(new Font("Cooper Black", Font.PLAIN, 40));
			btnSilaba.setBounds(xProximoBotao, 199, 141, 89);
			contentPane.add(btnSilaba);

			btnSilaba.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(numeroSilabasNaTela < palavra.getNumSilabas()) {
						adicionarSilaba(btnSilaba.getText());
						colocarSilabasNaTela();
					} else {
						System.out.println("Você atingiu o número máximo de sílabas.");
					}
					
					/*!< O usuário colocou o número máximo de sílabas então deve ser validado se a palavra está correta*/
					if(numeroSilabasNaTela == palavra.getNumSilabas()) {
						for(int i = 0; i < palavra.getNumSilabas(); i++) {
							if(!palavra.getSilabasPalavra()[i].equals(silabasNaTela[i].getText())) {
								lblMensagem.setText("Você não acertou a palavra. Tente limpar as sílabas e tentar novamente");
								repaint();
								return;
							}
						}
						
						int input = JOptionPane.showConfirmDialog(null, "Você acertou a palavra!\nDeseja jogar novamente?", "Parabéns!", JOptionPane.YES_NO_OPTION);
						if(input == 0) {/*!< Sim. Iniciar nova partida */
							/*!< A nova partida será no próximo nível caso o nível atual não for o 3º. Se for o 3º, a pessoa continuará nele */
							Niveis proximoNivel;
							int valorNivel = nivel.getNivel();
							switch(valorNivel) {
								case 1:
									proximoNivel = Niveis.NIVEL2;
									break;
								case 2: 
									proximoNivel = Niveis.NIVEL3;
									break;
								default:
									proximoNivel = Niveis.NIVEL3;
							}
							Jogo jogo = new Jogo(menu, usuario, proximoNivel);
							jogo.setLocationRelativeTo(null);
							jogo.setVisible(true);
							dispose();
						} else { /*!< Não. Voltar para a página inicial */
							voltarParaMenu();
						}
						
					}
					
				}
			});
			
			xProximoBotao += 150;
		}
		
		/*!< Criando os campos de preenchimento */
		for(int i = 0; i < palavra.getNumSilabas(); i++) {
			JLabel lblBarra = new JLabel();
			lblBarra.setText("________");			
			lblBarra.setForeground(Color.BLACK);
			lblBarra.setFont(new Font("Dialog", Font.PLAIN, 25));
			lblBarra.setBounds(xProximaBarra, 550, 997, 141);
			contentPane.add(lblBarra);
			this.xProximaBarra = this.xProximaBarra + ESPACAMENTO;
		}
		
		repaint();
	}
	
	/**
	 * Adiciona uma silaba selecionada pelo botão na tela.
	 * Armazena e contabiliza as silabas adiciondas na tela. 
	 **/
	private void adicionarSilaba(String silaba) {
		JLabel lblSilaba = new JLabel(silaba);
		lblSilaba.setForeground(Color.ORANGE);
		lblSilaba.setFont(new Font("Cooper Black", Font.PLAIN, 32));
		lblSilaba.setBounds(xProximaSilaba+7, 586, 348, 55);
		silabasNaTela[numeroSilabasNaTela] = lblSilaba;
		numeroSilabasNaTela++;
		this.xProximaSilaba = this.xProximaSilaba + ESPACAMENTO;
	}
	
	/**
	 * Coloca na tela as silabas contidas no vetor silabasNaTela[]
	 */
	private void colocarSilabasNaTela() {
		for(int i = 0; i < numeroSilabasNaTela; i++) {
			contentPane.add(silabasNaTela[i]);
			contentPane.add(silabasNaTela[i]);
		}
		repaint();
	}
	
}