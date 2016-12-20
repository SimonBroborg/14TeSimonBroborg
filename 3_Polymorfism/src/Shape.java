
public class Shape {
	
	public static void main(String[]args){
		//sets the width and heights of the shapes through the constructors
		Triangle triangle = new Triangle(4.0, 6.0);
		Rectangle rect = new Rectangle(4.0, 6.0);
		
		//prints out the ares of the different shapes
		System.out.println(triangle.getArea());
		System.out.println(rect.getArea());
	}
	
	//private variables
	private double height;
	private double width;
	private double area;
	
	
	//Setter and getters for the private variables
	public double getHeight(){
		return height;
	}
	public void setHeight(double height){
		this.height = height;
	}
	
	public double getWidth(){
		return width;
	}
	public void setWidth(double width){
		this.width = width;
	}
	
	public double getArea(){
		return area;
	}
	public void setArea(double area){
		this.area = area;
	}
}
