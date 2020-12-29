package tud.exc2xml.objects;

public class Role {
String role = "";

public String getRole() {
	return this.role;
}

public void setRole(String role) {
	this.role = role;
}
public String toString() {
    if (!this.role.equals("")) {
        return "<cit:role><cat:CI_RoleCode codeListValue=\"" + this.role + "\" codeList=\"http://standards.iso.org/iso/19115/-3/cit/1.0/codelists.html#CI_RoleCode\"/></cit:role>";
    }
    return "";
}
}
