package tud.exc2xml.objects;

public class Dimension {
	String dimensionName = "";
	String dimensionSize = "";
	
	public String getDimensionName() {
		return this.dimensionName;
	}
	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}
	public String getDimensionSize() {
		return this.dimensionSize;
	}
	public void setDimensionSize(String dimensionSize) {
		this.dimensionSize = dimensionSize;
	}
	   public String toString() {
		   String s = "<msr:axisDimensionProperties><msr:MD_Dimension>";
	        if (!this.dimensionName.equals("")) {
	        	s = String.valueOf(s) + "<msr:dimensionName>" + this.dimensionName + "</msr:dimensionName>";
	        }
	        if (!this.dimensionSize.equals("")) {
	        	s = String.valueOf(s) + "<msr:dimensionSize>" + this.dimensionSize + "</msr:dimensionSize>";
	        }
	        s = String.valueOf(s) + "</msr:MD_Dimension></msr:axisDimensionProperties>";
	        return s;
	        }	

}
