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

	public String atacar() throws AcaoInvalidaException {
		validarCombate();
		int dano = Math.max(0, jogador.atacar() - inimigo.getDefesa());
		inimigo.receberDano(dano);
		String log = jogador.getNome() + " atacou " + inimigo.getNome() + " e causou " + dano + " de dano.\n";
		log += executarTurnoInimigo();
		return log;
	}
	
	public String defender() throws AcaoInvalidaException {
		validarCombate();
		jogador.defender();
		String log = jogador.getNome() + " assumiu postura de defesa.\n";
		log += executarTurnoInimigo();
		return log;
	}
	
	public String usarHabilidade() throws AcaoInvalidaException {
		validarCombate();
		if (!jogador.podeUsarHabilidade()) {
			throw new AcaoInvalidaException("Recurso insuficiente para usar a habilidade especial.");
		}
		int dano = Math.max(0, jogador.habilidadeEspecial() - inimigo.getDefesa());
		inimigo.receberDano(dano);
		String log = jogador.getNome() + " usou a habilidade especial em "
				+ inimigo.getNome() + " e causou " + dano + " de dano.\n";
		log += executarTurnoInimigo();
		return log;
	}
	
	public String usarPocao() throws AcaoInvalidaException {
		validarCombate();
		int vidaAntes = jogador.getVidaAtual();
		Item pocao = inventario.usarPocao(jogador);
		int curado = jogador.getVidaAtual() - vidaAntes;
		String log = jogador.getNome() + " usou " + pocao.getNome() + " e recuperou " + curado + " de vida.\n";
		log += executarTurnoInimigo();
		return log;
	}

	private void validarCombate() throws AcaoInvalidaException {
		if(jogador.estaDerrotado()) {
			throw new AcaoInvalidaException("Você foi derrotado! Não pode agir.");
		}
		if(!inimigo.inimigoVivo()) {
			throw new AcaoInvalidaException("O inimigo já foi derrotado.");
		}
	}

	private String executarTurnoInimigo() {
		    if (!inimigo.inimigoVivo()) {
		        return "";
		    }   
		boolean especial = inimigo.deveUsarEspecial();
		int dano;
		if (especial) {
			dano = inimigo.habilidadeEspecial(0);
		} else {
			dano = inimigo.getAtaqueBase();
		}
		int vidaAntes = jogador.getVidaAtual();
		jogador.receberDano(dano);
		int danoSofrido = vidaAntes - jogador.getVidaAtual();
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
