package tud.exc2xml.objects;

public class SpatialRepresentation {
	String numberOfDimensions= "";
	String axisDimensionPorperties = "";

	public String getNumberOfDimensions() {
		return this.numberOfDimensions;
	}
	public void setNumberOfDimensions(String numberOfDimensions) {
		this.numberOfDimensions = numberOfDimensions;
	}

	public String getAxisDimensionPorperties() {
		return this.axisDimensionPorperties;
	}
	public void setAxisDimensionPorperties(String axisDimensionPorperties) {
		this.axisDimensionPorperties = axisDimensionPorperties;
	}
	   public String toString() {
	        if (!this.numberOfDimensions.equals("") && !this.axisDimensionPorperties.equals("")) {
	            return "<msr:numberOfDimensions>" + this.numberOfDimensions + "</msr:numberOfDimensions><msr:axisDimensionProperties>" + this.axisDimensionPorperties + "</msr:axisDimensionProperties>";
	        }
	            return "";
	        }	
}
	
	
