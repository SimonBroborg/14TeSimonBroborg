import java.awt.*;
import java.awt.event.KeyEvent;

public class Racket {
	private int width = 20;
	private int height = 70;
	private int x = 100;
	private int y = 240 - height/2;
	private int moveY = 0;
	private int rackets;
	private int startX, startY;
	
	private BallGame game;
	public Racket(BallGame game, int racketNr){
		this.game = game;
		rackets = racketNr;
		
		if(rackets == 2)
		{
			height = 480;
			startX = x = 540;
			startY = y = 240 - height/2;
		}
		else{
			startX = x;
			startY = y;
		}

	}
	
	public void paint(Graphics2D g){
		if(rackets == 2){
			g.setColor(Color.red);
			g.fillRect(x,y , width, height);
		}
		else {
			g.setColor(Color.black);
			g.fillRect(x, y, width, height);
		}
		
	}
	
	void move(){
		if(y + moveY > 0 && y + moveY < game.getHeight() - height)
			y += moveY;
	}

	public void keyPressed(KeyEvent e) {
		if(rackets == 1){
			if(e.getKeyCode() == KeyEvent.VK_W)
				moveY = -3;
			if(e.getKeyCode() == KeyEvent.VK_S)
				moveY = 3;
		}
		
		if(rackets == 2){
			if(e.getKeyCode() == KeyEvent.VK_UP)
				moveY = -3;
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
				moveY = 3;
		}
	}

	public void keyReleased(KeyEvent e) {
		moveY = 0;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}

	public int getY(){
		return y;
	}
	
	public int getX(){
		return x;
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public int getMoveY(){
		return moveY;
	}
	
	public void restart(){
		x = startX;
		y = startY;
		moveY = 0;
		
	}
}
