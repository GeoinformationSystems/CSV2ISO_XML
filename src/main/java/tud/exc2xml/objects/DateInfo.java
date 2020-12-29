package tud.exc2xml.objects;

public class DateInfo {
	DateStamp dateInfoDate = new DateStamp();
	
	public DateStamp getDateInfoDate() {
		return dateInfoDate;
	}


	public void setDateInfoDate(DateStamp dateInfoDate) {
		this.dateInfoDate = dateInfoDate;
	}

	public String toString() {
		String s = "<mdb:dateInfo>";
		s = String.valueOf(s) + dateInfoDate.toString();
		s = String.valueOf(s) + "</mdb:dateInfo>";
		return s;
	}
}
