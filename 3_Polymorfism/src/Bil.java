
public class Bil extends Fordon {
	private double horsePowers;
	private double gas;
	private String regNr;
	
	//constructor which sets the specs for the vehicle when it is created
	public Bil(double price, double weight, String color, double horsePower, double gas, String regNr){
		this.setPrice(price);
		this.setWeight(weight);
		this.setColor(color);
		this.horsePowers = horsePower;
		this.gas = gas;
		this.regNr = regNr;
	}
	
	//Bil's own printInfo function
	public void printInfo(){
		System.out.println("Price: " +this.getPrice());
		System.out.println("Weight: " + this.getWeight());
		System.out.println("Color: " + this.getColor());
		System.out.println("RegNr: "+ this.getRegNr());
	}
		
	//Getters for the private variables
	public double getGas(){
		return gas;
	}
	
	public double getHorsePower(){
		return horsePowers;
	}
	
	public String getRegNr(){
		return regNr;
	}
}
