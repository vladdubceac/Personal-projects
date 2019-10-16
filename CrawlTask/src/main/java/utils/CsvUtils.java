package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import objects.ProductInfo;

public final class CsvUtils {
	public static LinkedHashSet<String> prepareCSVLines(Set<ProductInfo> productInfoSet, String csvSeparator) {
		return productInfoSet.stream().map(productInfo -> prepareCSVLine(productInfo, csvSeparator))
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}

	private static String prepareCSVLine(ProductInfo productInfo, String separator) {
		String s = Stream
				.of(new Object[] { productInfo.getASIN(), productInfo.getProductName(), productInfo.getPrice(),
						productInfo.getNumberOfReviews(), productInfo.getNumberOfStars() })
				.map(e -> String.valueOf(e)).collect(Collectors.joining(separator));
		return s;
	}

	public static void writeToCSV(String csvHeader, Set<String> csvLines, String fileName) {
		File csvOutputFile = new File(fileName);
		try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
			pw.println(csvHeader);
			csvLines.stream().forEach(pw::println);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
