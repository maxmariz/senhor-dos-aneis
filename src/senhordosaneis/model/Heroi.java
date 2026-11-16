package senhordosaneis.model;

public abstract class Heroi extends Personagem {

    public Heroi(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    public int rolarDados() {
        int dado1 = (int) (Math.random() * 101); // 0 a 100 inclusive
        int dado2 = (int) (Math.random() * 101);
        return Math.max(dado1, dado2);
    }
}