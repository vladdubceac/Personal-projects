package lesson2;

import java.util.ArrayList;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		ArrayList<String> animals = new ArrayList<>();
		animals.add("Lion");
		animals.add("cat");
		animals.add("Dog");
		animals.add("Bird");

		for (int i = 0; i < animals.size(); i++) {
			System.out.println(animals.get(i));
		}

		for(String value : animals) {
			System.out.println(value);
		}
		
		animals.forEach(System.out::println);
		
		List<Vehicle> vehicles = new ArrayList<>();
		Vehicle vehicle2 = new Vehicle("Toyota", "Camry", 12000, false);
		
		vehicles.add(new Vehicle("Honda","Accord", 12000,false));
		vehicles.add(vehicle2);
		vehicles.add(new Vehicle("Jeep", "Wrangler", 25000, true));
		
		for (Vehicle vehicle : vehicles) {
//			System.out.println(vehicle.getMake());
//			System.out.println(vehicle.getModel());
//			System.out.println(vehicle.getPrice());
			System.out.println(vehicle);
		}
		
		printElements(vehicles);
		printElements(animals);
	}

	
	public static void printElements(List someList) {
		for(int i=0;i<someList.size();i++) {
			System.out.println(someList.get(i));
		}
	}
}
