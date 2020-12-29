package tud.exc2xml.objects;

public class Individual {
String individualName = "";

public String getIndividualName() {
	return this.individualName;
}

public void setIndividualName(String individualName) {
	individualName = this.individualName;
}

@Override
public String toString() {
	if (!this.individualName.equals("")) {
        return "<cit:CI_Individual><cit:name>" + this.individualName + "</cit:name></cit:CI_Individual>";
    }
        return "";
}

}
