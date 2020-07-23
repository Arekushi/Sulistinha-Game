package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.MyLabel;
import frontend.MyPanel;
import frontend.ProcessImage;
import frontend.RegisterFont;
import statics.Colors;
import statics.Images;
import statics.PixelFont;

@SuppressWarnings("serial")
public class Splash extends JFrame {
	
	// Animacao 
	private JPanel tela;
	
	// JLabel
	private JLabel imgAnonymous;
	private JLabel lblAnonymous;
	
	// Toolkit
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension d = tk.getScreenSize();
	
	// Thread
	private Thread time;
	
	// Font
	private Font myFont;
	
	// TelaApresentacao 
 	private static Splash INSTANCE;
	
	// Construtor
	private Splash() {
		//Configura��es do JFrame
		setTitle("Nome do Joginho");
		setSize(d.width, d.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);

		init();

		// Thread
		time.start();
		
		// Adicionando a tela 
		tela.add(imgAnonymous);
		tela.add(lblAnonymous);
		
		setContentPane(tela);
		setVisible(true);
	}
	
	public void init() {
		tela = new MyPanel(Colors.corCinzaDark);
		
		time = new Thread(new TelaDelay());
		myFont = RegisterFont.minhaFont(PixelFont.PixelOperator, "45f");
		
		// imgAnonymous
		imgAnonymous = new MyLabel(200, 200, ProcessImage.processarArredondamento(Images.anonymous));
		
		imgAnonymous.setLocation(
			(d.width - imgAnonymous.getWidth()) / 2
			, (d.height - imgAnonymous.getHeight()) / 2 - 75
		);
		
		// lblAnonymous
		lblAnonymous = new MyLabel(250, 100, "<html><center>Anonymous da Favela</center></html>", myFont, Color.WHITE, null);
		
		lblAnonymous.setLocation(
			(d.width - lblAnonymous.getWidth()) / 2
			, (d.height - lblAnonymous.getHeight()) / 2 + 90
		);
	}
	
	/* Classe TextoLoading */
	public class TelaDelay implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 1; i++) {
				try {
					Thread.sleep(1200);
					
				} catch (Exception ignored) {}
			}
			
			Initial.getInstance();
			setInstance();
		}
	}
	
	public static synchronized void setInstance() {
		INSTANCE.dispose();
		INSTANCE = null;
	}
	
	public static synchronized Splash getInstance() {
		// Se n�o haver uma instancia, cria uma
		return (INSTANCE == null)? INSTANCE = new Splash(): INSTANCE;
	}

}

