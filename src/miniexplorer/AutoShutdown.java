/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AutoShutdown.java
 *
 * Created on Oct 11, 2013, 9:15:54 PM
 */
package miniexplorer;
import java.io.IOException;  
import java.util.logging.Level;  
import java.util.logging.Logger;  

/**
 *
 * @author AMAN
 */
public class AutoShutdown extends javax.swing.JFrame {

    /** Creates new form AutoShutdown */
    public boolean running;  
    public static Object MUTEX;  
    public int sec;  
    public int count;  
    Thread t;  
    public AutoShutdown() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        choice1 = new java.awt.Choice();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Enter Time");

        jLabel2.setText("Schdule your system shutdown");

        jTextField1.setText("10");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        choice1.add("sec");
        choice1.add("min")

        jButton1.setText("Schedule Shutdown");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel Shutdown");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(27, 27, 27)
                .addComponent(jButton2)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //System.out.print("System is shutting down");  
            System.out.println(timeRemain());  
            shutDown();  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
       // if(str.equals("cancel"))
        {  
             cancel();  
             this.setTitle("Shut Down Cancelled");  
          //   System.out.println("Shutdown canceled");  
        }  
    }//GEN-LAST:event_jButton2ActionPerformed

      public int timeRemain(){  
          
          int n = Integer.parseInt(jTextField1.getText());
          if(choice1.getSelectedIndex() == 1)
              n = n * 60;
        for(sec=n;sec>=0;sec--){  
            System.out.print(" in " + sec + " second ");  
            // synchronized(syncMutex.mutex){  
                 syncMutex.val = sec;  
                 count = syncMutex.val;  
                 this.setTitle("Remain " + count + " Second To Shut Down");  
           //  }  
            try {  
            Thread.sleep(1000);  
        } catch (InterruptedException ex) {  
            //Logger.getLogger(shutDown.class.getName()).log(Level.SEVERE, null, ex);  
        }  
     }  
        if(count == 0){  
            this.setTitle("System Is Shutting Down....");  
        }  
        
      return sec;  
    }  
    public void shutDown(){  
       Runtime r = Runtime.getRuntime();  
  try {  
                 r.exec("shutdown -s -t 60");  
            } catch (IOException ex) {  
               // Logger.getLogger(shutDown.class.getName()).log(Level.SEVERE,   
               //null, ex);  
            }  
  
    }  
     public void cancel(){  
       Runtime r = Runtime.getRuntime();  
  
            try {  
                 r.exec("shutdown -a");  
            } catch (IOException ex) {  
               
          }  
  
    }  
      
     public void start(){  
          t = Thread.currentThread();  
         if(!t.isAlive()){  
             t.start();  
         }  
           
     }  
  
  
    //@Override  
    public void run(){  
  
            if (running && !t.isAlive()) {  
                this.start();  
            }  
            running = false;  
            t = null;  
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
            java.util.logging.Logger.getLogger(AutoShutdown.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutoShutdown.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutoShutdown.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutoShutdown.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        AutoShutdown obj = new AutoShutdown();
        obj.setVisible(true);
        obj.run();
        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                new AutoShutdown().setVisible(true);
//                
//            }
//        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

class syncMutex{  
    static final Object mutex = new Object();  
    static  int val = 0;  
  
}