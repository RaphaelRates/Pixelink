package br.com.Pixelnik.teste;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class Teste {

    public static void main(String[] args) {
        // Criação do JFrame
        JFrame frame = new JFrame("Pixellink - v1.0.0 ");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Criação do JPanel
        JPanel panel = new JPanel();
        panel.setBackground(Color.MAGENTA);

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
