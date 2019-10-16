package task.crawl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static utils.HtmlUtils.Attributes;
import static utils.ParsingUtils.CssClasses;
import static utils.ParsingUtils.RegExp;
import static utils.HtmlUtils.Tags;
import objects.ProductInfo;
import utils.CrawlingUtils;
import utils.CsvUtils;
import utils.ParsingUtils;
import utils.PropertiesConstants;
import utils.PropertiesUtils;

public class Crawl {

	private static final String APP_PROPERTIES = "app.properties";
	private Set<String> pagesURLs;

	public Crawl() {
		pagesURLs = new TreeSet<String>();
	}

	public static void main(String[] args) {
		Properties properties = PropertiesUtils.loadProperties(APP_PROPERTIES);

		Crawl crawl = new Crawl();
		fillPagesUrlSet(crawl, properties);

		Set<ProductInfo> productInfoSet = prepareProductInfos(crawl);

		Set<String> csvLines = CsvUtils.prepareCSVLines(productInfoSet,properties.getProperty(PropertiesConstants.CSV_SEPARATOR));

		String csvHeader = properties.getProperty(PropertiesConstants.HEADER_COLUMNS);
		String fileName = properties.getProperty(PropertiesConstants.OUTPUT_FILE_NAME)
				+ properties.getProperty(PropertiesConstants.OUTPUT_FILE_EXTENSION);
		CsvUtils.writeToCSV(csvHeader, csvLines, fileName);
	}

	private static void fillPagesUrlSet(Crawl crawl, Properties properties) {
		crawl.getPagesURLs(properties.getProperty(PropertiesConstants.MAIN_PAGE));
	}

	private static Set<ProductInfo> prepareProductInfos(Crawl crawl) {
		Set<ProductInfo> productInfoSet = new LinkedHashSet<>();
		for (String pageURL : crawl.pagesURLs) {
			productInfoSet.addAll(crawl.extractProductInfos(pageURL));
		}
		return productInfoSet;
	}

	public Set<ProductInfo> extractProductInfos(String url) {
		LinkedHashSet<ProductInfo> prodSet = new LinkedHashSet<>();
		try {
			Document doc = Jsoup.connect(url).get();
			Element orderedList = doc.selectFirst(Tags.OL + "." + CssClasses.A_ORDERED_LIST);
			Element[] itemsArray = orderedList.select(Tags.LI).toArray(new Element[] {});
			prodSet = Stream.of(itemsArray).map(el -> {
				return CrawlingUtils.extractProductInfo(el);

			}).collect(Collectors.toCollection(() -> new LinkedHashSet<>()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prodSet;
	}

	public void getPagesURLs(String mainPageURL) {
		try {
			Document document = Jsoup.connect(mainPageURL).get();

			Element paginationElement = document.selectFirst(Tags.UL + "." + CssClasses.A_PAGINATION);
			Element[] numberedPagesElements = CrawlingUtils.getPageButtonElements(paginationElement);

			Set<String> urls = Stream.of(numberedPagesElements).filter(element -> {
				String hyperlink = element.selectFirst(Tags.A).attr(Attributes.HREF);
				return !pagesURLs.contains(hyperlink);
			}).map(e -> e.selectFirst(Tags.A).attr(Attributes.HREF)).collect(Collectors.toSet());

			for (String url : urls) {
				if (!pagesURLs.contains(url)) {
					pagesURLs.add(url);
					getPagesURLs(url);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
