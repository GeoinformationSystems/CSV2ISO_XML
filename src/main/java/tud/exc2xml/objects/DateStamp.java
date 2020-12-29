package tud.exc2xml.objects;

public class DateStamp {
String date = "";
String dateType = "";

public String getDate() {
	return this.date;
}
public void setDate(String date) {
	this.date = date;
}
public String getDateType() {
	return this.dateType;
}
public void setDateType(String dateType) {
	this.dateType = dateType;
}
public String toString() {
	String s = "<cit:CI_Date>";
    if (!this.date.equals("")) {
    	s = String.valueOf(s) + "<cit:date><xs:DateTime>" + this.date + "</xs:DateTime></cit:date>";
    }
    s = String.valueOf(s) + "<cit:dateType><cat:CI_DateTypeCode codeListValue =\"creation\" codeList=\"http://standards.iso.org/iso/19115/-3/cit/1.0/codelists.html#CI_DateTypeCode\"/></cit:dateType>";
    s = String.valueOf(s) + "</cit:CI_Date>";
    return s;
	}

}
