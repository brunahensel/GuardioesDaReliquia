
package personagens;

import excecoes.AcaoInvalidaException;
import excecoes.PersonagemInvalidoException;

public abstract class Personagem {

	private final String nome;
    private int vidaMaxima;
    private int vidaAtual;
    private int ataqueBase;
    private int defesa;
    private String especialidade;
    private boolean defendendo;

    public Personagem(String nome, int vidaMaxima, int ataqueBase,
            int defesa, String especialidade) throws PersonagemInvalidoException {
    	
        this.nome = nome;
        setVidaMaxima(vidaMaxima);
        setAtaqueBase(ataqueBase);
        setDefesa(defesa);
        setEspecialidade(especialidade);

        this.vidaAtual = vidaMaxima;
        this.defendendo = false;
    }
    
    //gets
    public String getNome() {
    	return nome;
    }
    
    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public int getAtaqueBase() {
        return ataqueBase;
    }

    public int getDefesa() {
        return defesa;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public boolean isDefendendo() {
        return defendendo;
    }

    //sets
    public void setVidaMaxima(int vidaMaxima) throws PersonagemInvalidoException {
        if (vidaMaxima <= 0) {
            throw new PersonagemInvalidoException("A vida máxima não pode ser menor ou igual a zero");
        }

        if (vidaMaxima < vidaAtual) {
            throw new PersonagemInvalidoException (
                    "Vida máxima menor que a vida atual!");
        }

        this.vidaMaxima = vidaMaxima;
    }

    public void setVidaAtual(int vidaAtual) throws PersonagemInvalidoException {
        if (vidaAtual > vidaMaxima) {
            throw new PersonagemInvalidoException("A vida atual não pode ser maior que a vida máxima");
        }

        this.vidaAtual = vidaAtual;
    }

    public void setAtaqueBase(int ataqueBase) throws PersonagemInvalidoException {
        if (ataqueBase < 0) {
            throw new PersonagemInvalidoException ("Ataque inválido!");
        }

        this.ataqueBase = ataqueBase;
    }

    public void setDefesa(int defesa) throws PersonagemInvalidoException {
        if (defesa < 0) {
            throw new PersonagemInvalidoException ("Defesa inválida!");
        }

        this.defesa = defesa;
    }

    public void setEspecialidade(String especialidade) throws PersonagemInvalidoException {
        if (especialidade == null || especialidade.isBlank()) {
            throw new PersonagemInvalidoException("Especialidade inválida!");
        }

        this.especialidade = especialidade;
    }

    
   
    public int atacar() {
        return ataqueBase;
    }

    public void defender() throws AcaoInvalidaException {

        if (estaDerrotado()) {
            throw new AcaoInvalidaException ("Personagem derrotado não pode defender!");
        }

        defendendo = true;
    }

    // Receber dano
    public void receberDano(int dano) {

        if (dano < 0) {
            throw new IllegalArgumentException("Dano inválido!");
        }

        int danoRecebido = dano - defesa;

        if (danoRecebido < 0) {
            danoRecebido = 0;
        }

        if (defendendo) {
            danoRecebido = danoRecebido / 2;
            defendendo = false;
        }

        vidaAtual = vidaAtual - danoRecebido;

        if (vidaAtual < 0) {
            vidaAtual = 0;
        }
    }

    
    // Recuperar vida
    
    public void recuperarVida(int cura) {

        if (cura < 0) {
            throw new IllegalArgumentException("Cura inválida!");
        }

        vidaAtual = vidaAtual + cura;

        if (vidaAtual > vidaMaxima) {
            vidaAtual = vidaMaxima;
        }
    }

    
    
    public boolean estaDerrotado() {
        return vidaAtual == 0;
    }

    
    // Método abstrato para habilidade especial
    public abstract boolean podeUsarHabilidade();
    public abstract int habilidadeEspecial() throws AcaoInvalidaException;

}


