package javaapplication14;

import java.io.*;  
import java.net.*;  
import java.util.Random;
public class MyServer 
{  
    static     int [][] sud=new int[9][9];
    static     Boolean[] arr=new Boolean[10];
    static     Random r=new Random();
    static     int val=0,blck=0,t=0,p,k;
    static     DataOutputStream dout;
    static DataInputStream dis;
public static void main(String[] args)
{  
try{  
ServerSocket ss=new ServerSocket(6666);  
Socket s=ss.accept();//establishes connection   
 dis=new DataInputStream(s.getInputStream());  
dout=new DataOutputStream(s.getOutputStream());

init();
String  str=(String)dis.readUTF();  

while(!str.equals("quit"))
{
if(str.equals("new"))
{
    create();
    print();
    send();
}

if(str.equals("submit"))
{
    recieve();
    
}

if(str.equals("solve"))
{
 sendsol();   
}
str=(String)dis.readUTF();

System.out.println("message= "+str);  
}


ss.close();  
}catch(Exception e){System.out.println(e);}  
}  

    private static void init() 
    {
        for(p=0;p<9;p++)
        {
            for(k=0;k<9;k++)
            {
                sud[p][k]=0;
            }
        }
    }

    private static void create() 
    {
        for(int b=0;b<10;b++)
        {
            arr[b]=false;
        }
    for(int i=1;i<10;i++)
        {
            do
            {
            val=r.nextInt(10);
            }while(val==0 || arr[val]==true);
            
            arr[val]=true;
            
            
            
            t=0;
            blck=0;
            for(int j=0;j<9;j++)
            {
                while(sud[blck][t]!=0)
                {
                    t++;
                    if(t==9)
                    {
                        t=0;
                        break;
                    }
                }
            sud[blck][t]=val;
            blck++;
            t=t+3;
            if((t-3)==6)
            {
                t=1;
            }
            else if((t-3)==7)
            {
                t=2;
            }
            else if((t-3)==8)
            {
                t=0;
            }
            } 
        }
    }

    private static void print() 
    {
    for(p=0;p<9;p++)
        {
            for(k=0;k<9;k++)
            {
                System.out.print(sud[p][k]+" ");
                if((k+1)%3==0)
                {
                    System.out.print("\t");
                }
            }
            if((p+1)%3==0)
                {
                    System.out.print("\n");
                }
            System.out.println("");
        }
    }

    private static void send() 
    {
    
        try
        {
            for(p=0;p<9;p++)
        {
            int temp=r.nextInt(8);
            int temp1=r.nextInt(8);
            int temp2=r.nextInt(8);
            for(k=0;k<9;k++)
            {
                if(k==temp || k==temp1 || k==temp2)
                dout.writeInt(sud[p][k]);
                else
                dout.writeInt(0);   
            }
        }
        
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void sendsol() 
    {
        try
        {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                dout.writeInt(sud[i][j]);
            }
        }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    
    }

    private static void recieve() 
    {
        Boolean wrong=false;
        try
        {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(sud[i][j]!=dis.readInt())
                {
                    wrong=true;
                }
            }
        }
        if(wrong==false)
        {
            dout.writeUTF("Congrats !! sudoku solved.");
        }
        else
        {
            dout.writeUTF("There is atleast one error");
        }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
} 