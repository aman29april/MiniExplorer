
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
 class Solution {
     List ls = new ArrayList<String>();
    public static void main(String args[] ) throws Exception 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        int n = Integer.parseInt(br.readLine());
        String ar[] = new String[n];
        Solution obj = new Solution();
        for(int i=0;i<ar.length;i++)
        {
            ar[i] = br.readLine();
            obj.ls.add(ar[i]);
        }
        int j=0;
        //text = text.replaceAll('', "");
       text = text.replace(".", "");
        //System.out.println(text);
        //text = text.toLowerCase();
        String tArray[] = text.split(" ");
        int i=0;
        for(i =0;i<(tArray.length - ar.length);i++)
        {
            boolean flag = false;
            obj.ls = new ArrayList<String>();
            for(int k=0;k<ar.length;k++)
            {
                obj.ls.add(ar[k]);
            }
            for(j = 0;j<ar.length;j++)
            {
                boolean res = obj.check(i+j,obj.ls,tArray);
                if(res)
                {
                    //continue;
                }
                else
                {
                    flag = true;
                    break;
                }
            }
             if(j == ar.length)
        {
            for(int k=0;k<ar.length;k++)
            {
                System.out.print(tArray[i+k] + " ");
            }
           System.exit(i);
        }
        
        }
       
        if(i==(tArray.length - ar.length))
        {
            System.out.println("NO SUBSEGMENT FOUND");
        }
        
    }

 boolean check(int n,List ls ,String[] ar)
 {
     int flag = 0;
     for(int i=0;i<ls.size();i++)
     {
        if(ar[n].equalsIgnoreCase((String) ls.get(i)))
        {
            
            flag = 1;
            ls.remove(i);
            break;
            //check(n++,ls,ar);
        }
     }
    
     if(flag == 0)
         return false;
     else 
         return true;
     
     
     
 }
 
 }
