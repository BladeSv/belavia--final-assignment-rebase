package by.htp.belavia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.belavia.entitis.OrderList;
import by.htp.belavia.menu.MainMenu;
import by.htp.belavia.steps.StepsOneWay;

public class OneWayTest {
	private StepsOneWay stepsOneWay;
	String startPage="https://booking.belavia.by";
	OrderList oneWayList;
	
	
	@BeforeMethod
	public void initStep(){
		 oneWayList=new 	OrderList();
		 stepsOneWay=new StepsOneWay(oneWayList.getOrderList());
		 

			try {
				MainMenu.finishDay.setTime((new SimpleDateFormat("dd.MM.yyyy").parse("20.08.2018")) );
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				MainMenu.starstDay.setTime((new SimpleDateFormat("dd.MM.yyyy").parse("15.08.2018")) );
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@Test
	public void Search() {
		
		stepsOneWay.startSearch(startPage);	
		stepsOneWay.CalendarTariffs();
		Assert.assertNotNull(oneWayList.getOrderList(), "List order empty");
	}
}
