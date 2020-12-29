package tud.exc2xml.objects;

public class Scope {
	String level = "";

	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String toString() {
		String s = "<mdq:Scope><mcc:MD_Scope>";
		s = String.valueOf(s) + "<mcc:level>" + this.level + "</mcc:level>";
		s = String.valueOf(s) + "</mcc:MD_Scope></mdq:Scope>";
		return s;
	}
}
