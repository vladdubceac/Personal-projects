package lesson1;
import java.util.ArrayList;
import java.util.LinkedList;

public class Application {

	public static void main(String[] args) {

		ArrayList<String> words = new ArrayList<>();
		words.add("hello");
		words.add("there");
		words.remove(0);
		words.add(10+"");
		words.add(9+"");
//		words.add(12.00);
		words.add("10");

		String item1 = words.get(2);
		String item2 = words.get(3);
		
		System.out.println(item1 + item2);
		
		LinkedList<Integer> numbers = new LinkedList<>();
		numbers.add(100);
		numbers.add(200);
		numbers.add(45);
		numbers.add(1000);
		numbers.removeFirst();
		
		for (int number : numbers) {
			System.out.println(number);
		}

	}

}
