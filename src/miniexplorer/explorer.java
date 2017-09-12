/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * explorer.java
 *
 * Created on Jul 29, 2012, 3:35:27 PM
 */
package miniexplorer;
import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;
//import de.
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import typingtest.*;

/**
 *
 * @author AMAN
 */
 class DigitPane extends JPanel {

        private int value;
       
        @Override
        public Dimension getPreferredSize() {
            FontMetrics fm = getFontMetrics(getFont());
            return new Dimension(fm.stringWidth("00"), fm.getHeight());
        }

        public void setValue(int aValue) {
            if (value != aValue) {
                int old = value;
                value = aValue;
                firePropertyChange("value", old, value);
                repaint();
            }
        }

        public int getValue() {
            return value;
        }

        protected String pad(int value) {
            StringBuilder sb = new StringBuilder(String.valueOf(value));
            while (sb.length() < 2) {
                sb.insert(0, "0");
            }
            return sb.toString();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
            String text = pad(getValue());
            FontMetrics fm = getFontMetrics(g.getFont());
            int x = (getWidth() - fm.stringWidth(text)) / 2;
            int y = ((getHeight()- fm.getHeight()) / 2) + fm.getAscent();
            g.drawString(text, x, y);
        }        
    }  


     class TestPane extends JPanel {

        private DigitPane hour;
        private DigitPane min;
        private DigitPane second;
        private JLabel[] seperator;

        private int tick = 0;

        public TestPane() {
            setLayout(new GridBagLayout());

            hour = new DigitPane();
            min = new DigitPane();
            second = new DigitPane();
            seperator = new JLabel[]{new JLabel(":"), new JLabel(":")};

            add(hour);
            add(seperator[0]);
            add(min);
            add(seperator[1]);
            add(second);

            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Calendar cal = Calendar.getInstance();
                    hour.setValue(cal.get(Calendar.HOUR_OF_DAY));
                    min.setValue(cal.get(Calendar.MINUTE));
                    second.setValue(cal.get(Calendar.SECOND));

                    if (tick % 2 == 1) {
                        seperator[0].setText(" ");
                        seperator[1].setText(" ");
                    } else {
                        seperator[0].setText(":");
                        seperator[1].setText(":");
                    }
                    tick++;
                }
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.start();
        }

    }

 class explorer extends javax.swing.JFrame {
     
     
     
    private DigitPane hour;
        private DigitPane min;
        private DigitPane second;
        private JLabel[] seperator;

        private int tick = 0;
    
    DefaultMutableTreeNode topNode;
    DefaultMutableTreeNode d;
    File drives[] = new File[6];
    String absolutePath = "";
    File f1 = new File("");
 JPopupMenu Pmenu;
        JMenuItem menuItem;
    public explorer() {
        initComponents();
        // topNode = new DefaultMutableTreeNode("My Computer");
        
        Pmenu = new JPopupMenu();
		menuItem = new JMenuItem("Cut");
		Pmenu.add(menuItem);
		menuItem = new JMenuItem("Copy");
		Pmenu.add(menuItem);
		menuItem = new JMenuItem("Paste");
		Pmenu.add(menuItem);
		menuItem = new JMenuItem("About Us");
		Pmenu.add(menuItem);
		menuItem = new JMenuItem("Exit");
		Pmenu.add(menuItem);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
                            String s = e.getActionCommand();
                            if(s.equals("Exit"))
                            {
                                /*
                                 this.setVisible(false);
        exitFrame obj1 = new exitFrame();
        obj1.setVisible(true);
        
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                
        try {
            Thread.sleep(3000);
            // obj1.dispose();
        } catch (Exception e) {
        }       
        
            }
        });
    
       obj1.dispose();
        this.dispose();
                                 */
                            }
                        }
			});
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent me){
				if(me.isPopupTrigger()){
					Pmenu.show(me.getComponent(), me.getX(), me.getY());
				}
			}
			public void mouseReleased(MouseEvent Me){
				if(Me.isPopupTrigger()){
					Pmenu.show(Me.getComponent(), Me.getX(), Me.getY());
				}
			}
		});
        
        t = new JTree(topNode);
        drives = File.listRoots();
        for (int i = 0; i < drives.length - 1; i++) {
            d = new DefaultMutableTreeNode(drives[i]);
            topNode.add(d);
        }

//        TestPane tp = (new TestPane());
//        tp.setVisible(true);
//      this.add(tp);
//      this.validate();
//      this.revalidate();
 }
   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        topNode = new DefaultMutableTreeNode("My Computer");
        t = new javax.swing.JTree(topNode);
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("File Accesories");
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new ImageIcon((getClass().getResource("pics//icon.jpg")))  
            .getImage());

        jSplitPane1.setDividerLocation(150);

        jPanel2.setBackground(new java.awt.Color(51, 0, 51));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel2);

        t.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        t.setToolTipText("");
        t.setDragEnabled(true);
        t.setLargeModel(true);
        t.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tMouseReleased(evt);
            }
        });
        t.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                tValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(t);
        //jTree1.add(DefaultMutableTreeNode("aa"));

        jSplitPane1.setLeftComponent(jScrollPane1);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setToolTipText("");

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton3)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton2)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        TestPane tp = new TestPane();
        tp.setVisible(true);
        jPanel5.add(tp);
        this.validate();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );

        jButton1.setText("New");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(102, 0, 153));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenuBar1.setForeground(new java.awt.Color(51, 51, 255));

        jMenu1.setText("File");

        jMenuItem6.setText("About");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem7.setText("Exit");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Tools");

        jMenuItem15.setText("Empty Folder Fixer");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);

        jMenuItem2.setText("Shortcut Fixer");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Bulk File Renamer");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Duplicate File Finder");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Find Files of Same Extension");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem10.setText("Bulk Files Extension Changer");
        jMenu2.add(jMenuItem10);

        jMenuItem12.setText("Set Date and Time");
        jMenu2.add(jMenuItem12);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Screen Shot");

        jMenuItem1.setText("Take Screen Shot");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("System Information");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        jMenuItem14.setText("File Acessories Tools");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem14);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("More Tools");

        jMenuItem8.setText("Image Viewer");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem8);

        jMenuItem9.setText("Typing Test");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenuItem11.setText("Automatic Shutdown");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuItem13.setText("Text Editor");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem13);

        jMenuItem16.setText("Talking Clock");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem16);

        jMenuItem17.setText("Text To Voice Convertor");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem17);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(37, 37, 37))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_tValueChanged

    JTree tr = (JTree) evt.getSource();
    DefaultMutableTreeNode tn = (DefaultMutableTreeNode) tr.getLastSelectedPathComponent();
    //String name = tn.toString();
    TreePath temp = evt.getPath();
    String a = temp.toString();
    String ar[] = a.split(",");
    absolutePath = "";
    if (ar.length != 1) {
        for (int i = 1; i < ar.length; i++) {
            String temp1 = ar[i].substring(1, ar[i].length());
            absolutePath = absolutePath + temp1 + "//";
        }
        absolutePath = absolutePath.substring(0, ((absolutePath.length()) - 3));
        System.out.println(absolutePath);
        f1 = new File(absolutePath);
        String subFiles[] = f1.list();
        if (subFiles != null) {
            for (int i = 0; i < subFiles.length; i++) {
                DefaultMutableTreeNode sub = new DefaultMutableTreeNode(subFiles[i]);
                tn.add(sub);
            }
        }
    }
    if (!f1.isDirectory()) {
        if (absolutePath.endsWith(".txt")) {
            try {

                FileInputStream fis = new FileInputStream(f1);
                DataInputStream in = new DataInputStream(fis);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String contents = "";
                String data;
                while ((data = br.readLine()) != null) {
                    contents = contents + data;

                }
                // System.out.println(contents);
                jTextArea1.setText(contents);
                jTextArea1.setEditable(false);


            } catch (Exception e) {

                System.out.println(e);
            }

        }
    }





}//GEN-LAST:event_tValueChanged

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    jTextArea1.setText("");
    jTextArea1.setEditable(true);
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    JFrame f1 = new JFrame();
    FileDialog fd = new FileDialog(f1, "Select Path to Save File", FileDialog.SAVE);
    fd.setSize(300, 300);
    fd.setVisible(true);
    String path = fd.getDirectory() + fd.getFile();

    File f = new File(path);
    String data = jTextArea1.getText();
    FileWriter fw;
    try {

        fw = new FileWriter(f);
        fw.write(data);
        fw.close();
        jTextArea1.setText("");
        JOptionPane.showMessageDialog(null, "File Saved");

    } catch (Exception ex) {
        Logger.getLogger(explorer.class.getName()).log(Level.SEVERE, null, ex);
    }






}//GEN-LAST:event_jButton2ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    if (!f1.isDirectory()) {
        try {
            File f3 = new File(f1.getAbsolutePath());
            f1 = null;
            //System.out.println(f1.getAbsolutePath());
            //File f2 = new File("d:/aaaa/aaa.txt");
            Boolean status = f3.delete();
            if (status == true) {
                JOptionPane.showMessageDialog(null, "File Deleted..");
            } else {
                JOptionPane.showMessageDialog(null, "Error in Deleting File..");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Sorry. You cant delete directory..");
    }

}//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

        new AboutUs().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
       
        this.setVisible(false);
        exitFrame obj1 = new exitFrame();
        obj1.setVisible(true);
        
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                
        try {
            Thread.sleep(3000);
            // obj1.dispose();
        } catch (Exception e) {
        }       
        
            }
        });
    
       obj1.dispose();
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        new SystemInformation().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

        // TODO add your handling code here:
        new imageViewer().setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new screenShot().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new shortcutFixer().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                //new explorer().setVisible(true);
                File[] s = null;
        new renamer(s).setVisible(true);
            }
        });
//        File[] s = new File[5];
//        new renamer(s).setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        new dubCheck().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed

         new SystemInformation().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed

         java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
//                File[] s = null;
//        new renamer(s).setVisible(true);
                new emptyFolderFixer().setVisible(true);
            }
        });
      
       
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        ArrayList ar = new ArrayList();
       // ArrayList ar = null;
        new SeeAllFilesOfSameExtension(ar).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed

      new  TalkingClock().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed

     private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed

         
         // TODO add your handling code here:
         new  AutoShutdown().setVisible(true);
     }//GEN-LAST:event_jMenuItem11ActionPerformed

     private void tMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMousePressed
         // TODO add your handling code here:
         if(evt.isPopupTrigger()){
					Pmenu.show(evt.getComponent(), evt.getX(), evt.getY());
				}
     }//GEN-LAST:event_tMousePressed

     private void tMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMouseReleased
         // TODO add your handling code here:
         if(evt.isPopupTrigger()){
					Pmenu.show(evt.getComponent(), evt.getX(), evt.getY());
				}
     }//GEN-LAST:event_tMouseReleased

     private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
         // TODO add your handling code here:
         new  Text2Speech().setVisible(true);
         
     }//GEN-LAST:event_jMenuItem17ActionPerformed

     private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
         // TODO add your handling code here:
         new  chooseText().setVisible(true);
     }//GEN-LAST:event_jMenuItem9ActionPerformed

     private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
         // TODO add your handling code here:
         new  notepad().setVisible(true);
     }//GEN-LAST:event_jMenuItem13ActionPerformed

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
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel(  UIManager.getCrossPlatformLookAndFeelClassName());
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    
// UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
// UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel");
UIManager.setLookAndFeel(new SyntheticaClassyLookAndFeel());
 // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            //         UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
        } catch (Exception e) {
            try
            {
            UIManager.setLookAndFeel(  UIManager.getCrossPlatformLookAndFeelClassName());
            
            }
            catch(Exception exp)
            {}
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        // try {
        // 
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }


        // } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(explorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(explorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(explorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(explorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new explorer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
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
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTree t;
    // End of variables declaration//GEN-END:variables
}
