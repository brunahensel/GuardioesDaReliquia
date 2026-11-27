package telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dominio.Partida;
import excecoes.AcaoInvalidaException;
import inimigos.Inimigo;
import personagens.Arqueiro;
import personagens.Guerreiro;
import personagens.Mago;
import personagens.Personagem;

public class TelaCombate extends JFrame {

	private static final long serialVersionUID = 1L;

	private Partida partida;

	private Inimigo inimigoEmTela;

	//informacoes do topo
	private JLabel lblPontuacao;

	//jogador
	private JLabel lblNomeJogador;
	private JProgressBar barraJogador;
	private JLabel lblRecurso;

	//inimigo
	private JLabel lblNomeInimigo;
	private JProgressBar barraInimigo;
	private JLabel lblInfoInimigo;

	//log do combate
	private JTextArea areaLog;

	//botoes
	private JButton btnAtacar;
	private JButton btnDefender;
	private JButton btnHabilidade;
	private JButton btnPocao;
	private JButton btnMenu;

	public TelaCombate(Partida partida) {
		this.partida = partida;
		this.inimigoEmTela = partida.getInimigoAtual();

		//configuracoes da janela
		setTitle("Combate");
		setSize(650, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		criarTopo();
		criarLog();
		criarBotoes();

		Personagem jogador = partida.getJogador();
		areaLog.append("Combate iniciado!\n");
		areaLog.append(jogador.getNome() + " enfrenta " + inimigoEmTela.getNome() + ".\n\n");

		atualizarTela();
	}

	//parte de cima -> pontuacao, jogador e inimigo
	private void criarTopo() {
		lblPontuacao = new JLabel("", JLabel.CENTER);

		//painel do jogador
		lblNomeJogador = new JLabel(partida.getJogador().getNome() + " ("
				+ partida.getJogador().getClass().getSimpleName() + ")", JLabel.CENTER);
		barraJogador = new JProgressBar();
		barraJogador.setStringPainted(true);
		barraJogador.setForeground(new Color(40, 160, 60));
		lblRecurso = new JLabel("", JLabel.CENTER);

		JPanel painelJogador = new JPanel(new GridLayout(3, 1, 5, 5));
		painelJogador.add(lblNomeJogador);
		painelJogador.add(barraJogador);
		painelJogador.add(lblRecurso);

		//painel do inimigo
		lblNomeInimigo = new JLabel("", JLabel.CENTER);
		barraInimigo = new JProgressBar();
		barraInimigo.setStringPainted(true);
		barraInimigo.setForeground(new Color(190, 40, 40));
		lblInfoInimigo = new JLabel("", JLabel.CENTER);

		JPanel painelInimigo = new JPanel(new GridLayout(3, 1, 5, 5));
		painelInimigo.add(lblNomeInimigo);
		painelInimigo.add(barraInimigo);
		painelInimigo.add(lblInfoInimigo);

		//junta os dois lado a lado
		JPanel painelLutadores = new JPanel(new GridLayout(1, 2, 20, 0));
		painelLutadores.add(painelJogador);
		painelLutadores.add(painelInimigo);

		JPanel painelTopo = new JPanel(new BorderLayout(0, 10));
		painelTopo.add(lblPontuacao, BorderLayout.NORTH);
		painelTopo.add(painelLutadores, BorderLayout.CENTER);

		add(painelTopo, BorderLayout.NORTH);
	}

	//centro -> area de texto com o que aconteceu no combate
	private void criarLog() {
		areaLog = new JTextArea();
		areaLog.setEditable(false);
		areaLog.setLineWrap(true);
		areaLog.setWrapStyleWord(true);

		JScrollPane scroll = new JScrollPane(areaLog); // barra de rolagem
		add(scroll, BorderLayout.CENTER);
	}

	//parte de baixo -> botoes de acao
	private void criarBotoes() {
		btnAtacar = new JButton("Atacar");
		btnDefender = new JButton("Defender");
		btnHabilidade = new JButton("Habilidade");
		btnPocao = new JButton("Poção");
		btnMenu = new JButton("Menu");
		btnMenu.setEnabled(false); //so libera quando a partida terminar

		JPanel painelBotoes = new JPanel(new GridLayout(1, 5, 5, 5));
		painelBotoes.add(btnAtacar);
		painelBotoes.add(btnDefender);
		painelBotoes.add(btnHabilidade);
		painelBotoes.add(btnPocao);
		painelBotoes.add(btnMenu);
		add(painelBotoes, BorderLayout.SOUTH);

		btnAtacar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atacar();
			}
		});

		btnDefender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				defender();
			}
		});

		btnHabilidade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				usarHabilidade();
			}
		});

		btnPocao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				usarPocao();
			}
		});

		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltarAoMenu();
			}
		});
	}

	//acoes do jogador 
	private void atacar() {
		try {
			String log = partida.getCombate().atacar();
			depoisDaAcao(log);
		} catch (AcaoInvalidaException e) {
			mostrarAviso(e.getMessage());
		}
	}

	private void defender() {
		try {
			String log = partida.getCombate().defender();
			depoisDaAcao(log);
		} catch (AcaoInvalidaException e) {
			mostrarAviso(e.getMessage());
		}
	}

	private void usarHabilidade() {
		try {
			String log = partida.getCombate().usarHabilidade();
			depoisDaAcao(log);
		} catch (AcaoInvalidaException e) {
			mostrarAviso(e.getMessage());
		}
	}

	private void usarPocao() {
		try {
			String log = partida.getCombate().usarPocao();
			depoisDaAcao(log);
		} catch (AcaoInvalidaException e) {
			mostrarAviso(e.getMessage());
		}
	}

	//roda depois de toda acao e confere se o combate acabou atualiza a tela
	//se a partida terminou encerra o jogo
	private void depoisDaAcao(String logDoCombate) {
		String logFim = partida.verificarFimCombate();

		areaLog.append(logDoCombate + logFim + "\n");
		areaLog.setCaretPosition(areaLog.getDocument().getLength()); // rola até o fim

		// se veio um novo inimigo, passa a mostrar ele
		if (partida.getInimigoAtual() != null) {
			inimigoEmTela = partida.getInimigoAtual();
		}

		atualizarTela();

		//depois que a partida acaba get combate é null
		//nunca mais chama os botoes de acao
		if (partida.partidaFinalizada()) {
			fimDeJogo();
		}
	}

	//atualização da tela
	private void atualizarTela() {
		Personagem jogador = partida.getJogador();

		lblPontuacao.setText("Pontuação: " + partida.getPontuacao()
				+ "   |   Inimigos derrotados: " + partida.getInimigosDerrotados());

		//jogador
		atualizarBarra(barraJogador, jogador.getVidaAtual(), jogador.getVidaMaxima());
		lblRecurso.setText(textoRecurso(jogador));

		//inimigo
		lblNomeInimigo.setText(inimigoEmTela.getNome());
		atualizarBarra(barraInimigo, inimigoEmTela.getVidaAtual(), inimigoEmTela.getVidaMaxima());
		lblInfoInimigo.setText("Ataque: " + inimigoEmTela.getAtaqueBase()
				+ " | Defesa: " + inimigoEmTela.getDefesa()
				+ " | " + inimigoEmTela.getEspecialidade());

		//botoes
		btnHabilidade.setEnabled(jogador.podeUsarHabilidade());

		int pocoes = partida.getInventario().quantidadeItens();
		btnPocao.setText("Poção (" + pocoes + ")");
		btnPocao.setEnabled(!partida.getInventario().inventarioVazio());
	}

	private void atualizarBarra(JProgressBar barra, int atual, int maximo) {
		barra.setMaximum(maximo);
		barra.setValue(atual);
		barra.setString(atual + " / " + maximo);
	}

	//cada classe tem um recurso diferente (energia, mana, precisão).
	//instanceof é para descobrir qual é e chamar o getter certo.
	private String textoRecurso(Personagem jogador) {
		if (jogador instanceof Guerreiro) {
			Guerreiro g = (Guerreiro) jogador;
			return "Energia: " + g.getEnergiaAtual() + "/" + g.getEnergiaMaxima();
		} else if (jogador instanceof Mago) {
			Mago m = (Mago) jogador;
			return "Mana: " + m.getManaAtual() + "/" + m.getManaMaxima();
		} else if (jogador instanceof Arqueiro) {
			Arqueiro a = (Arqueiro) jogador;
			return "Precisão: " + a.getPrecisaoAtual() + "/" + a.getPrecisaoMaxima();
		}
		return "";
	}

	//fim de jogo
	private void fimDeJogo() {
		btnAtacar.setEnabled(false);
		btnDefender.setEnabled(false);
		btnHabilidade.setEnabled(false);
		btnPocao.setEnabled(false);
		btnMenu.setEnabled(true);

		String mensagem;
		if (partida.partidaVencida()) {
			mensagem = "VITÓRIA! Você derrotou todos os inimigos.";
		} else {
			mensagem = "DERROTA! Você foi derrotado.";
		}
		mensagem += "\nPontuação final: " + partida.getPontuacao();

		JOptionPane.showMessageDialog(this, mensagem, "Fim de jogo", JOptionPane.INFORMATION_MESSAGE);
	}

	private void mostrarAviso(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem, "Ação inválida", JOptionPane.WARNING_MESSAGE);
	}

	private void voltarAoMenu() {
		MenuPrincipal menu = new MenuPrincipal();
		menu.setVisible(true);
		dispose();
	}
}
