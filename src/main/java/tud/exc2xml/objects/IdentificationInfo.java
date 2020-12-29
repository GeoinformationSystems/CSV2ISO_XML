package tud.exc2xml.objects;


public class IdentificationInfo {
	Identification identification = new Identification();
	DataIdentification dataIdentification = new DataIdentification();
	ServiceIdentification serviceIdentification = new ServiceIdentification();
	
	public Identification getIdentification() {
		return this.identification;
	}
	public void setIdentification(Identification identification) {
		this.identification = identification;
	}
	public DataIdentification getDataIdentification() {
		return this.dataIdentification;
	}
	public void setDataIdentification(DataIdentification dataIdentification) {
		this.dataIdentification = dataIdentification;
	}
	public ServiceIdentification getServiceIdentification() {
		return this.serviceIdentification;
	}
	public void setServiceIdentification(ServiceIdentification serviceIdentification) {
		this.serviceIdentification = serviceIdentification;
	}
	
	public String toString() {
		 String s = "<mdb:identificationInfo>";
		 s = String.valueOf(s) + identification.toString();
		 s = String.valueOf(s) + dataIdentification.toString();
		 s = String.valueOf(s) + serviceIdentification.toString();
		 s = String.valueOf(s) + "</mdb:identificationInfo>";
		 return s;
	}
	
}
