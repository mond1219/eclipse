import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PackMan_teacher extends JFrame {
	Image img;
	public PackMan_teacher() {
		img = Toolkit.getDefaultToolkit().getImage("img.packman.jpg");
		
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public class MyCanvas extends Canvas{
		public void paint(Graphics g) {
			
		}
	}

	public static void main(String[] args) {
		new PackMan_teacher();

	}

}
