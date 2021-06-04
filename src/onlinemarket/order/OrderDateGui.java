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
	
	private Date d;
	
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
		long l = new java.util.Date().getTime()+(24 * 60 * 60000);
		
		for(int i = 0; i < 6 ; i++ ,l = cal.getTimeInMillis()+(24*60*60000)) {
			cal.setTime(new java.util.Date(l));
			if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
				cal.setTime(new java.util.Date(l + (24 * 60 *60000)));
			d = new Date(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
			DayCB.getItems().add(d.toString());
		}
		DayCB.getSelectionModel().selectFirst();
	}
	
	public Date getDate() {
		return d;
	}

}
