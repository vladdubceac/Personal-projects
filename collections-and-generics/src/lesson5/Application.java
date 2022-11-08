package lesson5;

import java.util.Map;
import java.util.TreeMap;

public class Application {

	public static void main(String[] args) {

		Map<String, String> dictionary = new TreeMap<>();
		dictionary.put("Brave", "ready to face and endure danger or pain; showing courage.");
		dictionary.put("Brilliant", "exceptionally clever or talented.");
		dictionary.put("Joy", "a feeling of great pleasure and happines.");
		dictionary.put("Confidence", "the state of feeling certain about the thruth of something.");

		for (String word : dictionary.keySet()) {
			System.out.println(word + " - " + dictionary.get(word));
		}

		for (Map.Entry<String, String> entry : dictionary.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}

}
