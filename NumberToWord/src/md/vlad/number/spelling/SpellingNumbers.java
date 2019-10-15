package md.vlad.number.spelling;

import java.text.DecimalFormat;

import md.vlad.number.spelling.utils.NumberConstants;
import md.vlad.number.spelling.utils.NumberQuantity;

public class SpellingNumbers {

	public static String spellingLong(long number) {
		String s = "";
		String formatPattern = "###,###";
		long l = Long.MAX_VALUE;
		while (l >= 1) {
			s = s + new DecimalFormat(formatPattern).format(l) + "\n";
			l /= 2;
		}
		;
		return s;
	}

	public static String spell(long number) {
		StringBuffer spelling = new StringBuffer();

		if (number < 1000L) {
			return numberLessThan1_000ToWord(number);
		} else if (number >= 1000L && number < 1_000_000L) {
			return numberLessThan1_000_000_ToWord(number);
		} else if (number >= 1_000_000 && number < 1_000_000_000) {
			return numberLessThan_Billion_ToWord(number);
		}
		return spelling.toString();
	}

	private static String numberLessThan_Billion_ToWord(long number) {
		StringBuilder spelling = new StringBuilder();
		int millions = (int) (number / 1_000_000);
		if (millions == 0) {
			return numberLessThan1_000_000_ToWord(millions);
		}
		if (millions <= 10) {
			spelling.append(NumberQuantity.MILLIONS_QUANTITIES[millions - 1] + " ");
			if (millions == 1) {
				spelling.append(NumberQuantity.MILLION + " ");
			} else {
				spelling.append(NumberQuantity.MILLIONS + " ");
			}
		} else {
			spelling.append(numberLessThan1_000ToWord(millions) + " " + NumberQuantity.MILLIONS + " ");
		}
		int remainder = (int) (number % 1_000_000);
		if (remainder > 0) {
			spelling.append(numberLessThan1_000_000_ToWord(remainder));
		}
		return spelling.toString();
	}

	private static String numberLessThan1_000_000_ToWord(long number) {
		StringBuilder spelling = new StringBuilder();
		int thousands = (int) (number / 1000);
		if (thousands == 0) {
			return numberLessThan1_000ToWord(number);
		}

		if (thousands <= 10) {
			spelling.append(NumberQuantity.HUNDREDS_THOUSANDS_QUANTITIES[thousands - 1] + " ");
			if (thousands == 1) {
				spelling.append(NumberQuantity.THOUSAND + " ");
			} else {
				spelling.append(NumberQuantity.THOUSANDS + " ");
			}
		} else {
			spelling.append(numberLessThan1_000ToWord(thousands) + " " + NumberQuantity.THOUSANDS + " ");
		}
		int hundreds = (int) (number % 1_000);
		if (hundreds > 0) {
			spelling.append(numberLessThan1_000ToWord(hundreds));
		}
		return spelling.toString();
	}

	private static String numberLessThan1_000ToWord(long number) {
		StringBuilder spelling = new StringBuilder();
		int hundreds = (int) number / 100;
		if (hundreds == 0) {
			return numberLessThan100ToWord(number);
		}

		spelling.append(NumberQuantity.HUNDREDS_THOUSANDS_QUANTITIES[hundreds - 1]);
		if (hundreds == 1) {
			spelling.append(" " + NumberQuantity.HUNDRED + " ");
		} else {
			spelling.append(" " + NumberQuantity.HUNDREDS + " ");
		}
		int remainder = (int) number % 100;
		if (remainder > 0) {
			spelling.append(numberLessThan100ToWord(remainder));
		}
		return spelling.toString();
	}

	private static String numberLessThan100ToWord(long number) {
		StringBuffer spelling = new StringBuffer();

		if (number <= 10L) {
			return NumberConstants.ZERO_TO_TEN[(int) number];
		} else {
			int dozens = (int) (number / 10);
			int units = (int) (number % 10);
			if (dozens == 1) {
				return NumberConstants.ELEVEN_TO_NINETEEN_PREFIXES[units - 1] + NumberConstants.X_TEEN_JOIN
						+ NumberConstants.TEN;
			}

			switch (dozens) {
			case 2:
				spelling.append(NumberQuantity.TWO_F);
				break;
			case 3:
				spelling.append(NumberQuantity.THREE);
				break;
			case 4:
				spelling.append(NumberQuantity.FOUR);
				break;
			case 5:
				spelling.append(NumberQuantity.FIVE);
				break;
			case 6:
				spelling.append(NumberQuantity.SIX);
				break;
			case 7:
				spelling.append(NumberQuantity.SEVEN);
				break;
			case 8:
				spelling.append(NumberQuantity.EIGHT);
				break;
			case 9:
				spelling.append(NumberQuantity.NINE);
				break;
			default:
				break;
			}
			spelling.append(NumberQuantity.DOZENS);
			if (units > 0) {
				spelling.append(" și " + NumberConstants.ZERO_TO_TEN[units]);
			}
		}
		return spelling.toString();
	}
}
