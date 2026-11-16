package senhordosaneis.model;

public class Elfo extends Heroi {

    public Elfo(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    public int calcularPotenciaAtaque(Personagem inimigo) {
        int ataque = super.calcularPotenciaAtaque(inimigo);
        if (inimigo instanceof Orque) {
            ataque += 10;
        }
        return ataque;
    }
}