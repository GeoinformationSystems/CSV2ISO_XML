package tud.exc2xml.objects;

public class CellGeometry {
	String cellGeometry = "";

	public String getCellGeometry() {
		return this.cellGeometry;
	}

	public void setCellGeometry(String cellGeometry) {
		this.cellGeometry = cellGeometry;
	}
	 public String toString() {
	        if (!this.cellGeometry.equals("")) {
	            return "<msr:cellGeometry>" + this.cellGeometry + "</msr:cellGeometry>";
	        }
	        return "";
	 }
}
