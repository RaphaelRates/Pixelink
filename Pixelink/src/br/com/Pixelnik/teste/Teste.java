package br.com.Pixelnik.teste;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class Teste {

    public static void main(String[] args) {
        // Criação do JFrame
        JFrame frame = new JFrame("Minha Interface Java");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Criação do JPanel
        JPanel panel = new JPanel();

        // Criação do JButton
        JButton botao = new JButton("Clique-me");

        // Adiciona o botão ao painel
        panel.add(botao);

        // Adiciona o painel ao frame
        frame.add(panel);

        // Torna o frame visível
        frame.setVisible(true);
    }
}
