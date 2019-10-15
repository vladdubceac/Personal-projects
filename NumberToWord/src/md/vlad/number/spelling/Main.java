package md.vlad.number.spelling;

public class Main {
	// variable to hold string representation of number
	static String unitsArray[] = { "zero", "unu", "doi", "trei", "patru", "cinci", "sase", "sapte", "opt", "noua",
			"zece", "unsprezece", "dousprezece", "treisprezece", "paisprezece", "cinsprezece", "saisprezece",
			"saptesprezece", "optsprezece", "nouasprezece" };
	static String tensArray[] = { "zero", "zece", "douazece si", "treizeci si", "patruzeci si", "cincizeci si",
			"saizeci si", "saptezeci si", "optzeci si", "nouazeci si" };

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for (long l = 999_000_999; l < 1_000_000_001L; l++) {
			System.out.println(SpellingNumbers.spell(l));
		}
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("Time elapsed = " + timeElapsed);
	}

	private static String numberToWord(int number) {
		String words = "";
		if (number == 0) {
			return "zero";
		}
		// add minus before conversion if the number is less than 0
		if (number < 0) { // convert the number to a string
			String numberStr = "" + number;
			// remove minus before the number
			numberStr = numberStr.substring(1);
			// add minus before the number and convert the rest of number
			return "minus " + numberToWord(Integer.parseInt(numberStr));
		}
		// check if number is divisible by 1 million
		if ((number / 1000000) > 0) {
			words += numberToWord(number / 1000000) + " million ";
			number %= 1000000;
		}
		// check if number is divisible by 1 thousand
		if ((number / 1000) > 0) {
			words += numberToWord(number / 1000) + " o mie ";
			number %= 1000;
		}
		// check if number is divisible by 1 hundred
		if ((number / 100) > 0) {
			words += numberToWord(number / 100) + " sute ";
			number %= 100;
		}

		if (number > 0) {
			// check if number is within teens
			if (number < 20) {
				// fetch the appropriate value from unit array
				words += unitsArray[number];
			} else {
				// fetch the appropriate value from tens array
				words += tensArray[number / 10];
				if ((number % 10) > 0) {
					words += " " + unitsArray[number % 10];
				}
			}
		}

		return words;
	}
}
