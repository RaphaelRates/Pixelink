package br.com.Pixelink.Swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.com.Pixelink.entidades.Usuario;

public class FeedDeNoticias {

	private JFrame frame;

	public FeedDeNoticias(Usuario user) {
		initialize(user);
	}

	private void initialize(Usuario user) {
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle("Pixelink");
		frame.setBounds(100, 100, 1366, 768);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/logo.png")));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 0, 204));
		panel.setBounds(0, 0, 133, 768);
		frame.getContentPane().add(panel);

		JButton btnFeed = new JButton();
		btnFeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFeed.setForeground(new Color(148, 0, 211));
		btnFeed.setBackground(new Color(148, 0, 211));
		btnFeed.setBorderPainted(false);
		btnFeed.setFocusPainted(false);
		btnFeed.setContentAreaFilled(false);
		ImageIcon botaoFeed = new ImageIcon(getClass().getResource("/Img/botãoFeed.png"));
		ImageIcon botaoFeedClicado = new ImageIcon(getClass().getResource("/Img/BotãoFeedClicado.png"));
		btnFeed.setIcon(botaoFeed);
		btnFeed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnFeed.setIcon(botaoFeedClicado);
				Alert.exibirInformacao(user.getEmail() + user.getName() + user.getID());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnFeed.setIcon(botaoFeed);
			}
		});
		panel.add(btnFeed);

		JButton btnNotificacao = new JButton();
		btnNotificacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ImageIcon botaoNotificacao = new ImageIcon(getClass().getResource("/Img/botãoNotificações.png"));
		ImageIcon botaoNotificacaoClicado = new ImageIcon(getClass().getResource("/Img/botãoNotificaçõesClicado.png"));
		btnNotificacao.setIcon(botaoNotificacao);
		btnNotificacao.setBorderPainted(false);
		btnNotificacao.setFocusPainted(false);
		btnNotificacao.setContentAreaFilled(false);
		btnNotificacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNotificacao.setIcon(botaoNotificacaoClicado);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNotificacao.setIcon(botaoNotificacao);
			}
		});
		panel.add(btnNotificacao);

		JButton btnPesquisar = new JButton();
		ImageIcon botaoPesquisar = new ImageIcon(getClass().getResource("/Img/botãoPesquisar.png"));
		ImageIcon botaoPesquisarClicado = new ImageIcon(getClass().getResource("/Img/botãoPesquisarClicado.png"));
		btnPesquisar.setIcon(botaoPesquisar);
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setFocusPainted(false);
		btnPesquisar.setContentAreaFilled(false);
		SpringLayout sl_panel = new SpringLayout();
		sl_panel.putConstraint(SpringLayout.NORTH, btnNotificacao, 50, SpringLayout.SOUTH, btnFeed);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnNotificacao, -38, SpringLayout.NORTH, btnPesquisar);
		sl_panel.putConstraint(SpringLayout.WEST, btnFeed, 20, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnFeed, -21, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, btnPesquisar, -310, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnFeed, 271, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, btnFeed, 198, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnNotificacao, 22, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnNotificacao, -23, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnPesquisar, -187, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnPesquisar, 16, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnPesquisar, -17, SpringLayout.EAST, panel);
		panel.setLayout(sl_panel);
		btnPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPesquisar.setIcon(botaoPesquisarClicado);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnPesquisar.setIcon(botaoPesquisar);
			}
		});
		panel.add(btnPesquisar);
		
		JButton btnCriarPost = new JButton();
		sl_panel.putConstraint(SpringLayout.NORTH, btnCriarPost, -188, SpringLayout.SOUTH, panel);
		btnCriarPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ImageIcon botaoCriarPost = new ImageIcon(getClass().getResource("/Img/botãoCriarPost.png"));
		ImageIcon botaoCriarPostClicado = new ImageIcon(getClass().getResource("/Img/botãoCriarPostClicado.png"));
		sl_panel.putConstraint(SpringLayout.WEST, btnCriarPost, 15, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnCriarPost, -54, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnCriarPost, -15, SpringLayout.EAST, panel);
		btnCriarPost.setBorderPainted(false);
		btnCriarPost.setFocusPainted(false);
		btnCriarPost.setContentAreaFilled(false);
		btnCriarPost.setIcon(botaoCriarPost);
		btnCriarPost.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCriarPost.setIcon(botaoCriarPostClicado);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCriarPost.setIcon(botaoCriarPost);
			}
		});
		panel.add(btnCriarPost);

		JButton btnVerProprioPerfil = new JButton();
		sl_panel.putConstraint(SpringLayout.NORTH, btnVerProprioPerfil, 70, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnVerProprioPerfil, 18, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnVerProprioPerfil, -61, SpringLayout.NORTH, btnFeed);
		sl_panel.putConstraint(SpringLayout.EAST, btnVerProprioPerfil, -19, SpringLayout.EAST, panel);
		ImageIcon botaoVerProprioPerfil = new ImageIcon(getClass().getResource("/Img/botãoPerfil.png"));
		ImageIcon botaoVerProprioPerfilClicado = new ImageIcon(getClass().getResource("/Img/botãoPerfilClicado.png"));
		btnVerProprioPerfil.setIcon(botaoVerProprioPerfil);
		btnVerProprioPerfil.setBorderPainted(false);
		btnVerProprioPerfil.setFocusPainted(false);
		btnVerProprioPerfil.setContentAreaFilled(false);
		btnVerProprioPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVerProprioPerfil.setIcon(botaoVerProprioPerfilClicado);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnVerProprioPerfil.setIcon(botaoVerProprioPerfil);
			}
		});
		panel.add(btnVerProprioPerfil);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(133, 0, 1235, 768);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new SpringLayout());
	}

	public JFrame getFrame() {
        return frame;
    }
}