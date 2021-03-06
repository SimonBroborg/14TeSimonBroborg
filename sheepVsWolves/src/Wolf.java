import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends LivingThing{
	 private final ImageIcon image = new ImageIcon("bin/wolf.gif"); 

	    protected Point position;

	    protected Pasture pasture;
	    int move = 0;

	    
	    public Wolf(Pasture pasture, Point position) {
	    	this.pasture   = pasture;
	    	this.position  = position;
	    	this.type = "wolf"; 
	    		    }
	
	public void tick() {
		move++;
		if(move % 2  == 0)
			moveToClosestSheep(); 
	}
	
	public void setPosition(Point newPosition) { 
	    //If the new position is inside the borders the dummy will move
	    if(newPosition.x >= 0  && newPosition.x < this.pasture.getWidth() && newPosition.y >= 0  && newPosition.y < this.pasture.getHeight()){
	    	Collection world = this.pasture.getEntities();
	    	Iterator it = world.iterator();
	    	
	    	boolean sheep = false;
	    	while(it.hasNext()){
	    		Entity e = (Entity) it.next();
	    		if(e.getPosition().getX() == newPosition.getX() && e.getPosition().getY() == newPosition.getY()){
	    			if(e.getType().equals("sheep")){
	    				sheep = true;
	    			}    			
	    		}
	    		if(sheep){
	    			this.pasture.removeEntity(e);
	    			sheep = false;
	    		}
	    		
	    	}
	    	
	    	
	    	position = newPosition;  //Sets the current position equal to the new position
	    }
	    
	 }
	
	  
    public String getType() {
    	return this.type;
    }
    
    public ImageIcon getImage() { 
    	return image; 
    }

    public Point getPosition() {
		return position;
	}


    public void moveToClosestSheep(){
    	Point sheepPosition = null; //Keeps track of the position of the latest grass
    	Point newPosition = position;  //The new position for the dummy
    	Point closestSheepPos = position; //The closest grass
    	
    	Collection world = this.pasture.getEntities();
    	Iterator it = world.iterator();
    	int sheepNumber = 0; 
    	
    	//Loops through all the entities
    	while(it.hasNext()){
    		
    		Entity e = (Entity) it.next();
    		
    		//If the entity is grass 
    		if(e.getType().equals("sheep")){
    			sheepNumber++; 
    			//Gets the plants position
    			sheepPosition = e.getPosition();
    			
    			//If it is the first plant the closest position is the first plants position
    			if(sheepNumber == 1){
        			closestSheepPos = sheepPosition; 
        		}else{
        			if(checkDistance(this.position, sheepPosition) < checkDistance(this.position, closestSheepPos)){
        				closestSheepPos = sheepPosition; 
        				
        			}
        		}
    		
    		}
    	}
    	
    	if(closestSheepPos.getX() < position.getX())
    		newPosition.x = (int) (position.getX() - 1); 
    	else if(closestSheepPos.getX() > position.getX())
    		newPosition.x = (int) (position.getX() +1);
    	
    	if(closestSheepPos.getY() < position.getY())
    		newPosition.y = (int) (position.getY() - 1); 
    	else if(closestSheepPos.getY() > position.getY())
    		newPosition.y = (int) (position.getY() +1);
    	
    	
    	setPosition(new Point(newPosition));
    	
    	
    	
    }
}
