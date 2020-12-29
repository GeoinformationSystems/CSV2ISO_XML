package tud.exc2xml.objects;

public class AlternativeMetadataReference {

	Citation citation = new Citation();

	public Citation getCitation() {
		return this.citation;
	}

	public void setCitation(Citation citation) {
		this.citation = citation;
	}

	@Override
	public String toString() {
		String s = "<mdb:alternativeMetadataReference>" ;
		s = String.valueOf(s) + citation.toString();
		s = String.valueOf(s) + "</mdb:alternativeMetadataReference>" ;
		return s;
	}
	
}
