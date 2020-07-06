package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	private JTextField silabas[];
	
	private JFrame telaAnterior;
	
	public InserirPalavra(JFrame telaAnterior) {
		
		this.telaAnterior = telaAnterior;
		
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
				
				silabas = new JTextField[quantidadeDeSilabas];
				int posicaoY = 250;
				for(int i = 0; i < quantidadeDeSilabas; i++) {
					JTextField silaba = new JTextField();
					silaba.setBounds(86, posicaoY, 114, 19);
					silabas[i] = silaba;
					contentPane.add(silaba);
					posicaoY += 30;
					
	//				JButton botaoSilaba = new JButton("Apagar sílaba");
	//				botaoSilaba.addActionListener(new ActionListener() {
	//					public void actionPerformed(ActionEvent arg0) {
	//						silabas = ArrayUtils.removeElement(silabas, silaba);
	//					}
	//				});
	//				botaoSilaba.setBounds(130, posicaoY, 114, 19);
	//				contentPane.add(botaoSilaba);
				}
				repaint();
			}
		});
		btnCriarSilabas.setBounds(223, 213, 142, 25);
		contentPane.add(btnCriarSilabas);
		
		JTextField txtPalavra = new JTextField();
		txtPalavra.setBounds(86, 145, 114, 19);
		contentPane.add(txtPalavra);
		txtPalavra.setColumns(10);
		
		JLabel lblPalavra = new JLabel("Digite a palavra separando as silabas por espaço");
		lblPalavra.setBounds(88, 118, 401, 15);
		contentPane.add(lblPalavra);
		
		JLabel lblQuantidadeDeSilabas = new JLabel("Quantidade de silabas:");
		lblQuantidadeDeSilabas.setBounds(86, 189, 401, 15);
		contentPane.add(lblQuantidadeDeSilabas);
	}
	
	private void voltar() {
		telaAnterior.setVisible(true);
		dispose();
	}
}
