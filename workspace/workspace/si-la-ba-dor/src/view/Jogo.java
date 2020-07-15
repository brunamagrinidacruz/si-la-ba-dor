package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ManipulaArquivo;
import control.Niveis;
import exception.ArquivoException;
import model.Jogador;
import model.Palavra;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
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
	
	private Jogador usuario;
	private Niveis nivel;
	private Palavra palavra;
	
	/*!< Representa as silabas na tela */
	JLabel silabasNaTela[];
	/*!< Representa o número de silabas que a pessoa colocou na tela. Este valor não pode ultrapassar o número de silabas da palavra */
	private int numeroSilabasNaTela = 0; 
	
	private final int ESPACAMENTO = 250;

	private int xProximoBotao = 350;
	private int xProximoBotaoBaixo = 350;
	private int xProximaSilaba = 67;
	private int xProximaBarra = 65;
	
	
	public Jogo(JFrame menu, Usuario usuario, Niveis nivel){
		this.menu = menu;
		this.usuario = (Jogador) usuario;
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
		contentPane.setForeground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		inicializarSilabas();
		contentPane.setLayout(null);
		
		JLabel lblNivel = new JLabel("Nivel: " + this.nivel.getNivel());

		lblNivel.setForeground(new Color(0, 0, 128));
		lblNivel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		lblNivel.setBounds(41, 27, 78, 24);
		contentPane.add(lblNivel);
		
		JLabel lblJogador = new JLabel("Jogador: " + usuario.getNome());
		lblJogador.setForeground(new Color(0, 0, 128));
		lblJogador.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblJogador.setBounds(410, 72, 802, 71);
//		contentPane.add(lblJogador);
				
		lblJogador.setFont(new Font("Cooper Black", Font.PLAIN, 50));
		lblJogador.setBounds(277, 72, 747, 71);
		contentPane.add(lblJogador);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(new Color(255, 215, 0));
		btnVoltar.setForeground(new Color(255, 69, 0));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltarParaMenu();
			}
		});
		btnVoltar.setFont(new Font("Cooper Black", Font.BOLD, 20));
		btnVoltar.setBounds(1141, 11, 115, 28);
		contentPane.add(btnVoltar);
		
		JButton btnLimparSilabas = new JButton("Limpar s�labas");
		btnLimparSilabas.setBackground(new Color(255, 215, 0));
		btnLimparSilabas.setFont(new Font("Cooper Black", Font.PLAIN, 22));
		btnLimparSilabas.setForeground(new Color(255, 69, 0));
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
		btnLimparSilabas.setBounds(900, 10, 231, 29);
		contentPane.add(btnLimparSilabas);
		
		JLabel lblPontuao = new JLabel("Pontua��o: " + this.usuario.getPontuacao());
		lblPontuao.setForeground(new Color(255, 69, 0));
		lblPontuao.setBounds(145, 26, 155, 26);
		lblPontuao.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		contentPane.add(lblPontuao);
//		background.add(btnLimparSilabas);
		
		lblMensagem = new JLabel("");
		lblMensagem.setForeground(SystemColor.activeCaption);
		lblMensagem.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblMensagem.setBounds(1111, 39, 0, 0);
		
	
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
			
			if (i < 4) {
				btnSilaba.setBounds(xProximoBotao, 199, 141, 89);
			}
			else {
				btnSilaba.setBounds(xProximoBotaoBaixo, 299, 141, 89);
				xProximoBotaoBaixo += 150;
			}
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
						usuario.setPontuacao(usuario.getPontuacao() + nivel.getNivel());
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
			lblBarra.setForeground(new Color(0, 0, 128));
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
		lblSilaba.setForeground(new Color(0, 0, 128));
		lblSilaba.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		lblSilaba.setBounds(xProximaSilaba+20, 586, 348, 55);
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