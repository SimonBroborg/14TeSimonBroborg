import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.Integer.*;

public class Krypto extends JPanel {
	//best�mmer storlek f�r rutorna samt anpassar rutf�nstrets storlek efter antalet rutor
 	static int rectWidth = 60;
	static int rectHeight = 60;
	
	
	//skapar objekt f�r krypto, "checker" och Scanner
		static Krypto krypto = new Krypto();
		static Checker checker = new Checker(0, 0, rectWidth, rectHeight);
		static Scanner scan = new Scanner(System.in);
	
	
	//ArrayList som inneh�ller alla bokst�ver f�r varje siffra
	static ArrayList<Character>  letters = new ArrayList<Character>();
	
	//ArrayList som h�ller koll p� vilka siffror som har f�tt en bokstav
	ArrayList<Integer> unlockedNumbers = new ArrayList<Integer>();
	
	//h�ller koll p� vilken bokstav som skrevs in senast
	static char nextLetter;
	
	//h�ller koll p� om vald bokstav redan finns p� annan placering
	static boolean exists = false;
	

	
	static Board gameBoard;
				
	//storlekar f�r nummer och siffror
	int numberSize = 20;
	int fontSize = 25;
	
	static boolean startGame = false;
		
	
	public Krypto(){
		//keyListener som h�ller koll p� n�r jag skriver en bokstav eller flyttar p� "checker"
		addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				//checker r�r sig n�r jag trycker p� piltangenterna
				checker.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				//checker slutar r�ra sig n�r jag sl�pper piltangenterna
				checker.keyReleased(e);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
				//s�tter variabeln nextLetter lika med den nyss tryckta bokstaven
				nextLetter = Character.toUpperCase(e.getKeyChar());
				//skriver ut bokstaven f�r att se att det funkar
				System.out.println(nextLetter); 
				
				exists = false;
				//kollar om v�rdet av nextLetter redan finns i letters
				for(int i = 0; i < letters.size(); i ++){
					if(letters.get(i) == nextLetter){
						exists = true;
					}
				}
				//K�r koden om den skriva bokstaven inte �r inlagd
				if(!exists){
					//l�gger in den senast inskrivna bokstaven p� samma index som den nuvarande rutans siffra
					letters.set(gameBoard.board[checker.getY()/checker.getHeight()][checker.getX()/checker.getWidth()], nextLetter);
					//skriver ut vilken bokstav som lades in och p� vilket index f�r att se att det funkade
					System.out.println("Added the letter " +  nextLetter + " to letters at position " + gameBoard.board[checker.getY()/checker.getHeight()][checker.getX()/checker.getWidth()]);
				}
			}
		});
		
		setFocusable(true);
	}
	
	//funktion som kallar p� alla "move"-funktioner
	private void move() {
		// TODO Auto-generated method stub
		checker.move(gameBoard.board);
	}

	//ritar ut allting i rutan
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//ritar ut "checker"
		checker.paint(g2d);
		
		g.setColor(Color.black);
		
		//loopar igenom x och y koordinater f�r board och skriver ut rutan, siffran och bokstav om det finns
		for(int i = 0; i < gameBoard.board.length; i++){
			for (int j = 0; j < gameBoard.board[i].length; j++){
				g.drawRect(j * rectWidth, i * rectHeight, rectWidth, rectHeight);  
				g.setFont(new Font("Arial", Font.PLAIN,numberSize));
				g.drawString(String.valueOf(gameBoard.board[i][j]), j*rectWidth + 2, i * rectHeight + numberSize);
				g.setFont(new Font("Arial", Font.PLAIN,fontSize));
				
				//om den nuvarande rutan har samma nummer som ett nummber i unlockedNumbers
				//dvs. man har skrivit in en bokstav p� en ruta med samma nummer
				if(letters.get(gameBoard.board[i][j]) != null) {
					//skriver ut bokstaven fr�n letters med samma index som den nuvarande rutans nummer
					g.drawString(String.valueOf(letters.get(gameBoard.board[i][j])), j * rectWidth + rectWidth / 2 - fontSize / 2, i * rectHeight + fontSize + rectHeight/2 - fontSize / 2);
				}
			}
		}
	}
	
	public static void main(String[] args){
		JTextField xRowInput = new JTextField(10);
		JTextField yRowInput = new JTextField(10);
		JButton acceptButton = new JButton("Set x and y rows");

		
		xRowInput.setText("X");
		yRowInput.setText("Y");
		
		acceptButton.setEnabled(true);
		acceptButton.setActionCommand("Start");
				
		int boardXLength = 0;
		int boardYLength = 0; 
		
		JFrame settingsFrame = new JFrame();
		
		settingsFrame.setSize(500,100);
		settingsFrame.setTitle("Set board");
		settingsFrame.setVisible(true);
		settingsFrame.setResizable(false);
		settingsFrame.setDefaultCloseOperation(3);
		settingsFrame.add(xRowInput, BorderLayout.WEST);
		settingsFrame.add(yRowInput, BorderLayout.EAST);
		settingsFrame.add(acceptButton);
		
		while(!(boardXLength > 0 && boardYLength > 0)){
			
			acceptButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(e.getActionCommand() == "Start"){
						startGame = true;
					}
				}
			});
			if(startGame == true){
				boardXLength = Integer.parseInt(xRowInput.getText());
				boardYLength = Integer.parseInt(yRowInput.getText());
			}			
		}
		
		settingsFrame.dispose();
		
		gameBoard = new Board(boardXLength, boardYLength, rectWidth, rectHeight);
		
		int screenHeight = rectHeight * gameBoard.board.length + rectHeight / 2;
		int screenWidth = rectWidth * gameBoard.board[0].length + 7;
		
		//s�tter alla v�rden i letters till ' ' f�r att kunna anv�nda mig av ".set()" ist�llet f�r ".add() n�r jag l�gger in nya bokst�ver"
		for( int i = 0; i < gameBoard.board.length; i++){
			for(int j = 0; j < gameBoard.board[0].length; j++){
				letters.add(' ');
			}
		}
		//skapar en JFrame
		JFrame frame = new JFrame();
		frame.setSize(screenWidth, screenHeight); //width and height of the window
		frame.add(krypto);
		frame.setTitle("SPEEEEEL"); //sets the title of the window
		frame.setResizable(false); //the user can't resize the window
		frame.setDefaultCloseOperation(3); //then the player tries to exit the window, it will close
		frame.setVisible(true); //makes it possible to see the window
				
		
		//spel-loopen, kollar efter input, sedan om n�got r�rt p� sig och sedan ritas allt ut p� nytt
		while(true){
			//flyttar p� "checkern"
			krypto.move();
			//skriver ut allt p� skr�men
			krypto.repaint();
		}
	}		
}
