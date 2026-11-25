package dominio;

import java.util.Objects;

import excecoes.AcaoInvalidaException;
import inimigos.Inimigo;
import personagens.Personagem;

public class Combate {

	private final Personagem jogador;
	private final Inimigo inimigo;
	private final Inventario inventario;
	
	public Combate(Personagem jogador, Inimigo inimigo, Inventario inventario) {
		this.jogador = Objects.requireNonNull(jogador, "O jogador não pode ser nulo.");
		this.inimigo = Objects.requireNonNull(inimigo, "O inimigo não pode ser nulo.");
		this.inventario = Objects.requireNonNull(inventario, "O inventário não pode ser nulo.");
	}
	
	//inimigo desse encontro
	public Inimigo getInimigo() {
		return inimigo;
	}

	public String atacar() throws AcaoInvalidaException { //coisa nova "log"
		validarCombate();
		int dano = jogador.calcularDano(inimigo.getDefesa());
		inimigo.receberDano(dano);
		String log = jogador.getNome() + " atacou" + inimigo.getNome() +
				" e causou " + dano + " de dano.\n";
		log += executarTurnoInimigo();
		return log;
	}
	
	public String defender() {
		//TODO
	}
	
	public String usarHabilidade() {
		//TODO
	}
	
	public String usarPocao() {
		//TODO
	}

	private void validarCombate() throws AcaoInvalidaException {
		if(!jogador.estaVivo()) {
			throw new AcaoInvalidaException("Você foi derrotado! Não pode agir.");
		}
		if(!inimigo.inimigoVivo()) {
			throw new AcaoInvalidaException("O inimigo já foi derrotado.");
		}
	}

	private String executarTurnoInimigo() {
		if(!inimigo.inimigoVivo()) {
			return inimigo.getNome() + " foi derrotado!\n";
		}
		boolean especial = inimigo.deveUsarEspecial();
		int defesaJogador = jogador.getDefesa();
		int dano;
		if (especial) {
			dano = inimigo.habilidadeEspecial(defesaJogador);
		} else {
			dano = inimigo.calcularDano(defesaJogador);
		}
		int danoSofrido = jogador.receberDano(dano);
		String log;
		if(especial) {
			log = inimigo.getNome() + " usou " + inimigo.getEspecialidade() + " e causou " + danoSofrido
					+ " de dano em " + jogador.getNome() + ".\n";
		} else {
			log = inimigo.getNome() + " atacou e causou " + danoSofrido + " de dano em " 
					+ jogador.getNome() + ".\n";
	}
		inimigo.aposAcao(especial);
		return log;
	}
}
