package telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dominio.Partida;
import excecoes.InimigoInvalidoException;
import excecoes.PersonagemInvalidoException;
import personagens.Arqueiro;
import personagens.Guerreiro;
import personagens.Mago;
import personagens.Personagem;

public class TelaPersonagem extends JFrame {

	private static final long serialVersionUID = 1L;

	//componentes da tela
	private JComboBox<String> cbClasse;
	private JTextField txtNome;
	private JTextArea areaDescricao;
	private JButton btnIniciar;
	private JButton btnVoltar;

	public TelaPersonagem() {
		//configurações da janela
		setTitle("Escolha seu personagem");
		setSize(450, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		//parte de cima -> escolher a classe e digitar o nome
		cbClasse = new JComboBox<String>();
		cbClasse.addItem("Guerreiro");
		cbClasse.addItem("Mago");
		cbClasse.addItem("Arqueiro");

		txtNome = new JTextField();

		JPanel painelTopo = new JPanel(new GridLayout(2, 2, 5, 5));
		painelTopo.add(new JLabel("Classe:"));
		painelTopo.add(cbClasse);
		painelTopo.add(new JLabel("Nome:"));
		painelTopo.add(txtNome);
		add(painelTopo, BorderLayout.NORTH);

		//meio -> descrição da classe escolhida
		areaDescricao = new JTextArea();
		areaDescricao.setEditable(false); // só para leitura
		add(areaDescricao, BorderLayout.CENTER);

		// parte de baixo -> botões
		btnIniciar = new JButton("Iniciar Jogo");
		btnVoltar = new JButton("Voltar");

		JPanel painelBotoes = new JPanel(new FlowLayout());
		painelBotoes.add(btnVoltar);
		painelBotoes.add(btnIniciar);
		add(painelBotoes, BorderLayout.SOUTH);

		//quando trocar a classe atualiza a descrição
		cbClasse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarDescricao();
			}
		});

		btnIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarJogo();
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltar();
			}
		});

		atualizarDescricao(); //mostra a descrição da primeira classe ao abrir
	}

	//cria o personagem de acordo com a classe escolhida 
	private Personagem criarPersonagem(String nome) throws PersonagemInvalidoException {
		String classe = (String) cbClasse.getSelectedItem();

		if (classe.equals("Guerreiro")) {
			return new Guerreiro(nome);
		} else if (classe.equals("Mago")) {
			return new Mago(nome);
		} else {
			return new Arqueiro(nome);
		}
	}

	//mostra os atributos da classe escolhida
	//cria um personagem para ler os valores
	private void atualizarDescricao() {
		try {
			Personagem p = criarPersonagem("Exemplo");
			String classe = (String) cbClasse.getSelectedItem();

			String texto = classe + "\n\n";
			texto += p.getEspecialidade() + "\n\n";
			texto += "Vida: " + p.getVidaMaxima() + "\n";
			texto += "Ataque: " + p.getAtaqueBase() + "\n";
			texto += "Defesa: " + p.getDefesa() + "\n\n";
			texto += "Habilidade especial: " + descricaoHabilidade(classe);

			areaDescricao.setText(texto);
		} catch (PersonagemInvalidoException e) {
			areaDescricao.setText("Erro ao carregar a classe: " + e.getMessage());
		}
	}

	private String descricaoHabilidade(String classe) {
		if (classe.equals("Guerreiro")) {
			return "gasta 5 de energia e causa 1,5x o ataque base.";
		} else if (classe.equals("Mago")) {
			return "gasta 5 de mana e causa 2x o ataque base.";
		} else {
			return "gasta 5 de precisão e causa 1,8x o ataque base.";
		}
	}

	private void iniciarJogo() {
		String nome = txtNome.getText().trim();

		if (nome.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Digite o nome do personagem.", "Atenção",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			Personagem jogador = criarPersonagem(nome);
			Partida partida = new Partida(jogador);

			
			new TelaCombate(partida).setVisible(true);
			dispose();
			JOptionPane.showMessageDialog(this,
					"Partida criada para " + partida.getJogador().getNome() + "! Vamos ao combate.");

		} catch (PersonagemInvalidoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (InimigoInvalidoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void voltar() {
		MenuPrincipal menu = new MenuPrincipal();
		menu.setVisible(true);
		dispose(); //fecha essa janela
	}
}
