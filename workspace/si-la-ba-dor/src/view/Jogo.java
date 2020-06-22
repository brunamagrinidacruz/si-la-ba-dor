package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Jogo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Jogo frame = new Jogo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */

	public Jogo(String user, int nivel) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel background;
		setSize(1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon img = new ImageIcon("background_silabador.png");
		
		JLabel lblNivel = new JLabel("Nivel: " + (nivel + 1));
		lblNivel.setForeground(new Color(0, 0, 128));
		lblNivel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		lblNivel.setBounds(29, 8, 113, 40);
		contentPane.add(lblNivel);
		
		JLabel lblJogadorDelamaro = new JLabel("Jogador: " + user);
		lblJogadorDelamaro.setForeground(new Color(0, 0, 128));
		lblJogadorDelamaro.setFont(new Font("Cooper Black", Font.PLAIN, 50));
		lblJogadorDelamaro.setBounds(277, 72, 747, 71);ntentPane.add(lblJogadorDelamaro);
		
		JButton btnPro = new JButton("PRO");
		btnPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPro.setBackground(new Color(0, 0, 128));
		btnPro.setForeground(new Color(255, 204, 102));
		btnPro.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		btnPro.setBounds(178, 199, 141, 89);
		contentPane.add(btnPro);
		
		JButton btnBa_1 = new JButton("��O");
		btnBa_1.setBackground(new Color(0, 0, 128));
		btnBa_1.setForeground(new Color(255, 204, 102));
		btnBa_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBa_1.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		btnBa_1.setBounds(457, 199, 141, 89);
		contentPane.add(btnBa_1);
		
		JButton btnBa = new JButton("BA");
		btnBa.setBackground(new Color(0, 0, 128));
		btnBa.setForeground(new Color(255, 204, 102));
		btnBa.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		btnBa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBa.setBounds(732, 199, 141, 89);
		contentPane.add(btnBa);
		
		JButton btnMa = new JButton("MA");
		btnMa.setBackground(new Color(0, 0, 128));
		btnMa.setForeground(new Color(255, 204, 102));
		btnMa.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		btnMa.setBounds(308, 381, 150, 89);
		contentPane.add(btnMa);
		
		JButton btnBa_1_1 = new JButton("CHA");
		btnBa_1_1.setBackground(new Color(0, 0, 128));
		btnBa_1_1.setForeground(new Color(255, 204, 102));
		btnBa_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBa_1_1.setEnabled(false);
		btnBa_1_1.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		btnBa_1_1.setBounds(586, 381, 150, 89);
		contentPane.add(btnBa_1_1);
		
		JButton btnPro_2_1 = new JButton("GRA");
		btnPro_2_1.setBackground(new Color(0, 0, 128));
		btnPro_2_1.setForeground(new Color(255, 204, 102));
		btnPro_2_1.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		btnPro_2_1.setBounds(903, 381, 141, 89);
		contentPane.add(btnPro_2_1);
		
		JLabel label = new JLabel("_ _ _ _ _ _ _ _ _ _ ");
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Dialog", Font.BOLD, 99));
		label.setBounds(253, 528, 997, 141);
		contentPane.add(label);
		
		JLabel lblCha = new JLabel(" C  H  A  ");
		lblCha.setForeground(Color.ORANGE);
		lblCha.setFont(new Font("Cooper Black", Font.PLAIN, 65));
		lblCha.setBounds(238, 586, 348, 55);
		contentPane.add(lblCha);
		
		background = new JLabel("", img,JLabel.CENTER);
		background.setBackground(new Color(51, 204, 204));
		background.setBounds(0, 0, 1262, 673);
		getContentPane().add(background);
	}

}
