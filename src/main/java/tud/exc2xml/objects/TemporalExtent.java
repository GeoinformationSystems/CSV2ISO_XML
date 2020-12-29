package tud.exc2xml.objects;

public class TemporalExtent {
	String begin = "";
	String end = "";
	public String getBegin() {
		return this.begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return this.end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	@Override
	public String toString() {
		if (!this.begin.equals("") && !this.end.equals("")) {
            return "<gex:temporalElement><gex:EX_TemporalExtent><gex:begin>"+ this.begin + "</gex:begin><gex:end>" + this.end + "</gex:end></gex:EX_TemporalExtent></gex:temporalElement> "; }
        return "";
	}
	
}
