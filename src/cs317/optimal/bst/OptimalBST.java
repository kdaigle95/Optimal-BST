/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs317.optimal.bst;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Kyle
 */
public class OptimalBST {
    
    private static int inputSize;
    private static int[][] values;      //dp values table 
    private static int[][] roots;       //dp roots table
    private static int[] inputArray;
    private File outFile;
    
    protected OptimalBST()
    {
        readFile();
        outFile = new File("kad0011.txt"); //output file
        values =  new int[inputSize + 2][inputSize + 1];
        roots = new int[inputSize + 2][inputSize + 1];
    }
    
    protected void createTree()
    {       
        for (int i = 1; i <= inputSize; i++)
        {
        values[i][i] = inputArray[i-1];
        roots[i][i] = i;
        }
    

        for (int d = 1; d <= (inputSize - 1); d++) 
        {
            for (int i = 1; i <= (inputSize - d); i++) 
            {
                int j = i + d;
                int sum_p = 0;
                int min_value = (int)Double.POSITIVE_INFINITY;
                int min_root = 0;
                
                for (int k = i; k <= j; k++) 
                {
                    sum_p += inputArray[k-1];
                    int value = values[i][k-1] + values[k+1][j];

                    if (value < min_value) 
                    {
                        min_value = value;
                        min_root = k;
                    }
                }
                values[i][j] = sum_p + min_value;
                roots[i][j] = min_root;
            }
        }
        //printTree(1, inputSize, 0);
    }
    
    public void printTree(int i, int j, int space_needed, PrintWriter output)
    {
        
        for(int k = 0 ; k < space_needed; k++)
        {
            output.print("  ");
        }
        if(i <= j)
        {
          output.println(roots[i][j]);
          
          printTree(i, roots[i][j] - 1, space_needed + 1, output);

          printTree(roots[i][j] + 1, j, space_needed + 1, output);
        }
        else
            output.println("-");
        
    }
    
    public void printRoots(PrintWriter output)
    {
        for(int i = -1; i < inputSize + 1; i++)
        {
            if(i == -1)
                output.print("--\t\t");
            else
                output.print("0" + i + "\t\t");
        }
        output.println();
        for(int i = 1; i < inputSize + 2; i++)
        {
            output.print("0" + i + "\t\t");
            for(int j = 0; j < inputSize + 1; j++)
            {
                    output.print(roots[i][j] + "\t\t");
            }
            output.println();
        }
        
        output.println();
    }
    
    public void printValues(PrintWriter output)
    {
        output.println("Values table: ");
        for(int i = -1; i < inputSize + 1; i++)
        {
            if(i == -1)
                output.print("--\t\t");
            else
                output.print("0" + i + "\t\t");
        }
        output.println();
        
        for(int i = 1; i < inputSize + 2; i++)
        {
            output.print("0" + i + "\t\t");
            for(int j = 0; j < inputSize + 1; j++)
            {
                    output.print(values[i][j] + "\t\t");
            }
            output.println();
        }
        
        output.println();
    }

    private void readFile()
    {
        File inputFile = new File("input.txt"); //read input for array
        
        try
        {
            Scanner input = new Scanner(inputFile);
            
            inputSize = input.nextInt(); //read first line for amount of doubles to be stored
            inputArray = new int[inputSize + 1]; //initialize array big enough to hold all doubles
            for(int i = 0; i < inputSize; i++)
            {
             inputArray[i] = input.nextInt(); //add values to array
            }
        }
        catch(IOException ex)
        {
            System.out.println("Reader exception");
        }      
    }
    
    protected void writeFile()
    {
        try
        {
        PrintWriter output = new PrintWriter(outFile);
        
        output.println("Roots table: ");       
        printRoots(output);
        
        output.println("Values table: ");
        printValues(output);
        
        output.println("Roots tree: ");
        printTree(1, inputSize, 0, output);
         
        output.close();
        }
        catch (IOException ex)
        {
            System.out.println("ERROR: IOException write");
        }
    }
    
}
