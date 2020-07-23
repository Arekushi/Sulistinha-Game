package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import frontend.InfiniteAnimation;
import frontend.MyLabel;
import frontend.MyPanel;
import frontend.RegisterFont;
import statics.Colors;
import statics.PixelFont;

@SuppressWarnings("serial")
public class Loading extends JFrame {
	
	// JPanel
	private JPanel tela;
	private JPanel animacao;
	
	// Objeto
	private Class<?> objClasse;
	
	// Method
	private Method methodClass;
	
	// JLabel
	private JLabel lblLoading;
	
	// Toolkit
	public Toolkit tk = Toolkit.getDefaultToolkit();
	public Dimension d = tk.getScreenSize();
    
    // Thread
    private Thread loading;
    private Thread nowLoading;
    
    // Font
    private Font myFont;
    
    // String
    private final String[] txtLoading = {"Now Loading", "Now Loading.", "Now Loading..", "Now Loading..."};
    
    // TelaApresentacao 
    private static Loading INSTANCE;
    
    
    // Construtor
	private Loading(Class<?> objClasse) {
		//Configura��es do JFrame
		setTitle("Nome do Joginho");
		setSize(d.width, d.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		
		init(objClasse);
		
		// Thread
		loading.start();
		nowLoading.start();
		
		// Adicionar a tela 
		tela.add(lblLoading);
		tela.add(animacao);
		
		setContentPane(tela);
		setVisible(true);
	}
	
	private void init(Class<?> objClasse) {
		tela = new MyPanel(Colors.corCinzaDark);
		myFont = RegisterFont.minhaFont(PixelFont.PixelOperator, "40f");
		this.objClasse = objClasse;
		
		// Thread
		nowLoading = new Thread(new TextoLoading());
		loading = new Thread(new CarregarBarra());
		
		// Label - Now Loading
		lblLoading = new MyLabel(
			250, 50, txtLoading[0], myFont
			, Color.WHITE , null, SwingConstants.LEFT, SwingConstants.CENTER
		);
		
		lblLoading.setLocation(
			(d.width - lblLoading.getWidth() - 50)
			, (d.height - lblLoading.getHeight() - 50)
		);
		
		// Animacao
		animacao = new InfiniteAnimation("telaCarregamento", 45, 100, 74, false);
		animacao.setLocation(
			(d.width - animacao.getWidth() - 65) - lblLoading.getWidth()
			, (d.height - animacao.getHeight() - 50)
		);
	}
	
	/* Classe CarregarBarra */
	public class CarregarBarra implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				try {
					Thread.sleep(40);
					
				} catch (Exception ignored) {}
			}
			
			try {
				// Pegando metodo dessa classe e 'invocando'
				methodClass = objClasse.getMethod("getInstance");
				methodClass.invoke(null, new Object[0]);
				
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | 
					IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			setInstance();
		}
	}
	
	/* Classe TextoLoading */
	public class TextoLoading implements Runnable {
		@Override
		public void run() {
			int j = 0;
			for (int i = 0; i < 100; i++) {
				try {
					Thread.sleep(300);
					if (j == txtLoading.length) j = 0;
					lblLoading.setText(txtLoading[j]);
					j++;
					
				} catch (Exception ignored) {}
			}
		}
	}
	
	public static synchronized void setInstance() {
		INSTANCE.dispose();
		INSTANCE = null;
	}
	
	public static synchronized Loading getInstance(Class<?> objClasse) {
		// Se n�o haver uma instancia, cria uma
		return (INSTANCE == null)? INSTANCE = new Loading(objClasse): INSTANCE;
	}
	
}
