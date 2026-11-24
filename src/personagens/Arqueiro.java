package personagens;

public class Arqueiro extends Personagem {
	
	private int precisaoMaxima;
	private int precisaoAtual;

	public Arqueiro(String nome) {

		super(nome, 80, 22, 10,
				"Combate à distância, agilidade e precisão! ");
		
		this.precisaoMaxima = 10;
		this.precisaoAtual = 0;
		
	}
	
	// Getters
	
	public int getPrecisaoMaxima() {
		return precisaoMaxima;
		
	}
	
	public int getPrecisaoAtual() {
		return precisaoAtual;
		
	}
	
	//Ataque normal (ataque base)
	
	@Override
	
	public int atacar() {
		if(estaDerrotado()) {
			throw new IllegalArgumentException("Personagem derrotado não pode atacar! ");
			
		}
		
		precisaoAtual = precisaoAtual + 2;
		 if(precisaoAtual > precisaoMaxima) {
			 precisaoAtual = precisaoMaxima;
			 
		 }
		 
		 return super.atacar();
		 
	}
	
	//Habilidade especial
	
	@Override 
	
	public int habilidadeEspecial() {
		if(estaDerrotado()) {
			throw new IllegalArgumentException("Personagem derrotado não pode utilizar habilidades! ");
			
		}
		
		if(precisaoAtual < 5) {
			throw new IllegalArgumentException("Precisão insuficiente! ");
			
		}
		
		precisaoAtual = precisaoAtual - 5;
		
		return (int) (getAtaqueBase() * 1.8);
	}

}
