
public class Triangle extends Shape {

	//constructor which sets the height and width of the triangle and calculates the area
	public Triangle(double width, double height){
		this.setWidth(width);
		this.setHeight(height);
		
		this.calcArea();
	}
	
	//calculates the area of the triangle
	public void calcArea(){
		this.setArea(this.getHeight() * this.getWidth() / 2);
	}
}
