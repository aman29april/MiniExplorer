/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TypingFrame.java
 *
 * Created on Aug 5, 2012, 11:33:30 AM
 */
package typingtest;

import java.awt.Color;
import java.awt.Container;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;

/**
 *
 * @author AMAN
 */
public class TypingFrame extends javax.swing.JFrame implements Runnable {

    /** Creates new form TypingFrame */
    Thread t;
    String fpath;
    String filetext = "";
    String typetext = "";
//start and index are the starting and ending positions of current word to be typed
    private int index=0;
    private int start;

    private String sub="";				// this will store the word that is to be typed 
    private String ttext="";			// holds the current word we are typing 

    private int totalhit=0;				// total word we have typed
    private int wronghit=0;				//number of mistakes

    private Highlighter fileht;			//highlighter for file text
    private Highlighter textht;			//highlighter for type text
    private Highlighter.HighlightPainter filepainter;  
    private Highlighter.HighlightPainter textpainter;

    private boolean endfilestatus;			
    private boolean typingstartstatus;
    File file;
    int time;
    boolean go=true;
    int ctime=0;
    public TypingFrame(String fpth,int time)  {
        this.time = time * 60 ;
        
        initComponents();
        t = new Thread(this);
        if(fpth.equals(""))
        {
          this.fpath = "F:\\ajava\\netbeans\\TypingTest\\src\\typingtext\\19.txt";
        }
        else
        {
            fpath = fpth;
        }
        
        file = new File(fpath);
        try {
            FileReader fr = new FileReader(file);
            int i=0;
            while((i=fr.read()) != -1)
            {
                filetext = filetext + (char) i;
            }

            jTextArea1.setText(filetext);
            
            filepainter = new DefaultHighlightPainter(Color.CYAN);
            fileht = new DefaultHighlighter();
            jTextArea1.setHighlighter(fileht);
            
            textht = new DefaultHighlighter();
	    textpainter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
            jTextArea2.setHighlighter(textht);
            
            validate();
            underLine();
            
        } catch (Exception ex) {
            Logger.getLogger(TypingFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void run()
    {
        try
        {
            while(ctime<=time)
            {
                ctime++;
                int val = ctime * 100 /time ;
                
                jProgressBar1.setValue(val);
                System.out.println(val+", ctime = "+ctime+", time = "+time);
                Thread.sleep(1000);
                
            }
            result(time, this);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void underLine()
    {
        String str = jTextArea1.getText();
        if(str.length() > index)
        {
            String space = "";  // to check occurance of space or next word
            sub = "";   //word to be typed
            start = index ; //last position of previous word become starting index of new word
            while(!space.equals("\n") && !space.equals(" "))
            {
                space = ""+ str.charAt(index);
                index ++;
                
            }
            //sub=str.substring(start,index-1); // store the words to typed 
					
	   jTextArea1.setCaretPosition(start); // sets the courser to current postion 
           sub=str.substring(start,index-1); // store the words to typed 
	   jTextArea1.setCaretPosition(start); // sets the courser to current postion 
           
           try {
                    fileht.removeAllHighlights();			//removes all highlights added on filetext 
                    fileht.addHighlight(start, index-1, filepainter); //highlights the current word that is to be typed
                    
               }
           catch (Exception e) 
           {
		System.out.println("some problem with Highlighter "+e);
	    }
           
           //runs the underLine function again if String sub has nothing  
            if(sub.equals("")){
                    underLine();
            }
        }
        else
        {
            endfilestatus=true;
            jTextArea1.setEditable(false);
        }
        
    }

    public boolean getfilestatus(){
		return endfilestatus;
	} 
   
    public synchronized void check(String text){
		
		sub=sub.trim(); 
		
		//if word is not correctly typed
		if(!sub.equals(text)){
			
			wronghit++;	//increment in wronghit
			
			//add highlight to the not correctly typed word
			try {
				textht.addHighlight(jTextArea2.getText().length()-text.length()-1, jTextArea2.getText().length()-1, textpainter);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		
		totalhit++; //increment total hit
		
	}//end of 
    
    public synchronized void backSpace(){
		
		if(ttext.length()>0){
		ttext=ttext.substring(0,ttext.length()-1);
		}
		else{
				jTextArea2.append(" ");
		}
	}//end of backspace function
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 17));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea2KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea2);

        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Time");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1))
                        .addGap(169, 169, 169))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(13, 13, 13))
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jTextArea2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea2KeyPressed

    if(go)
    {
        t.start();
        go = false;
    }
    if(evt.getKeyChar() == ' ')
    {
        jTextArea2.append(" ");
    }
    typingstartstatus = true;
}//GEN-LAST:event_jTextArea2KeyPressed

private void jTextArea2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea2KeyTyped

    jTextArea2.setCaretPosition(jTextArea2.getText().length());
    String ch = "" + evt.getKeyChar();
    
    if((!ch.equals(" ") && !ch.equals("\n") && evt.getKeyChar()!=8 && evt.getKeyChar()!=9 && evt.getKeyChar()!=38 && evt.getKeyChar()!=39 && evt.getKeyChar()!=37 && evt.getKeyChar()!=40) || ch.equals("'") || ch.equals("(") || ch.equals("%") || ch.equals("&"))
                {
			 ttext=ttext+ch;	
		}
    else if(evt.getKeyChar()==8){
            backSpace();	
    }
    else
    {
        if(ttext.length() >0)
        {
            check(ttext);
            underLine();
            ttext = "";
        }
    }
    
}//GEN-LAST:event_jTextArea2KeyTyped

private void jTextArea2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea2KeyReleased

    if(evt.getKeyCode()==37 || evt.getKeyCode()==38 || evt.getKeyCode()==39 || evt.getKeyCode()==40)
		{
			jTextArea2.setCaretPosition(jTextArea2.getText().length());
			System.out.println("released");
		}
}//GEN-LAST:event_jTextArea2KeyReleased

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    
    this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    String fn = file.getName();
    String parent = file.getParent();
    fn = fn.substring(0,fn.indexOf("."));
    int number = Integer.parseInt(fn);
    if(number<19)
    {
        number ++;
    }
    else
    {
        number = 0;
    }
    fn = parent + "\\" + number + ".txt";
    System.out.println(fn);
    this.dispose();
    TypingFrame obj = new TypingFrame(fn,2);
    obj.setVisible(true);
    obj.pack();
    
}//GEN-LAST:event_jButton1ActionPerformed


	public void result(float time, Container win){
		
		jTextArea2.setEditable(false);
                try
                {
                    Thread.sleep(1000);
                }
                catch(Exception e)
                {
                
                }
		if(totalhit>0){
		JOptionPane.showMessageDialog(win, "Gross Speed : "+(int)(totalhit/(time/60))+" WPM \n"
				+"Accuracy : "+(((totalhit-wronghit)*100)/totalhit)+"%"+"\n"
				+"Net Speed : "+(int)((totalhit-wronghit)/(time/60))+" wpm", "Your Typing Speed Result ",JOptionPane.INFORMATION_MESSAGE);
		
			
		}
		else{
			JOptionPane.showMessageDialog(win, "You haven't Typed anything.");
		}
                this.dispose();
                chooseText obj = new  chooseText();
                obj.pack();
                obj.setVisible(true);
		
	}
	
	
	//get typing start status
	public boolean getTypingStartStatus(){
		return typingstartstatus;
	}
	
	public void setTypingStartStatus(boolean set ){
		typingstartstatus=set;
	}

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
            java.util.logging.Logger.getLogger(TypingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TypingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TypingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TypingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TypingFrame("",2).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
