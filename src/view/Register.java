package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import data.CRUDUser;
import frontend.MyButton;
import frontend.MyLabel;
import frontend.MyPanel;
import frontend.MyTextField;
import frontend.RegisterFont;
import statics.Colors;
import statics.PixelFont;

@SuppressWarnings("serial")
public class Register extends JDialog implements ActionListener, KeyListener {

	// JPanel 
	private JPanel panel;
	
	// Toolkit
	public Toolkit tk = Toolkit.getDefaultToolkit();
	public Dimension d = tk.getScreenSize();
	
	// JTextField
	private JTextField txfTitle;
	private JTextField txfNome; 
	
	// Font
	private Font myFont;
	
	// Color
	private Color corFundo;
	private Color corVerde;
	private Color corVermelha;
	private Color corBranca;
	
	// Button
	private JButton go;
	private JButton x;
	
	// Label
	private JLabel lblAviso;
	
	// Border
	private Border greenLine;
	private Border redLine;
	private Border whiteLine;
	
	// Titled Border
	private TitledBorder greenTitle;
	private TitledBorder redTitle;
	private TitledBorder whiteTitle;
	
	// Class
	private Class<?> classe;
	
	// Method
	private Method metodo;
	
	
	// Construtor 
	public Register(Class<?> classe) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(750, 350);
		setLayout(null);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		
		init(classe);
		
		SwingUtilities.invokeLater(() -> txfNome.requestFocus());
		
		// Listeners
		txfNome.addKeyListener(this);
		go.addActionListener(this);
		go.addKeyListener(this);
		x.addActionListener(this);
		x.addKeyListener(this);
		
		// Adicionar ao panel
		panel.add(lblAviso);
		panel.add(txfNome);
		panel.add(go);
		panel.add(x);
		panel.add(txfTitle);
		
		setContentPane(panel);
		setVisible(true);
	}
	
	public void init(Class<?> classe) {
		myFont = RegisterFont.minhaFont(PixelFont.PixelOperator, "30f");
		corFundo = Colors.corCinza;
		corVerde = Colors.corVerde;
		corVermelha = Colors.corVermelha;
		corBranca = Color.WHITE;
		
		// Panel
		panel = new MyPanel(corFundo);
		
		// Class
		this.classe = classe;
		
		// Titulo
		txfTitle = new MyTextField(getWidth(), 50, "Cadastre-se", myFont, corVerde, Color.WHITE, false);
		txfTitle.setLocation(0, 0);
		
		// Label Aviso
		lblAviso = new MyLabel(500, 50, "Voc� tem que digitar mais de 3 caracteres", myFont, corVermelha, null);
		lblAviso.setLocation((getWidth() - 500) / 2, (getHeight() - 50) / 2 + 150);
		lblAviso.setVisible(false);
		
		// Border verde
		greenLine = BorderFactory.createLineBorder(corVerde, 3);
		greenTitle = BorderFactory.createTitledBorder(greenLine, "Digite seu nome");
		greenTitle.setTitleJustification(TitledBorder.CENTER);
		greenTitle.setTitleFont(myFont);
		greenTitle.setTitleColor(corVerde);
		
		// Border vermelha
		redLine = BorderFactory.createLineBorder(corVermelha, 3);
		redTitle = BorderFactory.createTitledBorder(redLine, "Digite seu nome");
		redTitle.setTitleJustification(TitledBorder.CENTER);
		redTitle.setTitleFont(myFont);
		redTitle.setTitleColor(corVermelha);
		
		// Border vermelha
		whiteLine = BorderFactory.createLineBorder(corBranca, 3);
		whiteTitle = BorderFactory.createTitledBorder(whiteLine, "Digite seu nome");
		whiteTitle.setTitleJustification(TitledBorder.CENTER);
		whiteTitle.setTitleFont(myFont);
		whiteTitle.setTitleColor(corBranca);
		
		// Nome
		txfNome = new MyTextField(500 - 100, 65, myFont, corVerde);
		txfNome.setLocation((getWidth() - 500) / 2, (getHeight() - 65) / 2);
		txfNome.setBorder(whiteTitle);
		txfNome.setCaretColor(Color.WHITE);
		
		// Voltar Menu
		go = new MyButton (
			txfNome.getWidth() + 150, (getHeight() - 50) / 2 + 5,
			75, 50,
			null, Color.WHITE,
			myFont, "Go",
			null, 3, corVerde,
			corFundo
		);
		
		// Botao X
		x = new MyButton (
			(getWidth() - 50), 0,
			50, 50,
			null, Color.WHITE,
			myFont, "X",
			null, 0, null,
			corFundo
		);
		
		x.setOpaque(false);
	}
	
	// Methods 
	public void vefNome() {
		// Verificar se o texto escrito tem mais do que 3 caracteres para insercao
		if (txfNome.getText().length() < 3) {
			txfNome.setBorder(redTitle);
			txfNome.setForeground(corVermelha);
			txfNome.setCaretColor(corVermelha);
			lblAviso.setVisible(true);
			
			new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
					txfNome.setBorder(whiteTitle);
					txfNome.setForeground(corBranca);
					txfNome.setCaretColor(corBranca);
					lblAviso.setVisible(false);
				}
				
			}, 1500);
			
		} else {
			CRUDUser insert = new CRUDUser();
			insert.insereUsuario(txfNome.getText().trim(), null, null);

			Loading.getInstance(Main.class);
			
			try {
				this.metodo = classe.getMethod("stopSom");
				metodo.invoke(null);
				
				this.metodo = classe.getMethod("setInstance");
				metodo.invoke(null);
				
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException 
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			dispose();
		}
	}

	// Action Listener
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == go) {
			vefNome();
		}
		
		if (evt.getSource() == x) {
			dispose();
		}
	}
	
	// Key Listener
	@Override
	public void keyPressed(KeyEvent e) {
		
		// ENTER
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			go.setBackground(corVerde);
			go.setForeground(corFundo);
			go.setBorder(BorderFactory.createLineBorder(corFundo, 3));
		}
		
		// ESC
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			x.setForeground(corFundo);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		// Se o campo estiver vazio - Cor Branca | Escreveu algo - Cor Verde
		if (txfNome.getText().isEmpty()) {
			txfNome.setBorder(whiteTitle);
			txfNome.setForeground(corBranca);
			txfNome.setCaretColor(corBranca);
		} else {
			txfNome.setBorder(greenTitle);
			txfNome.setForeground(corVerde);
			txfNome.setCaretColor(corVerde);
		}
		
		// ENTER
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			go.setBackground(null);
			go.setForeground(Color.WHITE);
			go.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
			vefNome();
		}
		
		// ESC
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			x.setForeground(Color.WHITE);
			dispose();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// N�o permitir que ele digite mais do que 25 caracteres
		if(txfNome.getText().length() == 25) {
			e.consume();
		}
	}
	
}
