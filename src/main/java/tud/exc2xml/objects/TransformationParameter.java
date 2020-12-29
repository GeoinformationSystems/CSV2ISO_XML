package tud.exc2xml.objects;

public class TransformationParameter {
	String transformationParameter = "";

	public String getTransformationParameter() {
		return this.transformationParameter;
	}

	public void setTransformationParameter(String transformationParameter) {
		this.transformationParameter = transformationParameter;
	}
	public String toString() {
        if (!this.transformationParameter.equals("")) {
            return "<msr:transformationParameterAvailability>" + this.transformationParameter + "</msr:transformationParameterAvailability>";
        }
        return "";
		}	
     }
