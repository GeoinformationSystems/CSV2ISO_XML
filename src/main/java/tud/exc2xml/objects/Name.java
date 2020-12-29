package tud.exc2xml.objects;

public class Name {
	Citation citation = new Citation();
	
	public String toString() {
		String s = "<mas:name>";
		s = String.valueOf(s) + citation.toString();
		s = String.valueOf(s) +	"</mas:name>";
		return s;
	}
}
