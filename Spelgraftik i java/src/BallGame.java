import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BallGame extends JPanel{
	Ball ball = new Ball(this,1, 320, 240, -2 ,0, "per1.jpg");
	Ball ball2 = new Ball(this,2, 210, 240,3 ,-0, "per1.jpg");
	Racket racket = new Racket(this, 1);
	Racket racket2 = new Racket(this, 2);
	
	public BallGame(){
		
		addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				racket.keyPressed(e);
				racket2.keyPressed(e);

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		setFocusable(true);
	}
	
	private void move(){
		ball.moveBall();
		ball2.moveBall();
		racket2.move();
		racket.move();
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		ball2.paint(g2d);
		racket.paint(g2d);
		racket2.paint(g2d);
	}
	
	public void gameOver(){
		if(JOptionPane.showConfirmDialog(this,  "Game over", "Play Again?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			ball.restart();
			ball2.restart();
			racket.restart();
			racket2.restart();
		}
		else
		System.exit(ABORT);
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		BallGame game = new BallGame();

		frame.setSize(640, 480);
		frame.setLocation(0, 0);
		frame.add(game);
		frame.setTitle("PING PONG!");
		frame.setDefaultCloseOperation(3);
		frame.setResizable(false);
		frame.setVisible(true);
		
		while(true){
			game.move();
			game.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
}

