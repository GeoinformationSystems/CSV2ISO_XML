package tud.exc2xml.objects;

public class OnlineResource {
	String linkage = "";
	String name = "";
	String description = "";
	String function = "";
	
	public String getLinkage() {
		return this.linkage;
	}
	public void setLinkage(String linkage) {
		this.linkage = linkage;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFunction() {
		return this.function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	
	public String toString() {
		String s = "<cit:onlineResource><cit:CI_OnlineResource>";
        if (!this.linkage.equals("")){
        	s = String.valueOf(s) + "<cit:linkage>" + this.linkage + "</cit:linkage>";
        }
        if (!this.name.equals("")) {
        	s = String.valueOf(s) + "<cit:name>" + this.name + "</cit:name>";
        }
        if (!this.description.equals("")) {
        	s = String.valueOf(s) + "<cit:description>" + this.description + "</cit:description>";
        }
        if (!this.function.equals("")) {
        	s = String.valueOf(s) + "<cit:function>" + this.function + "</cit:function>";
        }
        s = String.valueOf(s) + "</cit:CI_OnlineResource></cit:onlineResource>";
        return s;
		}
}
