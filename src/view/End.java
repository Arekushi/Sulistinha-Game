package view;

import java.awt.Color;
import java.awt.Dimension;
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
public class End extends JDialog implements ActionListener, KeyListener {

	// JPanel 
	private JPanel panel;
	
	// Toolkit
	public Toolkit tk = Toolkit.getDefaultToolkit();
	public Dimension d = tk.getScreenSize();
	
	// JLabel
	private JLabel lblRegras;
	
	// JTextField
	private JTextField txfTitle;
	
	// Font
	private Font myFont;
	private Font titleFont;
	
	// String
	private String txtResultado;
	private String titleResultado;
	
	// Color
	private Color corFundo;
	private Color corResultado;
	
	// Button
	protected JButton reiniciar;
	protected JButton voltarMenu;
	
	
	// Construtor 
	public End(String quemGanhou) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(750, 400);
		setLayout(null);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		
		if (quemGanhou.equals("Mob")) {
			txtResultado = LabelHTML.html("Infelizmente voc� levou 5 golpes e acabou por morrer, tente novamente...", "50");
			corResultado = Colors.corVermelha;
			titleResultado = "Voc� perdeu";
			
		} else if (quemGanhou.equals("Jogador")) {
			txtResultado = LabelHTML.html("Parab�ns, voc� conseguiu concluir o desafio, deseja tentar novamente?", "50");
			corResultado = Colors.corVerde;
			titleResultado = "Voc� ganhou";
			
		} else {
			txtResultado = LabelHTML.html("Voc� conseguiu dar um empate, parab�ns (IMPOSS�VEL)", "50");
			corResultado = Colors.corAmarela;
			titleResultado = "Voc� empatou";
		}
		
		init();
		
		// Listeners 
		voltarMenu.addActionListener(this);
		voltarMenu.addKeyListener(this);
		reiniciar.addActionListener(this);
		reiniciar.addKeyListener(this);
		
		// Adicionar ao panel
		panel.add(reiniciar);
		panel.add(voltarMenu);
		panel.add(lblRegras);
		panel.add(txfTitle);
		
		setContentPane(panel);
		setVisible(true);
	}
	
	public void init() {
		myFont = RegisterFont.minhaFont(PixelFont.PixelOperator, "25f");
		titleFont = RegisterFont.minhaFont(PixelFont.PixelOperator, "30f");
		corFundo = Colors.corCinza;
		
		// Panel
		panel = new MyPanel(corFundo);
		
		// Titulo
		txfTitle = new MyTextField(getWidth(), 50, titleResultado, titleFont, corResultado, Color.WHITE, false);
		txfTitle.setLocation(0, 0);
		
		// Texto
		lblRegras = new MyLabel(750, 200, txtResultado, myFont, Color.WHITE, null);
		lblRegras.setLocation(
				(getWidth() - lblRegras.getWidth()) / 2
				, (getHeight() - lblRegras.getHeight()) / 2
		);
		
		// Voltar Menu
		voltarMenu = new MyButton (
			150, lblRegras.getHeight() + 100,
			150, 50,
			null, Color.WHITE,
			titleFont, "Menu",
			null, 3, corResultado,
			corFundo
		);
		
		// Reiniciar
		reiniciar = new MyButton (
			getWidth() - 300, lblRegras.getHeight() + 100,
			150, 50,
			null, Color.WHITE,
			titleFont, "Reiniciar",
			null, 3, corResultado,
			corFundo
		);
	}
	
	// Methods
	public void voltarMenu() {
		dispose();
		
		Main.objSound.stopSound(Main.musicaFundo);
		Initial.getInstance();
		Main.setInstance();
	}
	
	public void reiniciar() {
		dispose();
		
		Main.objSound.stopSound(Main.musicaFundo);
		Main.restart();
	}

	// Action Listener
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == voltarMenu) {
			voltarMenu();
		}
		
		if (evt.getSource() == reiniciar) {
			reiniciar();
		}
		
	}

	// Key Listener
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			voltarMenu.requestFocus();
			voltarMenu.setBackground(corResultado);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			reiniciar.requestFocus();
			reiniciar.setBackground(corResultado);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (getFocusOwner() == voltarMenu) {
				voltarMenu();
				
			} else if (getFocusOwner() == reiniciar){
				reiniciar();
				
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}
