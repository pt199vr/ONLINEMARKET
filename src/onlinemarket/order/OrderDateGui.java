package onlinemarket.order;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import onlinemarket.datentime.Date;
import onlinemarket.datentime.Time;


public class OrderDateGui extends AnchorPane{
	
	@FXML
	private ChoiceBox<String> TimeCB,DayCB;
	
	private Date d,tmp;
	private Time one, two;
	
	public OrderDateGui() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderDate.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		int hour = 9, minute = 0;
		Time t;
		for(int i = hour;i < 18;i++) {
			t = new Time(i , minute);
			String s = t.toString() + "-" + new Time(i + 1, minute);
			TimeCB.getItems().add(s);
		}
		
		TimeCB.getSelectionModel().selectFirst();
				
		
		Calendar cal= GregorianCalendar.getInstance();
		long l = new java.util.Date().getTime() + (24 * 60 * 60000);
		
		for(int i = 0; i < 6 ; i++ ,l += (24 * 60 * 60000)) {
			cal.setTime(new java.util.Date(l));
			if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
				cal.setTime(new java.util.Date(l + (24 * 60 * 60000)));
			d = new Date(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
			DayCB.getItems().add(d.toString());
		}
		DayCB.getSelectionModel().selectFirst();
	}
	
	public Date getDate() {
		return tmp;
	}

	public Time getFirstTime() {
		return one;
	}
	
	public Time getSecondTime() {
		return two;
	}
	
	public void getTime() {
		String s = TimeCB.getSelectionModel().getSelectedItem();
		
		int h = 0, m = 0;
		
		String g = ""; g = g + s.charAt(0); g = g + s.charAt(1);
		h = Integer.parseInt(g);
		g = "";
		g = g + s.charAt(3); g = g + s.charAt(4);
		m = Integer.parseInt(g);
		one = new Time(h, m);
		g = g + s.charAt(6); g = g + s.charAt(7);
		h = Integer.parseInt(g);
		g = "";
		g = g + s.charAt(9); g = g + s.charAt(10);
		m = Integer.parseInt(g);
		two = new Time(h, m);	
	}
	public void getChosenDate() {
		String s = DayCB.getSelectionModel().getSelectedItem();
		int day = 0, month = 0, year = 0;
		String z = "0";
		String g = ""; g+=s.charAt(0);
		if(s.charAt(1)=='/') {
			day = Integer.parseInt(g);
			g="" + s.charAt(2)+s.charAt(3);
			
			month= Integer.parseInt(g);
			g = "" + s.charAt(5) + s.charAt(6)+ s.charAt(7)+ s.charAt(8);
			year = Integer.parseInt(g);
			tmp = new Date(year,month,day);
		}
		else {
			g+=s.charAt(1);
			day= Integer.parseInt(g);
			
			g = "" +s.charAt(3) + s.charAt(4);
			month= Integer.parseInt(g);
				
			g = "" + s.charAt(6) + s.charAt(7)+ s.charAt(8)+ s.charAt(9);
			year=Integer.parseInt(g);
			tmp = new Date(year,month,day);
		}
			
	}
		
		
		
}
