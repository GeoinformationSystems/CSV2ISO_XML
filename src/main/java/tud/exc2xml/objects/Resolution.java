package tud.exc2xml.objects;

public class Resolution {
	String resolution;

	public String getResolution() {
		return this.resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String toString() {
        if (!this.resolution.equals("")) {
            return "<msr:resolution>" + this.resolution + "</msr:resolution>";
        }
        return "";
	}
}
