package inimigos;

import excecoes.InimigoInvalidoException;

public abstract class Inimigo {
	
	protected int vidaMaxima;
	protected int ataqueBase;
	protected int defesa;
	protected String especialidade;
	private final String nome; //sem setter
	protected int vidaAtual;

	public Inimigo(int vidaMaxima, int ataqueBase, int defesa, String especialidade, int vidaAtual, String nome) throws InimigoInvalidoException {
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

	//sets
	public void setVidaMaxima(int vidaMaxima) throws InimigoInvalidoException {
		if (vidaMaxima <= 0) {
			throw new InimigoInvalidoException("A vida máxima deve ser maior que zero.");
		}
		this.vidaMaxima = vidaMaxima;
		if (vidaAtual > vidaMaxima) {
			vidaAtual = vidaMaxima;
		}
	}

	public void setVidaAtual(int vidaAtual) throws InimigoInvalidoException {
		if (vidaAtual < 0 || vidaAtual > vidaMaxima) {
			throw new InimigoInvalidoException("A vida atual deve estar entre 0 e a vida máxima.");
		}
		this.vidaAtual = vidaAtual;
	}

	public void setAtaqueBase(int ataqueBase) throws InimigoInvalidoException {
		if (ataqueBase < 0) {
			throw new InimigoInvalidoException("O ataque base não pode ser negativo.");
		}
		this.ataqueBase = ataqueBase;
	}

	public void setDefesa(int defesa) throws InimigoInvalidoException {
		if (defesa < 0) {
			throw new InimigoInvalidoException("A defesa não pode ser negativa.");
		}
		this.defesa = defesa;
	}

	public void setEspecialidade(String especialidade) throws InimigoInvalidoException {
		if (especialidade == null || especialidade.trim().isEmpty()) {
			throw new InimigoInvalidoException("A especialidade não pode ser vazia.");
		}
		this.especialidade = especialidade;
	}
	
	//metodos
	//Math.max devolve o maior numero, proibindo de ficar abaixo de zero
	public int calcularDanoEspecial(int defesaAdversario) {
		return Math.max(0, ataqueBase * 2 - defesaAdversario);
	}
	
	public void receberDano(int dano) {
		vidaAtual = Math.max(0, vidaAtual - dano); 
	}
	
	public boolean inimigoVivo() {
		return vidaAtual > 0;
	}
	
	public int calcularDano(int defesaAdversario) {
		return Math.max(0, ataqueBase - defesaAdversario);
	}
	
	public abstract int habilidadeEspecial(int defesaAdversario);
	
	@Override
	public String toString() {
		return nome + " | Vida: " + vidaAtual + "/" + vidaMaxima
				+ " | Ataque: " + ataqueBase + " | Defesa: " + defesa
				+ " | Habilidade: " + especialidade;
	}
}
