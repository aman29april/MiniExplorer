/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SeeAllFilesOfSameExtension.java
 *
 * Created on Aug 8, 2012, 9:58:48 PM
 */
package miniexplorer;

import java.awt.FileDialog;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mm.fileHandling;
import mm.showFileDialog;


public class SeeAllFilesOfSameExtension extends javax.swing.JFrame implements Runnable {

    /** Creates new form SeeAllFilesOfSameExtension */
    Thread t;
    File dir;
    String ext = ".jpg"; // for extension check
    long lessthan =10 * 1024; // for size should not be less than this value
    long greaterthan = 10 * 1024 * 1024; // for long should not be greater than this values
    long lvalue=10; // for textfield in which less than value added
    long gvalue=10; // for textfield in which greater than value is filled
    long lMultiple =1024;
    long gMultiple = 1024*1024;  // to multiply with text box value with unit
    
    ArrayList ar = new ArrayList();
    int totalFiles =0;
    boolean status = true; // for progress bar..
    showFileDialog fDialog = new showFileDialog();
    public SeeAllFilesOfSameExtension( ArrayList ar) {
        initComponents();
        t = new Thread(this);
        this.ar = ar;
        if(! ar.isEmpty())
        {
            makeTable(ar);
        }
        
    }

    public void makeTable(ArrayList ar) {
        
        //String data[][] = new String[1][1];
        if(ar.size() ==  0 )
        {
            String cols[] = {"File Name ","File Path","Size" };
             String data[][] = {{"No files..","",""}};
             jTable1 = new JTable(data,cols);
        }
        else
        {
            String cols[] = {"File Name ","File Path","Size" };
            String data[][] = new String[ar.size()][3];
            Iterator itr = ar.iterator();
            int i,j;
            i=0;
            j=0;
            //System.out.println(ar.size());
            while(itr.hasNext())
            {
                File temp = (File) itr.next();
                data[i][j] =  temp.getName();
                data[i][++j] =  temp.getAbsolutePath();
                data[i][++j] = (temp.length() / 1024 )+ " kb";
                //data[i][++j] =  temp.lastModified() + "";
                i++;
                j=0;
                //System.out.println(i);
            }
            jTable1 = new JTable(data,cols);
         }   
        
        jScrollPane1.setViewportView(jTable1);
    }

    public void moveOrCopy(String type) throws HeadlessException {
        if(ar.isEmpty())  JOptionPane.showMessageDialog(this, "Add some files first.","Error Msg",JOptionPane.ERROR_MESSAGE);
        else
        {
            FileDialog fd = fDialog.openDialog();
            fd.setTitle("Select path to copy files..");
            if(fd.getFile() !=null)
            {
                String path = fd.getDirectory();
                Iterator itr = ar.iterator();
               // Object options[] = {"ok","cancel"};
                int ans = JOptionPane.showConfirmDialog(this,"If same file lie in source and destination \n file will be replaced \n you still want to continue?", path,JOptionPane.OK_CANCEL_OPTION);


                if(ans == 0)// if ok pressed. . ans=2 for cancel..
                {
                    while(itr.hasNext())
                    {
                        File src = (File) itr.next();
                        String name = src.getName();
                        Path sp = Paths.get(src.getAbsoluteFile()+"");
                        Path dp = Paths.get(path + "\\" + name);
                           try {
                               if(type.equals("copy"))
                               Files.copy(sp, dp,StandardCopyOption.REPLACE_EXISTING);

                               else if(type.equals("move"))
                                   Files.move(sp, dp, StandardCopyOption.REPLACE_EXISTING);

                               else if(type.equals("delete"))
                               {
                                   Files.deleteIfExists(dp);

                               }


                               //Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
                           } catch (IOException ex) {
                               Logger.getLogger(SeeAllFilesOfSameExtension.class.getName()).log(Level.SEVERE, null, ex);
                           }


                    }
                    if(ar.size() == 0) JOptionPane.showMessageDialog(this, "There are not files..");
                    else
                    JOptionPane.showMessageDialog(this, "File Action Sucessful..");
                }   
            }
        }    
    }
    
    public void deleteAll(String type)
    {       
            if(ar.isEmpty())  JOptionPane.showMessageDialog(this, "Add some files first.","Error Msg",JOptionPane.ERROR_MESSAGE);
        else
         {
                Iterator itr = ar.iterator();
                int ans = JOptionPane.showConfirmDialog(this,"these files will be deleted permanantly \n you still want to continue?","",JOptionPane.OK_CANCEL_OPTION);
                if(ans == 0)// if ok pressed. . ans=2 for cancel..
                {
                    while(itr.hasNext())
                    {
                        File src = (File) itr.next();

                        Path dp = Paths.get(src.getAbsolutePath()+"");
                           try {
                              if(type.equals("delete"))
                               {
                                   Files.deleteIfExists(dp);

                               }
                           } catch (IOException ex) {
                               Logger.getLogger(SeeAllFilesOfSameExtension.class.getName()).log(Level.SEVERE, null, ex);
                           }


                }   
            }
                JOptionPane.showMessageDialog(this, "Files deleted Sucessfuly..");
                ar = new ArrayList();
        }       
    }
    
    public void run()
    {
        boolean flag = false;
        try
        { 
            int i=0;
            while(status)
            {
                if(jProgressBar1.getValue() >=100)
                     i =0;// flag = true;
                if(jProgressBar1.getValue() <= 0)
                     flag = false;
                
                if(flag == true)
                    i = i-10;
                else
                    i = i+10;
                
                jProgressBar1.setValue(i);
                //jProgressBar1.set
                Thread.sleep(100);
            }
            i=0;
            jProgressBar1.setValue(i);
           t.suspend();
        }
        catch(Exception ae)
        {
            System.out.println(ae);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("See Files of Same Extension");

        jTextField1.setColumns(20);

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.addItem("Image Files(.jpg)");
        jComboBox1.addItem("Text Files (.txt)");
        jComboBox1.addItem("Audio Files(.mp3)");
        jComboBox1.addItem("Video Files(.mp4)");
        jComboBox1.addItem("HTML Files(.html)");
        jComboBox1.addItem("PHP Files(.php)");
        jComboBox1.addItem("JAVA Files(.java)");
        jComboBox1.addItem("C Files(.c)");
        jComboBox1.addItem("C++ Files(.cpp)");
        jComboBox1.addItem("Java Script Files(.js)");
        jComboBox1.addItem("CSS Files(.css)");
        jComboBox1.addItem("Gif Files(.gif)");
        jComboBox1.addItem("XML Files(.xml)");
        jComboBox1.addItem("All Files()");
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Select File type To Search");

        jLabel2.setText("Select Directory To Scan");

        jToggleButton1.setText("Advance Option>>");
        jToggleButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButton1StateChanged(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel2))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addComponent(jToggleButton1)
                        .addGap(32, 32, 32)))
                .addGap(71, 71, 71))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("Move All To Folder");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete Parmanently");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Move To Recycle Bin");

        jButton6.setText("Copy All To Folder");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Rename These");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton6)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton7))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel3.setVisible(false);
        jPanel3.setEnabled(false);

        jLabel3.setText("File Size should be not be less than");

        jTextField2.setColumns(3);
        jTextField2.setText("10");

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox2.addItem("bytes");
        jComboBox2.addItem("kb");
        jComboBox2.addItem("mb");
        jComboBox2.setSelectedIndex(1);

        jLabel4.setText("greater than");

        jTextField3.setColumns(3);
        jTextField3.setText("10");

        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jComboBox3.addItem("kb");
        jComboBox3.addItem("mb");
        jComboBox3.addItem("gb");
        jComboBox3.setSelectedIndex(1);

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jCheckBox1.setSelected(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox1)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("SCAN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField4.setColumns(10);
        jTextField4.setEditable(false);

        jLabel5.setText("Total Files :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(124, 124, 124))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE))
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    deleteAll("delete");
    makeTable(new ArrayList());
    
}//GEN-LAST:event_jButton4ActionPerformed

private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jComboBox2ActionPerformed

private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jComboBox3ActionPerformed

private void jToggleButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButton1StateChanged

    
}//GEN-LAST:event_jToggleButton1StateChanged

private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

    if(jPanel3.isVisible())
    {
        jPanel3.setVisible(false);
    }
    else
    {
        jPanel3.setVisible(true);
    }
}//GEN-LAST:event_jToggleButton1ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    JFrame jf = new JFrame();
    FileDialog fd = new FileDialog(jf,"Select path to scan..", FileDialog.LOAD);
    fd.setVisible(true);
    fd.setSize(300, 300);
    
    if(fd.getFile() != null)
    {
        dir = new File(fd.getDirectory());
        t= new Thread(this);
        status = true;
        t.start();
        ar = new ArrayList();
             fileHandling obj = new fileHandling();
             
             //ext = ".jpg";
             if(jCheckBox1.isSelected())
             {
                 lvalue = Integer.parseInt(jTextField2.getText());
                 gvalue = Integer.parseInt(jTextField3.getText());
                 lessthan =lvalue * lMultiple; // for size should not be less than this value
                 greaterthan = gvalue * gMultiple; 
                 System.out.println(lessthan + "  " + greaterthan);
                 ar = obj.scanFiles(dir,ext,lessthan,greaterthan);
             }
             else
             {
                 ar = obj.scanFiles(dir, "", -1, -1);
             }
            
            totalFiles = ar.size();
            jTextField4.setText(""+totalFiles);
            makeTable(ar);
            status = false;
    }
}//GEN-LAST:event_jButton1ActionPerformed

private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

    String select = (String) jComboBox1.getSelectedItem();
    int start = select.lastIndexOf("(");
    int end = select.lastIndexOf(")");
    ext = select.substring(++start,end);
    if(select.equals("All Files()"))
        ext ="";
//    System.out.println(ext+"1");
//    if(ext == null)
//    {
//        ext ="";
//        System.out.println(ext +" ok");
//    }
    
}//GEN-LAST:event_jComboBox1ItemStateChanged

private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged

    String unit = (String) jComboBox2.getSelectedItem();
    
    if(unit.equals("kb"))
    {
        lMultiple =1024;
    }
    else if(unit.equals("mb"))
    {
        lMultiple= 1024*1024;
    }
    else
    {
        lMultiple = 1;
    }
}//GEN-LAST:event_jComboBox2ItemStateChanged

private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged

    String unit = (String) jComboBox3.getSelectedItem();
    gvalue = Integer.parseInt(jTextField3.getText());
    if(unit.equals("kb"))
    {
        gMultiple = 1024;
    }
    else if(unit.equals("mb"))
    {
        gMultiple = 1024*1024;
    }
    else if(unit.equals("gb"))
    {
        gMultiple = 1024*1024* 1024;
    }
    else
    {
    
    }
}//GEN-LAST:event_jComboBox3ItemStateChanged

private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jCheckBox1ActionPerformed

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        moveOrCopy("copy");
        makeTable(new ArrayList());
}//GEN-LAST:event_jButton6ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    moveOrCopy("move");
    makeTable(new ArrayList());
}//GEN-LAST:event_jButton3ActionPerformed

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jComboBox1ActionPerformed

private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    if(ar.isEmpty()) JOptionPane.showMessageDialog(this, "Add some files first.. ", "Eror message",JOptionPane.ERROR_MESSAGE);
    else
    {
        int size = ar.size();
        File[] file =  new File[size];
        Iterator itr = ar.iterator();
        int i=0;
        while(itr.hasNext())
        {
            file[i] = (File) itr.next();
            i++;
        }
        
        renamer obj = new renamer(file);
        this.dispose();
        obj.setVisible(true);
        obj.pack();
    }   
}//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(SeeAllFilesOfSameExtension.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeeAllFilesOfSameExtension.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeeAllFilesOfSameExtension.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeeAllFilesOfSameExtension.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ArrayList temp = new ArrayList();
                new SeeAllFilesOfSameExtension(temp).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
