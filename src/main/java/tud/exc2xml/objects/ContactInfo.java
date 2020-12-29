package tud.exc2xml.objects;

public class ContactInfo {
	Role role = new Role();
	Individual individualName = new Individual();
	String organisationName = "";
	String position = "";
	String phone = "";
	String eMailAddress = "";
	
	public Role getRole() {
		return this.role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Individual getIndividualName() {
		return this.individualName;
	}
	public void setIndividualName(Individual individualName) {
		this.individualName = individualName;
	}
	public String getOrganisationName() {
		return this.organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public String getPosition() {
		return this.position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEMailAddress() {
		return this.eMailAddress;
	}
	public void setEMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}
	  public String toString() {
	        if (!this.role.equals("")) {
	            return role.toString();
	        }
	        if (!this.individualName.equals("")) {
	            return individualName.toString();
	        }
	            return "";
	  }
}
