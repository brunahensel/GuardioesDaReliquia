
package personagens;

public abstract class Personagem {

    private String nome;
    private int vidaMaxima;
    private int vidaAtual;
    private int ataqueBase;
    private int defesa;
    private String especialidade;
    private boolean defendendo;

    public Personagem(String nome, int vidaMaxima, int ataqueBase,
            int defesa, String especialidade) {

        setNome(nome);
        setVidaMaxima(vidaMaxima);
        setAtaqueBase(ataqueBase);
        setDefesa(defesa);
        setEspecialidade(especialidade);

        this.vidaAtual = vidaMaxima;
        this.defendendo = false;
    }
    

    // Getters
    
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

    // Setters
    
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }

        this.nome = nome;
    }

    public void setVidaMaxima(int vidaMaxima) {
        if (vidaMaxima <= 0) {
            throw new IllegalArgumentException("Vida máxima inválida!");
        }

        if (vidaMaxima < vidaAtual) {
            throw new IllegalArgumentException(
                    "Vida máxima menor que a vida atual!");
        }

        this.vidaMaxima = vidaMaxima;
    }

    public void setVidaAtual(int vidaAtual) {
        if (vidaAtual < 0 || vidaAtual > vidaMaxima) {
            throw new IllegalArgumentException("Vida inválida!");
        }

        this.vidaAtual = vidaAtual;
    }

    public void setAtaqueBase(int ataqueBase) {
        if (ataqueBase < 0) {
            throw new IllegalArgumentException("Ataque inválido!");
        }

        this.ataqueBase = ataqueBase;
    }

    public void setDefesa(int defesa) {
        if (defesa < 0) {
            throw new IllegalArgumentException("Defesa inválida!");
        }

        this.defesa = defesa;
    }

    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Especialidade inválida!");
        }

        this.especialidade = especialidade;
    }

    
    // Combate
    
    public int atacar() {
        return ataqueBase;
    }

    
    // Defender
    
    public void defender() {

        if (estaDerrotado()) {
            throw new IllegalStateException(
                    "Personagem derrotado não pode defender!");
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
    
    public abstract int habilidadeEspecial();

}

//hehehe
