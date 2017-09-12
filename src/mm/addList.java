/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;



import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class addList extends JList implements ListSelectionListener
 {

    ImageIcon i1,i2;
    
    
    JPanel jf;
    Vector v1;
   JPanel p2;
   JPanel p1;
   JPanel p = new JPanel();
   
 public addList(String s) 
   {    
      
       jf = new JPanel();
       v1 = new Vector();
       p1 = new JPanel();
       p2 = new JPanel();
       //setCellRenderer(new CustomCellRenderer());
       p1.setForeground(Color.black);
       p1.setBackground(Color.white);
       
       p2.setForeground(Color.black);
       p2.setBackground(Color.white);
       //v1.addElement(addFrame("", ""));
       //v1.addElement(addFrame("", ""));
       setListData(v1);
       setCellRenderer(new CustomCellRenderer());
       this.addListSelectionListener(this);
       //this.setBorder(new LineBorder(Color.black));
       //p.add(this);

   }
 
 public JPanel addFrame(String s1,String s2)
 {
     jf = new JPanel();
     p1 = new JPanel();
     p2 = new JPanel();
     p1.setLayout(new FlowLayout(FlowLayout.LEFT));
     p2.setLayout(new FlowLayout(FlowLayout.LEFT));
    
     p1.setForeground(Color.black);
       p1.setBackground(Color.white);
       
       p2.setForeground(Color.black);
       p2.setBackground(Color.white);
     jf.setLayout(new GridLayout(1, 2));
     //p1.add()
     p1.add(new JLabel(s1));
     p2.add(new JLabel(s2));
     jf.add(p1);
     jf.add(p1);
     jf.setBorder(new LineBorder(Color.black));
     jf.add(p2);
     setCellRenderer(new CustomCellRenderer());
     v1.addElement(jf);
    setListData(v1);
   //this.setCellRenderer(new CustomCellRenderer());
//     v1.addElement(jf);
//     setListData(v1);
      
////     setCellRenderer(new CustomCellRenderer());
     //jf.add(this);
     return jf;
 }
 
 public void valueChanged(ListSelectionEvent evt)
 {
 
//   JPanel p = (JPanel)this.getSelectedValue();
//   JLabel l = (JLabel)p.getComponent(1);
   //this.setCellRenderer(new CustomCellRenderer());
 }
 

 public static void main(String[] args) 
  {
  
   addList obj = new addList("");
   //obj.setVisible(true);
   //obj.setSize(300, 300);
  
   }

 class CustomCellRenderer implements ListCellRenderer
  {
 
   public Component getListCellRendererComponent (JList l1, Object v, int index,boolean selected,boolean focus) 
   {
     Component c = (Component)v;
     c.setBackground(selected ? Color.yellow : Color.white);
     c.setForeground(selected ? Color.white : Color.black);
     return c;
     }
   }
}