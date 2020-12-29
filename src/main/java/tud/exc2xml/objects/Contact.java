package tud.exc2xml.objects;

public class Contact {
	Responsibility responsibility = new Responsibility ();

	public Responsibility getResponsibility() {
		return this.responsibility;
	}

	public void setResponsibility(Responsibility responsibility) {
		this.responsibility = responsibility;
	}

	@Override
	public String toString() {
		String s = "<mdb:contact>" + responsibility.toString() + "</mdb:contact>";
		return s;
	}
	
}
