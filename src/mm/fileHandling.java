/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author AMAN
 */
public class fileHandling {
    
    ArrayList ar = new ArrayList();
    String filter;
    long lessthan;
    long greaterthan;
    
    public  ArrayList scanFiles(File f,String filter,long lessthan,long greaterthan)  // filter to find this extension only,  lessthan for size should not be less than this, greaterthan for size should not be greater than this size..
    {
        this.lessthan = lessthan;
        this.greaterthan = greaterthan;
        this.filter = filter;
        if(f.isDirectory())
        {
            //System.out.println(f.getAbsoluteFile());
            File sub[] = f.listFiles();
            //System.out.println(sub.length);
            for(int i=0;i<sub.length;i++)
            {
                scanFiles(sub[i],this.filter,this.lessthan,this.greaterthan);
            }
        }
        else
        {
             //System.out.println(lessthan +" "+ greaterthan + " " +  filter);
            if(filter.equals("") && lessthan==-1 && greaterthan==-1)
            ar.add(f);
            else if(! filter.equals(""))
            {
                String name = f.getName();
                int dotPos = name.lastIndexOf(".");
              
                if(dotPos != -1)
                {
                    String ext = name.substring(dotPos);
                    ext = ext.toLowerCase();
                    if(ext.equals(filter))
                    {   //System.out.print(ext);
                        long size = f.length();
                        if(this.lessthan !=-1  && this.greaterthan == -1)
                        {
                            if( size>=lessthan)
                            {
                                ar.add(f);
                            }
                        }
                        else if(this.greaterthan != -1 && this.lessthan == -1 )
                        {
                            if(size <=greaterthan)
                            {
                                ar.add(f);
                            }
                        }
                        else if(this.lessthan == -1 && this.greaterthan == -1)
                        {
                            ar.add(f);
                        }
                        else
                        {
                            if(size >=lessthan && size<=greaterthan)
                            {
                                ar.add(f);
                            }
                        }
                    }
                }
            }
            
            else if(filter.equals(""))
            {
                long size = f.length();
                        if(this.lessthan !=-1  && this.greaterthan == -1)
                        {
                            if( size>=lessthan)
                            {
                                ar.add(f);
                            }
                        }
                        else if(this.greaterthan != -1 && this.lessthan == -1 )
                        {
                            if(size <=greaterthan)
                            {
                                ar.add(f);
                            }
                        }
                        else if(this.lessthan == -1 && this.greaterthan == -1)
                        {
                            ar.add(f);
                        }
                        else
                        {
                            if(size >=lessthan && size<=greaterthan)
                            {
                                ar.add(f);
                            }
                        }
            }
            
        }
        return ar;
    }
    
}
