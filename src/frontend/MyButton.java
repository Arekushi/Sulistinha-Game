package frontend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

@SuppressWarnings("serial")
public class MyButton extends JButton {
	
	public MyButton(int x, int y, int width, int height, Color backgroung, Color foreground, Font font, 
			String txt, Color border, int line, Color hoverBackground, Color hoverForeground) {
		setSize(width, height);
		setLocation(x, y);
		setFont(font);
		setBackground(backgroung);
		setForeground(foreground);
		setText(txt);
		setSelected(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(border, line));
		setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Mouse Listener
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(backgroung);
				setForeground(foreground);
				setBorder(BorderFactory.createLineBorder(border, line));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(hoverBackground);
				setForeground(hoverForeground);
				setBorder(BorderFactory.createLineBorder(hoverForeground, line));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
			
		});
	}
	
	public MyButton(int x, int y, int width, int height, 
			Image img, Color backgroung, Color border, int line, Color hoverBackground, int radius) {
		setSize(width, height);
		setIcon(new ImageIcon(img));
		setLocation(x, y);
		setBackground(backgroung);
		setSelected(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
		setBorder(new RoundedBorder(radius, border, line));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		setUI(new BasicButtonUI() {
	        @Override
	        public void update(Graphics g, JComponent c) {
	        	Graphics2D g2d = (Graphics2D) g;
	            if (c.isOpaque() == false) {
	            	
	                Color fillColor = c.getBackground();
	                AbstractButton button = (AbstractButton) c;
	                ButtonModel model = button.getModel();

	                if (model.isPressed()) {
	                    fillColor = fillColor.darker();
	                    
	                } else if (model.isRollover()) {
	                    fillColor = fillColor.brighter();
	                    
	                }

	                g2d.setColor(fillColor);
	                g2d.fillRoundRect(1, 1, c.getWidth()-line, c.getHeight()-line, radius, radius);
	            }
	            
	            paint(g2d, c);
	        }
	    });

		// Mouse Listener
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {
//				setBackground(backgroung);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
//				setBackground(hoverBackground);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
			
		});
	}
	
}
