package javaapplication14;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.*;  
import java.net.*;  
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MyClient 
{  
    static     int [][] sud=new int[9][9];
    static JFrame f=new JFrame("Sudoku");  
    static Dimension def=Toolkit.getDefaultToolkit().getScreenSize();  
    static JTextField[][] t1 = new JTextField[9][9];
    static JButton sub=new JButton("Submit");
    static JButton chk=new JButton("Solve");
    static JButton nw=new JButton("New game");
    static JButton qt=new JButton("quit");
    static JLabel lab=new JLabel("!!! SUDOKU GAME !!!");
    static String str="new";
    static int x=100,y=100;
    static DataInputStream dis;
    static DataOutputStream dout;
public static void main(String[] args) 
{
    
    
try
{      
Socket s=new Socket("localhost",6666);  
dout=new DataOutputStream(s.getOutputStream()); 
dis=new DataInputStream(s.getInputStream());

init();    
    

    nw.addActionListener(new ActionListener()
    {   
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
            try 
            {
                str="new";
                dout.writeUTF(str);
                MyClient.recieve();
                MyClient.print();
                lab.setText("!!! SUDOKU GAME !!!");
            }
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
        }
    });

    
    sub.addActionListener(new ActionListener()
    {   
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
            try 
            {
                str="submit";
                dout.writeUTF(str);
                MyClient.send();
                lab.setText(dis.readUTF());
            }
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
        }
    });
    
     chk.addActionListener(new ActionListener()
    {   
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
            try 
            {
                str="solve";
                dout.writeUTF(str);
                MyClient.recieve();
                MyClient.print();
                lab.setText("Congrats !! sudoku solved.");
            }
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
        }
    });


    
    qt.addActionListener(new ActionListener()
    {   
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
            try 
            {
                str="quit";
                dout.writeUTF(str);
                dout.flush();
                dout.close();  
                s.close();
                f.setVisible(false);
                f.dispose();
            }
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
        }
    });
    

    

    

}catch(Exception e){System.out.println(e);}  
}  

    private static void init() 
    {
        Color c=Color.WHITE;
        Color c1=Color.BLACK;
        lab.setBounds(100, 20, 300, 60);
        for(int i=0;i<9;i++)
    {
        for(int j=0;j<9;j++)
        {
        t1[i][j]=new JTextField(""+0);
        t1[i][j].setBounds(x,y, 40, 40);
        f.add(t1[i][j]);
        x=x+50;
        if((j>=3 && j<=5) && (i<3 || i>5))
        {
            t1[i][j].setBackground(c1);
        t1[i][j].setForeground(c);
        }
        if((j<3 || j>5) && (i>=3 && i<=5))
        {
            t1[i][j].setBackground(c1);
        t1[i][j].setForeground(c);
        }
        }
    y=y+50;
    x=100;
    }
       
    y=y+20;
    sub.setBounds(x, y, 120, 60);
    f.add(sub);
   
    y=y+80;
    qt.setBounds(x, y, 120, 60);
    f.add(qt);
    
    y=y-80;
    x=x+140;
    chk.setBounds(x, y, 120, 60);
    f.add(chk);
    
    x=x+140;
    nw.setBounds(x, y, 120, 60);
    f.add(nw);
    
    f.add(lab);
     
    f.setSize(def);
    f.setLayout(null);  
    f.setVisible(true); 
    
    for(int p=0;p<9;p++)
        {
            for(int k=0;k<9;k++)
            {
                sud[p][k]=0;
            }
        }
    
    }

    private static void recieve() 
    {
        
        try 
        {
            for(int i=0;i<9;i++)
            {
            for(int j=0;j<9;j++)    
            {
            sud[i][j]=dis.readInt();
            }
            }
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    private static void send() 
    {
        try
        {
        int x;
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                x=Integer.parseInt(t1[i][j].getText());
                dout.writeInt(x);
            }
            
        }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void print() 
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                t1[i][j].setText(""+sud[i][j]);
            }
        }
        
    
    }
} 