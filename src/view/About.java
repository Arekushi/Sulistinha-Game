package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frontend.LabelHTML;
import frontend.MyButton;
import frontend.MyLabel;
import frontend.MyPanel;
import frontend.MyTextField;
import frontend.RegisterFont;
import statics.Colors;
import statics.PixelFont;

@SuppressWarnings("serial")
public class About extends JDialog implements ActionListener, KeyListener {

	// JPanel 
	private JPanel panel;
	
	// Toolkit
	public Toolkit tk = Toolkit.getDefaultToolkit();
	
	// JLabel
	private JLabel lblRegras;
	
	// JTextField
	private JTextField txfTitle;
	
	// Font
	private Font myFont;
	private Font titleFont;
	
	// String
	private String texto;
	
	// Color
	private Color corFundo;
	
	// Button
	private JButton x;
	
	
	// Construtor 
	public About() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(750, 400);
		setLayout(null);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		
		init();
		
		// Listeners
		x.addActionListener(this);
		x.addKeyListener(this);
		
		// Adicionar ao panel
		panel.add(lblRegras);
		panel.add(x);
		panel.add(txfTitle);
		
		setContentPane(panel);
		setVisible(true);
	}
	
	public void init() {
		// Font
		myFont = RegisterFont.minhaFont(PixelFont.PixelOperator, "25f");
		titleFont = RegisterFont.minhaFont(PixelFont.PixelOperator, "30f");
		
		// Panel
		panel = new MyPanel(Colors.corCinza);
		
		// Texto
		texto = LabelHTML.html(
			"Esse � um jogo que testar� seus conhecimentos sobre a regi�o Sul do Brasil.<br>"
			+ "Ele consiste em diversas quest�es com 4 alternativas cada, sendo apenas 1 correta, "
			+ "a cada acerto voc� infligir� 1 cora��o de dano ao oponente, caso contr�rio "
			+ "o inimigo lhe causar� 1 cora��o de dano, os personagens possuem 5 cora��es cada, "
			+ "vence quem levar o oponente a 0 cora��es primeiro.", "50"
		);
		
		// Titulo
		txfTitle = new MyTextField(getWidth(), 50, "Sobre", titleFont, Colors.corVerde, Color.WHITE, false);
		txfTitle.setLocation(0, 0);
		
		// Texto
		lblRegras = new MyLabel(750, 400, texto, myFont, Color.WHITE, null);
		lblRegras.setLocation(
			(getWidth() - lblRegras.getWidth()) / 2
			, (getHeight() - lblRegras.getHeight()) / 2
		);
		
		// Botao X
		x = new MyButton (
			(getWidth() - 50), 0,
			50, 50,
			null, Color.WHITE,
			titleFont, "X",
			null, 0, null,
			corFundo
		);
		
		x.setOpaque(false);
	}

	
	// Action Listener 
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == x) {
			dispose();
		}
		
	}

	// Key Listener
	@Override
	public void keyPressed(KeyEvent e) {
		// ESC
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			x.setForeground(corFundo);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// ESC
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			x.setForeground(Color.WHITE);
			dispose();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}
