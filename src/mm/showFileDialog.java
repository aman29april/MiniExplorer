/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import java.awt.FileDialog;
import javax.swing.JFrame;

/**
 *
 * @author AMAN
 */
public  class showFileDialog {
    
    JFrame jf;
    FileDialog fd;
    
    public FileDialog SaveDialog()
    {
        jf = new JFrame();
        fd = new FileDialog(fd, "Select path to save file..", FileDialog.SAVE);
        fd.setSize(300, 300);
        fd.setVisible(true);
        return fd;
    }
    
    public FileDialog openDialog()
    {
        jf = new JFrame();
        fd = new FileDialog(fd, "Select path to open..");
        fd.setSize(300, 300);
        fd.setVisible(true);
        return fd;
    }
}
