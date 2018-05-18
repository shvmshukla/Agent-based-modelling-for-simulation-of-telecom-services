/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.util.Scanner;

/**
 *
 * @author shivam
 */
public class QosCalculator {
    public static void main(String[] args) {
        
   
        Qos();
    }
    
    public static int Qos(){
    AHP ahp=new AHP();
    int n=3;
    int NUMBER_COMPARISON;
    Scanner keyboard=new Scanner(System.in);

    //System.out.println("Enter the number of criteria");
    //System.out.println("n=");
    //n=keyboard.nextInt();
    NUMBER_COMPARISON=(n*n-n)/2;

    double [][] a=new double[n][n];
    String [] criteria=new String[n];
    double [] p=new double[NUMBER_COMPARISON];//used to hold the values of comparisons
    criteria[0]="Bandwidh";
    criteria[1]="Latency";
    criteria[2]="TurnaroundTime"; 
   /* System.out.println("Enter the criteria:");
    for(int i=0; i<n;i++)
    {
        System.out.print("Criterion "+(i+1)+":");
        criteria[i]=keyboard.next();
    }*/

   // System.out.println("Enter the comparison");
        int m=0; 
        for(int i=0; i<n;i++)
        {
            for(int j=i+1; j<n;j++)
            {
               // System.out.println("Compare "+criteria[i]+" with "+criteria[j]+":");
                //p[m]=keyboard.nextDouble();
                p[m] = Math.random();
                m++;
            }
        }

    a=ahp.initialize_matrix(p);
    ahp.show_matrix(a);
    
    double [] cubic_sum = new double[n];
    double  grand_sum=0;
    
    for(int i=0; i<n;i++)
    {
        
        for(int j=0; j<n;j++)
        {
            cubic_sum[i] += a[i][j];
        } 
        
        cubic_sum[i] = Math.cbrt(cubic_sum[i]);
        grand_sum += cubic_sum[i];
        
    }
    for(int i=0; i<n;i++)
    {
        cubic_sum[i] = (cubic_sum[i]/grand_sum);
    }
    //System.out.println();
    for(int i=0; i<n;i++)
    {
      //  System.out.println(cubic_sum[i]);
    }
    int qos=0;
    while(qos==0)
    {
        qos =(int)((cubic_sum[0]*Math.random()+cubic_sum[1]*Math.random()+cubic_sum[2]*Math.random())*10);
    }
    
    if(qos<5)
    qos=(int)((1.65)*qos);
    else if(qos>5)
    qos=(int)((1.4)*qos);
    
    if(qos>10)
    qos-=(qos-10);
    //System.out.println("Qos = "+qos);  
    
    return qos;
    
}

}
