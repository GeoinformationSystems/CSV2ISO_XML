package tud.exc2xml.objects;

public class SpatialRepresentationInfo {
	
	GridSpatialRepresentation gridSpatialRepresentation = new GridSpatialRepresentation();
	Georectified georectified = new Georectified();
	VectorSpatialRepresentation vectorSpatialRepresentation = new VectorSpatialRepresentation();
	
	public GridSpatialRepresentation getGridSpatialRepresentation() {
		return this.gridSpatialRepresentation;
	}

	public void setGridSpatialRepresentation(GridSpatialRepresentation gridSpatialRepresentation) {
		this.gridSpatialRepresentation = gridSpatialRepresentation;
	}

	public Georectified getGeorectified() {
		return this.georectified;
	}

	public void setGeorectified(Georectified georectified) {
		this.georectified = georectified;
	}

	public VectorSpatialRepresentation getVectorSpatialRepresentation() {
		return this.vectorSpatialRepresentation;
	}

	public void setVectorSpatialRepresentation(VectorSpatialRepresentation vectorSpatialRepresentation) {
		this.vectorSpatialRepresentation = vectorSpatialRepresentation;
	}

	public String toString() {
		String s = "<mdb:spatialRepresentationInfo>";
		s = String.valueOf(s) + gridSpatialRepresentation.toString();
		s = String.valueOf(s) + georectified.toString();
		s = String.valueOf(s) + vectorSpatialRepresentation.toString();
		s = String.valueOf(s) + "</mdb:spatialRepresentationInfo>";
		return s;
	}
}