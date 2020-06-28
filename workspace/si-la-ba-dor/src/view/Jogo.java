package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Niveis;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Jogo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private final int ESPACAMENTO = 200;
	
	private int xProximaSilaba = 67;
	private int xProximaBarra = 65;
	
	public Jogo(JFrame menu, String user, Niveis nivel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 363);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel background;
		setSize(1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon img = new ImageIcon("assets/background_silabador.png");

		
		JLabel lblNivel = new JLabel("Nivel: " + nivel.getNivel());
		lblNivel.setForeground(new Color(0, 0, 128));
		lblNivel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		lblNivel.setBounds(29, 8, 113, 40);
		contentPane.add(lblNivel);
		
		JLabel lblJogador = new JLabel("Jogador: " + user);
		lblJogador.setForeground(new Color(0, 0, 128));
		lblJogador.setFont(new Font("Cooper Black", Font.PLAIN, 50));
		lblJogador.setBounds(277, 72, 747, 71);
		contentPane.add(lblJogador);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnVoltar.setBounds(1013, 17, 94, 24);
		contentPane.add(btnVoltar);
		
		JButton btnSilaba = new JButton("PRO");
		btnSilaba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarSilaba(btnSilaba.getText());
			}
		});
		btnSilaba.setBackground(new Color(0, 0, 128));
		btnSilaba.setForeground(new Color(255, 204, 102));
		btnSilaba.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		btnSilaba.setBounds(178, 199, 141, 89);
		contentPane.add(btnSilaba);				
		background = new JLabel("", img,JLabel.CENTER);
		background.setBackground(new Color(51, 204, 204));
		background.setBounds(0, 0, 1262, 673);
		getContentPane().add(background);
	}
	
	private void adicionarSilaba(String silaba) {
		JLabel lblSilaba = new JLabel(silaba);
		lblSilaba.setForeground(Color.ORANGE);
		lblSilaba.setFont(new Font("Cooper Black", Font.PLAIN, 65));
		lblSilaba.setBounds(xProximaSilaba, 586, 348, 55);
		contentPane.add(lblSilaba);
		this.xProximaSilaba = this.xProximaSilaba + ESPACAMENTO;
	}
	
	private void adicionarBarra(int quantidadeDeLetras) {
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
		lblBarra.setBounds(xProximaBarra, 528, 997, 141);
		contentPane.add(lblBarra);
		this.xProximaBarra = this.xProximaBarra + ESPACAMENTO;
	}
	
	
	private void selecionarSilaba(String texto) {
		adicionarSilaba(texto);
		adicionarBarra(texto.length());
	}
}
