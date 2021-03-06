import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel{
		
	public ArrayList<Snake> snakes = new ArrayList<Snake>(); 
	
	public Game(){
	
		
	}
	
	void initGame(){		
	}
	
	private void startGame(){
		
	}
	
	public void paint(Graphics g){
		super.paint(g); 
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for(int i = 0; i < snakes.size(); i++){
			snakes.get(i).paint(g2d); 
		}
		
	}
	
	public void update(){
		for(int i = 0; i < snakes.size(); i++){
			snakes.get(i).move();
		}
	}
	
	public void addSnake(int x, int y, int angle){
		Snake snake = new Snake(x, y, angle, this.snakes, snakes.size());
		snakes.add(snake); 
	}
	
	class Player extends Thread{
		int snakeNr;
		BufferedReader input;
		PrintWriter output; 
		Socket socket; 
		
		public Player(Socket socket, int snakeNr){
			this.socket = socket;
			this.snakeNr = snakeNr; 
			
			try{
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				output = new PrintWriter(socket.getOutputStream(), true);
			}catch(IOException e){
				System.out.println("Player died: "  + e);
			}
		}
	}

	
	/*public static void main(String[] args){
		Game game = new Game();
		
		JFrame frame = new JFrame("Achtung die kurve");
		frame.setVisible(true); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640,480); 
		frame.getContentPane().setBackground(Color.WHITE); 
		frame.add(game);
		
		frame.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				game.snakes.get(0).keyPressed(e);
				

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				game.snakes.get(0).keyReleased(e);

				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		game.initGame(); 
		
		while(true){
			game.update(); 
			game.repaint(); 
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}*/
}
