import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class PastureGUI extends JFrame implements ActionListener {
   
    public static void main(String[] args) {
        PastureGUI gui = new PastureGUI();
    }
    
    //Icon for each empty space in the pasture
    private final ImageIcon II_EMPTY = new ImageIcon("bin/empty.gif");
     
    //Creates and object of the pasture
    private Pasture pasture;
    
    //2D array containing all the image objects showing on the screen
    private JLabel[][] grid;
    
    //Creates the button object which starts the game
    private JButton startButton = new JButton("Start");
    
    //Creates an exit button
    private JButton exitButton = new JButton("Exit");
    
    //Creates a pause button
    private JButton pauseButton = new JButton("Pause");
    
    private JButton restartButton = new JButton("Restart");
    
    
    public PastureGUI() {
    	//Creates a new pasture object, uses this as parameter
    	pasture = new Pasture(this);
    	
    	//Inits the pasture graphical user interface
    	initPastureGUI();
    }
    
    private void initPastureGUI() {
    	//Sets the size of the pasture. 
        setSize(pasture.getWidth()*30, pasture.getHeight()*30);
	
        startButton.addActionListener(this);
        exitButton.addActionListener(this);
        pauseButton.addActionListener(this);
        restartButton.addActionListener(this);
             	
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,2));
        buttons.add(startButton);
        buttons.add(pauseButton);
        buttons.add(restartButton);
        buttons.add(exitButton);

        

	JPanel field = new JPanel();
        field.setBackground(new Color(27,204,89));
        field.setLayout(new GridLayout(pasture.getHeight(),
				       pasture.getWidth()));
        grid = new JLabel[pasture.getWidth()][pasture.getHeight()];
	
        for (int y = 0; y < pasture.getHeight(); y++) {
            for (int x = 0; x < pasture.getWidth(); x++) {
                grid[x][y] = new JLabel(II_EMPTY);
                grid[x][y].setVisible(true);
                field.add(grid[x][y]);
            }
        }
	
	Container display = getContentPane();
        display.setBackground(new Color(27,204,89));
        display.setLayout(new BorderLayout());
        display.add(field,BorderLayout.CENTER);
        display.add(buttons,BorderLayout.SOUTH);

	startButton.setEnabled(true);
	pauseButton.setEnabled(true);
	exitButton.setEnabled(true);
	restartButton.setEnabled(true);
	
	updateAll();
	setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == startButton) {
	    startButton.setEnabled(false);
	    pauseButton.setEnabled(true);
	    pasture.start();   
	}
	if(e.getSource() == exitButton){
		System.exit(0);
	}
	
	if(e.getSource() == pauseButton){
		pauseButton.setEnabled(false);
		startButton.setEnabled(true);
		pasture.stop();
	}
	if(e.getSource() == restartButton){
		startButton.setEnabled(true);
	    pauseButton.setEnabled(false);
	    
	    Collection world = pasture.getEntities();
	    Iterator it = world.iterator();
	    
	    while(it.hasNext()){
	    	pasture.removeEntity((Entity) it.next());
	    }

	    pasture.restart();
	    pasture.stop();
	}
    }
    

    public void updateAll() {
    	int width  = pasture.getWidth();
    	int height = pasture.getHeight();
	
        Entity[][] tempGrid = 
	    new Entity[width][height];


        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                tempGrid[x][y] = null;
	
        Collection world  = pasture.getEntities();
        Iterator   it     = world.iterator();
	
        while (it.hasNext()) {
        	Entity e = (Entity)it.next(); 
        	int    x = (int)e.getPosition().getX();
        	int    y = (int)e.getPosition().getY();
	    
        	if (tempGrid[x][y] == null)	tempGrid[x][y] = e;
        }
	
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	ImageIcon icon ;
                Entity e = tempGrid[x][y];
		
                if (e == null) 
                	icon = II_EMPTY;
                else 
                	icon = e.getImage();
                grid[x][height - y - 1].setIcon(icon);
            }
        }
    }
}

