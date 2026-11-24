package dominio;

import java.util.ArrayList;

public class Inventario {
	
	private ArrayList<Item> itens;

	public Inventario() {
		
		itens = new ArrayList<Item>();

	} // cria uma lista vazia sempre que um inventário é criado 
	
	public void adicionarItem(Item item) {
		if(item == null) {
			throw new IllegalArgumentException("O item não pode ser nulo!");
			
		} // recebe um objeto "item" e se ele passar pela validação é add a lista 
		
		itens.add(item); // se for válido o item, isso add os "itens" na lista "Item"
		
	}
	
	 public void removerItem(Item item) {
	        if (item == null || !itens.contains(item)) {
	            throw new IllegalArgumentException("Item não encontrado no inventário!");
	        } // verifica se aquele item que queremos remover está na lista, se n estiver, entra na excessão 

	        itens.remove(item);
	        
	    }
	 
	 public ArrayList<Item>getItens(){
		 return new ArrayList<Item>(itens);
		 
	 } 
	 // encapsulamento, getItens() retorna uma cópia da lista para permitir 
	 //a consulta dos itens sem deixar que outras classes modifiquem diretamente 
	 //o inventário original.
	 
	 
	
	 public boolean estaVazio() {
		 return itens.isEmpty();
		 
	 } // verifica se não existem itens armazenados
	 
	 public int quantidadeItens() {
		 return itens.size();
		 
	 } // retorna quantos itens extistem na lista usando o size

}
