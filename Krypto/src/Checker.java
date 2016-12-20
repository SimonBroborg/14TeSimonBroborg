import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Checker {
	private int m_checkerWidth;
	private int m_checkerHeight;
	private int m_xPos;
	private int m_yPos;
	private int m_xVel;
	private int m_yVel;
	
	public Checker(int x, int y,int width, int height){
		m_checkerWidth = width;
		m_checkerHeight = height;
		m_xPos = x;
		m_yPos = y;
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.pink);
		g2d.fillRect(m_xPos, m_yPos, m_checkerWidth, m_checkerHeight);
	}
	
	
	public void move(int board[][]){
		if(canMove(board)){
			m_xPos += m_xVel;
			m_yPos += m_yVel;
		}
		
		try {
			Thread.sleep(90);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean canMove(int board[][]) {
		boolean move = true;
		
		if(m_xPos + m_xVel < 0){
			move = false;
		}
		if(m_yPos + m_yVel < 0){
			move = false;
		}
		if(m_xPos + m_xVel > board[0].length * m_checkerWidth - m_checkerWidth){
			move = false;
		}
		if(m_yPos + m_yVel > board.length * m_checkerHeight - m_checkerHeight){
			move = false;
		}
		// TODO Auto-generated method stub
		return move;
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			m_xVel = -60;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			m_xVel = 60;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			m_yVel = -60;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			m_yVel = 60;
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		m_xVel = 0;
		m_yVel = 0;
	}

	
	public int getWidth(){
		return m_checkerWidth;
	}
	public int getHeight(){
		return m_checkerHeight;
	}
	public int getX(){
		return m_xPos;
	}
	public int getY(){
		return m_yPos;
	}
	
	
}
