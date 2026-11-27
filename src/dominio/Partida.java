package dominio;

import java.util.ArrayList;                         
import java.util.List;
import java.util.Objects;

import excecoes.InimigoInvalidoException;
import inimigos.Gruk;
import inimigos.Inimigo;
import inimigos.Malakar;
import inimigos.ODestruidor;
import personagens.Personagem;

public class Partida {

	private final Personagem jogador;
	private final Inventario inventario;
	private final List<Inimigo> inimigos;

	private int indiceInimigoAtual;
	private int pontuacao;
	private Combate combate;

	public Partida(Personagem jogador) throws InimigoInvalidoException {

		this.jogador = Objects.requireNonNull(jogador, "O jogador não pode ser nulo.");

		this.inventario = new Inventario();
		this.inimigos = new ArrayList<Inimigo>();

		inimigos.add(new Gruk());
		inimigos.add(new Malakar());
		inimigos.add(new ODestruidor());

		this.indiceInimigoAtual = 0;
		this.pontuacao = 0;

		this.combate = new Combate(jogador, inimigos.get(indiceInimigoAtual), inventario);
	}

	// Gets

	public Personagem getJogador() {
		return jogador;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public Inimigo getIndiceInimigoAtual() {
		if (combate == null) {
			return null;
		}

		return combate.getInimigo();
	}

	public Combate getCombate() {
		return combate;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public int getInimigosDerrotados() {
		return indiceInimigoAtual;
	}

	// Verifica estado da partida

	public boolean partidaFinalizada() {
		return jogador.estaDerrotado() || indiceInimigoAtual >= inimigos.size();
	}

	public boolean partidaVencida() {
		return indiceInimigoAtual >= inimigos.size() && !jogador.estaDerrotado();
	}

	// Calcula pontuação de cada inimigo

	private int calcularPontosInimigo(int indice) {

		if (indice == 0) {
			return 100;
		} else if (indice == 1) {
			return 150;
		} else {
			return 200;
		}
	}

	// Verifica se o combate terminou

	public String verificarFimCombate() {

		if (jogador.estaDerrotado()) {
			combate = null;
			return "Você foi derrotado! Fim de partida.\n";
		}

		if (combate == null || combate.getInimigo().inimigoVivo()) {
			return "";
		}

		pontuacao += calcularPontosInimigo(indiceInimigoAtual);

		pontuacao += (jogador.getVidaAtual() / 10) * 10;

		String log = combate.getInimigo().getNome() + " foi derrotado!\n";

		indiceInimigoAtual++;

		if (indiceInimigoAtual < inimigos.size()) {

			combate = new Combate(jogador, inimigos.get(indiceInimigoAtual), inventario);

			log += "Próximo inimigo: " + combate.getInimigo().getNome() + "\n";

		} else {

			combate = null;

			log += "Parabéns! Você venceu todos os inimigos!\n";
		}

		return log;
	}
}
