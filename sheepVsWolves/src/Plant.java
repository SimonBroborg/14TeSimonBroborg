import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Plant extends LivingThing{ 
	private int growTime;
	public Plant(Pasture pasture, Point position, String imagePath){
		this.pasture = pasture;
		this.position = position;  //Sets the position 
		this.image =  new ImageIcon(imagePath);  //Sets the image of the plant based on url
		this.type = "plant"; //Sets the type
		
		growTime = 0;
	}

	
	public void tick() {
		//Grows out (creates a new grass object) after 7 ticks
		growTime++;
		if(growTime % 15 == 0)
			growOut();	
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	//Tries to create a new plant from the current plant
	public void growOut(){
		
			//Gets a random position one tile away from the current plant
	        Point position = new Point((int)getPosition().getX() + ThreadLocalRandom.current().nextInt(-1, 1 + 1),(int)getPosition().getY() + ThreadLocalRandom.current().nextInt(-1, 1 + 1));
		    
	        //Makes sure the plant won't grow outside the window
	        if(position.x > 0  && position.x < this.pasture.getWidth() && 
		    	position.y > 0  && position.y < this.pasture.getHeight()){
				    
	        	Collection world = this.pasture.getEntities();
	        	Iterator it = world.iterator(); 
	        	boolean canGrow = true; 
	        	while(it.hasNext()){
	        		Entity e = (Entity) it.next();
	        		
	        		if(e.getPosition().getX() == position.getX() && e.getPosition().getY() == position.getY()){
	        			canGrow = false;
	        		}
	        	}
	        	if(canGrow){
	        		//Creates a new plant 
	        		Entity plant = new Plant(this.pasture, position, "bin/plant.gif");
				   
	        		//Adds the new plant to the world
	        		pasture.addEntity(plant); 
	        	}
	        		
	        	}
	        
		    }
		
		
	
}
