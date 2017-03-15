import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;


abstract class LivingThing implements Entity{

	protected Point position; 
	
	protected ImageIcon image = new ImageIcon("bin/unknown.gif"); 
	
	protected Pasture pasture; 
	
	public String type; 
	
	public LivingThing(){
		
	}
	 public Point getPosition() {
		return position;
	}

	 public void setPosition(Point newPosition) { 
	    //If the new position is inside the borders the dummy will move
	    if(newPosition.x > 0  && newPosition.x < this.pasture.getWidth() && 
	    		newPosition.y > 0  && newPosition.y < this.pasture.getHeight()){
	    	position = newPosition;  //Sets the current position equal to the new position
	    }
	 }
	 
	 public ImageIcon getImage(){
		 return image;
	 }
	 
	 public String getType(){
		 return type; 
	 }
	 
	 public float checkDistance(Point a, Point b){
		 float distance = 0;
		 
		 distance = (float) Math.sqrt( (Math.pow(b.getX() - a.getX(), 2)+ Math.pow( b.getY() - a.getY(),2)));
		 
		 return distance; 
	 }

}
