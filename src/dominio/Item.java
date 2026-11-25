
<<<<<<< HEAD
package dominio;

public class Item {
	
	private String nome;
	private String descricao;
	private int valorCura;

	public Item(String nome, String descricao, int valorCura) {
		 setNome(nome);
		 setDescricao(descricao);
		 setValorCura(valorCura);
		 
	}
	
	public String getNome() {
		return nome;
		
	}
	
	public String getDescricao() {
		return descricao;
		
	}
	
	public int getValorCura() {
		return valorCura;
		
	}
	
	public void setNome(String nome) {
		if(nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do item não pode ser vazio! ");
			
		}
		
		this.nome = nome;
		
	}
	
	public void setDescricao(String descricao) {
		if(descricao == null || descricao.trim().isEmpty()) {
			
		}
		
		this.descricao = descricao;
		
	}
	
	public void setValorCura(int valorCura) {
		if(valorCura <= 0) {
			throw new IllegalArgumentException("O valor de cura deve ser maior que zero! ");
			
		}
		
		this.valorCura = valorCura;
		
	}
	
	@Override
	
	public String toString() {
		return nome + " - " + descricao + " (Cura: " + valorCura + ")";
		
	}

}
=======
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
>>>>>>> refs/remotes/origin/main

