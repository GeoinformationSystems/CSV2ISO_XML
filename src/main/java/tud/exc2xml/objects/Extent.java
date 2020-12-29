package tud.exc2xml.objects;

public class Extent {
	GeographicBoundingBox geographicBoundingBox = new GeographicBoundingBox();
	TemporalExtent temporalExtent = new TemporalExtent();
	
	public GeographicBoundingBox getGeographicBoundingBox() {
		return this.geographicBoundingBox;
	}
	public void setGeographicBoundingBox(GeographicBoundingBox geographicBoundingBox) {
		this.geographicBoundingBox = geographicBoundingBox;
	}
	public TemporalExtent getTemporalExtent() {
		return this.temporalExtent;
	}
	public void setTemporalExtent(TemporalExtent temporalExtent) {
		this.temporalExtent = temporalExtent;
	}
	@Override
	public String toString() {
		String s = "<mri:extent><gex:EX_Extent>";
		s = String.valueOf(s) + geographicBoundingBox.toString();
		s = String.valueOf(s) + temporalExtent.toString();
		s = String.valueOf(s) + "</gex:EX_Extent></mri:extent>";
		return s;
	}
}
