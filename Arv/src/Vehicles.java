
public abstract class Vehicles {
	private String color; //the color of the vehicle
	private float price; //the price of the vehicle in skr
	private float weight; //the weight of the vehicle in kg
	
	public void setColor(String newColor){
		color = newColor;
	}
	
	public void setPrice(float newPrice){
		price = newPrice;
	}
	
	public void setWeight(float newWeight){
		weight = newWeight;
	}
	
	public float getPrice(){
		return price;
	}
	
	public float getWeight(){
		return weight;
	}
	
	public String getColor(){
		return color;
	}
	
}
