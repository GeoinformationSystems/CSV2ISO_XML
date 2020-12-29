package tud.exc2xml.objects;

public class SpatialRepresentationType {
	
	String spatialRepresentationType = "";
	
	public String getSpatialRepresentationType() {
		return this.spatialRepresentationType;
	}
	public void setSpatialRepresentationType(String spatialRepresentationType) {
		this.spatialRepresentationType = spatialRepresentationType;
	}
	@Override
	public String toString() {
		 if (!this.spatialRepresentationType.equals("")) {
	            return "<mri:spatialRepresentationType><cat:MD_SpatialRepresentationTypeCode>" + this.spatialRepresentationType + "</cat:MD_SpatialRepresentationTypeCode><codeList>http://standards.iso.org/iso/19115/-3/mcc/1.0/codelists.html#MD_SpatialRepresentationTypeCode></codeList></mri:spatialRepresentationType>";
	        }
	            return "";
	        }	
}


