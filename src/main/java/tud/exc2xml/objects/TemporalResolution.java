package tud.exc2xml.objects;

public class TemporalResolution {
	String periodDuration = ""; // PyYmMdDThHmMsS

	public String getPeriodDuration() {
		return this.periodDuration;
	}

	public void setPeriodDuration(String periodDuration) {
		this.periodDuration = periodDuration;
	}

	@Override
	public String toString() {
		if (!this.periodDuration.equals("")) {
		return "<mri:temporalResolution>"+ this.periodDuration+ "</mri:temporalResolution>";
	}
	return "";
	}
	
}
