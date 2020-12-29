package tud.exc2xml.objects;

public class DataQualityInfo {
	
Scope scope = new Scope();
StandaloneQualityReport standaloneQualityReport = new StandaloneQualityReport();

public Scope getScope() {
	return scope;
}
public void setScope(Scope scope) {
	this.scope = scope;
}
public StandaloneQualityReport getStandaloneQualityReport() {
	return standaloneQualityReport;
}
public void setStandaloneQualityReport(StandaloneQualityReport standaloneQualityReport) {
	this.standaloneQualityReport = standaloneQualityReport;
}
public String toString() {
	String s = "<mdb:dataQualityInfo><mdq:DQ_DataQuality>";
	s = String.valueOf(s) + scope.toString();
	s = String.valueOf(s) + standaloneQualityReport.toString();
	s = String.valueOf(s) + "</mdq:DQ_DataQuality></mdb:dataQualityInfo>";
	return s;
}
}
