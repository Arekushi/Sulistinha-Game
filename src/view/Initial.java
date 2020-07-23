package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import backend.Sound;
import frontend.InfiniteAnimation;
import frontend.MyButton;
import frontend.RegisterFont;
import statics.Colors;
import statics.Images;
import statics.PixelFont;

@SuppressWarnings("serial")
public class Initial extends JFrame implements ActionListener {

	// Panel de Fundo
	private JPanel tela;
	
	// Botoes
	private JButton btnStartGame;
	private JButton btnAboutGame;
	private JButton btnQuitGame;
	
	// Toolkit
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension d = tk.getScreenSize();
	
	// Musica 
	private static Sound objSound;
	private static String musicaFundo;
	
	// Color
	private Color corLetra;
	private Color corFundo;
	private Color corBorda;
	
	// Font
	private Font fontBtn;
	
	// TelaInicial 
 	private static Initial INSTANCE;
	
	
	// Construtor
	private Initial() {
		//Configura��es do JFrame
		setTitle("Nome do Joginho");
		setSize(d.width, d.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		
		init();
		
		// A��o dos bot�es
		btnStartGame.addActionListener(this);
//		btnAboutGame.addActionListener(this);
//		btnQuitGame.addActionListener(this);

		// Adicionando a tela
		tela.add(btnStartGame);
//		tela.add(btnAboutGame);
//		tela.add(btnQuitGame);
		
		// Tocando a m�sica
		objSound.playSound(musicaFundo, Clip.LOOP_CONTINUOUSLY);
		
		setContentPane(tela);
		setVisible(true);
	}
	
	private void init() {
		tela = new InfiniteAnimation("telaInicial", 80, d.width, d.height, false);
		objSound = new Sound();
		
		corLetra = Colors.corCinza;
		corFundo = Colors.corVerde;
		corBorda = Colors.corCinza;
		
		musicaFundo = statics.Sound.feelGoodInc;
		fontBtn = RegisterFont.minhaFont(PixelFont.PixelOperator, "28f");
		
		int btnWidth = 200, btnHeight = 200;
		int reguladorHorizontal = 0, reguladorVertical = 0;
		
		/*btnStartGame = new MyButton (
			d.width/2 - btnWidth/2 + reguladorHorizontal, d.height/2 - btnHeight/2  + reguladorVertical,
			btnWidth, btnHeight,
			corFundo, corLetra,
			fontBtn, "Start Game",
			corBorda, 3, corLetra,
			corFundo
		);*/
		
		btnStartGame = new MyButton(
			(d.width - btnWidth) / 2 + reguladorHorizontal, (d.height - btnHeight) / 2  + reguladorVertical,
			btnWidth, btnHeight,
			tk.createImage(Images.play).getScaledInstance(58, 74, Image.SCALE_DEFAULT), corFundo,
			corBorda, 3, 
			corBorda, 50
		);
		
//		btnAboutGame = new MyButton (
//			d.width/2 - btnWidth/2 + reguladorHorizontal, d.height/2 - btnHeight/2 + (btnHeight + 20)  + reguladorVertical,
//			btnWidth, btnHeight,
//			corFundo, corLetra,
//			fontBtn, "Sobre o Jogo",
//			corBorda, 3, corLetra,
//			corFundo
//		);
//		
//		btnQuitGame = new MyButton (
//			d.width/2 - btnWidth/2 + reguladorHorizontal, d.height/2 - btnHeight/2 + 2 * (btnHeight + 20)  + reguladorVertical,
//			btnWidth, btnHeight,
//			corFundo, corLetra,
//			fontBtn, "Sair do Jogo",
//			corBorda, 3, corLetra,
//			corFundo
//		);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		
		// Start game 
		if (evt.getSource() == btnStartGame) {
			new Register(this.getClass());
		}
		
		// About game
		if (evt.getSource() == btnAboutGame) {
			new About();
		}
		
		// Quit game
		if (evt.getSource() == btnQuitGame) {
			System.exit(0);
		}
	}
	
	public static void stopSom() {
		objSound.stopSound(musicaFundo);
	}
	
	// INSTANCE
	public static synchronized void setInstance() {
		INSTANCE.dispose();
		INSTANCE = null;
	}
	
	public static synchronized Initial getInstance() {
		// Se n�o haver uma instancia, cria uma
		return (INSTANCE == null)? INSTANCE = new Initial(): INSTANCE;
	}
	
}