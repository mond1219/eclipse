package java05_swing;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageGraphics extends JFrame implements ActionListener{
	JPanel pane = new JPanel();
		JButton btn1 = new JButton("원본그리기");
		JButton btn2 = new JButton("축소그리기");
		JButton btn3 = new JButton("확대그리기");
		JButton btn4 = new JButton("좌우뒤집기");
		JButton btn5 = new JButton("상하뒤집기");
		JButton btn6 = new JButton("일부그리기");
		
	MyCanvas mc = new MyCanvas();
	String selBtn = "";//현재 선택된 버튼 라벨
	Image img;
	
	public ImageGraphics() {
		pane.add(btn1); pane.add(btn2);pane.add(btn3);
		pane.add(btn4); pane.add(btn5);pane.add(btn6);
		add("North",pane);
		add("Center",mc);
		
		setSize(1200,1000);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn6.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent ae) {
		selBtn = ae.getActionCommand();
		mc.repaint();//paint()메소드 호출
	}
	public class MyCanvas extends Canvas{
		int w;
		int h;
		public MyCanvas() {
			img = Toolkit.getDefaultToolkit().getImage("img/pink.png");
			
		}
		public void paint(Graphics g) {
			//이미지의 폭과 높이를 구한다.
			//반드시 paint메소드내에서 구해야한다. 
			w =img.getWidth(this);
			h = img.getHeight(this);
			//이미지를 그림으로 그리기
			if(selBtn.equals("원본그리기")) {
				//           canvas          image
				g.drawImage(img,0,0,w,h    ,0,0,w,h,  this);
			}else if(selBtn.equals("축소그리기")) {
				g.drawImage(img,0,0,w/2,h/2    ,0,0,w,h,  this); //1/4크기이다.
			}else if(selBtn.equals("확대그리기")) {
				//캔버스 크기를 구해서 캔버스 크기만하게 그리기 
				int cw = getWidth(); //캔버스 폭
				int ch = getHeight();//캔버스 높이
				g.drawImage(img,0,0,cw,ch    ,0,0,w,h,  this);
			}else if(selBtn.equals("좌우뒤집기")) {
				g.drawImage(img, w,0, 0, h, 0, 0, w, h, this);
			}else if(selBtn.equals("상하뒤집기")) {
				g.drawImage(img,0,0,w,h, 0,h,w,0, this );
			}else if(selBtn.equals("일부그리기")){
				g.drawImage(img,0,0,300,300,200,200,500,500,this);
			}
		}
	}

	public static void main(String[] args) {
		new  ImageGraphics();

	}

}