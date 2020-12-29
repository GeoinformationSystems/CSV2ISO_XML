package tud.exc2xml.objects;

public class PointOfContact {
	
	Responsibility contactResponsibility = new Responsibility();
	
	public Responsibility getContactResponsibility() {
		return contactResponsibility;
	}

	public void setContactResponsibility(Responsibility contactResponsibility) {
		this.contactResponsibility = contactResponsibility;
	}

	public String toString() {
		String s = "<mri:pointOfContact>";
		s = String.valueOf(s) + contactResponsibility.toString();
		s = String.valueOf(s) + "</mri:pointOfContact>";
		return s;
	}
}
