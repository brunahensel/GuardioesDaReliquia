package personagens;

import excecoes.PersonagemInvalidoException;

public abstract class Personagem {

	private int vidaMaxima;
	private int ataqueBase;
	private int defesa;
	private String especialidade;
	private final String nome;
	private int vidaAtual;
	private boolean defendendo = false;
	
	
	public Personagem(int vidaMaxima, int ataqueBase, int defesa, String especialidade, String nome) throws PersonagemInvalidoException {
		this.nome = nome;
		setVidaMaxima(vidaMaxima);
		setAtaqueBase(ataqueBase);
		setDefesa(defesa);
		setEspecialidade(especialidade);
		setVidaAtual(vidaAtual);
	
	}
	
	//gets
	public String getNome() {
			return nome;
	}

	public int getVidaMaxima() {
			return vidaMaxima;
	}

	public int getVidaAtual() {
			return vidaAtual;
	}

	public int getAtaqueBase() {
			return ataqueBase;
	}

	public int getDefesa() {
			return defesa;
	}

	public String getEspecialidade() {
			return especialidade;
	}
	
	public void defender() {
		defendendo = true;
	}


	//sets
	public void setVidaMaxima(int vidaMaxima) throws PersonagemInvalidoException {
		if (vidaMaxima <= 0) {
				throw new PersonagemInvalidoException("A vida máxima deve ser maior que zero.");
		}
		this.vidaMaxima = vidaMaxima;
		if (vidaAtual > vidaMaxima) {
				vidaAtual = vidaMaxima;
		}
	}

	public void setVidaAtual(int vidaAtual) throws PersonagemInvalidoException {
		if (vidaAtual < 0 || vidaAtual > vidaMaxima) {
			throw new PersonagemInvalidoException("A vida atual deve estar entre 0 e a vida máxima.");
		}
		this.vidaAtual = vidaAtual;
	}

	public void setAtaqueBase(int ataqueBase) throws PersonagemInvalidoException {
		if (ataqueBase < 0) {
			throw new PersonagemInvalidoException("O ataque base não pode ser negativo.");
		}
		this.ataqueBase = ataqueBase;
	}

	public void setDefesa(int defesa) throws PersonagemInvalidoException {
		if (defesa < 0) {
			throw new PersonagemInvalidoException("A defesa não pode ser negativa.");
		}
		this.defesa = defesa;
	}

	public void setEspecialidade(String especialidade) throws PersonagemInvalidoException {
		if (especialidade == null || especialidade.trim().isEmpty()) {
			throw new PersonagemInvalidoException("A especialidade não pode ser vazia.");
		}
		this.especialidade = especialidade;
	}

	public boolean isDefendendo() {
		return defendendo;
	}
	
	
	//metodos
	public boolean estaVivo() {
		return vidaAtual > 0;
	}
	
	public int calcularDano(int defesaAlvo) {
		return Math.max(0, ataqueBase - defesaAlvo);
	}
	
	public int receberDano(int dano) {
		if (dano < 0) {
			dano = 0;
		}
		if (defendendo) {
			dano = dano / 2;
			defendendo = false;
		}
		int vidaAntes = vidaAtual;
		vidaAtual = Math.max(0, vidaAtual - dano);
		return vidaAntes - vidaAtual;
	}
	
	public void curar(int quantidade) {
		Math.min(vidaMaxima, vidaAtual + quantidade);
	}
	
	//public void defender()                    // liga defesa
	//public abstract int habilidadeEspecial(int defesaAdversario) throws AcaoInvalidaException
	    // cada subclasse valida e consome o recurso ANTES de mexer em qualquer coisa
	    // e devolve o dano; lança AcaoInvalidaException se o recurso for insuficiente
}
