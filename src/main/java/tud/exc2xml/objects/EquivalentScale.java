package tud.exc2xml.objects;

public class EquivalentScale {
	String denominator = "";

	public String getDenominator() {
		return this.denominator;
	}

	public void setDenominator(String denominator) {
		this.denominator = denominator;
	}
	   public String toString() {
	        if (!this.denominator.equals("")) {
	            return "<mri:equivalentScale><mri:MD_RepresentativeFraction><mri:denominator>" + this.denominator + "</mri:denominator></mri:MD_RepresentativeFraction></mri:equivalentScale>";
	        }
	        return "";
	   }
}
