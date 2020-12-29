package tud.exc2xml.objects;
import java.util.Random;

public class MetadataIdentifier {
	
	Random rand = new Random(); 
	int int_random = rand.nextInt(10000000);
	@Override
	public String toString() {
	
	String s = "<mdb:metadataIdentifier><mcc:MD_Identifier><mcc:code>" + int_random  + "</mcc:code><mcc:codeSpace>\"http://geokur.tu-dresden.de\"</mcc:codeSpace></mcc:MD_Identifier></mdb:metadataIdentifier>";
	        
	return s;	
	}


}
