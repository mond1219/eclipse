package swingTest;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;



public class PackMan extends JPanel implements Runnable{
   Image img;
   MyCanvas mc = new MyCanvas();
   int x,y;
   int p=0;
   /////////////////////
   Dimension frameSize,canvasSize;
   public PackMan() {
      setLayout(new BorderLayout());
	   img = Toolkit.getDefaultToolkit().getImage("img/packMan.jpg");
      
      mc = new MyCanvas();
      add(BorderLayout.CENTER, mc);
//      setBounds(xx, yy, ww, hh);
//      setVisible(true);
//      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      
      mc.addKeyListener(new CanvasKeyEvent());
      //JFrame, Canvas크기 
      //시작 좌표
//      getPackManSize();
//      
//      x=(int)canvasSize.getWidth()/2-25;
//      y=(int)canvasSize.getHeight()/2-25;
      
      mc.setFocusable(true); //커서가 캔버스에 집중한다.
   }
   
   // 반복실행되는 구간을 스레드로 구현하기 위해 run 메소드로
   public void run() {
	      while(true) {
	          try {Thread.sleep(200);}catch(Exception e) {}
	          //이미지 변환 
	            //짝수일때
	          if(p%2==0) p++;
	          else p--; //홀수 일때
	          
	          mc.repaint();
	          
	          //방향 바꾸기 
	          if(p==1||p==0) {
	             x-=5;
	             if(x<=-50) x=(int)canvasSize.getWidth();
	          }else if(p==2||p==3) {
	             x+=5;
	             if(x>=(int)canvasSize.getWidth()) x=-50;
	          }else if(p==4||p==5) {
	             y-=5;
	             if(y<=-50) y=(int)canvasSize.getHeight();
	          }else if(p==6||p==7) {
	             y+=5;
	             if(y>=(int)canvasSize.getHeight()) y=-50;
	          }
	       }
   }
   public class CanvasKeyEvent extends KeyAdapter{
      public void keyReleased(KeyEvent ke) {//캔버스에서 키보드를 누르면
         int key =ke.getKeyCode();
         if(key==KeyEvent.VK_A||key==KeyEvent.VK_LEFT) {p=0;}
         else if(key==KeyEvent.VK_D||key==KeyEvent.VK_RIGHT) {p=2;}
         else if(key==KeyEvent.VK_W||key==KeyEvent.VK_UP) {p=4;}
         else if(key==KeyEvent.VK_S||key==KeyEvent.VK_DOWN) {p=6;}
      } 
   }
   public class MyCanvas extends Canvas{
      public void paint(Graphics g) {
         g.drawImage(img, x, y, x+50, y+50, p*50, 0, p*50+50, 50, this);
      }
   }
   public void getPackManSize() {
      frameSize= getSize();
      canvasSize = mc.getSize();
      x=(int)canvasSize.getWidth()/2-25;
      y=(int)canvasSize.getHeight()/2-25;
   }
}