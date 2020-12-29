package tud.exc2xml.objects;

public class GeometricObjects {
	String geometricObjectType = "";
	String geometricObjectCount = "";
	
	public String getGeometricObjectType() {
		return this.geometricObjectType;
	}
	public void setGeometricObjectType(String geometricObjectType) {
		this.geometricObjectType = geometricObjectType;
	}
	public String getGeometricObjectCount() {
		return this.geometricObjectCount;
	}
	public void setGeometricObjectCount(String geometricObjectCount) {
		this.geometricObjectCount = geometricObjectCount;
	}
	public String toString() {
	if (!this.geometricObjectType.equals("") && this.geometricObjectCount.equals("")) {
		return "<msr:geometricObjects><msr:MD_GeometricObjects><msr:geometricObjectType>" + this.geometricObjectType + "</msr:geometricObjectType><msr:geometricObjectCount>" + this.geometricObjectCount + "</msr:geometricObjectCount></msr:MD_GeometricObjects></msr:geometricObjects>";
	}
	return "";
	}
}
