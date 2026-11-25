package personagens;

import excecoes.AcaoInvalidaException;
import excecoes.PersonagemInvalidoException;

/*
  Mago o maior ataque, pouca vida e defesa, gaanha mana a cada ataque
  básico e gasta mana para usar a habilidade especial (dano 2 * ataque base).
 */ 

public class Mago extends Personagem {

	private static final int MANA_HABILIDADE = 5;

	private int manaMaxima;
	private int manaAtual;

	public Mago(String nome) throws PersonagemInvalidoException {
		super(nome, 70, 25, 8, "Magia, inteligência e ataques elementais! ");

		this.manaMaxima = 10;
		this.manaAtual = 0;

	}

	//Getters

	public int getManaMaxima() {
		return manaMaxima;

	}

	public int getManaAtual() {
		return manaAtual;

	}

	// Ataque normal(ataque base):

	@Override
	public int atacar() {
		if (estaDerrotado()) {
			throw new IllegalStateException("Personagem derrotado não pode atacar! ");

		}

		manaAtual = manaAtual + 2;

		if (manaAtual > manaMaxima) {
			manaAtual = manaMaxima;

		}

		return super.atacar();

	}

	//habilidade especial

	@Override
	public boolean podeUsarHabilidade() {
		return !estaDerrotado() && manaAtual >= MANA_HABILIDADE;
	}


	@Override
	public int habilidadeEspecial() throws AcaoInvalidaException {
		if (estaDerrotado()) {
			throw new AcaoInvalidaException ("Personagem derrotado não pode utilizar habilidades! ");

		}

		if (manaAtual < MANA_HABILIDADE) {
			throw new AcaoInvalidaException ("Mana insuficiente! ");

		}

		manaAtual = manaAtual - MANA_HABILIDADE;

		return (int) (getAtaqueBase() * 2.0);

	}

}