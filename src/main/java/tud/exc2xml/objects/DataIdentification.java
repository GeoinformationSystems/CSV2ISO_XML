package tud.exc2xml.objects;

import java.util.ArrayList;

public class DataIdentification {
	Citation citation = new Citation();
	PointOfContact pointOfContact = new PointOfContact();
	Abstract abstractText = new Abstract();
	SpatialResolution spatialResolution = new SpatialResolution();
	TemporalResolution temporalResolution = new TemporalResolution();
	Extent extent = new Extent();
	ArrayList<DescriptiveKeywords> descriptiveKeywords = new ArrayList<DescriptiveKeywords>();
	SpatialRepresentationType spatialRepresentationType = new SpatialRepresentationType();
	
	
	public Citation getCitation() {
		return citation;
	}


	public void setCitation(Citation citation) {
		this.citation = citation;
	}


	public PointOfContact getPointOfContact() {
		return pointOfContact;
	}


	public void setPointOfContact(PointOfContact pointOfContact) {
		this.pointOfContact = pointOfContact;
	}


	public Abstract getAbstractText() {
		return abstractText;
	}


	public void setAbstractText(Abstract abstractText) {
		this.abstractText = abstractText;
	}


	public SpatialResolution getSpatialResolution() {
		return spatialResolution;
	}


	public void setSpatialResolution(SpatialResolution spatialResolution) {
		this.spatialResolution = spatialResolution;
	}


	public TemporalResolution getTemporalResolution() {
		return temporalResolution;
	}


	public void setTemporalResolution(TemporalResolution temporalResolution) {
		this.temporalResolution = temporalResolution;
	}


	public Extent getExtent() {
		return extent;
	}


	public void setExtent(Extent extent) {
		this.extent = extent;
	}

	public ArrayList<DescriptiveKeywords> getDescriptiveKeywords() {
		return descriptiveKeywords;
	}


	public void setDescriptiveKeywords(ArrayList<DescriptiveKeywords> descriptiveKeywords) {
		this.descriptiveKeywords = descriptiveKeywords;
	}


	public SpatialRepresentationType getSpatialRepresentationType() {
		return spatialRepresentationType;
	}


	public void setSpatialRepresentationType(SpatialRepresentationType spatialRepresentationType) {
		this.spatialRepresentationType = spatialRepresentationType;
	}


	public String toString() {
		 int i;
		 String s = "<mri:MD_DataIdentification>";
		 s = String.valueOf(s) + citation.toString();
		 s = String.valueOf(s) + abstractText.toString();
	     s = String.valueOf(s) + pointOfContact.toString();
	     s = String.valueOf(s) + spatialResolution.toString();
	     s = String.valueOf(s) + temporalResolution.toString();	    		 
	     s = String.valueOf(s) + extent.toString();
	     for (i = 0; i < this.descriptiveKeywords.size(); ++i) {
	            s = String.valueOf(s) + ((DescriptiveKeywords)this.descriptiveKeywords.get(i)).toString();
	        }
	     s = String.valueOf(s) + spatialRepresentationType.toString();
	     s = String.valueOf(s) + "</mri:MD_DataIdentification>";
	     return s;
		 }
}
