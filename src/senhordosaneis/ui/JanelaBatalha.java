package senhordosaneis.ui;

import senhordosaneis.model.*;
import senhordosaneis.service.Batalha;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JanelaBatalha extends JFrame {

    private DefaultListModel<Heroi> modeloHerois;
    private DefaultListModel<Besta> modeloBestas;
    private JList<Heroi> listaHerois;
    private JList<Besta> listaBestas;
    private JTextArea areaResultado;

    public JanelaBatalha() {
        setTitle("Batalha pela Terra Média");
        setSize(720, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        modeloHerois = new DefaultListModel<>();
        modeloBestas = new DefaultListModel<>();

        // Painel Superior: Criação de Personagens
        JPanel painelCriacao = new JPanel(new GridLayout(1, 2, 10, 10));
        painelCriacao.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        painelCriacao.add(criarPainelFormularioHeroi());
        painelCriacao.add(criarPainelFormularioBesta());

        // Painel Inferior: Listas, Controles e Log da Luta
        JPanel painelLuta = criarPainelLuta();

        add(painelCriacao, BorderLayout.NORTH);
        add(painelLuta, BorderLayout.CENTER);

        carregarExemplosIniciais();
    }

    private JPanel criarPainelFormularioHeroi() {
        JPanel painel = new JPanel(new GridLayout(5, 2, 5, 5));
        painel.setBorder(new TitledBorder("Heróis"));

        JTextField txtNome = new JTextField();
        JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Elfo", "Humano", "Hobbit"});
        JTextField txtVida = new JTextField();
        JTextField txtArmadura = new JTextField();
        JButton btnAdicionar = new JButton("Adicionar");

        painel.add(new JLabel("Nome:"));
        painel.add(txtNome);
        painel.add(new JLabel("Tipo:"));
        painel.add(cmbTipo);
        painel.add(new JLabel("Vida:"));
        painel.add(txtVida);
        painel.add(new JLabel("Armadura:"));
        painel.add(txtArmadura);
        painel.add(new JLabel(""));
        painel.add(btnAdicionar);

        btnAdicionar.addActionListener(e -> {
            try {
                String nome = txtNome.getText().trim();
                int vida = Integer.parseInt(txtVida.getText().trim());
                int armadura = Integer.parseInt(txtArmadura.getText().trim());
                String tipo = (String) cmbTipo.getSelectedItem();

                if (nome.isEmpty() || vida <= 0 || armadura < 0) {
                    JOptionPane.showMessageDialog(this, "Preencha dados válidos!");
                    return;
                }

                Heroi novoHeroi = switch (tipo) {
                    case "Elfo" -> new Elfo(nome, vida, armadura);
                    case "Hobbit" -> new Hobbit(nome, vida, armadura);
                    default -> new Humano(nome, vida, armadura);
                };

                modeloHerois.addElement(novoHeroi);
                txtNome.setText("");
                txtVida.setText("");
                txtArmadura.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vida e Armadura devem ser números inteiros.");
            }
        });

        return painel;
    }

    private JPanel criarPainelFormularioBesta() {
        JPanel painel = new JPanel(new GridLayout(5, 2, 5, 5));
        painel.setBorder(new TitledBorder("Bestas"));

        JTextField txtNome = new JTextField();
        JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Orque", "Troll"});
        JTextField txtVida = new JTextField();
        JTextField txtArmadura = new JTextField();
        JButton btnAdicionar = new JButton("Adicionar");

        painel.add(new JLabel("Nome:"));
        painel.add(txtNome);
        painel.add(new JLabel("Tipo:"));
        painel.add(cmbTipo);
        painel.add(new JLabel("Vida:"));
        painel.add(txtVida);
        painel.add(new JLabel("Armadura:"));
        painel.add(txtArmadura);
        painel.add(new JLabel(""));
        painel.add(btnAdicionar);

        btnAdicionar.addActionListener(e -> {
            try {
                String nome = txtNome.getText().trim();
                int vida = Integer.parseInt(txtVida.getText().trim());
                int armadura = Integer.parseInt(txtArmadura.getText().trim());
                String tipo = (String) cmbTipo.getSelectedItem();

                if (nome.isEmpty() || vida <= 0 || armadura < 0) {
                    JOptionPane.showMessageDialog(this, "Preencha dados válidos!");
                    return;
                }

                Besta novaBesta = tipo.equals("Orque")
                        ? new Orque(nome, vida, armadura)
                        : new Troll(nome, vida, armadura);

                modeloBestas.addElement(novaBesta);
                txtNome.setText("");
                txtVida.setText("");
                txtArmadura.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vida e Armadura devem ser números inteiros.");
            }
        });

        return painel;
    }

    private JPanel criarPainelLuta() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 10, 10, 10),
                new TitledBorder("Luta")
        ));

        listaHerois = new JList<>(modeloHerois);
        listaBestas = new JList<>(modeloBestas);

        JPanel painelListas = new JPanel(new GridLayout(1, 2, 10, 10));
        painelListas.add(criarBlocoLista("Heróis", listaHerois, modeloHerois));
        painelListas.add(criarBlocoLista("Bestas", listaBestas, modeloBestas));

        JButton btnLutar = new JButton("¡ LUTAR !");
        btnLutar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLutar.addActionListener(e -> iniciarCombate());

        JPanel painelBotaoLutar = new JPanel();
        painelBotaoLutar.add(btnLutar);

        areaResultado = new JTextArea(12, 40);
        areaResultado.setEditable(false);
        JScrollPane scrollResultado = new JScrollPane(areaResultado);

        JPanel painelCentral = new JPanel(new BorderLayout(5, 5));
        painelCentral.add(painelListas, BorderLayout.CENTER);
        painelCentral.add(painelBotaoLutar, BorderLayout.SOUTH);

        painel.add(painelCentral, BorderLayout.NORTH);
        painel.add(scrollResultado, BorderLayout.CENTER);

        return painel;
    }

    private <T> JPanel criarBlocoLista(String titulo, JList<T> jList, DefaultListModel<T> model) {
        JPanel bloco = new JPanel(new BorderLayout(5, 5));
        bloco.add(new JLabel(titulo), BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(jList);
        scroll.setPreferredSize(new Dimension(280, 120));
        bloco.add(scroll, BorderLayout.CENTER);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        JButton btnSubir = new JButton("Subir");
        JButton btnDescer = new JButton("Descer");
        JButton btnEliminar = new JButton("Eliminar");

        btnSubir.addActionListener(e -> moverItem(jList, model, -1));
        btnDescer.addActionListener(e -> moverItem(jList, model, 1));
        btnEliminar.addActionListener(e -> {
            int idx = jList.getSelectedIndex();
            if (idx != -1) model.remove(idx);
        });

        botoes.add(btnSubir);
        botoes.add(btnDescer);
        botoes.add(btnEliminar);
        bloco.add(botoes, BorderLayout.SOUTH);

        return bloco;
    }

    private <T> void moverItem(JList<T> jList, DefaultListModel<T> model, int direcao) {
        int idx = jList.getSelectedIndex();
        int novoIdx = idx + direcao;
        if (idx != -1 && novoIdx >= 0 && novoIdx < model.getSize()) {
            T item = model.remove(idx);
            model.add(novoIdx, item);
            jList.setSelectedIndex(novoIdx);
        }
    }

    private void iniciarCombate() {
        List<Heroi> herois = new ArrayList<>();
        for (int i = 0; i < modeloHerois.size(); i++) {
            // Cria cópias novas para permitir repetir a luta sem manter vida zerada
            Heroi h = modeloHerois.get(i);
            if (h instanceof Elfo) herois.add(new Elfo(h.getNome(), h.getVida(), h.getArmadura()));
            else if (h instanceof Hobbit) herois.add(new Hobbit(h.getNome(), h.getVida(), h.getArmadura()));
            else herois.add(new Humano(h.getNome(), h.getVida(), h.getArmadura()));
        }

        List<Besta> bestas = new ArrayList<>();
        for (int i = 0; i < modeloBestas.size(); i++) {
            Besta b = modeloBestas.get(i);
            if (b instanceof Orque) bestas.add(new Orque(b.getNome(), b.getVida(), b.getArmadura()));
            else bestas.add(new Troll(b.getNome(), b.getVida(), b.getArmadura()));
        }

        Batalha batalha = new Batalha();
        String resultado = batalha.iniciarBatalha(herois, bestas);
        areaResultado.setText(resultado);
        areaResultado.setCaretPosition(0);
    }

    private void carregarExemplosIniciais() {
        modeloHerois.addElement(new Elfo("Légolas", 150, 30));
        modeloHerois.addElement(new Humano("Aragorn", 150, 50));
        modeloHerois.addElement(new Humano("Boromir", 100, 60));
        modeloHerois.addElement(new Humano("Gandalf", 300, 30));
        modeloHerois.addElement(new Hobbit("Frodo", 20, 10));

        modeloBestas.addElement(new Orque("Lurtz", 200, 60));
        modeloBestas.addElement(new Orque("Shagrat", 220, 50));
        modeloBestas.addElement(new Troll("Uglúk", 120, 30));
        modeloBestas.addElement(new Troll("Mauhúr", 100, 30));
    }
}