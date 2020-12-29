package tud.exc2xml.objects;

public class VectorSpatialRepresentation {
	String topologyLevel = "";
	GeometricObjects geometricObjects = new GeometricObjects();

	public String getTopologyLevel() {
		return this.topologyLevel;
	}

	public void setTopologyLevel(String topologyLevel) {
		this.topologyLevel = topologyLevel;
	}
	
	public GeometricObjects getGeometricObjects() {
		return geometricObjects;
	}

	public void setGeometricObjects(GeometricObjects geometricObjects) {
		this.geometricObjects = geometricObjects;
	}

	public String toString() {
		String s = "<msr:MD_VectorSpatialRepresentation>";
		if (!this.topologyLevel.equals("")) {
        	s = String.valueOf(s) + "<msr:topologyLevel>" + this.topologyLevel + "</msr:topologyLevel>";
        }
		s = String.valueOf(s) + geometricObjects.toString();	
		s = String.valueOf(s) + "</msr:MD_VectorSpatialRepresentation>";
		return s;
	}
}
