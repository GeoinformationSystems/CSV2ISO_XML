package tud.exc2xml.objects;

public class ApplicationSchemaInfo {
	ApplicationSchemaInformation applicationSchemaInformation = new ApplicationSchemaInformation();

	public ApplicationSchemaInformation getApplicationSchemaInformation() {
		return this.applicationSchemaInformation;
	}

	public void setApplicationSchemaInformation(ApplicationSchemaInformation applicationSchemaInformation) {
		this.applicationSchemaInformation = applicationSchemaInformation;
	}
	
	public String toString() {
		String s = "<mdb:applicationSchemaInfo>";
		s = String.valueOf(s) + applicationSchemaInformation.toString();
		s = String.valueOf(s) + "</mdb:applicationSchemaInfo>";
		return s;
	}
}
