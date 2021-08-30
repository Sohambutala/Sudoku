package javaapplication14;

import java.util.Random;

public class Sudoku 
{
    public static void main(String[] args) 
    {
        int [][] sud=new int[9][9];
        Boolean[] arr={false,false,false,false,false,false,false,false,false,false};
        Random r=new Random();
        int val=0,blck=0,t=0,p,k,temp=0;
        
        //init
        for(p=0;p<9;p++)
        {
            for(k=0;k<9;k++)
            {
                sud[p][k]=0;
            }
        }
        
        //create sudoku
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
        
        
        //print
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
        
        //print
         for(p=0;p<9;p++)
        {
            temp=r.nextInt(8);
            int temp1=r.nextInt(8);
            int temp2=r.nextInt(8);
            for(k=0;k<9;k++)
            {
                if(k==temp || k==temp1 || k==temp2)
                System.out.print(sud[p][k]+" ");
                else
                System.out.print("0 ");   
                
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
    
}
