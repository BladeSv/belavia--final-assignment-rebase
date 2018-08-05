package by.htp.belavia.steps;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import by.htp.belavia.driver.DriverSingleton;
import by.htp.belavia.entitis.Order;
import by.htp.belavia.menu.MainMenu;
import by.htp.belavia.pages.CalendarTariffsPage;
import by.htp.belavia.pages.DayChoosePage;
import by.htp.belavia.pages.SearchPage;

public class StepsOneWay {
	private List<Order> orderList;
	private GregorianCalendar dayDeparture;

	WebDriver driver = DriverSingleton.getDriver();

	String startPage = "https://booking.belavia.by";

	SearchPage searchPage = new SearchPage(driver);

	CalendarTariffsPage calTariff = new CalendarTariffsPage(driver);

	DayChoosePage dayChoosePage = new DayChoosePage(driver);

	public StepsOneWay(List<Order> orderList) {
		super();
		this.orderList = orderList;

	}

	public void startSearch(String url) {
		searchPage.openPage(url);
		searchPage.enterPlacelaunch("�����");
		searchPage.EnterDestinationLocation("����");
		searchPage.chooseRadioButtenOneWay();
		searchPage.chooseDatePlacelaunch();

	}

	public void CalendarTariffs() {

		boolean endWeak = true;

		calTariff.goToCalendarTariffs();
		while (endWeak) {
			List<WebElement> elements = calTariff.findElementsOneWayCalendar();

			dayDeparture = calTariff.choiceDayOneWayCalendar(elements);
			if (MainMenu.finishDay.equals(dayDeparture)) {

				endWeak = false;
			}

			List<WebElement> dayticketList = dayChoosePage.getClassElements();

			for (WebElement element : dayticketList) {

				String dateFly = dayChoosePage.getDepartureTime();

				dayChoosePage.chooseCreateOrder(element, dayDeparture, dateFly, orderList);
			}

			calTariff.goToCalendarTariffs();

		}
	}
}
