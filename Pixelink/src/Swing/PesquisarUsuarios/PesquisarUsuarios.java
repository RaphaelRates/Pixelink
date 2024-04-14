package Swing.PesquisarUsuarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

import Swing.Componentes.Alert;
import Swing.Componentes.PlaceholderTextField;
import Swing.Componentes.ScrollbarCustomizado;
import data.Dados;
import entidades.Usuario;

public class PesquisarUsuarios extends JPanel {

	private static final long serialVersionUID = 1L;
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice[] gd = ge.getScreenDevices();
	DisplayMode mode = gd[0].getDisplayMode();
	private JPanel resultsPanel;
	private Usuario usuario;
	private Image fundo;
	private JFrame frame;
	private JScrollPane scrollPane;
	private LineBorder bordaRoxa = new LineBorder(Color.decode("#7304D7"), 3, false);
	private PlaceholderTextField textField;

	public PesquisarUsuarios(Usuario usuario, JFrame frame) {
		this.setFrame(frame);
		try {
			fundo = ImageIO.read(getClass().getResource("/img/fundoPesquisar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.usuario = usuario;

		initialize();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (fundo != null) {
			g.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(fundo.getWidth(this), fundo.getHeight(this));
	}

	public void initialize() {
		setSize(new Dimension(mode.getWidth() - 150, mode.getHeight()));

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		textField = new PlaceholderTextField("  Digite o ID ou Nome de Usuário");
		textField.setBorder(bordaRoxa);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 109, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textField, 300, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 155, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textField, -460, SpringLayout.EAST, this);
		add(textField);
		textField.setColumns(10);

		JButton botaoPesquisar = new JButton("");
		springLayout.putConstraint(SpringLayout.NORTH, botaoPesquisar, 108, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, botaoPesquisar, 1, SpringLayout.EAST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, botaoPesquisar, 0, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, botaoPesquisar, -371, SpringLayout.EAST, this);
		botaoPesquisar.setIcon(new ImageIcon(PesquisarUsuarios.class.getResource("/img/botaoPesquisar.png")));
		botaoPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().trim().equals("Digite o ID ou Nome de Usuário")) {
					Alert.exibirAlerta("Digite algo no campo de pesquisa");
				} else {
					buscarUsuarios(textField.getText());
				}

			}
		});
		botaoPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				botaoPesquisar
						.setIcon(new ImageIcon(PesquisarUsuarios.class.getResource("/img/botaoPesquisarClicado.png")));
				botaoPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				botaoPesquisar.setIcon(new ImageIcon(PesquisarUsuarios.class.getResource("/img/botaoPesquisar.png")));
				botaoPesquisar.setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				botaoPesquisar.setIcon(
						new ImageIcon(PesquisarUsuarios.class.getResource("/img/botaoPesquisarPressionado.png")));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				botaoPesquisar
						.setIcon(new ImageIcon(PesquisarUsuarios.class.getResource("/img/botaoPesquisarClicado.png")));
			}

		});

		add(botaoPesquisar);

		JLabel lblTitulo = new JLabel("Pesquisar Usuários");
		springLayout.putConstraint(SpringLayout.WEST, lblTitulo, 445, SpringLayout.WEST, this);
		lblTitulo.setForeground(Color.white);
		lblTitulo.setFont(Dados.loadFontFromFile(30));
		springLayout.putConstraint(SpringLayout.SOUTH, lblTitulo, -20, SpringLayout.NORTH, textField);
		add(lblTitulo);

		resultsPanel = new JPanel();
		scrollPane = new JScrollPane(resultsPanel);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 300, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -370, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 226, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -100, SpringLayout.SOUTH, this);
		scrollPane.setPreferredSize(new Dimension(516, 118));
		add(scrollPane);
		scrollPane.setViewportView(resultsPanel);
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.X_AXIS));
		scrollPane.setFocusable(isFocusable());
		scrollPane.getVerticalScrollBar().setUI(new ScrollbarCustomizado());
		scrollPane.getHorizontalScrollBar().setUI(new ScrollbarCustomizado());
		scrollPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if (resultsPanel.getComponentCount() % 5 == 0) {
					scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				} else {
					scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				}
			}
		});
		scrollPane.setVisible(false);

	}

	private void buscarUsuarios(String termoPesquisa) {
	    JLabel lblPesquisando = new JLabel("Pesquisando...");
	    resultsPanel.removeAll();
	    resultsPanel.add(lblPesquisando, BorderLayout.CENTER);
	    resultsPanel.revalidate();
	    resultsPanel.repaint();

	    ArrayList<Usuario> usuariosEncontrados = Dados.pesquisarUsuario(termoPesquisa);
	    resultsPanel.removeAll();
	    resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

	    if (usuariosEncontrados.isEmpty()) {
	        JLabel lblNenhumResultado = new JLabel("  Nenhum resultado encontrado.");
	        lblNenhumResultado.setForeground(Color.RED);
	        resultsPanel.add(lblNenhumResultado);
	    } else {
	        for (Usuario usuarioEncontrado : usuariosEncontrados) {
	            JPanel panelAba = new JPanel();
	            panelAba.setLayout(new BorderLayout());
	            AbaPesquisaEncontrado abaPesquisaEncontrado = new AbaPesquisaEncontrado(usuarioEncontrado, this.usuario);
	            abaPesquisaEncontrado.setPreferredSize(new Dimension(517, 99));
	            panelAba.add(abaPesquisaEncontrado, BorderLayout.NORTH);
	            resultsPanel.add(panelAba);
	        }
	    }

	    // Atualiza o painel de resultados
	    resultsPanel.revalidate();
	    resultsPanel.repaint();
	    scrollPane.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
