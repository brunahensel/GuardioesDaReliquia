package personagens;

public class Guerreiro extends Personagem {

	private int energiaMaxima;
	private int energiaAtual;
	
	// Construtor 
	
	public Guerreiro(String nome) {
		
		super(nome, 100, 18, 15,
				"Combate corpo a corpo, força e resistência! ");
		
		this.energiaMaxima = 10;
		this.energiaAtual = 0;

	}
	
	//getters
	
	public int getEnergiaMaxima() {
		return energiaMaxima;
		
	}
	
	public int getEnergiaAtual() {
		return energiaAtual;
		
	}
	
	// Ataque normal(Ataque Base);
	
	@Override
	
	public int atacar() {
		if(estaDerrotado()){
			throw new IllegalArgumentException("Personagem derrotado não pode atacar!");
			
		}
		
		energiaAtual = energiaAtual + 2;
		
		if(energiaAtual > energiaMaxima) {
			energiaAtual = energiaMaxima;
			
		}
		
		return super.atacar();
		
	}
	
	// Habilidade especial
	
    @Override
    
    public int habilidadeEspecial() {
        if (estaDerrotado()) {
            throw new IllegalStateException(
                    "Personagem derrotado não pode utilizar habilidades!");
        }

        if (energiaAtual < 5) {
            throw new IllegalStateException("Energia insuficiente!");
        }

        energiaAtual = energiaAtual - 5;

        return (int) (getAtaqueBase() * 1.5);
    }
}
	

