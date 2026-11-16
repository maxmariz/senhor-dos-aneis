package senhordosaneis.service;

import senhordosaneis.model.Heroi;
import senhordosaneis.model.Besta;
import senhordosaneis.model.Personagem;
import java.util.ArrayList;
import java.util.List;

public class Batalha {

    public String iniciarBatalha(List<Heroi> exercitoHerois, List<Besta> exercitoBestas) {
        StringBuilder log = new StringBuilder();
        List<Heroi> herois = new ArrayList<>(exercitoHerois);
        List<Besta> bestas = new ArrayList<>(exercitoBestas);

        if (herois.isEmpty() || bestas.isEmpty()) {
            return "Ambos os exércitos precisam ter pelo menos uma personagem!\n";
        }

        int turno = 1;

        while (!herois.isEmpty() && !bestas.isEmpty()) {
            log.append("Turno ").append(turno).append(":\n");

            int totalCombates = Math.min(herois.size(), bestas.size());

            for (int i = 0; i < totalCombates; i++) {
                Heroi heroi = herois.get(i);
                Besta besta = bestas.get(i);

                log.append("Luta entre ")
                   .append(heroi.getNome()).append(" (Vida=").append(heroi.getVida())
                   .append(" Armadura=").append(heroi.getArmadura()).append(") e ")
                   .append(besta.getNome()).append(" (Vida=").append(besta.getVida())
                   .append(" Armadura=").append(besta.getArmadura()).append(")\n");

                // Ataque do Herói
                executarAtaque(heroi, besta, log);

                // Ataque da Besta (ocorre no mesmo turno, mesmo se a vida chegou a <= 0)
                executarAtaque(besta, heroi, log);

                // Verifica baixas após ambos atacarem
                if (!heroi.estaVivo()) {
                    log.append("Morre o ").append(heroi.getTipo().toLowerCase())
                       .append(" ").append(heroi.getNome()).append("!\n");
                }
                if (!besta.estaVivo()) {
                    log.append("Morre o ").append(besta.getTipo().toLowerCase())
                       .append(" ").append(besta.getNome()).append("!\n");
                }
            }

            // Remove os mortos e os restantes avançam automaticamente na lista
            herois.removeIf(h -> !h.estaVivo());
            bestas.removeIf(b -> !b.estaVivo());

            log.append("\n");
            turno++;
        }

        if (!herois.isEmpty()) {
            log.append("VITÓRIA DOS HERÓIS!!\n");
        } else if (!bestas.isEmpty()) {
            log.append("VITÓRIA DAS BESTAS!!\n");
        } else {
            log.append("EMPATE! Ambos os exércitos foram destruídos.\n");
        }

        return log.toString();
    }

    private void executarAtaque(Personagem atacante, Personagem defensor, StringBuilder log) {
        int potencia = atacante.calcularPotenciaAtaque(defensor);
        int armaduraDefensor = atacante.calcularArmaduraAlvo(defensor);
        int dano = 0;

        if (potencia > armaduraDefensor) {
            dano = potencia - armaduraDefensor;
            defensor.receberDano(dano);
        }

        log.append(atacante.getNome())
           .append(" saca ").append(potencia)
           .append(" e tira ").append(dano)
           .append(" de vida a ").append(defensor.getNome()).append("\n");
    }
}