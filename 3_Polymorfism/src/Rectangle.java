
public class Rectangle extends Shape {

	//constructor which sets the height and width of the rectangle and calculates the area
	public Rectangle(double width, double height){
		this.setWidth(width);
		this.setHeight(height);
		
		this.calcArea();
	}
	
	//calculates the are of the rectangle
	public void calcArea(){
		this.setArea(this.getHeight() * this.getWidth());
	}
}
