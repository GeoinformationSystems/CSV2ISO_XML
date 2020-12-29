package tud.exc2xml.reading;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import tud.exc2xml.reading.FileChoice;

/*
 * Init software and gui.
 */
public class Start {
	 
	public static void main (String args[]) { 
		SwingUtilities.invokeLater(new Runnable() {
            @SuppressWarnings("static-access")
			public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                FileChoice fileChoice = new FileChoice();
                fileChoice.createAndShowGUI();
            }
        });
		
	}	
}
