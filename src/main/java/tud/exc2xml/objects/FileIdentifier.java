/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  tud.exc2xml.objects.FileIdentifier
 */
package tud.exc2xml.objects;

public class FileIdentifier {
    String fileIdentifier = "";

    public String getFileIdentifier() {
        return this.fileIdentifier;
    }

    public void setFileIdentifier(String fileIdentifier) {
        this.fileIdentifier = fileIdentifier;
    }

    public String toString() {
        if (this.fileIdentifier.equals("")) {
            return "";
        }
        return "<gmd:fileIdentifier><gco:CharacterString>" + this.fileIdentifier + "</gco:CharacterString></gmd:fileIdentifier>";
    }

    public String toServiceString() {
        if (this.fileIdentifier.equals("")) {
            return "";
        }
        return "<gmd:fileIdentifier><gco:CharacterString>" + this.fileIdentifier.replace(":metadata:dataset", ":metadata:service") + "</gco:CharacterString></gmd:fileIdentifier>";
    }
}

