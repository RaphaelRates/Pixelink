package br.com.Pixelink.Swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.com.Pixelink.entidades.Usuario;

public class FeedDeNoticias {

    private JFrame frame;
    private Usuario usuario;

    public FeedDeNoticias(Usuario usuario) {
        initialize();
        this.usuario = usuario;
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Pixelink");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(500,150);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        JPanel sidePanel = new JPanel(new GridLayout(5, 1, 0, 10));
        sidePanel.setBackground(new Color(102, 0, 204));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnFeed = createButton("/Img/botãoFeed.png", "/Img/BotãoFeedClicado.png");
        JButton btnNotificacao = createButton("/Img/botãoNotificações.png", "/Img/botãoNotificaçõesClicado.png");
        JButton btnPesquisar = createButton("/Img/botãoPesquisar.png", "/Img/botãoPesquisarClicado.png");
        JButton btnCriarPost = createButton("/Img/botãoCriarPost.png", "/Img/botãoCriarPostClicado.png");
        JButton btnVerProprioPerfil = createButton("/Img/botãoPerfil.png", "/Img/botãoPerfilClicado.png");

        sidePanel.add(btnFeed);
        sidePanel.add(btnNotificacao);
        sidePanel.add(btnPesquisar);
        sidePanel.add(btnCriarPost);
        sidePanel.add(btnVerProprioPerfil);

        mainPanel.add(sidePanel, BorderLayout.WEST);
        
        frame.setSize(1080, 720);
        
        

        frame.getContentPane().add(mainPanel);
    }
    
//    private void initialize() {
//        frame = new JFrame();
//        frame.setTitle("Pixelink");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.setBackground(Color.LIGHT_GRAY);
//
//        JPanel sidePanel = new JPanel(new GridLayout(5, 1, 0, 10));
//        sidePanel.setBackground(new Color(102, 0, 204));
//        sidePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        JButton btnFeed = createButton("/Img/botãoFeed.png", "/Img/BotãoFeedClicado.png");
//        JButton btnNotificacao = createButton("/Img/botãoNotificações.png", "/Img/botãoNotificaçõesClicado.png");
//        JButton btnPesquisar = createButton("/Img/botãoPesquisar.png", "/Img/botãoPesquisarClicado.png");
//        JButton btnCriarPost = createButton("/Img/botãoCriarPost.png", "/Img/botãoCriarPostClicado.png");
//        JButton btnVerProprioPerfil = createButton("/Img/botãoPerfil.png", "/Img/botãoPerfilClicado.png");
//
//        sidePanel.add(btnFeed);
//        sidePanel.add(btnNotificacao);
//        sidePanel.add(btnPesquisar);
//        sidePanel.add(btnCriarPost);
//        sidePanel.add(btnVerProprioPerfil);
//
//        mainPanel.add(sidePanel, BorderLayout.WEST);
//
//        frame.getContentPane().add(mainPanel);
//
//        // Configure o tamanho do frame
//        frame.setSize(800, 600);
//
//        // Torne o frame visível
//        frame.setVisible(true);
//    }

    private JButton createButton(String defaultIconPath, String clickedIconPath) {
        JButton button = new JButton();
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        ImageIcon defaultIcon = new ImageIcon(getClass().getResource(defaultIconPath));
        ImageIcon clickedIcon = new ImageIcon(getClass().getResource(clickedIconPath));
        Image scaledDefaultImage = defaultIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        Image scaledClickedImage = clickedIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledDefaultImage));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(new ImageIcon(scaledClickedImage));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(new ImageIcon(scaledDefaultImage));
            }
            
        });
        return button;
    }
    
    public JFrame getFrame() {
		return frame;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
