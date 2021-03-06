import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Dummy extends LivingThing {

    private final ImageIcon image = new ImageIcon("bin/sheep.gif"); 

    protected Point position;

    protected Pasture pasture;
    
    int move; 
    
    
    public Dummy(Pasture pasture) {
    	this.pasture = pasture;
   
    }

    public Dummy(Pasture pasture, Point position) {
    	this.pasture   = pasture;
    	this.position  = position;
    	this.type = "sheep"; 
    	move = 0;
    }
    
    public Point getPosition() {
		return position;
	}

	 public void setPosition(Point newPosition) { 
	    //If the new position is inside the borders the dummy will move
	   // if(newPosition.x >= 0  && newPosition.x < this.pasture.getWidth() && newPosition.y >= 0  && newPosition.y < this.pasture.getHeight()){
	    	Collection world = this.pasture.getEntities();
	    	Iterator it = world.iterator();
	    	
	    	boolean grass = false;
	    	
	    	
	    	while(it.hasNext()){
	    		Entity e = (Entity) it.next();
	    		if(e.getPosition().getX() == newPosition.getX() && e.getPosition().getY() == newPosition.getY()){
	    			if(e.getType().equals("plant")){
	    				grass = true;
	    			}    			
	    		}
	    		if(grass){
	    			this.pasture.removeEntity(e);
	    			grass = false;
	    		}
	    		
	    	}
	    	
	    	if(newPosition.x < 0 ){
	    		newPosition.x = pasture.getWidth() -1; 
	    	}
	    	if(newPosition.x == pasture.getWidth())
	    		newPosition.x = 0; 
	    	if(newPosition.y < 0 )
	    		newPosition.y = pasture.getHeight() -1; 
	    	if(newPosition.y == pasture.getHeight())
	    		newPosition.y = 0; 
	    	
	    	position = newPosition;  //Sets the current position equal to the new position
	    	//}
	 }
	 
    public void tick() {
    	move++;
		if(move % 1  == 0)
    	moveToClosestGrass();
    	//setPosition(new Point((int)getClosestGrass().getX(), (int)getClosestGrass().getY()));
    	//Tries to set a new position based on random x and y coordinates
    	//setPosition(new Point((int)getPosition().getX() + ThreadLocalRandom.current().nextInt(-1, 1 + 1),(int)getPosition().getY() + ThreadLocalRandom.current().nextInt(-1, 1 + 1)));    	
    }

    public String getType() {
    	return this.type;
    }
    
    public ImageIcon getImage() { 
    	return image; 
    }

    public void moveToClosestGrass(){
    	Point plantPosition = null; //Keeps track of the position of the latest grass
    	Point newPosition = position;  //The new position for the dummy
    	Point closestPlantPos = position; //The closest grass
    	
    	Point wolfPosition = null;
    	Point closestWolfPos = position; 
    	
    	Collection world = this.pasture.getEntities();
    	Iterator it = world.iterator();
    	int plantNumber = 0; 
    	int wolfNumber = 0; 
    	
    	boolean moveFromWolf = false;
    	
    	//Loops through all the entities
    	while(it.hasNext()){
    		
    		moveFromWolf = false; 
    		Entity e = (Entity) it.next();
    		if(e.getType().equals("wolf")){
    			wolfNumber++; 
    			wolfPosition = e.getPosition();
    			
    			if(wolfNumber == 1){
    				closestWolfPos = wolfPosition;
    			}
    			else{
    				if(checkDistance(this.position, wolfPosition) < checkDistance(this.position, closestWolfPos)){
    					closestWolfPos = wolfPosition; 
    				}
    			}
    		}
    		
    		//If the entity is grass 
    		else if(e.getType().equals("plant") && checkDistance(this.position, closestWolfPos) > 6){
    			plantNumber++; 
    			//Gets the plants position
    			plantPosition = e.getPosition();
    			
    			//If it is the first plant the closest position is the first plants position
    			if(plantNumber == 1){
        			closestPlantPos = plantPosition; 
        		}else{
        			if(checkDistance(this.position, plantPosition) < checkDistance(this.position, closestPlantPos)){
        				closestPlantPos = plantPosition; 
        				
        			}
        		}
    		
    		}
    		else{
    			moveFromWolf = true; 
    		}
    	}
    	
    	if(moveFromWolf){
    		if(closestWolfPos.getX() > position.getX())
        		newPosition.x = (int) (position.getX() - 1); 
        	else if(closestWolfPos.getX() < position.getX())
        		newPosition.x = (int) (position.getX() +1);
        	
        	if(closestWolfPos.getY() > position.getY())
        		newPosition.y = (int) (position.getY() - 1); 
        	else if(closestWolfPos.getY() < position.getY())
        		newPosition.y = (int) (position.getY() +1);
    	}else{
    	
    	if(closestPlantPos.getX() < position.getX())
    		newPosition.x = (int) (position.getX() - 1); 
    	else if(closestPlantPos.getX() > position.getX())
    		newPosition.x = (int) (position.getX() +1);
    	
    	if(closestPlantPos.getY() < position.getY())
    		newPosition.y = (int) (position.getY() - 1); 
    	else if(closestPlantPos.getY() > position.getY())
    		newPosition.y = (int) (position.getY() +1);
    	}
    	
    	setPosition(new Point(newPosition));
    	
    	
    	
    }
	

   
}
