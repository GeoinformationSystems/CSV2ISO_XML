package tud.exc2xml.objects;

public class ReferenceSystemInfo {
	ReferenceSystem referenceSystem = new ReferenceSystem();
	

	public ReferenceSystem getReferenceSystem() {
		return this.referenceSystem;
	}

	public void setReferenceSystem(ReferenceSystem referenceSystem) {
		this.referenceSystem = referenceSystem;
	}
	public String toString() {
		String s = "<mdb:referenceSystemInfo><mrs:MD_ReferenceSystem><mrs:referenceSystemIdentifier>";
		s = String.valueOf(s) + referenceSystem.toString();
		s = String.valueOf(s) + "</mrs:referenceSystemIdentifier></mrs:MD_ReferenceSystem></mdb:referenceSystemInfo>";
		return s;
	}

}
