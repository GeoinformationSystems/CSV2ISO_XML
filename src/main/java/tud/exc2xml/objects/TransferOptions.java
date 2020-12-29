/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  tud.exc2xml.objects.TransferOptions
 */
package tud.exc2xml.objects;

public class TransferOptions {
    String url = "";
    String type = "";

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        if (!this.url.equals("") && !this.type.equals("")) {
            return "<gmd:transferOptions><gmd:MD_DigitalTransferOptions><gmd:onLine><gmd:CI_OnlineResource><gmd:linkage><gmd:URL>" + this.url + "</gmd:URL>" + "</gmd:linkage>" + "<gmd:function>" + "<CI_OnLineFunctionCode " + "xmlns=\"http://www.isotc211.org/2005/gmd\" " + "codeList=\"http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/Codelist/ML_gmxCodelists.xml#CI_OnLineFunctionCode\" " + "codeListValue=\"" + this.type + "\"/>" + "</gmd:function>" + "</gmd:CI_OnlineResource>" + "</gmd:onLine>" + "</gmd:MD_DigitalTransferOptions>" + "</gmd:transferOptions>";
        }
        return "";
    }
}

