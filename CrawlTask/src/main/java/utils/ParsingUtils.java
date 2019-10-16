package utils;

public interface ParsingUtils {
	String ASIN_PREFIX_IN_URL = "dp/";

	interface CssClasses {
		String A_PAGINATION = "a-pagination";
		String A_NORMAL = "a-normal";
		String A_ORDERED_LIST = "a-ordered-list";
		String A_COLOR_PRICE = "a-color-price";
		String A_LINK_NORMAL = "a-link-normal";
		String PROD_NAME_ARIA_CLASS = "p13n-sc-truncate";
		String A_ICON_ALT = "a-icon-alt";
		String A_SIZE_SMALL = "a-size-small";
	}

	interface RegExp {

		String LETTERS = "[A-Za-z]";
		String RATING_ELEMENT_TITLE = "(von 5 Sternen)$";
		String WHITE_SPACE = "\\s";

	}
}
