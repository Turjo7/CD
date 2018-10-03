/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author TURJO-T86
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
 
public class Calculator
{
    public static void main(String[] args)
    { 
        //Scanner scan = new Scanner(System.in);
        
        Stack<Integer> op  = new Stack<Integer>(); // Stack newa hoise operator er jonoo
        Stack<Double> val = new Stack<Double>(); // stack newa hoise value er jonoo
       
        Stack<Integer> optmp  = new Stack<Integer>();  
        Stack<Double> valtmp = new Stack<Double>();
        
        
        
    //
    String input = null ;
        
        File file = new File("D:","Input.txt");
         try
        {
            
            BufferedReader in = new BufferedReader(new FileReader(file));
           input=in.readLine();
            while(input !=null)
            {
                System.out.println("Read: "+input);
                input=in.readLine();
            }
           // in.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println(ex);
            
        }       catch(IOException ex)
        {
            System.out.println(ex);
            
        }
        
        
        
        
        // File theke input newa hochhe
      //  int input = Integer.parseInt(input1);
        
        input = "0" + input;
        input = input.replaceAll("-","+-");
        /* Store operands and operators in respective stacks */
        String temp = "";
        for (int i = 0;i < input.length();i++)
        {
            char ch = input.charAt(i);
            if (ch == '-')
                temp = "-" + temp;
            else if (ch != '+' &&  ch != '*' && ch != '/')
               temp = temp + ch;
            else
            {
                val.push(Double.parseDouble(temp));
                op.push((int)ch);
                temp = "";
            }
        }
        val.push(Double.parseDouble(temp));
       
        char operators[] = {'/','*','+'};
        /* Evaluation of expression */
        for (int i = 0; i < 3; i++)
        {
            boolean it = false;
            while (!op.isEmpty())
            {
                int optr = op.pop();
                double v1 = val.pop();
                double v2 = val.pop();
                if (optr == operators[i])
                {
                   
                    if (i == 0)
                    {
                        valtmp.push(v2 / v1);
                        it = true;
                        break;
                    }
                    else if (i == 1)
                    {
                        valtmp.push(v2 * v1);
                        it = true;
                        break;
                    }
                    else if (i == 2)
                    {
                        valtmp.push(v2 + v1);
                        it = true;
                        break;
                    }                                        
                }
                else
                {
                    valtmp.push(v1);
                    val.push(v2);
                    optmp.push(optr);
                }                
            }    
                 
            while (!valtmp.isEmpty())
                val.push(valtmp.pop());
            while (!optmp.isEmpty())
                op.push(optmp.pop());
            /* Iterate again for same operator */
            if (it)
                i--;                            
        } 
        
        
        //
        
        
         File file2 = new File("D:","Output.txt");
        try
        {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(isr);
            PrintWriter out = new PrintWriter(new FileWriter(file2));
            System.out.println("Write In The File ");
            //string = in.readLine();
            out.print(val.pop());
            in.close();
            isr.close();
            out.close();
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        
       
        
        // File e output likha hocchhe
        
    }
}