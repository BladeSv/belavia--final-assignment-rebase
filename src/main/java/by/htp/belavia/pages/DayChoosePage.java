package by.htp.belavia.pages;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import by.htp.belavia.entitis.Order;

public class DayChoosePage extends AbstractPage {
	private String priceTemp = "---";

	public DayChoosePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void openPage(String url) {
		// TODO Auto-generated method stub

	}

	WebDriverWait wait = new WebDriverWait(driver, 20);

	private By findDepartureTime = By.xpath("//*[@id='outbound']/div[3]/div/div[1]/div[1]/strong");
	private By findPriceTicket = By.xpath("//*[@id='price']/div/span[@class='amount']");
	private By findClassList = By.xpath("//*[@id='outbound']/div[3]/div/div[2]/div[@class]");
	private By findLabelForClass = By.xpath("./label");
	private By priceScrept = By.xpath("/html/body/script[6]");

	public String getDepartureTime() {
		String DepartureTime;
		WebElement element = driver.findElement(findDepartureTime);
		DepartureTime = element.getText();
		return DepartureTime;

	}

	public double getPrice(WebElement element2) {
		double price;

		wait.until(ExpectedConditions.elementToBeClickable(element2));

		// wait.until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElement(element,
		// "---")));
		// wait.until(ExpectedConditions.textMatches(element, pattern));
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			new Thread();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement element = driver.findElement(findPriceTicket);
		String pr = element.getText();
		priceTemp = pr;
		price = Double.parseDouble(pr.replace(',', '.'));
		return price;
	}

	public List<WebElement> getClassElements() {
		List<WebElement> ClassList = driver.findElements(findClassList);
		return ClassList;

	}

	public void findLabelClick(WebElement element) {
		WebElement element2 = element.findElement(findLabelForClass);
		wait.until(ExpectedConditions.elementToBeClickable(element2));
		element2.click();

	}

	public void chooseCreateOrder(WebElement element, GregorianCalendar dateFly, String DepartureTime,
			List<Order> orderList) {

		String classFly = element.getAttribute("class");

		if ((classFly.contains("er fare")) && (!classFly.contains("hidden-xs"))) {
			findLabelClick(element);
			double price = getPrice(element);

			orderList.add(new Order(dateFly, DepartureTime, "Economy Restricted", price));

		}
		if ((classFly.contains("sf fare")) && (!classFly.contains("hidden-xs"))) {
			findLabelClick(element);
			double price = getPrice(element);
			Order order = new Order(dateFly, DepartureTime, "Economy Semi-Flexible", price);
			orderList.add(order);

		}
		if ((classFly.contains("ef fare")) && (!classFly.contains("hidden-xs"))) {
			findLabelClick(element);
			double price = getPrice(element);
			orderList.add(new Order(dateFly, DepartureTime, "Economy Flexible", price));

		}
		if ((classFly.contains("bc fare")) && (!classFly.contains("hidden-xs"))) {
			findLabelClick(element);
			double price = getPrice(element);
			orderList.add(new Order(dateFly, DepartureTime, "Business", price));

		}

	}
}
