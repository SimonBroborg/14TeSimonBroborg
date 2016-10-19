import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main extends JPanel{
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		Vehicles car = new Car(100, 1584, "red");
		Vehicles bike = new Bicycle(10, 100, "blue");
		Vehicles truck = new Truck(1000, 1230, "black");
		
		ArrayList<Vehicles> vehicles = new ArrayList();
		vehicles.add(car);
		vehicles.add(bike);
		vehicles.add(truck);
		String j = sc.nextLine();
		while(!(j.equals("exit"))){
			j = sc.nextLine();
			if(j.equals("car")){
				System.out.println("YES");
			}
		}	
		vehicles.clear();
		System.out.println("Cleared");
		
	}
}