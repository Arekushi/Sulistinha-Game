package frontend;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MyTextField extends JTextField {
	
	public MyTextField(int largura, int altura, String txt, Font font, Color background, Color foreground, Boolean editavel) {
		setSize(largura, altura);
		setText(txt);
		setFont(font);
		setBackground(background);
		setForeground(foreground);
		setLayout(null);
		setBorder(null);
		setEditable(editavel);
		setFocusable(editavel);
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public MyTextField(int largura, int altura, String txt, 
			Font font, Color background, Color foreground, Color border, int line) {
		setSize(largura, altura);
		setText(txt);
		setFont(font);
		setBackground(background);
		setForeground(foreground);
		setBorder(BorderFactory.createLineBorder(border, line));
		setLayout(null);
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public MyTextField(int largura, int altura, Font font, Color foreground) {
		setSize(largura, altura);
		setFont(font);
		setForeground(foreground);
		setBackground(null);
		setLayout(null);
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	
}