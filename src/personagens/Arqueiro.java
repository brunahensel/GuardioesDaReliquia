package personagens;

import excecoes.AcaoInvalidaException;
import excecoes.PersonagemInvalidoException;

/*Arqueiro ataque alto e defesa média, ganha precisão a cada ataque básico e
 gasta precisão para usar a habilidade especial (dano 1,8 * ataque base).
 */

public class Arqueiro extends Personagem {

	private static final int ENERGIA_HABILIDADE = 5;

	private int precisaoMaxima;
	private int precisaoAtual;


	public Arqueiro(String nome) throws PersonagemInvalidoException {
		super(nome, 80, 22, 10, "Combate à distância, agilidade e precisão! ");

		this.precisaoMaxima = 10;
		this.precisaoAtual = 0;

	}

	// Getters

	public int getPrecisaoMaxima() {
		return precisaoMaxima;

	}

	public int getPrecisaoAtual() {
		return precisaoAtual;

	}

	//Ataque normal (ataque base)

	
	@Override
	public int atacar() {
		if (estaDerrotado()) {
			throw new IllegalStateException("Personagem derrotado não pode atacar! ");

		}

		precisaoAtual = precisaoAtual + 2;
		if (precisaoAtual > precisaoMaxima) {
			precisaoAtual = precisaoMaxima;

		}

		return super.atacar();

	}

	//Habilidade especial

	@Override
	public boolean podeUsarHabilidade() {
		return !estaDerrotado() && precisaoAtual >= ENERGIA_HABILIDADE;
	}

	
	@Override
	public int habilidadeEspecial() throws AcaoInvalidaException {
		if (estaDerrotado()) {
			throw new AcaoInvalidaException ("Personagem derrotado não pode utilizar habilidades! ");

		}

		if (precisaoAtual < ENERGIA_HABILIDADE) {
			throw new AcaoInvalidaException ("Precisão insuficiente! ");

		}

		precisaoAtual = precisaoAtual - ENERGIA_HABILIDADE;

		return (int) (getAtaqueBase() * 1.8);
	}

}
