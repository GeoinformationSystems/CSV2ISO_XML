package tud.exc2xml.objects;

public class Abstract {
String abstractText = "";

public String getAbstractText() {
	return this.abstractText;
}

public void setAbstractText(String abstractText) {
	this.abstractText = abstractText;
}
public String toString() {
    if (!this.abstractText.equals("")) {
        return "<mdq:abstract>" + this.abstractText + "</mdq:abstract>";
    }
    return "";
	}
}
