package tud.exc2xml.objects;

public class Identifier {
	String code = "";
	String nameSpace = "";
	String version = "";
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNameSpace() {
		return nameSpace;
	}
	public void setNameSpace(String codeSpace) {
		this.nameSpace = codeSpace;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	 public String toString() {
	        if (!this.code.equals("") && !this.nameSpace.equals("") && !this.version.equals("")) {
	            return "<mcc:MD_Identifier><mcc:code>" + this.code + "</mcc:code><mcc:codeSpace>" + this.nameSpace + "</mcc:codeSpace><mcc:version>" + this.version + "</mcc:version></mcc:MD_Identifier>";
	        }
	        return "";
	        }
}
