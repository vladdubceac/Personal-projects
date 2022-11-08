package lesson4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Application {

	public static void main(String[] args) {
		
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add("Random");
		hashSet.add("Tooth Brush");
		hashSet.add("Computer");
		hashSet.add("Cloths");
		
		HashSet<Employee> hashSet1 = new HashSet<Employee>();
		hashSet1.add(new Employee("Mike", 3500, "Accounting"));
		hashSet1.add(new Employee("Paul", 3000, "Admin"));
		hashSet1.add(new Employee("Peter", 4000, "IT"));
		hashSet1.add(new Employee("Angel", 2000, "Maint"));
		
//		List<Integer> li = new ArrayList<Integer>(list1);
//		
//		ArrayList<Integer> newList = new ArrayList<Integer>();
//		newList.add(10);
//		newList.add(67);
//		newList.add(15);
//		
//		list1.addAll(newList);
//		System.out.println(list1);
//		
//		list1.removeAll(newList);
//		System.out.println(list1);
//		
//		boolean hasValue = list1.contains(67);
//		System.out.println(hasValue);
//
//		hasValue = list1.contains(48);
//		System.out.println(hasValue);
//
//		hasValue = list1.isEmpty();
//		System.out.println(hasValue);
//		
//		hasValue = list1.retainAll(newList);
//		System.out.println(hasValue);
//		
//		System.out.println(list1);
//
//		list1.clear();
//		System.out.println(list1);
		
		ArrayList<String> myList = new ArrayList<String>(hashSet);
		
		Collections.sort(myList);
		
		System.out.println(myList);
		
		ArrayList<Employee> myList1 = new ArrayList<Employee>(hashSet1);
		
		Collections.sort(myList1);
		
		System.out.println(myList1);
		
	}

}
