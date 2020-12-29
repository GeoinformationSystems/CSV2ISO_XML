package tud.exc2xml.objects;

public class DoiIdentifier {
String dataset = "";
String publication = "";

public String getDataset() {
	return dataset;
}

public void setDataset(String dataset) {
	this.dataset = dataset;
}

public String getPublication() {
	return publication;
}

public void setPublication(String publication) {
	this.publication = publication;
}

public String toString() {
	String s = "<cit:identifier><mcc:MD_Identifier>";
    if (!this.dataset.equals("")) {
    	s = String.valueOf(s) + "<mcc:datasetDOI><mcc:code>" + this.dataset  + "</mcc:code><mcc:codeSpace>\"http://dx.doi.org\"</mcc:codeSpace></mcc:datasetDOI>";
    }
    if (!this.publication.equals("")) {
    	s = String.valueOf(s) + "<mcc:publicationDOI><mcc:code>" + this.publication + "</mcc:code><mcc:codeSpace>\"http://dx.doi.org\"</mcc:codeSpace></mcc:publicationDOI>";
    }
    s = String.valueOf(s) + "</mcc:MD_Identifier></cit:identifier>";
return s;
}
}
