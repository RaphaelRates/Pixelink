package Swing.Feed;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import Swing.Componentes.ScrollbarCustomizado;
import Swing.Posts.PostImagePanel;
import Swing.Posts.PostTextPanel;
import data.Dados;
import entidades.Post;
import entidades.PostImage;
import entidades.PostText;
import entidades.Usuario;

public class FeedPosts extends JPanel {
	private static final long serialVersionUID = 1L;
	private Usuario logado;
	private Image fundo;
	private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private GraphicsDevice[] gd = ge.getScreenDevices();
	private DisplayMode mode = gd[0].getDisplayMode();
	private JPanel viewport;
	private boolean isLoading = false;
	private int totalPostsRenderizados = 0;
	private int maxPostsRenderizados;
	private Set<String> renderedPostIds = new HashSet<>();

	public FeedPosts(Usuario logado) {
		try {
			fundo = ImageIO.read(getClass().getResource("/img/fundoPesquisar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setUsuario(logado);
		setSize(new Dimension(mode.getWidth(), mode.getHeight()));
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

	private void initialize() {
		setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUI(new ScrollbarCustomizado());
		scrollPane.getHorizontalScrollBar().setUI(new ScrollbarCustomizado());
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
		scrollPane.setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setOpaque(false);
		add(scrollPane, BorderLayout.CENTER);

		List<Usuario> usuarios = Dados.lerUsuarios();

		viewport = new JPanel();
		viewport.setLayout(new BoxLayout(viewport, BoxLayout.Y_AXIS));
		viewport.setOpaque(false);
		scrollPane.setViewportView(viewport);

		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if (!isLoading && e.getValue() == scrollPane.getVerticalScrollBar().getMaximum()
						- scrollPane.getVerticalScrollBar().getVisibleAmount()) {
					if (totalPostsRenderizados < maxPostsRenderizados) {
						loadMoreLabelsForUsers(usuarios);
					}
				}
			}
		});
		setPreferredSize(new Dimension(mode.getWidth() - 150, mode.getHeight()));
		loadMoreLabelsForUsers(usuarios);
	}

	private void loadLabelsForUsers(List<Usuario> usuarios) {
		ArrayList<Post> allPosts = new ArrayList<Post>();
		for (Usuario usuario : usuarios) {
			allPosts.addAll(usuario.getAllPost());

		}
		maxPostsRenderizados = allPosts.size();
		Collections.shuffle(allPosts);
		for (int i = 0; i < 3; i++) {
			if (totalPostsRenderizados >= maxPostsRenderizados) {
				return;
			}
			Post post = allPosts.get(i);
			if (!renderedPostIds.contains(post.getIdPost())) {
				if (post instanceof PostImage) {
					viewport.add(new PostImagePanel(Dados.getUsuario(post.getUsuario()), (PostImage) post));
				} else if (post instanceof PostText) {
					viewport.add(new PostTextPanel(Dados.getUsuario(post.getUsuario()), (PostText) post));
				}
				viewport.add(Box.createVerticalStrut(30));
				totalPostsRenderizados++;
				renderedPostIds.add(post.getIdPost());
			}
		}
		viewport.revalidate();
		viewport.repaint();

	}

	private void loadMoreLabelsForUsers(List<Usuario> usuarios) {
		isLoading = true;
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				Thread.sleep(1000);
				return null;
			}

			@Override
			protected void done() {
				loadLabelsForUsers(usuarios);
				isLoading = false;
			}
		};
		worker.execute();
	}

	public void setUsuario(Usuario usuario) {
		this.setLogado(usuario);
	}

	public Usuario getLogado() {
		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}
}
