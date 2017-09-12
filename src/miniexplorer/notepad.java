/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * notepad.java
 *
 * Created on Aug 6, 2012, 5:00:01 PM
 */
package miniexplorer;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

/**
 *
 * @author AMAN
 */
public class notepad extends javax.swing.JFrame {

    UndoManager um = new UndoManager();
    Document doc ;
    boolean textChanged=false;
    JTextArea textarea;
    
    TextTab tab = new TextTab();
  
    public notepad() {
        initComponents();
        textarea = tab.getjTextArea1();
        jTabbedPane1.addTab("title", new TextTab());
        doc = textarea.getDocument();
        doc.addUndoableEditListener(um);
        //jTextArea1.addKeyListener( public void keyty );
    }

    public FileDialog creatSaveFileDialog() throws HeadlessException {
        JFrame jf = new JFrame();
        FileDialog fd = new FileDialog(jf, "Save As", FileDialog.SAVE);
        fd.setSize(300,300);
        fd.setVisible(true);
        return fd;
    }

    public boolean getSavedStatus() {
        
        if (f.exists()) {
            return true;
        } else {
            return false;
        }
    }
    
    public File getThisFile()
    {
        String fname = jTabbedPane1.getToolTipTextAt(getThisTabIndex());
        File f = new File(fname);
        if(f.exists())
        {
            return f;
        }
        else
        {
            return null;
        }
    }
    
    public void keyTyped(KeyEvent ke)
    {
        textChanged = true;
        System.out.println("ok");
    }

    public String getThisText() {
        JPanel temp = (JPanel)jTabbedPane1.getSelectedComponent();
        JScrollPane  jsp = (JScrollPane)temp.getComponent(1);
        JViewport va = (JViewport)jsp.getComponent(0);
        JTextArea ta = (JTextArea)va.getComponent(0);
        return (ta.getText());
    }
    public JPanel getThisPanel()
    {
        
        return((JPanel)jTabbedPane1.getSelectedComponent());
    }
    
    public JTextArea getThisTextArea(){
        JPanel temp = (JPanel)jTabbedPane1.getSelectedComponent();
        JScrollPane  jsp = (JScrollPane)temp.getComponent(1);
        JViewport va = (JViewport)jsp.getComponent(0);
        JTextArea ta = (JTextArea)va.getComponent(0);
        return (ta);
    }
    
    public int getThisTabIndex()
    {
        return(jTabbedPane1.getSelectedIndex());
    }

    public void makeNewTab(String name , String path) {
        
        
        
        JPanel p1 = new JPanel();
       
        p1.setLayout(new FlowLayout(SwingConstants.CENTER));
        JLabel l1 = new JLabel("1");
        //l1.setAlignmentY(CENTER_ALIGNMENT);
        p1.add(l1);
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1,1));
        
        JTextArea ta = new JTextArea(10,10);
        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
        ta.setAutoscrolls(true);
        JScrollPane jsp = new JScrollPane();
        jsp.add(p1);
        p2.add(jsp);
        
        int n=jTabbedPane1.getTabCount();
        ++n;
        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        p3.add(p1,BorderLayout.WEST);
        p3.add(jsp,BorderLayout.CENTER);
        if(name.equals(""))
        {
             jTabbedPane1.addTab("New "+n,null, p3,"New " +n);
             //jTabbedPane1.addtab
            ta.grabFocus();
            p2.grabFocus();
            jTabbedPane1.setSelectedIndex(n-1);
        }
        else
        {
            jTabbedPane1.addTab(name,null, p3,path);
            jTabbedPane1.setSelectedIndex(jTabbedPane1.getTabCount() -1);
        }
        
        jsp.setViewportView(ta);
        ta.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke)
            {
                textChanged = true;
            }
        });
        
        
        
    }

    public void saveThisFile(File f) {
            try {
                FileWriter fr = new FileWriter(f);
                String data = getThisText();
                fr.write(data);
                fr.close();
                updateTabTitle( f.getName(), f.getAbsolutePath());
                
            } catch (IOException ex) {
                Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void updateTabTitle(String fname, String dir) {
        jTabbedPane1.setTitleAt(getThisTabIndex(), fname);
        jTabbedPane1.setToolTipTextAt(getThisTabIndex(), dir);
    }
            

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Notepad");

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setAutoscrolls(true);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Open");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Reload From Disk");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Save");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Save As");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Save All");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem7.setText("Rename");
        jMenu1.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Close");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Close All");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Print..");
        jMenu1.add(jMenuItem10);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Undo");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setText("Redo");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem13.setText("Cut");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem14.setText("Copy");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem15.setText("Paste");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);

        jMenuItem16.setText("Delete");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem16);

        jMenuItem17.setText("Select All");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem17);

        jMenu3.setText("Copy To Clip Board");
        jMenu2.add(jMenu3);

        jMenu4.setText("Change Case to");

        jMenuItem18.setText("Upper Case");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem18);

        jMenuItem19.setText("Lower Case");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem19);

        jMenu2.add(jMenu4);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Search");

        jMenuItem20.setText("Find");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem20);

        jMenuItem21.setText("Find in Files");
        jMenu5.add(jMenuItem21);

        jMenuItem22.setText("Find and Replace");
        jMenu5.add(jMenuItem22);

        jMenuItem23.setText("Go To");
        jMenu5.add(jMenuItem23);

        jMenuItem24.setText("jMenuItem21");
        jMenu5.add(jMenuItem24);

        jMenuItem25.setText("jMenuItem21");
        jMenu5.add(jMenuItem25);

        jMenuItem26.setText("jMenuItem21");
        jMenu5.add(jMenuItem26);

        jMenuItem27.setText("jMenuItem21");
        jMenu5.add(jMenuItem27);

        jMenuItem28.setText("jMenuItem21");
        jMenu5.add(jMenuItem28);

        jMenuItem29.setText("jMenuItem21");
        jMenu5.add(jMenuItem29);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("View");
        jMenuBar1.add(jMenu6);

        jMenu7.setText("jMenu7");
        jMenuBar1.add(jMenu7);

        jMenu8.setText("jMenu8");
        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("New 1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        makeNewTab("","");
    
    
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        File f = getThisFile();
        if(f == null)  // means file has never been saved
        {
        
            FileDialog fd = creatSaveFileDialog();
            String dir = fd.getDirectory() + fd.getFile();
            if(fd.getFile() != null)
            {
                f = new File(dir);
                saveThisFile(f);    

            }
        }
        else  
        {
            if(textChanged)
            {
                saveThisFile(f);
            }
        }
        
    if(textChanged)
    {
        
    }   

}//GEN-LAST:event_jMenuItem4ActionPerformed

private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed

    JTextArea ta = getThisTextArea();
    String select = ta.getSelectedText();
    if(select != null)
    {
        select = select.toUpperCase();
        ta.replaceSelection(select);;
    }
    else
    {
        select = ta.getText().toUpperCase();
        ta.setText(select);
    }
}//GEN-LAST:event_jMenuItem18ActionPerformed

private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed

    JTextArea ta = getThisTextArea();
    String select = ta.getSelectedText();
    if(select != null)
    {
        select = select.toLowerCase();
        ta.replaceSelection(select);;
    }
    else
    {
        select = ta.getText().toLowerCase();
        ta.setText(select);
    }
}//GEN-LAST:event_jMenuItem19ActionPerformed

private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed

    JTextArea ta = getThisTextArea();
    ta.selectAll();
}//GEN-LAST:event_jMenuItem17ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    JFrame jf  = new JFrame();
    FileDialog fd =  new FileDialog(jf, "Select File To Open ");
    fd.setSize(300,300);
    fd.setVisible(true);
    
    String file = fd.getDirectory() + fd.getFile();
    if(fd.getFile() != null)
    {
        File f = new File(file);
        for(int i=0;i<jTabbedPane1.getTabCount();i++)
        {
            
        }
            try {
                FileInputStream fis = new FileInputStream(f);
                byte b[] = new byte[(int)f.length()];
                fis.read(b);
                String data ="";
                for(int i=0;i<b.length;i++)
                {
                    data = data + (char)b[i];
                }
                makeNewTab(fd.getFile(),f.getAbsolutePath());
                JTextArea ta = getThisTextArea();
                ta.setText(data);
                
            } catch (Exception ex) {
                Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
}//GEN-LAST:event_jMenuItem2ActionPerformed

private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed

    JTextArea ta = getThisTextArea();
    //String selected = ta.getSelectedText();
    ta.cut();
}//GEN-LAST:event_jMenuItem13ActionPerformed

private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed

    JTextArea ta = getThisTextArea();
    ta.copy();
}//GEN-LAST:event_jMenuItem14ActionPerformed

private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed

    JTextArea ta = getThisTextArea();
    ta.paste();
}//GEN-LAST:event_jMenuItem15ActionPerformed

private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

//    Component cmp = jTabbedPane1.getSelectedComponent();
//    JPanel p = (JPanel) cmp;
//    p.setVisible(false);
    
    jTabbedPane1.remove(jTabbedPane1.getSelectedIndex());
}//GEN-LAST:event_jMenuItem8ActionPerformed

private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed

    for(int i=0;i<jTabbedPane1.getTabCount();i++)
    {
        jTabbedPane1.remove(i);
    }
}//GEN-LAST:event_jMenuItem9ActionPerformed

private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed

    if(um.canUndo())
    {
        um.undo();
    }
}//GEN-LAST:event_jMenuItem11ActionPerformed

private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed

    String name = 
}//GEN-LAST:event_jMenuItem16ActionPerformed

private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed

    if(um.canRedo())
    {
        um.redo();
    }
}//GEN-LAST:event_jMenuItem12ActionPerformed

private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        FileDialog fd = creatSaveFileDialog();
    String dir = fd.getDirectory() + fd.getFile();
    if(fd.getFile() != null)
    {
            File temp = new File(dir);
            saveThisFile(temp);
        
    }
}//GEN-LAST:event_jMenuItem5ActionPerformed

private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

    for(int i=0;i<jTabbedPane1.getTabCount();i++)
    {
        //JPanel p
    }
}//GEN-LAST:event_jMenuItem6ActionPerformed

private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed

    Find_Replace obj = new Find_Replace(this);

    obj.pack();
    obj.setVisible(true);
}//GEN-LAST:event_jMenuItem20ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new notepad().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private java.awt.PopupMenu popupMenu1;
    // End of variables declaration//GEN-END:variables
}
