package tud.exc2xml.objects;

import java.util.ArrayList;

public class Citation {
	String title = "";
	String edition = "";
	ArrayList<OnlineResource> onlineResource = new ArrayList<OnlineResource>();
	DateStamp dateInfoDate = new DateStamp();
	DoiIdentifier doiIdentifier = new DoiIdentifier();
	Responsibility contactResponsibility = new Responsibility();

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getEdition() {
		return this.edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public DateStamp getDateInfoDate() {
		return dateInfoDate;
	}

	public void setDateInfoDate(DateStamp dateInfoDate) {
		this.dateInfoDate = dateInfoDate;
	}
	
	public DoiIdentifier getDoiIdentifier() {
		return doiIdentifier;
	}

	public void setDoiIdentifier(DoiIdentifier doiIdentifier) {
		this.doiIdentifier = doiIdentifier;
	}

	public ArrayList<OnlineResource> getOnlineResource() {
		return onlineResource;
	}

	public void setOnlineResource(ArrayList<OnlineResource> onlineResource) {
		this.onlineResource = onlineResource;
	}

	public Responsibility getContactResponsibility() {
		return contactResponsibility;
	}
	public void setContactResponsibility(Responsibility contactResponsibility) {
		this.contactResponsibility = contactResponsibility;
	}

	public String toString() {
		int i;
		String s = "";
	        s = String.valueOf(s) + "<cit:CI_Citation>";
	        if (!this.title.equals("")) {
	        s = String.valueOf(s) +  "<cit:title>" + this.title + "</cit:title>";
	        }
	        s = String.valueOf(s) + dateInfoDate.toString();
	        if (!this.edition.equals("")) {
	        	s = String.valueOf(s) +  "<cit:edition>" + this.edition + "</cit:edition>";
	        }
	        s = String.valueOf(s) + "<cit:identifier>" + doiIdentifier.toString() + "</cit:identifier>";
	        s = String.valueOf(s) + "<cit:citedResponsibleParty>" + contactResponsibility.toString() + "</cit:citedResponsibleParty>";
	        for (i = 0; i < this.onlineResource.size(); ++i) {
	            s = String.valueOf(s) + ((OnlineResource)this.onlineResource.get(i)).toString();
	        }
	        s = String.valueOf(s) + "</cit:CI_Citation>";
	        	return s;
	 }
}
