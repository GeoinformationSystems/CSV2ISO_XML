package tud.exc2xml.objects;

public class StandaloneQualityReport {

	Citation citation = new Citation();
	Abstract abstractText = new Abstract();
	
	public Citation getCitation() {
		return citation;
	}

	public void setCitation(Citation citation) {
		this.citation = citation;
	}

	public Abstract getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(Abstract abstractText) {
		this.abstractText = abstractText;
	}

	public String toString() {
		String s = "<mdq:standaloneQualityReport><mdq:DQ_StandaloneQualityReportInformation>";
		s = String.valueOf(s) + "<mdq:reportReference>";
		s = String.valueOf(s) + citation.toString();
		s = String.valueOf(s) + "</mdq:reportReference>";
		s = String.valueOf(s) + abstractText.toString();
		s = String.valueOf(s) + "</mdq:DQ_StandaloneQualityReportInformation></mdq:standaloneQualityReport>";
		return s;
	}

}