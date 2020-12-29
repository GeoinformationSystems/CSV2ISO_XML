package tud.exc2xml.objects;

public class ReferenceSystem {
	String code = "";
	String nameSpace = "";
	String version = "";
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNameSpace() {
		return this.nameSpace;
	}
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	public String getVersion() {
		return this.version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		String s = "";
        if (!(this.code.equals("") && this.nameSpace.equals("") && this.version.equals(""))) {
            s = "<mcc:MD_Identifier><mcc:code>" + this.code +"</mcc:code><mcc:codeSpace>" + this.nameSpace + "</mcc:codeSpace><mcc:version>" + this.version + "</mcc:version></mcc:MD_Identifier>";
        }
        return s;
    }
}
