package br.com.Pixelnik.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class teste extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel painelPrincipal;
    private JPanel primeiroPainel;
    private JPanel segundoPainel;

    public teste() {
        setTitle("Exemplo de Troca de Painel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Criando os painéis
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new CardLayout()); // Usamos CardLayout para alternar entre os painéis

        primeiroPainel = new JPanel();
        primeiroPainel.setBackground(Color.RED);
        primeiroPainel.add(new JLabel("Este é o primeiro painel"));

        segundoPainel = new JPanel();
        segundoPainel.setBackground(Color.BLUE);
        segundoPainel.add(new JLabel("Este é o segundo painel"));

        painelPrincipal.add(primeiroPainel, "primeiro");
        painelPrincipal.add(segundoPainel, "segundo");

        // Botão para trocar de painel
        JButton trocarPainelButton = new JButton("Trocar de Painel");
        trocarPainelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Trocar para o próximo painel
                CardLayout layout = (CardLayout) painelPrincipal.getLayout();
                layout.next(painelPrincipal);
            }
        });

        // Adicionando componentes ao JFrame
        add(painelPrincipal, BorderLayout.CENTER);
        add(trocarPainelButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new teste();
        });
    }
}
