package tud.exc2xml.objects;

public class SpatialResolution {
	String distance = "";
	String vertical = "";
	String angularDistance = "";
	String levelOfDetail = "";
	String unitOfMeasurement = "";
	EquivalentScale equivalentScale = new EquivalentScale();
	
	public String getDistance() {
		return this.distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getVertical() {
		return this.vertical;
	}
	public void setVertical(String vertical) {
		this.vertical = vertical;
	}
	public String getAngularDistance() {
		return this.angularDistance;
	}
	public void setAngularDistance(String angularDistance) {
		this.angularDistance = angularDistance;
	}
	public String getLevelOfDetail() {
		return this.levelOfDetail;
	}
	public void setLevelOfDetail(String levelOfDetail) {
		this.levelOfDetail = levelOfDetail;
	}
	public String getUnitOfMeasurement() {
		return this.unitOfMeasurement;
	}
	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
	
	public EquivalentScale getEquivalentScale() {
		return equivalentScale;
	}
	public void setEquivalentScale(EquivalentScale equivalentScale) {
		this.equivalentScale = equivalentScale;
	}
	public String toString() {
		   String s = "<mri:spatialResolution><mri:MD_Resolution>";
		   s = String.valueOf(s) + equivalentScale.toString();
		   
	        if (!this.distance.equals("")) {
	        	s = String.valueOf(s) + "<mri:distance>" + this.distance + "</mri:distance>";
	        }
	        if (!this.vertical.equals("")) {
	        	s = String.valueOf(s) + "<mri:vertical>" + this.vertical + "</mri:vertical>";
	        }
	        if (!this.angularDistance.equals("")) {
	        	s = String.valueOf(s) + "<mri:angularDistance>" + this.angularDistance + "</mri:angularDistance>";
	        }
	        if (!this.levelOfDetail.equals("")) {
	        	s = String.valueOf(s) + "<mri:levelOfDetail>" + this.levelOfDetail + "</mri:levelOfDetail>";
	        }
	        if (!this.unitOfMeasurement.equals("")) {
	        	s = String.valueOf(s) + "<mri:unitOfMeasurement>" + this.unitOfMeasurement + "</mri:unitOfMeasurement>";
	        }
	        s = String.valueOf(s) + "</mri:MD_Resolution></mri:spatialResolution>";
	            return s;
	        }
}
