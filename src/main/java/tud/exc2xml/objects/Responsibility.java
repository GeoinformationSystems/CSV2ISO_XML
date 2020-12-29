package tud.exc2xml.objects;

import java.util.ArrayList;

public class Responsibility {
	Role role = new Role();
	ArrayList<OnlineResource> onlineResource = new ArrayList<OnlineResource>();
	String individualName = "";
	String OrcID = "";
	String organisation = "";
	String position = "";
	String phone = "";
	String mail = "";
	

	public Role getRole() {
		return this.role;
	}
	public void setRole(Role role) {
		this.role = role;	
	}

	public ArrayList<OnlineResource> getOnlineResource() {
		return onlineResource;
	}
	public void setOnlineResource(ArrayList<OnlineResource> onlineResource) {
		this.onlineResource = onlineResource;
	}
	
	public String getIndividualName() {
		return individualName;
	}
	public void setIndividualName(String individualName) {
		this.individualName = individualName;
	}
	public String getOrcID() {
		return OrcID;
	}
	public void setOrcID(String orcID) {
		OrcID = orcID;
	}
	
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	@Override
	public String toString() {
		int i;
		String s = "<cit:CI_Responsibility>";
		s = String.valueOf(s) + role.toString();
		s = String.valueOf(s) + "<cit:party><cit:CI_Party><cit:name/><cit:contactInfo><cit:CI_Contact>";
		for (i = 0; i < this.onlineResource.size(); ++i) {
            s = String.valueOf(s) + ((OnlineResource)this.onlineResource.get(i)).toString();
        }
		s = String.valueOf(s) + "</cit:CI_Contact></cit:contactInfo></cit:CI_Party>";
		if (!this.OrcID.equals("")) {
        	s = String.valueOf(s) + "<cit:orcID>" + this.OrcID + "</cit:orcID>";
        }
		if (!this.individualName.equals("")) {
			s = String.valueOf(s) + "<cit:CI_Individual><cit:name>" + this.individualName + "</cit:name></cit:CI_Individual>";
	    }
        if (!this.organisation.equals("") || !this.position.equals("") || !this.phone.equals("") || !this.mail.equals("")) {
        	s = String.valueOf(s) + "<cit:organisationName>" + this.organisation + "</cit:organisationName><cit:position>" + this.position + "</cit:position><cit:phone>" + this.phone + "</cit:phone><cit:eMailAddress>" + this.mail+ "</cit:eMailAddress>";
        }
		s = String.valueOf(s) + "</cit:party></cit:CI_Responsibility>";
		return s;
	}
	
	
	

}
