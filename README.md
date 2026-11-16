# Projeto Final Java — Batalha pela Terra Média

Implementação de um jogo de combate por turnos baseado no universo de *O Senhor dos Anéis*, desenvolvido como Projeto Final do curso de Programação Java (Tokio School).

## Estrutura do Projeto

- `senhordosaneis.model`: Hierarquia de classes das personagens (`Personagem`, `Heroi`, `Besta`, `Elfo`, `Hobbit`, `Humano`, `Orque`, `Troll`), aplicando encapsulamento, herança e polimorfismo.
- `senhordosaneis.service`: Classe `Batalha`, responsável pela lógica de turnos, cálculo de danos, verificação de baixas e reorganização dinâmica das filas de combate.
- `senhordosaneis.ui`: Interface gráfica desenvolvida em **Java Swing** (`JanelaBatalha`) para criação personalizada de exércitos, ordenação das unidades e visualização do histórico da batalha.
- `senhordosaneis.Main`: Ponto de entrada que executa a simulação inicial em consola e inicia a janela gráfica.

## Regras Implementadas

- **Heróis:** Lançam dois dados (`0` a `100`) e utilizam o maior valor.
  - *Elfos:* `+10` de potência ofensiva ao enfrentar *Orques*.
  - *Hobbits:* `-5` de potência ofensiva ao enfrentar *Trolls*.
- **Bestas:** Lançam um dado (`0` a `90`).
  - *Orques:* Reduzem em `10%` a armadura do oponente durante o ataque no turno atual.
- **Combate por Turnos:** Enfrentam-se as personagens na mesma posição de cada lista. Quando a vida de uma unidade chega a `<= 0`, ela é removida e as restantes avançam para cobrir a posição no turno seguinte.