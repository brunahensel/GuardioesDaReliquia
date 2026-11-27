package inimigos;

import excecoes.InimigoInvalidoException;

public class Malakar extends Inimigo {
	
	private int defesaMax;
	private int defesaAtual;
	private boolean especialUsado;

	public Malakar() throws InimigoInvalidoException {
		super(140, 20, 14, "Explosão Arcana", 140, "Malakar");
		this.setDefesaMax(18);
		this.setDefesaAtual(0);
		this.especialUsado = false;
	}

	//gets
	public int getDefesaMax() {
		return defesaMax;
	}

	public int getDefesaAtual() {
		return defesaAtual;
	}
	
	//sets
	public void setDefesaMax(int defesaMax) throws InimigoInvalidoException {
		if (defesaMax <= 0) {
			throw new InimigoInvalidoException("A defesa máxima deve ser maior que zero.");
		}
		this.defesaMax = defesaMax;
		if (defesaAtual > defesaMax) {
			defesaAtual = defesaMax;
		}
	}

	public void setDefesaAtual(int defesaAtual) throws InimigoInvalidoException {
		if (defesaAtual < 0 || defesaAtual > defesaMax) {
			throw new InimigoInvalidoException("A defesa atual deve estar entre 0 e a defesa máxima.");
		}
		this.defesaAtual = defesaAtual;
	}

	@Override
	public int habilidadeEspecial(int defesaAdversario) {
		return calcularDanoEspecial(defesaAdversario);
	}

	@Override
	public String toString() {
		return super.toString() + " | Defesa atual: " + defesaAtual + "/" + defesaMax;
	}
	
	@Override
	public boolean deveUsarEspecial() {
	    if (vidaAtual < vidaMaxima / 2 && !especialUsado) {
	        especialUsado = true;
	        return true;
	    }
	    return false;
	}
}
