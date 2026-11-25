package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import excecoes.AcaoInvalidaException;
import personagens.Personagem;

public class Inventario {

	private final List<Item> itens;
	
	
	
	public Inventario() {
		itens = new ArrayList<Item>();
	}
	
	public void addItem(Item item) {
		Objects.requireNonNull(item, "O item não pode ser nulo."); //lança NullPointerException (NOVO) evita colocar o throws.
		itens.add(item);
	}

	public int quantidadeItens() {
		return itens.size();
	}
	
	public boolean inventarioVazio() {
		return itens.isEmpty();
	}
	
	//vai dar acesso por posição
	public Item getItem(int indice) {
		return itens.get(indice);
	}
	
	public Item usarPocao(Personagem personagem) throws AcaoInvalidaException {
		if(itens.isEmpty()) {
			throw new AcaoInvalidaException("Não há poções no inventário.");
		}
		if(personagem.estaDerrotado()) {
			throw new AcaoInvalidaException("Você foi derrotado! Não pode usar itens.");
		}
		Item pocao = itens.remove(0);
		personagem.recuperarVida(pocao.getCura());
		return pocao;
	}
	
	@Override
	public String toString() {
		return "Inventário: " + itens.size() + " item(ns)";
	}
}
