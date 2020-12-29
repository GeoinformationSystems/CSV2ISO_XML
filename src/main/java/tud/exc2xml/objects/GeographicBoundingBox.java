package tud.exc2xml.objects;

public class GeographicBoundingBox {
	String westBoundLongitude = "";
	String eastBoundLongitude = "";
	String northBoundLatitude = "";
	String southBoundLatitude = "";
	Identifier identifier = new Identifier();

	public String getWestBoundLongitude() {
		return this.westBoundLongitude;
	}

	public void setWestBoundLongitude(String westBoundLongitude) {
		this.westBoundLongitude = westBoundLongitude;
	}

	public String getEastBoundLongitude() {
		return this.eastBoundLongitude;
	}

	public void setEastBoundLongitude(String eastBoundLongitude) {
		this.eastBoundLongitude = eastBoundLongitude;
	}

	public String getNorthBoundLatitude() {
		return this.northBoundLatitude;
	}

	public void setNorthBoundLatitude(String northBoundLatitude) {
		this.northBoundLatitude = northBoundLatitude;
	}

	public String getSouthBoundLatitude() {
		return this.southBoundLatitude;
	}

	public void setSouthBoundLatitude(String southBoundLatitude) {
		this.southBoundLatitude = southBoundLatitude;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public String toString() {
		String s = "<gex:geographicElement><gex:EX_GeographicBoundingBox>";
		if (!this.westBoundLongitude.equals("") && !this.eastBoundLongitude.equals("") && !this.southBoundLatitude.equals("") && !this.northBoundLatitude.equals("")) {
			s = String.valueOf(s) + "<gex:westBoundLongitude>" + this.westBoundLongitude + "</gex:westBoundLongitude>";
			s = String.valueOf(s) + "<gex:eastBoundLongitude>" + this.eastBoundLongitude + "</gex:eastBoundLongitude>";
			s = String.valueOf(s) + "<gex:southBoundLatitude>" + this.southBoundLatitude + "</gex:southBoundLatitude>";
			s = String.valueOf(s) + "<gex:northBoundLatitude>" + this.northBoundLatitude + "</gex:northBoundLatitude>";
		}
		s = String.valueOf(s) + "</gex:EX_GeographicBoundingBox>";
		s = String.valueOf(s) + "<gex:EX_GeographicDescription><gex:geographicIdentifier>" + identifier.toString() + "</gex:geographicIdentifier></gex:EX_GeographicDescription>";
		s = String.valueOf(s) + "</gex:geographicElement>";
		return s;
	}
}
