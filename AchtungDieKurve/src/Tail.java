import java.awt.Color;
import java.awt.Graphics2D;

public class Tail {
	float x; 
	float y; 
	Color color; 
	public Tail(float x, float y, Color color){
		this.x = x; 
		this.y = y; 
		this.color = color; 
	}
	
	
	public void paint(Graphics2D g) {
		g.fillOval((int) x, (int) y, 10, 10);

	}
}
