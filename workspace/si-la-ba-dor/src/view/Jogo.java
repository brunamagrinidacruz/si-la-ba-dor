package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNivel = new JLabel("Nivel: " + (nivel + 1));
		lblNivel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNivel.setBounds(29, 12, 70, 15);
		contentPane.add(lblNivel);
		
		JLabel lblJogadorDelamaro = new JLabel("Jogador: " + user);
		lblJogadorDelamaro.setFont(new Font("Dialog", Font.BOLD, 16));
		lblJogadorDelamaro.setBounds(248, 5, 163, 28);
		contentPane.add(lblJogadorDelamaro);
		
		JButton btnPro = new JButton("PRO");
		btnPro.setFont(new Font("Dialog", Font.BOLD, 16));
		btnPro.setBounds(53, 45, 94, 66);
		contentPane.add(btnPro);
		
		JButton btnBa_1 = new JButton("ÇÃO");
		btnBa_1.setFont(new Font("Dialog", Font.BOLD, 16));
		btnBa_1.setBounds(179, 45, 88, 66);
		contentPane.add(btnBa_1);
		
		JButton btnBa = new JButton("BA");
		btnBa.setFont(new Font("Dialog", Font.BOLD, 16));
		btnBa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBa.setBounds(300, 45, 94, 66);
		contentPane.add(btnBa);
		
		JButton btnMa = new JButton("MA");
		btnMa.setFont(new Font("Dialog", Font.BOLD, 16));
		btnMa.setBounds(53, 132, 94, 66);
		contentPane.add(btnMa);
		
		JButton btnBa_1_1 = new JButton("CHA");
		btnBa_1_1.setEnabled(false);
		btnBa_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		btnBa_1_1.setBounds(179, 132, 88, 66);
		contentPane.add(btnBa_1_1);
		
		JButton btnPro_2_1 = new JButton("GRA");
		btnPro_2_1.setFont(new Font("Dialog", Font.BOLD, 16));
		btnPro_2_1.setBounds(300, 132, 94, 66);
		contentPane.add(btnPro_2_1);
		
		JLabel label = new JLabel("_ _ _ _ _ _ _ _ _ _ ");
		label.setFont(new Font("Dialog", Font.BOLD, 40));
		label.setBounds(52, 210, 359, 48);
		contentPane.add(label);
		
		JLabel lblCha = new JLabel(" C    H    A  ");
		lblCha.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCha.setBounds(46, 225, 348, 35);
		contentPane.add(lblCha);
	}

}
