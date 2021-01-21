
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class PackMan extends JFrame implements KeyListener{

	MyCanvas mc = new MyCanvas();
	Image img;
	int i=1; 
	int j=0;
	int x1=400-25;int y1=400-25;//시작 좌표
	int x2=400+25;int y2=400+25;
	//이미지 좌표
	int imgEndX=50;int imgEndY=50; //이미지 끝 x,y좌표
	int imgstartX=0; int imgstartY=0;//이미지 시작 x좌표
	
	public PackMan() {
		super("PackMan");
		add("Center",mc);
		
		setSize(800,800);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		addKeyListener(this);//프레임이 키이벤트 추가
		
		while(true) {
		mc.repaint(); //다시 호출
		if(j==0)setLeft();
		else if(j==1)setUp();
		else if(j==2)setRight();
		else if(j==3)setDown();
		
		try {Thread.sleep(5);}catch(Exception e) {} //0.1초 딜레이 주기 25로해야하는 데 빨리 볼려고 10
		}
		
				
	}
	public void keyPressed(KeyEvent e) {
		int key= e.getKeyCode();
			if(key==e.VK_UP) j=1;
			else if(key==e.VK_DOWN)j=3;
			else if(key==e.VK_RIGHT)j=2;
			else if(key==e.VK_LEFT) j=0;
		
	}
	//아래쪽 이동 메소드
	public void setDown() {
		y1++;y2++;
		if(y1==800) {y1=-50;y2=0;}
		if(i==1) {
			imgstartX=300;imgstartY=0; imgEndX=350; imgEndY=50;i=2;}
		else if(i==2) {
				imgstartX=350;imgstartY=0;imgEndX=400;imgEndY=50;i=1;
			}
	}
	
	//위쪽 이동 메소드
	public void setUp() {
		y1--;y2--;
		if(y2==0) {y1=800;y2=850;}
		if(i==1) {
			imgstartX=200;imgstartY=0; imgEndX=250; imgEndY=50;i=2;}
		else if(i==2) {
				imgstartX=250;imgstartY=0;imgEndX=300;imgEndY=50;i=1;
			}
	}
	
	//오른쪽 이동 메소드
	public void setRight() {
		x1++;x2++;
		if(x1==800) {x2=0;x1=-50;}
		if(i==1) {
		imgstartX=100;imgstartY=0; imgEndX=150; imgEndY=50;i=2;}
		else if(i==2) {
			imgstartX=150;imgstartY=0;imgEndX=200;imgEndY=50;i=1;
		}
	}
	//왼쪽 이동 메소드
	public void setLeft() {
		x1--;x2--;
		if(x2==0) {x1=800;x2=800+50;}
			
		if(i==1) {
			imgEndX=50;imgEndY=50;imgstartX=0;
			i=2;
		}
		else if(i==2) {
			imgEndX=100;imgEndY=50;imgstartX=50;
			i=1;
		}
	}
	public class MyCanvas extends Canvas{
		public MyCanvas() {
			img=Toolkit.getDefaultToolkit().getImage("img/packMan.jpg");
		}
		public void paint(Graphics g) {
			g.drawImage(img, x1, y1, x2, y2, imgstartX, imgstartY, imgEndX, imgEndY, this);
		}
		
	}
	// 오버라이딩 부분
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	public static void main(String[] args) {
		new PackMan();

	}

}
