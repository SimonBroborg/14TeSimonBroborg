
public class Fordon {
	
	//variables used for all vehicles
	private double price;
	private double weight;
	private String color;
	
	public static void main(String[]args){
		//Creates new vehicles with required arguments
		Bil bil = new Bil(100, 1509.43, "Red", 100, 7.65, "420Blaze");
		Cykel cykel = new Cykel(200, 433, "Black");
		
		//Different printInfo functions, "bil" uses its own and "cykel" uses Fordon's
		bil.printInfo();
		cykel.printInfo();
	}
	
	//Print out info about the vehicle
	public void printInfo(){
		System.out.println("Price: "+this.price);
		System.out.println("Weight: "+this.weight);
		System.out.println("Color: "+ this.getColor());
	}
	
	//Getters and setters for private variables
	public double getPrice(){
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getWeight(){
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}
	void setColor(String color) {
		this.color = color;
	}

	
	
	
}
