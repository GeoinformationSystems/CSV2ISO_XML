package tud.exc2xml.objects;


public class DescriptiveKeywords {
	String descriptiveKeyword = "";
	String thesaurusName = "";
	String keywordClass = "";
	String conceptIdentifier = "";
	String ontologyName = "";
	String ontologyURL = "";
	

	
	public String getDescriptiveKeyword() {
		return descriptiveKeyword;
	}



	public void setDescriptiveKeyword(String descriptiveKeyword) {
		this.descriptiveKeyword = descriptiveKeyword;
	}



	public String getThesaurusName() {
		return thesaurusName;
	}



	public void setThesaurusName(String thesaurusName) {
		this.thesaurusName = thesaurusName;
	}



	public String getKeywordClass() {
		return keywordClass;
	}



	public void setKeywordClass(String keywordClass) {
		this.keywordClass = keywordClass;
	}



	public String getConceptIdentifier() {
		return conceptIdentifier;
	}



	public void setConceptIdentifier(String conceptIdentifier) {
		this.conceptIdentifier = conceptIdentifier;
	}



	public String getOntologyName() {
		return ontologyName;
	}



	public void setOntologyName(String ontologyName) {
		this.ontologyName = ontologyName;
	}



	public String getOntologyURL() {
		return ontologyURL;
	}



	public void setOntologyURL(String ontologyURL) {
		this.ontologyURL = ontologyURL;
	}



	public String toString() {
		String s = "<mri:descriptiveKeywords><mri:MD_Keywords>";
		if (!this.descriptiveKeyword.equals("")) {
			s = String.valueOf(s) + "<mri:keyword>" + this.descriptiveKeyword + "</mri:keyword>";
		}
		if (!this.thesaurusName.equals("")) {
			s = String.valueOf(s) + "<mri:thesaurusName>" + this.thesaurusName + "</mri:thesaurusName>";
		}
		if (!this.keywordClass.equals("") && !this.conceptIdentifier.equals("") && !this.ontologyName.equals("") && !this.ontologyURL.equals("")) {
			s = String.valueOf(s) + "<mri:keywordClass><mri:MD_KeywordClass>";
			s = String.valueOf(s) + "<mri:className>" + this.keywordClass + "</mri:className>";
			s = String.valueOf(s) + "<mri:conceptIdentifier>" + this.conceptIdentifier + "</mri:conceptIdentifier>";
			s = String.valueOf(s) + "<mri:ontology><mri:ontologyName>" + this.ontologyName + "</mri:ontologyName><mri:ontologyURL>" + this.ontologyURL + "</mri:ontologyURL></mri:ontology>";
			s = String.valueOf(s) + "</mri:MD_KeywordClass></mri:keywordClass>";
		}
		s = String.valueOf(s) + "</mri:MD_Keywords></mri:descriptiveKeywords>";
		return s;
	}
}