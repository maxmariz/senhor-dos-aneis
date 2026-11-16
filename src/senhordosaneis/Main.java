package senhordosaneis;

import senhordosaneis.model.*;
import senhordosaneis.service.Batalha;
import senhordosaneis.ui.JanelaBatalha;

import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Execução em Modo Consola (Exemplo do enunciado)
        List<Heroi> herois = new ArrayList<>();
        herois.add(new Elfo("Legolas", 150, 30));
        herois.add(new Humano("Aragorn", 150, 50));
        herois.add(new Humano("Boromir", 100, 60));
        herois.add(new Humano("Gandalf", 300, 30));
        herois.add(new Hobbit("Frodo", 20, 10));

        List<Besta> bestas = new ArrayList<>();
        bestas.add(new Orque("Lurtz", 200, 60));
        bestas.add(new Orque("Shagrat", 220, 50));
        bestas.add(new Troll("Uglúk", 120, 30));
        bestas.add(new Troll("Mauhúr", 100, 30));

        Batalha batalha = new Batalha();
        System.out.println("=== SIMULAÇÃO EM MODO CONSOLA ===\n");
        System.out.println(batalha.iniciarBatalha(herois, bestas));

        // 2. Execução em Modo Interface Gráfica (Swing)
        SwingUtilities.invokeLater(() -> {
            JanelaBatalha janela = new JanelaBatalha();
            janela.setVisible(true);
        });
    }
}