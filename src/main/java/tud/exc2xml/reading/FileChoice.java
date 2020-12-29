package tud.exc2xml.reading;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Document;
import tud.exc2xml.reading.Read;

/*
 * Create GUI and define actions.
 */
public class FileChoice extends JPanel implements ActionListener {
    private static final long serialVersionUID = 291801956569639445L;
    JButton openButton;
    JButton saveButton;
    JTextArea log = new JTextArea(5, 20);
    JFileChooser fc;
    private File zipFile;
    private File folder;
    private static final int BUFFER = 2048;

    public FileChoice() {
        super(new BorderLayout());
        this.log.setMargin(new Insets(5, 5, 5, 5));
        this.log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(this.log);
        logScrollPane.setSize(600, 500);
        this.log.append("Choose a file. \n");
        this.log.append("\n");
        this.log.append("------------------------------------------------------------------------------------\n");
        this.fc = new JFileChooser();
        this.openButton = new JButton("Choose input file");
        this.openButton.addActionListener((ActionListener)this);
        this.saveButton = new JButton("Choose output directory");
        this.saveButton.addActionListener((ActionListener)this);
        this.saveButton.setEnabled(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.openButton);
        buttonPanel.add(this.saveButton);
        this.add((Component)buttonPanel, (Object)"First");
        this.add((Component)logScrollPane, (Object)"Center");
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("GEOKUR - GENERATED XML METADATA");
        frame.setDefaultCloseOperation(3);
        frame.add((Component)new FileChoice());
        frame.setSize(600, 500);
        frame.setMinimumSize(new Dimension(600, 500));
        frame.setPreferredSize(new Dimension(600, 500));
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.openButton) {
            int returnVal = this.fc.showOpenDialog((Component)this);
            if (returnVal == 0) {
                this.openButton.setEnabled(false);
                File file = this.fc.getSelectedFile();
                this.log.append("Opening: " + file.getName() + "." + "\n");
                this.log.append("Process: " + file.getName() + "." + "\n");
                this.log.append("\n");
                Read read = new Read();
                this.folder = read.read(file.getAbsolutePath());
                this.log.append("------------------------------------------------------------------------------------\n");
                this.log.append("Process finished. \n");
                this.log.append("\n");
                this.log.append("------------------------------------------------------------------------------------\n");
                this.log.append("Choose folder to store.\n");
                this.log.append("\n");
                this.saveButton.setEnabled(true);
                this.fc.setFileSelectionMode(1);
            } else {
                this.log.append("Open command cancelled by user.\n");
            }
            this.log.setCaretPosition(this.log.getDocument().getLength());
        } else if (e.getSource() == this.saveButton) {
            int returnVal = this.fc.showSaveDialog((Component)this);
            if (returnVal == 0) {
                this.zipFile = new File(String.valueOf(this.fc.getSelectedFile().getAbsolutePath()) + "\\Generated_XML_Files.zip");
                try {
                    FileChoice.zipDir((File)this.zipFile, (File)this.folder);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.log.append("Saving: " + this.zipFile + " " + "\n");
                this.log.append("\n");
                this.log.append("------------------------------------------------------------------------------------\n");
                this.log.append("Finished.\n");
            } else {
                this.log.append("Save command cancelled by user.\n");
            }
            this.log.setCaretPosition(this.log.getDocument().getLength());
        }
    }

    private static void zipDir(File zipFileName, File dir) throws Exception {
        File dirObj = dir;
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        FileChoice.addDir((File)dirObj, (ZipOutputStream)out, (File)dir);
        out.close();
    }

    static void addDir(File dirObj, ZipOutputStream out, File base) throws IOException {
        File[] files = dirObj.listFiles();
        byte[] tmpBuf = new byte[2048];
        for (int i = 0; i < files.length; ++i) {
            int len;
            if (files[i].isDirectory()) {
                FileChoice.addDir((File)files[i], (ZipOutputStream)out, (File)base);
                continue;
            }
            FileInputStream in = new FileInputStream(files[i].getAbsolutePath());
            out.putNextEntry(new ZipEntry(FileChoice.relative((File)base, (File)files[i])));
            while ((len = in.read(tmpBuf)) > 0) {
                out.write(tmpBuf, 0, len);
            }
            out.closeEntry();
            in.close();
        }
    }

    public static String relative(File base, File file) {
        int rootLength = base.getAbsolutePath().length();
        String absFileName = file.getAbsolutePath();
        String relFileName = absFileName.substring(rootLength + 1);
        return relFileName;
    }

    public static void copyFile(File folder, File file) {
        try {
            int len;
            File f = new File(folder, file.getName());
            f.createNewFile();
            FileInputStream inputStream = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream(f);
            byte[] buf = new byte[2048];
            while ((len = ((InputStream)inputStream).read(buf)) > 0) {
                ((OutputStream)out).write(buf, 0, len);
            }
            ((OutputStream)out).close();
            ((InputStream)inputStream).close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

