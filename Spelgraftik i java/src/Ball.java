import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.*;

import javax.imageio.ImageIO;
public class Ball {
	
	private int d = 30;
	private int posX, startX;
	private double startMoveY, startMoveX;
	private int posY, startY;
	private double moveX;
	private float speed;
	private double moveY;
	private BallGame game;
	private Racket racket;
	private Racket racket2;
	private float collissionCounter = 0;
	private float ballNumber;
	private BufferedImage image;
	
	public Ball(BallGame game, int ballNr, int x, int y, double beginMoveX, double beginMoveY, String path){
		this.game = game;
		startX = posX = x;
		startY = posY = y;
		startMoveX = moveX = beginMoveX;
		startMoveY = moveY = beginMoveY;
		ballNumber = ballNr;
		if(ballNumber == 1){
		try {
			image = ImageIO.read(new File("Per1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		}
		else{
			try {
				image = ImageIO.read(new File("harambe.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}
		
	void moveBall(){
		
			if(posX + moveX < 0)
				game.gameOver();
			
			if(posX + moveX > game.getWidth() - d )
				game.gameOver();
			if(posY + moveY > game.getHeight() - d)
				moveY = -3;
				//game.gameOver();
			if(posY + moveY < 0)
				moveY = 3;
			
			if(collission()){
				if(posX - moveX >= game.racket.getX() + game.racket.getWidth()){
					moveX = -moveX;
					moveY = game.racket.getMoveY();
					
				}
				else if(if ifisdifsd)
				{
					moveX = -moveX;
					moveY = -moveY;
				}
						
			}
			if(collission2()){
				if(posX -moveX <= game.racket2.getX() - d){
					moveX = -moveX;
					moveY = (game.racket2.getMoveY());					
				}
				else
				{
					moveX = -moveX;
					moveY = -moveY;
				}
				
			}
			
			if(collissionBall()){
				float distance = ((game.ball.posX- game.ball2.posX)* (game.ball.posX- game.ball2.posX));
				float radiDistance = (game.ball.d / 2 + game.ball2.d / 2) * (game.ball.d / 2 + game.ball2.d / 2);
				
				if(distance < radiDistance)
				{
					double newVelX1 = (game.ball.moveX * (game.ball.d  /2 - game.ball2.d /2) + (game.ball2.d * game.ball2.moveX)) / (game.ball.d / 2 + game.ball2.d / 2);
					double newVelX2 = (game.ball2.moveX * (game.ball2.d  /2 - game.ball.d /2) + (game.ball.d * game.ball.moveX)) / (game.ball2.d / 2 + game.ball.d / 2);
					
					game.ball.moveX = newVelX1;
					game.ball2.moveX = newVelX2;
				}
			}
			posX += moveX;
			posY += moveY;
					
	}
	
	public void paint(Graphics2D g){
		g.fillOval(posX, posY, d, d);
		
	}
	
	private Rectangle getBounds(){
		return new Rectangle(posX, posY, d, d);
	}
	
	public boolean collission(){
		return game.racket.getBounds().intersects(getBounds());
	}
	public boolean collission2(){
		return game.racket2.getBounds().intersects(getBounds());
	}
	
	public boolean collissionBall(){
		if(ballNumber == 1)
		return game.ball2.getBounds().intersects(getBounds());
		
		else if(ballNumber == 2)
			return game.ball.getBounds().intersects(getBounds());
		else
			return false;
	}

	public void restart(){
		posX = startX;
		posY = startY;
		moveX = startMoveX;
		moveY = startMoveY;
	}
}

