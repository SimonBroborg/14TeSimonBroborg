import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client{

	//Declares objects
	BufferedReader in;
	PrintWriter out;
	
	JFrame frame; 
	
	///////////////// Declares variables ///////////////////////
	//Integers
	int snakeNr;
	int angle; 
	int snakes; 
	
	//Strings
	String dataSnakeNr;
	String dataAngle; 
	String serverAddress; 
	

	public Client() {
		//Sets the wanted server address
		this.serverAddress = setServerAddress(); 
		//Initializes variables
		snakes = 0; 
		angle = 0; 
		dataSnakeNr = null; 
		dataAngle = null; 
		snakeNr = 0; 
		
	}

	private String setServerAddress(){
		//The return value is equal to the IP address which the user choses
		return JOptionPane.showInputDialog(
					frame, 
					"Enter IP address of server: ", 
					"Welcome to Achtung Die Kurve!", 
					JOptionPane.QUESTION_MESSAGE 
				);
	}

	private void run() throws IOException {

		// Make connection and initialize streams  
		Socket socket = new Socket(serverAddress, 6666);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		
		
		String input = in.readLine();
		snakeNr = Integer.parseInt(input.substring(0,1));
		angle = Integer.parseInt(input.substring(1,input.length() -1));
		System.out.println(snakeNr);
		
		
		Game game = new Game();
		StringBuilder stringBuilder = new StringBuilder();
		
		//Creates and sets up the JFrame
		frame = new JFrame("Achtung die kurve");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.add(game);
		
		boolean go = false;
		while(!go){
			String startGame = this.in.readLine();
			
			//When the server tells the clients to start, the clients also gets the number of connected players
			if(startGame.startsWith("GO")){
				go = true; 
				snakes = Integer.parseInt(startGame.substring(startGame.length()-1));
			}
		}

		//Checks the number of players and prints out snakes accordingly
		for(int i = 0; i < snakes; i++){
			game.addSnake(i *100,i*100,0);
		}		
		
		//Checks input from the player and moves the clients snake accordingly
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				game.snakes.get(snakeNr-1).keyPressed(e);
			}
 
			@Override
			public void keyReleased(KeyEvent e) {
				game.snakes.get(snakeNr-1).keyReleased(e);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

		});

		while (true) {
			game.update();	
			
			/*Creates a string containing the clients snake number followed by the angle of the snake
			 * "snakeNr-1" is to get the right index
			 */
			stringBuilder.append(this.snakeNr-1);
			stringBuilder.append( game.snakes.get(this.snakeNr -1).angle);
			
			out.println(stringBuilder.toString()); 	
			
			String text = in.readLine();
			if(!(text.substring(0,1).equals(this.snakeNr -1)))
				game.snakes.get(Integer.parseInt(text.substring(0,1))).angle = Integer.parseInt(text.substring(1));
			
			//Draws the updated versions of the snakes
			game.repaint();			
			
			//resets the string builder
			stringBuilder.setLength(0);
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) throws Exception {
		//Creates a client and sets the server address 
		Client client = new Client();
		//"192.168.210.154"
		client.run();

		

	}

}
