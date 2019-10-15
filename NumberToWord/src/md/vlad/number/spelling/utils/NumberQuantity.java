package md.vlad.number.spelling.utils;

public interface NumberQuantity {
	String ONE_F = "una";
	String ONE_M = "un";
	String TWO_M = "doi";
	String TWO_F = "două";
	String THREE = "trei";
	String FOUR = "patru";
	String FIVE = "cinci";
	String SIX = "șase";
	String SIX_DOZENS_PREF = "șai";
	String SEVEN = "șapte";
	String EIGHT = "opt";
	String NINE = "nouă";
	String TEN = "zece";
	String DOZENS = "zeci";

	String HUNDRED = "sută";
	String HUNDREDS = "sute";
	String THOUSAND = "mie";
	String THOUSANDS = "mii";
	String MILLION = "milion";
	String MILLIONS = "milioane";
	String BILLION = "miliard";
	String BILLIONS = "miliard";

	String[] HUNDREDS_THOUSANDS_QUANTITIES = new String[] { ONE_F, TWO_F, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
			TEN };
	String[] MILLIONS_QUANTITIES = new String[] { ONE_M, TWO_F, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN };
}
