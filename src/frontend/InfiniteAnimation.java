package frontend;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import backend.StoreFrame;

@SuppressWarnings("serial")
public class InfiniteAnimation extends JPanel implements ActionListener {

	// Timer
	private Timer animationTimer;
	private Integer animationTimerDelay;
	
	// Animacao 
	private StoreFrame af;
	private int sizeAnimation;
	private final ImageIcon[] images;
	private BufferedImage img;
	private int imagemAtual;
	
	public InfiniteAnimation(String nomeAnimacao, int delay, int width, int height, Boolean invertido) {
		super();
		
		this.animationTimerDelay = delay;
		this.af = new StoreFrame(nomeAnimacao);
		this.sizeAnimation = af.getFrames().size();
		this.images = new ImageIcon[sizeAnimation];
		this.setOpaque(false);
		this.setSize(new Dimension(width, height));
		this.setLayout(null);

		for (int i = 0; i < sizeAnimation; i++) {
			
			try { img = ImageIO.read(new File(af.getFrames().get(i).getCaminho())); }
			catch (IOException ignored) {}
			
			// M�todo para inverter a imagem
			if (invertido) {
				AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
				tx.translate(-img.getWidth(null), 0);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
				images[i] = new ImageIcon(op.filter(img , null));
				
			} else {
				images[i] = new ImageIcon(img);
				
			}
			
			images[i] = new ImageIcon(images[i].getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
			
			iniciarAnimacao();
		}
	}
	
	public void iniciarAnimacao() {
		if (animationTimer == null) {
			imagemAtual = 0;
			animationTimer = new Timer(animationTimerDelay, this);
			animationTimer.start();
	      
	    } else if (!animationTimer.isRunning()) animationTimer.restart();
	      
	}
	
	public void pararAnimacao() {
		animationTimer.stop();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (images[imagemAtual].getImageLoadStatus() == MediaTracker.COMPLETE) {
			images[imagemAtual].paintIcon(this, g, 0, 0);
			imagemAtual = (imagemAtual + 1) % sizeAnimation;
	    }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
}
