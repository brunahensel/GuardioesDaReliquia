package personagens;

public class Mago extends Personagem {
	
	private int manaMaxima;
	private int manaAtual;

	public Mago(String nome) {
		super(nome, 70, 25, 8,
				"Magiaa, inteligência e ataque elementais! ");
		
		this.manaMaxima = 10;
		this.manaAtual = 0;

	}
	
	//Getters
	
	public int getManaMaxima() {
		return manaMaxima;
		
	}
	
	public int getManaAtual() {
		return manaAtual;
		
	}
	
	// Ataque normal(ataque base):
	
	@Override 
	
	public int atacar() {
		if(estaDerrotado()) {
			throw new IllegalArgumentException("aPersonagem derrotado não pode atacar! ");
			
		}
		
		manaAtual = manaAtual + 2;
		
		if(manaAtual > manaMaxima) {
			manaAtual = manaMaxima;
			
		}
		
		return super.atacar();
		
	}
	
	//habilidade especial
	
	@Override
	
	public int habilidadeEspecial() {
		if(estaDerrotado()) {
			throw new IllegalArgumentException("Personagem derrotado não pode utilizar habilidades! ");
			
		}
		
		if(manaAtual < 5) {
			throw new IllegalArgumentException("Mana insuficiente! ");
			
		}
		
		manaAtual = manaAtual - 5;
		
		return (int) (getAtaqueBase() * 2.0);
		
	}

}
