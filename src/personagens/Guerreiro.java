package personagens;

import excecoes.AcaoInvalidaException;
import excecoes.PersonagemInvalidoException;

/*Ganha energia a cada ataque basico e gasta
 * energia para usar a habilidade especial (dano 1,5 * ataque base)*/

public class Guerreiro extends Personagem {

	private static final int ENERGIA_HABILIDADE = 5;
	private int energiaMaxima;
	private int energiaAtual;

	
	public Guerreiro(String nome) throws PersonagemInvalidoException {
		super(nome, 100, 18, 15, "Combate corpo a corpo, força e resistência! ");

		this.energiaMaxima = 10;
		this.energiaAtual = 0;

	}


	public int getEnergiaMaxima() {
		return energiaMaxima;

	}

	public int getEnergiaAtual() {
		return energiaAtual;

	}

	@Override
	public int atacar() {
		if (estaDerrotado()) {
			throw new IllegalStateException("Personagem derrotado não pode atacar!");

		}

		energiaAtual = energiaAtual + 2;

		if (energiaAtual > energiaMaxima) {
			energiaAtual = energiaMaxima;

		}

		return super.atacar();

	}

	@Override
	public boolean podeUsarHabilidade() {
		return !estaDerrotado() && energiaAtual >= ENERGIA_HABILIDADE;
	}

	@Override
	public int habilidadeEspecial() throws AcaoInvalidaException {
		if (estaDerrotado()) {
			throw new AcaoInvalidaException (
					"Personagem derrotado não pode utilizar habilidades!");
		}

		if (energiaAtual < ENERGIA_HABILIDADE) {
			throw new AcaoInvalidaException("Energia insuficiente!");
		}

		energiaAtual = energiaAtual - ENERGIA_HABILIDADE;

		return (int) (getAtaqueBase() * 1.5);
	}
}

