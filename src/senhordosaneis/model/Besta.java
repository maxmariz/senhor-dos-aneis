package senhordosaneis.model;

public abstract class Besta extends Personagem {

    public Besta(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    public int rolarDados() {
        return (int) (Math.random() * 91); // 0 a 90 inclusive
    }
}