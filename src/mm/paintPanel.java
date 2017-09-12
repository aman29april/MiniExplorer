/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import javax.swing.JPanel;

/**
 *
 * @author AMAN
 */
public class paintPanel extends JPanel {
    
    int x1,y1,x2,y2;
    boolean point=true,circle,line, rect ,roundrect, start=true;
    Raster r; // to store current drawing
    Raster rpre;  // to store previous drawing
    BufferedImage bf;   // used for drawing
    Cursor cursor;
    Rectangle rec; 
    int a;
    int b ;
    int c ;
    int d ;
    Color color;
    paintPanel()
    {
        super();
        jPanel1.requestFocus();
        rec = jPanel1.getBounds();
        
        d = (int) rec.getHeight();
        c = (int) rec.getWidth();
        a = (int) rec.getX() ;
        b = (int) rec.getY();
        bf = new BufferedImage(c,d, BufferedImage.TYPE_INT_RGB);
        color = Color.BLACK;
        System.out.println(a +","+b+","+c +","+d);
        validate();
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        
        g.setColor(color);
        if(bf.getHeight() != d || bf.getWidth() !=c)
        {
            r = bf.getData();
            bf= new BufferedImage(jPanel1.getWidth(),jPanel1.getHeight(),BufferedImage.TYPE_INT_RGB);
            bf.getGraphics().setColor(Color.WHITE);
            bf.getGraphics().fillRect(this.getX(),this.getY(),this.getWidth(),this.getHeight());
            bf.setData(r);
        }
         draw(bf.getGraphics());
		
       g.drawImage(bf,a+10,b+50,null);
      
       
        
    }
    
}
