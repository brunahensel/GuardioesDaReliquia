package telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	//componentes da tela
	private JLabel lblTitulo;
	private JButton btnNovoJogo;
	private JButton btnSair;

	public MenuPrincipal() {
		//configurações da janela
		setTitle("RPG de Combate");
		setSize(400, 250);
		setLocationRelativeTo(null); //abre no centro da tela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fecha a janela encerra o programa
		setLayout(new BorderLayout());

		//título fica na parte de cima da janela
		lblTitulo = new JLabel("GUARDIÕES DA RELÍQUIA", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblTitulo.setForeground(Color.BLUE);
		add(lblTitulo, BorderLayout.NORTH);

		//botoes ficam em um painel no centro da janela
		btnNovoJogo = new JButton("Novo Jogo");
		btnSair = new JButton("Sair");

		JPanel painelBotoes = new JPanel(new FlowLayout());
		painelBotoes.add(btnNovoJogo);
		painelBotoes.add(btnSair);
		add(painelBotoes, BorderLayout.CENTER);

		//o que acontece quando clicar em cada botao
		btnNovoJogo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				novoJogo();
			}
		});

		btnSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void novoJogo() {
		new TelaPersonagem().setVisible(true);
		dispose();
		JOptionPane.showMessageDialog(this, "Vamos escolher o seu personagem!");
	}
}
