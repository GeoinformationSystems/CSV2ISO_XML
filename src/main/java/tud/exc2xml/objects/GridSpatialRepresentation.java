package tud.exc2xml.objects;

public class GridSpatialRepresentation {

	SpatialRepresentation spatialRepresentation = new SpatialRepresentation();
	Dimension dimension = new Dimension();
	CellGeometry cellGeometry = new CellGeometry();
	TransformationParameter transformationParameter = new TransformationParameter();
	
	 public SpatialRepresentation getSpatialRepresentation() {
		return spatialRepresentation;
	}
	public void setSpatialRepresentation(SpatialRepresentation spatialRepresentation) {
		this.spatialRepresentation = spatialRepresentation;
	}
	public Dimension getDimension() {
		return dimension;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	public CellGeometry getCellGeometry() {
		return cellGeometry;
	}
	public void setCellGeometry(CellGeometry cellGeometry) {
		this.cellGeometry = cellGeometry;
	}
	public TransformationParameter getTransformationParameter() {
		return transformationParameter;
	}
	public void setTransformationParameter(TransformationParameter transformationParameter) {
		this.transformationParameter = transformationParameter;
	}

	public String toString() {
		 String s = "<msr:MD_GridSpatialRepresentation>";
		 s = String.valueOf(s) + spatialRepresentation.toString();
		 s = String.valueOf(s) + dimension.toString();
		 s = String.valueOf(s) + cellGeometry.toString();
		 s = String.valueOf(s) + transformationParameter.toString();
		 s = String.valueOf(s) + "</msr:MD_GridSpatialRepresentation>";
		 return s;
	 }

}
