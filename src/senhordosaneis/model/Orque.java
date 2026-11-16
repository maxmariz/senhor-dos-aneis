package senhordosaneis.model;

public class Orque extends Besta {

    public Orque(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    public int calcularArmaduraAlvo(Personagem inimigo) {
        // Reduz a armadura do oponente em 10% apenas para este ataque
        return (int) (inimigo.getArmadura() * 0.90);
    }
}