
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

