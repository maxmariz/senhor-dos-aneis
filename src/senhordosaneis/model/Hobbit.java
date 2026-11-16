package senhordosaneis.model;

public class Hobbit extends Heroi {

    public Hobbit(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    public int calcularPotenciaAtaque(Personagem inimigo) {
        int ataque = super.calcularPotenciaAtaque(inimigo);
        if (inimigo instanceof Troll) {
            ataque = Math.max(0, ataque - 5);
        }
        return ataque;
    }
}