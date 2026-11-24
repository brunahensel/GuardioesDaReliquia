package dominio;

import excecoes.ItemInvalidoException;

public class Item {
	
	//nomes padroes
	public static final String nomePocao = "Poção de Cura";
	public static final int curaPocao = 40;
	
	private final String nome;
	private final int cura;
	
	public Item(String nome, int cura) throws ItemInvalidoException {
				if (nome == null || nome.isEmpty()) {
					throw new ItemInvalidoException("O nome do item não pode ser vazio.");
				}
				if (cura < 0) {
					throw new ItemInvalidoException("A cura não pode ser negativa.");
				}
				this.nome = nome;
				this.cura = cura;
	}
	
	//pra nao precisar instanciar uma poção nova toda vez:
	public static Item pocao() {
		try {
			return new Item(nomePocao, curaPocao);
		} catch (ItemInvalidoException e) {
			throw new IllegalStateException("Poções inválidas.", e);
		}
	}

	//gets 
	public String getNome() {
		return nome;
	}

	public int getCura() {
		return cura;
	}

	@Override
	public String toString() {
		return nome + " (+" + cura + " de vida)";
	}
}

