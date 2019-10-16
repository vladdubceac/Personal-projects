package utils;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import objects.ProductInfo;
import utils.HtmlUtils.Attributes;
import utils.HtmlUtils.Tags;
import utils.ParsingUtils.CssClasses;
import utils.ParsingUtils.RegExp;

public final class CrawlingUtils {

	public static Element[] getPageButtonElements(Element paginationElement) {
		Elements numberedPagesButtons = paginationElement.select("." + CssClasses.A_NORMAL);
		Element[] numberedPagesElements = numberedPagesButtons.toArray(new Element[] {});
		return numberedPagesElements;
	}

	public static ProductInfo extractProductInfo(Element el) {
		String itemASIN = extractASINFromElement(el);
		String name = extractProductNameFromElement(el);
		double nrOfStars = extractNumberOfStars(el);
		long numberOfReviews = extractNumberOfReviews(el);
		double price = extractPrice(el);
		return new ProductInfo(itemASIN, name, price, numberOfReviews, nrOfStars);
	}

	private static double extractPrice(Element el) {
		String priceText = el.selectFirst("." + CssClasses.A_COLOR_PRICE).text();
		NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
		numberFormat.setCurrency(Currency.getInstance(Locale.GERMANY));
		double price = 0.0d;
		try {
			price = numberFormat.parse(priceText).doubleValue();
		} catch (ParseException e) {
			try {
				price = numberFormat.parse(priceText.replaceAll(RegExp.LETTERS, "").trim()).doubleValue();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return price;
	}

	private static long extractNumberOfReviews(Element el) {
		Element nrReviewsElement = el.selectFirst("." + CssClasses.A_SIZE_SMALL + "." + CssClasses.A_LINK_NORMAL);
		String elementText = nrReviewsElement.text();
		long numberOfReviews = 0L;
		try {
			Number parsedNumber = NumberFormat.getInstance(Locale.GERMANY).parse(elementText);
			numberOfReviews = parsedNumber.longValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return numberOfReviews;
	}

	private static double extractNumberOfStars(Element el) {
		String nrOfStarsText = el.selectFirst("[" + Attributes.TITLE + "~=" + RegExp.RATING_ELEMENT_TITLE + "]")
				.selectFirst("." + CssClasses.A_ICON_ALT).text().split(RegExp.WHITE_SPACE)[0];
		double nrOfStars = Double.parseDouble(nrOfStarsText.replace(",", "."));
		return nrOfStars;
	}

	private static String extractProductNameFromElement(Element el) {
		String name = el
				.selectFirst(Tags.DIV + "[" + Attributes.ARIA_HIDDEN + "=true]." + CssClasses.PROD_NAME_ARIA_CLASS)
				.text();
		try {
			return new String(name.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return name;
	}

	private static String extractASINFromElement(Element el) {
		String itemASIN = el.selectFirst(Tags.A + "." + CssClasses.A_LINK_NORMAL).absUrl(Attributes.HREF);
		itemASIN = itemASIN
				.substring(itemASIN.indexOf(ParsingUtils.ASIN_PREFIX_IN_URL) + ParsingUtils.ASIN_PREFIX_IN_URL.length())
				.split("\\/")[0];
		return itemASIN;
	}
}
