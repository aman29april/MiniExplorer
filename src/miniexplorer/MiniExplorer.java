/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniexplorer;

import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;
import javax.swing.UIManager;

/**
 *
 * @author AMAN
 */
public class MiniExplorer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        try
//        {
//            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
//        }
//        catch(Exception e)
//        {
//        }
        
                try {
            
UIManager.setLookAndFeel(new SyntheticaClassyLookAndFeel());
        } catch (Exception e) {
            try
            {
            UIManager.setLookAndFeel(  UIManager.getCrossPlatformLookAndFeelClassName());
            
            }
            catch(Exception exp)
            {}
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        loading1 obj = new loading1();
        obj.setVisible(true);
        try
        {
            Thread.sleep(3000);
            obj.setVisible(false);
            obj.dispose();
            explorer obj1 = new explorer();
            obj1.setVisible(true);
        }
        catch(Exception e)
        {}
    }
}
