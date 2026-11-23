package inimigos;

import excecoes.InimigoInvalidoException;


public class Gruk extends Inimigo {
	
	private int forcaMax;
	private int forcaAtual;


	public Gruk() throws InimigoInvalidoException {
		super(80, 16, 5, "Golpe Brutal", 80, "Gruk");
		this.forcaMax = 10;
		this.forcaAtual = 0; //força inicial, altera conforme os ataques.
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

	public void setForcaAtual (int forcaAtual) throws InimigoInvalidoException {
		if (forcaAtual < 0 || forcaAtual > forcaMax) {
			throw new InimigoInvalidoException("A força atual deve estar entre 0 e a força máxima.");
		}
		this.forcaAtual = forcaAtual;
	}

	@Override
	public int habilidadeEspecial(int defesaAdversario) {
		return calcularDanoEspecial(defesaAdversario);
	}
	
	public void ganharForca(int quantidade) {
		forcaAtual = Math.min(forcaMax, forcaAtual + quantidade); 
	}
	
	public boolean usarHabilidadeEspecial() {
		return forcaAtual == forcaMax;
	}
	
	public void zerarForca() {
		if (forcaAtual == forcaMax) {
			forcaAtual = 0; 
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + " | Força: " + forcaAtual + "/" + forcaMax;
	}
}
