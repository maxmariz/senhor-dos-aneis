package senhordosaneis.model;

public abstract class Personagem {
    private String nome;
    private int vida;
    private int armadura;

    public Personagem(String nome, int vida, int armadura) {
        this.nome = nome;
        this.vida = vida;
        this.armadura = armadura;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getArmadura() {
        return armadura;
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public void receberDano(int dano) {
        if (dano > 0) {
            this.vida -= dano;
        }
    }

    // Cada subcategoria (Herói ou Besta) define como rola os dados
    public abstract int rolarDados();

    // Permite aplicar bônus ou penalizações específicas contra certos inimigos
    public int calcularPotenciaAtaque(Personagem inimigo) {
        return rolarDados();
    }

    // Permite modificar a armadura do alvo temporariamente (caso do Orque)
    public int calcularArmaduraAlvo(Personagem inimigo) {
        return inimigo.getArmadura();
    }

    public String getTipo() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return nome + " - " + getTipo() + " (" + vida + ", " + armadura + ")";
    }
}