/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

/**
 *
 * @author shivam
 */
public class AHP {
    public static double[][] initialize_matrix(double[] p)
{
    //initialize the matrix a
    double a[][]=new double[p.length][p.length];    
    int k=0;        
    for(int i=0; i<p.length; i++)
    {
        for(int j=0; j<p.length;j++)
        {
            if(i==j)
                a[i][j]=1;
            else if(i<j)
            {

                a[i][j]=p[k];
                k++;
            }

            else if(i>j)
                a[i][j]=1/a[j][i];
        }
    }
    return a;
}

public static void show_matrix(double[][] b )
{
    //display the elements of the matrix a
    /*System.out.println("\nThe matrix a is:");
    for(int i=0; i<b.length;i++)
    {
        for(int j=0; j<b[i].length; j++)
            System.out.print(b[i][j]+"    ");
        System.out.println();   
    }
    */
  
    
    
    
}




}


