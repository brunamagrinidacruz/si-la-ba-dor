package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Jogo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private final int ESPACAMENTO = 50;
	
	private int xProximaSilaba = 67;
	private int xProximaBarra = 65;
	
	public Jogo(JFrame menu, String user, int nivel) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNivel = new JLabel("Nivel: " + (nivel + 1));
		lblNivel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNivel.setBounds(31, 48, 70, 15);
		contentPane.add(lblNivel);
		
		JLabel lblJogador = new JLabel("Jogador: " + user);
		lblJogador.setFont(new Font("Dialog", Font.BOLD, 16));
		lblJogador.setBounds(250, 41, 163, 28);
		contentPane.add(lblJogador);
		
		JButton btnPro = new JButton("PRO");
		btnPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarSilaba(btnPro.getText());
			}
		});
		btnPro.setFont(new Font("Dialog", Font.BOLD, 16));
		btnPro.setBounds(55, 81, 94, 66);
		contentPane.add(btnPro);
		
		JButton btnBa_1 = new JButton("ÇÃO");
		btnBa_1.setFont(new Font("Dialog", Font.BOLD, 16));
		btnBa_1.setBounds(181, 81, 88, 66);
		contentPane.add(btnBa_1);
		
		JButton btnBa = new JButton("BA");
		btnBa.setFont(new Font("Dialog", Font.BOLD, 16));
		btnBa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarSilaba(btnBa.getText());
			}
		});
		btnBa.setBounds(302, 81, 94, 66);
		contentPane.add(btnBa);
		
		JButton btnMa = new JButton("MA");
		btnMa.setFont(new Font("Dialog", Font.BOLD, 16));
		btnMa.setBounds(55, 168, 94, 66);
		contentPane.add(btnMa);
		
		JButton btnBa_1_1 = new JButton("CHA");
		btnBa_1_1.setEnabled(false);
		btnBa_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		btnBa_1_1.setBounds(181, 168, 88, 66);
		contentPane.add(btnBa_1_1);
		
		JButton btnPro_2_1 = new JButton("GRA");
		btnPro_2_1.setFont(new Font("Dialog", Font.BOLD, 16));
		btnPro_2_1.setBounds(302, 168, 94, 66);
		contentPane.add(btnPro_2_1);
		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnVoltar.setBounds(12, 12, 94, 24);
		contentPane.add(btnVoltar);
	}
	
	private void adicionarBarra(int x, int quantidadeDeLetras) {
		JLabel lblBarra;
		switch(quantidadeDeLetras) {
			case 1:
				lblBarra = new JLabel("_");
				break;
			case 2:
				lblBarra = new JLabel("__");
				break;
			case 3:
				lblBarra = new JLabel("___");
				break;
			default:
				lblBarra = new JLabel("____");
				
		}
		lblBarra.setForeground(Color.BLACK);
		lblBarra.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblBarra.setBounds(x, 246, 348, 39);
		contentPane.add(lblBarra);
		this.xProximaBarra = this.xProximaBarra + ESPACAMENTO;
	}
	
	private void adicionarSilaba(int x, String silaba) {
		JLabel lblSilaba = new JLabel(silaba);
		lblSilaba.setForeground(Color.BLACK);
		lblSilaba.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblSilaba.setBounds(x, 247, 348, 39);
		contentPane.add(lblSilaba);
		this.xProximaSilaba = this.xProximaSilaba + ESPACAMENTO;
	}
	
	private void adicionarSilaba(String texto) {
		adicionarSilaba(xProximaSilaba, texto);
		adicionarBarra(xProximaBarra, texto.length());
	}
}
