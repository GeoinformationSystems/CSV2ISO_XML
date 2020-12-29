package tud.exc2xml.objects;

public class ApplicationSchemaInformation {
	Name name = new Name();
	
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String toString() {
		String s = "<mas:MD_ApplicationSchemaInformation>";
		s = String.valueOf(s) + name.toString();
		s = String.valueOf(s) + "</mas:MD_ApplicationSchemaInformation>";
		return s;
	}
}
