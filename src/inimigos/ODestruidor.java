package inimigos;

import excecoes.InimigoInvalidoException;

public class ODestruidor extends Inimigo {
	
	private int forcaMax;
	private int forcaAtual;

	public ODestruidor() throws InimigoInvalidoException {
		super(120, 14, 12, "Escudo das Trevas", 120, "O Destruidor");
		this.forcaMax = 10;
		this.forcaAtual = 0;
	}

	//gets
	public int getForcaMax() {
		return forcaMax;
	}

	public int getForcaAtual() {
		return forcaAtual;
	}
	
	//sets
	public void setForcaMax(int forcaMax) throws InimigoInvalidoException {
		if (forcaMax <= 0) {
			throw new InimigoInvalidoException("A força máxima deve ser maior que zero.");
		}
		this.forcaMax = forcaMax;
		if (forcaAtual > forcaMax) {
			forcaAtual = forcaMax;
		}
	}

	public void setForcaAtual(int forcaAtual) throws InimigoInvalidoException {
		if (forcaAtual < 0 || forcaAtual > forcaMax) {
			throw new InimigoInvalidoException("A força atual deve estar entre 0 e a força máxima.");
		}
		this.forcaAtual = forcaAtual;
	}
	
	public boolean podeUsarHabilidade() {
		return forcaAtual == forcaMax;
	}

	@Override
	public int habilidadeEspecial(int defesaAdversario) {
		int dano = calcularDanoEspecial(defesaAdversario);
		forcaAtual = 0; // gasta a força ao usar o especial
		return dano;
	}

	//Math.min usado para impedir de passar da força máxima
	public void ganharForca(int quantidade) {
		forcaAtual = Math.min(forcaMax, forcaAtual + quantidade);
	}
	
	
	@Override
	public String toString() {
		return super.toString() + " | Força: " + forcaAtual + "/" + forcaMax;
	}
	
	@Override
	public boolean deveUsarEspecial() {
		return podeUsarHabilidade();
	}
	
	public void aposAcao() {
		ganharForca(3);
	}
}
