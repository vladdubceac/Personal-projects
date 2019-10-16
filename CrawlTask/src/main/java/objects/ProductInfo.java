package objects;

public class ProductInfo {
	private String ASIN;
	private String productName;
	private double price;
	private long numberOfReviews;
	private double numberOfStars;

	public ProductInfo() {
		// TODO Auto-generated constructor stub
	}

	public ProductInfo(String aSIN, String productName, double price, long numberOfReviews, double numberOfStars) {
		ASIN = aSIN;
		this.productName = productName;
		this.price = price;
		this.numberOfReviews = numberOfReviews;
		this.numberOfStars = numberOfStars;
	}

	public String getASIN() {
		return ASIN;
	}

	public void setASIN(String aSIN) {
		ASIN = aSIN;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getNumberOfReviews() {
		return numberOfReviews;
	}

	public void setNumberOfReviews(long numberOfReviews) {
		this.numberOfReviews = numberOfReviews;
	}

	public double getNumberOfStars() {
		return numberOfStars;
	}

	public void setNumberOfStars(double numberOfStars) {
		this.numberOfStars = numberOfStars;
	}

	@Override
	public String toString() {
		return "ProductInfo [ASIN=" + ASIN + ", productName=" + productName + ", price=" + price + ", numberOfReviews="
				+ numberOfReviews + ", numberOfStars=" + numberOfStars + "]";
	}

}
