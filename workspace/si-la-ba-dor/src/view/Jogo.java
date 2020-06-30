package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ManipulaArquivo;
import control.Niveis;
import model.Palavra;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class Jogo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private final int ESPACAMENTO = 200;
	
	private int xProximaSilaba = 67;
	private int xProximaBarra = 65;
	
	private JLabel background;
	
	public Jogo(JFrame menu, String user, Niveis nivel) {
		ManipulaArquivo manipulaArquivo = new ManipulaArquivo(nivel);
		Palavra palavra = manipulaArquivo.recebePalavra();
		System.out.println(palavra);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 363);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(1280, 720);
		
		contentPane = new JPanel();
		
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ImageIcon img = new ImageIcon("assets/background_silabador.png");
		
		background = new JLabel(img);
		
		contentPane.add(background);
		
		background.setLayout(new FlowLayout());
		
		JLabel lblNivel = new JLabel("Nivel: " + nivel.getNivel());
		lblNivel.setForeground(new Color(0, 0, 128));
		lblNivel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		lblNivel.setBounds(29, 8, 113, 40);
		background.add(lblNivel);
		
		JLabel lblJogador = new JLabel("Jogador: " + user);
		lblJogador.setForeground(new Color(0, 0, 128));
		lblJogador.setFont(new Font("Cooper Black", Font.PLAIN, 50));
		lblJogador.setBounds(277, 72, 747, 71);
		background.add(lblJogador);
				
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnVoltar.setBounds(1013, 17, 94, 24);
		background.add(btnVoltar);
		
		JButton btnSilaba = new JButton("PRO");
		
		btnSilaba.setBackground(new Color(0, 0, 128));
		btnSilaba.setForeground(new Color(255, 204, 102));
		btnSilaba.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		btnSilaba.setBounds(178, 199, 141, 89);
		background.add(btnSilaba);
		
		btnSilaba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarSilaba(btnSilaba.getText());
				repaint();
			}
		});
	}
	
	private void adicionarSilaba(String silaba) {
		JLabel lblSilaba = new JLabel(silaba);
		lblSilaba.setForeground(Color.ORANGE);
		lblSilaba.setFont(new Font("Cooper Black", Font.PLAIN, 32));
		lblSilaba.setBounds(xProximaSilaba+7, 586, 348, 55);
		background.add(lblSilaba);
		this.xProximaSilaba = this.xProximaSilaba + ESPACAMENTO;
	}
	
	private void adicionarBarra(int quantidadeDeLetras) {
		JLabel lblBarra = new JLabel();
		switch(quantidadeDeLetras) {
			case 1:
				lblBarra.setText("__");
				break;
			case 2:
				lblBarra.setText("____");
				break;
			case 3:
				lblBarra.setText("______");
				break;
			default:
				lblBarra.setText("________");
		}
		
		lblBarra.setForeground(Color.BLACK);
		lblBarra.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblBarra.setBounds(xProximaBarra, 550, 997, 141);
		background.add(lblBarra);
		this.xProximaBarra = this.xProximaBarra + ESPACAMENTO;
	}
	
	private void selecionarSilaba(String texto) {
		adicionarSilaba(texto);
		adicionarBarra(texto.length());
	}
}