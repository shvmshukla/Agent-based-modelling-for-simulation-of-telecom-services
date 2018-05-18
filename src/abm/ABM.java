/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;
import java.awt.EventQueue;
import java.util.*;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.jfree.ui.RefineryUtilities;
/**
 *
 * @author shivam
 */
public class ABM  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Regulator's constraints 
        System.out.println("REGULATOR CONSTRAINTS");
        System.out.println();
        Regulator re = new Regulator();
        
        int minQoS,maxQoS,minPrice,maxPrice,no_of_strategy;
        
        Scanner sc = new Scanner(System.in);
        int c=0;
        do{
            if(c>0)
                System.out.println("Please follow Pattern");
            System.out.println("Enter Qos Range  (min,max)"); //constraint on QoS
            minQoS= sc.nextInt();
            maxQoS= sc.nextInt();
            c++;
        }while(minQoS > maxQoS);
        
        re.setRangeQos(minQoS, maxQoS);
        c=0;
        do{
            if(c>0)
                System.out.println("Please follow Pattern");
            System.out.println("Enter Price Range  (min,max)");//constraint on Price
        
            minPrice= sc.nextInt();
            maxPrice= sc.nextInt();
            c++;
        }while(minPrice>maxPrice);
        re.setRangePrice(minPrice, maxPrice);
        
        System.out.println("Enter Number of strategy for service provider");
        
        no_of_strategy = sc.nextInt();
        
       
        
        User temp = new User(minQoS,maxQoS,minPrice,maxPrice);
        
        re.setNo_of_strategy(no_of_strategy);
    
        
        // Service Provider
        
        int no_of_serviceprovider,ProviderQoS,ProviderPrice;
        
        System.out.println();
        System.out.println("SERVICE PROVIDERS");
        System.out.println();
        System.out.println("Enter Number of ServiceProvider");
        
        no_of_serviceprovider = sc.nextInt();
        
        ServiceProvider sp[] = new ServiceProvider[no_of_serviceprovider+1];
        
        /* int check=0;
        
        System.out.println("DO You want Risk Advisory if yes press 1 ");
        check = sc.nextInt();
        
        if(check == 1)
        {
           sp[no_of_serviceprovider-1] = new ServiceProvider();
                    
           c=0;
               do
                {
                    if(c>0)
                        System.out.println("Please Maintain Minimun & Maximun Qos index");
  
                    System.out.println("Enter Qos  ");
                    ProviderQoS = sc.nextInt();
                    c++;
                }while( minQoS > ProviderQoS || maxQoS < ProviderQoS );
            
            c=0;
                do{
                    if(c>0)
                        System.out.println("Please Maintain Minimun & Maximun Price index");
                        
                        System.out.println("Enter Price");
                
                        ProviderPrice = sc.nextInt();
                        c++;
                    }while( minPrice > ProviderPrice || maxPrice < ProviderPrice );   
                
                for(int i=0; i< no_of_strategy;i++)
                {
                    sp[no_of_serviceprovider-1].setQoS(ProviderQoS);
                    sp[no_of_serviceprovider-1].setPrice(temp.pickCost());
                }
                no_of_serviceprovider--;
        }
        
        */
        
        
            for(int i=0; i< no_of_serviceprovider; i++)
            {
                System.out.println((i+1)+"th Service Provider");
                sp[i] = new ServiceProvider();

                for(int j=0; j< no_of_strategy;j++)
                {
                    System.out.println((j+1)+"th  Strategy");
              /*      c=0;
                    do
                    {
                        if(c>0)
                            System.out.println("Please Maintain Minimun & Maximun Qos index");

                        System.out.println("Enter Qos for  "+(j+1)+"th strategy");
                        ProviderQoS = sc.nextInt();
                        c++;
                    }while( minQoS > ProviderQoS || maxQoS < ProviderQoS );
                */    
                    sp[i].setQoS(temp.getQos());
                    System.out.println("QoS :"+sp[i].getQos(j));
                  /*  c=0;
                        do{
                            if(c>0)
                                System.out.println("Please Maintain Minimun & Maximun Price index");

                            System.out.println("Enter Price for  "+(j+1)+"th strategy");

                            ProviderPrice = sc.nextInt();
                            c++;
                        }while( minPrice > ProviderPrice || maxPrice < ProviderPrice );
                    */    
                    sp[i].setPrice(temp.getPrice());
                   System.out.println("Price :"+sp[i].getprice(j));
                }

                System.out.println();

            }
        
        int Grid[] = new int[no_of_serviceprovider+1],userQoS=0,userPrice=0,second=-1;
        int service_provider_qos=0,service_provider_price=0,choice=-1;
        double user_ratio=0,service_provider_ratio=0,minimum1 = Integer.MAX_VALUE,maximum1 = Integer.MIN_VALUE,minimum2 = Integer.MAX_VALUE,maximum2 = Integer.MIN_VALUE;
        int flag1=0,flag2=0,flag3=0,flag4=0;
        
        User user = new User(minQoS,maxQoS,minPrice,maxPrice);

        int payoff[][] = new int[(int)Math.pow(no_of_strategy,no_of_serviceprovider+1)*3][no_of_serviceprovider+1];
        int win[][] = new int[(int)Math.pow(no_of_strategy,no_of_serviceprovider+1)*3][no_of_serviceprovider+1];
        
        int marker = 0,marker1=0;
        int option = 0;
        
        int ac=0;
        int loss[]=new int[(int)Math.pow(no_of_strategy,no_of_serviceprovider+1)*3];
        
   // Risk Analysis variables     
        int check=0;
        int us_qos[] = new int[1000];
        int us_price[] = new int[1000];
        int Grid1[][] = new int[(int)Math.pow(no_of_strategy,no_of_serviceprovider+1)*3][no_of_serviceprovider+1];
        
        System.out.println("Here Option  :");
        System.out.println("Press 1 for Data Analsys");
        System.out.println("Press 2 for Risk Analsys");
        option = sc.nextInt();
        switch(option)
        {
            case 1:
        
            /*for(int i=0; i< no_of_serviceprovider; i++)
            {
                System.out.println((i+1)+"th Service Provider");
                sp[i] = new ServiceProvider();

                for(int j=0; j< no_of_strategy;j++)
                {
                    System.out.println((j+1)+"th  Strategy");
                    c=0;
                    do
                    {
                        if(c>0)
                            System.out.println("Please Maintain Minimun & Maximun Qos index");

                        System.out.println("Enter Qos for  "+(j+1)+"th strategy");
                        ProviderQoS = sc.nextInt();
                        c++;
                    }while( minQoS > ProviderQoS || maxQoS < ProviderQoS );
                    
                    sp[i].setQoS(temp.pickQoS());
                    System.out.println("QoS :"+sp[i].getQos(j));
                    c=0;
                        do{
                            if(c>0)
                                System.out.println("Please Maintain Minimun & Maximun Price index");

                            System.out.println("Enter Price for  "+(j+1)+"th strategy");

                            ProviderPrice = sc.nextInt();
                            c++;
                        }while( minPrice > ProviderPrice || maxPrice < ProviderPrice );
                        
                    sp[i].setPrice(temp.pickCost());
                   System.out.println("Price :"+sp[i].getprice(j));
                }

                System.out.println();

            } */

            // strategy picking grid creation
            

                
            for(int i=0 ; i<(int)Math.pow(no_of_strategy,no_of_serviceprovider)*3;i++ ) // Regression
            {

                for(int j=0;j<no_of_serviceprovider;j++)  // Every proivder pick's strategy randomly
                {

                    Grid[j] = (int)(Math.random()*(no_of_strategy));
                    System.out.print(Grid[j]+"  ");
                }
                loss[i]=0;
                System.out.println();
                for(int k=0 ; k<1000 ; k++)    // for 1000 users
                { 
                    // randomly pick price and QoS by user 
                    userQoS = user.pickQoS();
                    userPrice = user.pickCost();
                    if(ac==0)
                    System.out.println(k+" :"+"UserQos:"+userQoS+"userPrice"+userPrice);
                    
                    
                    user_ratio = (userPrice/userQoS);

                    // Implementation of C-DISC algorithm

                    minimum1 = Integer.MAX_VALUE;
                    maximum1 = Integer.MIN_VALUE;
                    minimum2 = Integer.MAX_VALUE;
                    maximum2 = Integer.MIN_VALUE;
                    
                    flag1 = 0;
                    flag2 = 0;
                    flag3 = 0;
                    flag4 = 0;
                    
                    
                    choice=-1;
                    
                    for(int l=0; l < no_of_serviceprovider;l++)
                    {
                        service_provider_qos = sp[l].getQos(Grid[l]);

                        service_provider_price = sp[l].getprice(Grid[l]);
                        
                        if(service_provider_qos >= (int)(0.7*userQoS)  && service_provider_qos <= (int)(1.3*userQoS) && service_provider_price >= (int)(0.7*userPrice) && service_provider_price <= (int)(1.3*userPrice))
                        {
                            if((service_provider_qos - userQoS) > 0  && (service_provider_price - userPrice)  < 0  )   // region1
                            {
                                flag1=1;
                                
                                if(Math.abs(service_provider_qos - userQoS)+Math.abs(service_provider_price - userPrice) > maximum1)
                                {
                                    choice=l;
                                    maximum1 = Math.abs(service_provider_qos - userQoS)+Math.abs(service_provider_price - userPrice);
                                }
                            
                            }
                            else if((service_provider_qos - userQoS) > 0  && (service_provider_price - userPrice)  > 0 && flag1==0) // region2
                            {
                                flag2=1;
                                
                                 if(Math.abs(service_provider_qos - userQoS)+Math.abs(service_provider_price - userPrice) > maximum2)
                                {
                                    choice=l;
                                    maximum2 = Math.abs(service_provider_qos - userQoS)+Math.abs(service_provider_price - userPrice);
                                }
                            
                            }
                            else if((service_provider_qos - userQoS) < 0  && (service_provider_price - userPrice)  < 0 && flag1==0 && flag2==0) //region3
                            {
                                flag3=1;
                                
                                 if(Math.abs(service_provider_qos - userQoS)+Math.abs(service_provider_price - userPrice) < minimum1)
                                {
                                    choice=l;
                                    minimum1 = Math.abs(service_provider_qos - userQoS)+Math.abs(service_provider_price - userPrice);
                                }
                            
                            
                            }
                            else if((service_provider_qos - userQoS) < 0  && (service_provider_price - userPrice)  > 0 && flag1==0 && flag2==0 && flag3==0)   //region4
                            {
                                flag4=1;
                                
                                if(Math.abs(service_provider_qos - userQoS)+Math.abs(service_provider_price - userPrice) < minimum2)
                                {
                                    choice=l;
                                    minimum2 = Math.abs(service_provider_qos - userQoS)+Math.abs(service_provider_price - userPrice);
                                }
                            
                            }
                            
                        }
                       
                    
                    
                    
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                /*    marker =0;
                    marker1 =0;
                for(int l=0; l < no_of_serviceprovider;l++)
                    {
                        service_provider_qos = sp[l].getQos(Grid[l]);

                        service_provider_price = sp[l].getprice(Grid[l]);
                        
                        if(service_provider_price <= userPrice)      //price constraint
                        {
                            if(service_provider_qos >= userQoS)
                            {
                                marker=1;
                                 try
                                {
                                    service_provider_ratio = (service_provider_price/service_provider_qos);
                                } catch (ArithmeticException ae) {
                                    System.out.println("ArithmeticException occured!");
                                }

                                if(service_provider_ratio < minimum)
                                {
                                    second = choice;
                                    choice = l;
                                    minimum = ( service_provider_ratio);

                                }                          
                            }
                            
                        }
                        
                    }
                    if(second == -1 && marker == 1)
                    {
                       for(int l=0; l < no_of_serviceprovider;l++)
                       {
                        minimum = Integer.MAX_VALUE;
                        service_provider_qos = sp[l].getQos(Grid[l]);

                        service_provider_price = sp[l].getprice(Grid[l]);
                        
                        if(service_provider_price <= userPrice)      //price constraint
                        {
                            if(service_provider_qos >= userQoS)
                            {
                               
                                 try
                                {
                                    service_provider_ratio = (service_provider_price/service_provider_qos);
                                } catch (ArithmeticException ae) {
                                    System.out.println("ArithmeticException occured!");
                                }

                                if(service_provider_ratio < minimum && l!=choice)
                                {
                                    marker1=1;
                                    second=l;
                                }                          
                            }
                            
                        }
                        }
                    }
                    if(marker!=1)
                    {
                       for(int l=0; l < no_of_serviceprovider;l++)
                        {
                            service_provider_qos = sp[l].getQos(Grid[l]);

                            service_provider_price = sp[l].getprice(Grid[l]);
                        
                            if(service_provider_price <= userPrice)      //price constraint
                            { 
                                if(service_provider_qos >= userQoS-(int)(0.15*userQoS))
                                {
                                     try
                                {
                                    service_provider_ratio = (service_provider_price/service_provider_qos);
                                } catch (ArithmeticException ae) {
                                    System.out.println("ArithmeticException occured!");
                                }

                                if(service_provider_ratio < minimum)
                                {
                                    second = choice;
                                    choice = l;
                                    minimum = ( service_provider_ratio);

                                }    
                                    
                                
                                }
                            }
                            else
                            {
                            
                                loss[i]++;
                            
                            }
                        }
                        }
                    
                        if(marker!=1 && second == -1)
                        {
                            for(int l=0; l < no_of_serviceprovider;l++)
                        {
                            service_provider_qos = sp[l].getQos(Grid[l]);

                            service_provider_price = sp[l].getprice(Grid[l]);
                        
                            if(service_provider_price <= userPrice)      //price constraint
                            { 
                               10
                                     try
                                {
                                    service_provider_ratio = (service_provider_price/service_provider_qos);
                                } catch (ArithmeticException ae) {
                                    System.out.println("ArithmeticException occured!");
                                }

                                if(service_provider_ratio < minimum && l!= choice)
                                {
                                    marker1=1;
                                    second = l;                                  
                                }  
                                
                        }
                        }
                        }*/
                    
                    
                    
                    
                    
                    
                    
                    

                    //Previous Algorithm
                    
                  /*  for(int l=0; l < no_of_serviceprovider;l++)
                    {

                        service_provider_qos = sp[l].getQos(Grid[l]);

                        service_provider_price = sp[l].getprice(Grid[l]);

                        try
                        {
                            service_provider_ratio = (service_provider_price/service_provider_qos);
                        } catch (ArithmeticException ae) {
                              System.out.println("ArithmeticException occured!");
                            }

                        if(Math.abs(user_ratio - service_provider_ratio) < minimum)
                        {
                            second = choice;
                            choice = l;
                            minimum = Math.abs(user_ratio - service_provider_ratio);

                        }
                    }
                    if(second == -1)
                    {

                        minimum = Integer.MAX_VALUE;
                        for(int l=0; l<no_of_serviceprovider;l++)
                        {

                        service_provider_qos = sp[l].getQos(Grid[l]);
                        service_provider_price = sp[l].getprice(Grid[l]);

                         try
                        {
                            service_provider_ratio = (service_provider_price/service_provider_qos);
                        } catch (ArithmeticException ae) {
                              System.out.println("ArithmeticException occured!");
                            }

                        if(Math.abs(user_ratio - service_provider_ratio) < minimum && l!=choice)
                        {
                            second = l;

                        }
                        }
                        }
                  
                   */
                  
                  
                  if(flag1==1 || flag2==1 || flag3==1 || flag4==1 )
                  { 
                      
                      payoff[i][choice]+=sp[choice].getprice(Grid[choice]);   //payoff calculation using clark's mechanisam
                      win[i][choice]++;  // tracking number of user for each service privider
                  }
                  else
                        {
                        
                        loss[i]++;
                        
                        }

            }
                ac=1;
        }
            
            for(int i=0 ; i<(int)Math.pow(no_of_strategy,no_of_serviceprovider)*3;i++ )
            {
                for(int j=0;j<no_of_serviceprovider;j++)
                {
                    System.out.print(win[i][j]+"           ");
                }
                System.out.println();
            }
            for(int i=0 ; i<(int)Math.pow(no_of_strategy,no_of_serviceprovider)*3;i++ )
            {
                for(int j=0;j<no_of_serviceprovider;j++)
                {
                    System.out.print(payoff[i][j]+"           ");
                }
                System.out.println();
            }
            
            for(int i=0 ; i<(int)Math.pow(no_of_strategy,no_of_serviceprovider)*3;i++ )
            {
                System.out.println("loss "+(i+1)+" "+loss[i]);
            }
            //Market Analysis

            int Bad,Good,Moderate,flag;
            ArrayList<Integer> arr = new ArrayList<Integer>();
            
            //Scatterplot s = new Scatterplot();
            float count_bad=0,count_moderate=0,count_good=0;

            for(int i=0 ; i<(int)Math.pow(no_of_strategy,no_of_serviceprovider)*3;i++ )
            {
                for(int j=0;j<no_of_serviceprovider-1;j++)
                {
                    Bad=0;
                    Good=0;
                    Moderate=0;
                    flag=0;
                    //count_bad=0;
                    //count_moderate=0;
                   // count_good=0;

                    for(int k=j+1;k<no_of_serviceprovider;k++)
                    {
                        if(Math.abs(win[i][j]- win[i][k]) > (1000/no_of_serviceprovider) ||win[i][j]==0 ||win[i][k]==0 )
                        {
                            flag = 3;
                            break;
                        }
                        else if(Math.abs(win[i][j]- win[i][k])>=(int)((1000/no_of_serviceprovider)*0.25) && Math.abs(win[i][j]- win[i][k]) < (1000/no_of_serviceprovider) )
                        {
                            Moderate++;
                        }
                        else
                        {
                            Good++;
                        }


                    }
                    if(flag==3)
                    {
                        arr.add(620);
                        count_bad++;
                //        System.out.println(count_bad);
                    }
                    else if(Moderate >= Good)
                    {

                        arr.add(540);
                        count_moderate++;
                    }
                    else
                    {
                        arr.add(420);
                        count_good++;
                    }

                }
            }
            float tot;
            tot=count_bad+count_moderate+count_good;    
            System.out.println("Very Good     : " +(count_good/tot)*100+" %");
            System.out.println("Moderate      : " +(count_moderate/tot)*100 +" %");
            System.out.println("Bad           : " +(count_bad/tot)*100 +" %");


            SwingUtilities.invokeLater(new Runnable() {
             @Override
             public void run() {
                DrawGraph.createAndShowGui(arr);
             }
          });



            //Pie Chart Plot
            PieChart_AWT demo = new PieChart_AWT((count_good/tot)*100,(count_moderate/tot)*100,(count_bad/tot)*100,"Telecom Mraket Analysis" );  
            demo.setSize( 560 , 367 );    
            RefineryUtilities.centerFrameOnScreen( demo );    
            demo.setVisible( true );
            
            break;
            
        case 2:
            
           int fix=0,fix1=0;
            
            sp[no_of_serviceprovider] = new ServiceProvider();
        do
        {
            c=0;
           
                    do
                    {
                        if(c>0)
                            System.out.println("Please Maintain Minimun & Maximun Qos index");

                        System.out.println("Enter Qos of client");
                        ProviderQoS = sc.nextInt();
                        c++;
                    }while( minQoS > ProviderQoS || maxQoS < ProviderQoS );
                    
                    
                   
                    c=0;
                        do{
                            if(c>0)
                                System.out.println("Please Maintain Minimun & Maximun Price index");

                            System.out.println("Enter Price of client");

                            ProviderPrice = sc.nextInt();
                            c++;
                        }while( minPrice > ProviderPrice || maxPrice < ProviderPrice );
                      
                        
                        
                        
                        
                for(int j=0; j< no_of_strategy;j++)        
                {
                    sp[no_of_serviceprovider].setQoS(ProviderQoS);   
                    sp[no_of_serviceprovider].setPrice(ProviderPrice);
                }
            
            
            
            
            
            
            
            
            
          for(int i=0 ; i<(int)Math.pow(no_of_strategy,no_of_serviceprovider+1)*3;i++ ) // Regression
            {
              
               if(fix1!=1)
               {
                   for(int j=0;j<no_of_serviceprovider+1;j++)  // Every proivder pick's strategy randomly
                    {

                        Grid1[i][j] = (int)(Math.random()*(no_of_strategy));
                        System.out.print(Grid1[i][j]+"  ");
                    }
                   System.out.println();
                  
               }  
              
              
                loss[i]=0;
              //  System.out.println();
                for(int k=0 ; k<1000 ; k++)    // for 1000 users
                { 
                    // randomly pick price and QoS by user 
                   if(fix!=1)
                   {
                       userQoS = user.pickQoS();
                       userPrice = user.pickCost();
                       us_qos[k] = userQoS;
                       us_price[k] = userPrice;
                   }
                    
                     check=0;
                    //if(i<=1)
                    //System.out.println(k+" :"+"UserQos:"+us_qos[k]+"userPrice"+us_price[k]);
                    
                    
                    user_ratio = (us_price[k]/us_qos[k]);

                    // Implementation of C-DISC algorithm

                    minimum1 = Integer.MAX_VALUE;
                    maximum1 = Integer.MIN_VALUE;
                    minimum2 = Integer.MAX_VALUE;
                    maximum2 = Integer.MIN_VALUE;
                    
                    flag1 = 0;
                    flag2 = 0;
                    flag3 = 0;
                    flag4 = 0;
                    
                    
                    choice=-1;
                    
                    for(int l=0; l < no_of_serviceprovider+1;l++)
                    {
                        service_provider_qos = sp[l].getQos(Grid1[i][l]);

                        service_provider_price = sp[l].getprice(Grid1[i][l]);
                        
                        if(service_provider_qos >= (int)(0.7*us_qos[k])  && service_provider_qos <= (int)(1.3*us_qos[k]) && service_provider_price >= (int)(0.7*us_price[k]) && service_provider_price <= (int)(1.3*us_price[k]))
                        {
                            if((service_provider_qos - us_qos[k]) > 0  && (service_provider_price - us_price[k])  < 0  )   // region1
                            {
                                flag1=1;
                                
                                if(Math.abs(service_provider_qos - us_qos[k])+Math.abs(service_provider_price - us_price[k]) > maximum1)
                                {
                                    choice=l;
                                    maximum1 = Math.abs(service_provider_qos - us_qos[k])+Math.abs(service_provider_price - us_price[k]);
                                }
                            
                            }
                            else if((service_provider_qos - us_qos[k]) > 0  && (service_provider_price - us_price[k])  > 0 && flag1==0) // region2
                            {
                                flag2=1;
                                
                                 if(Math.abs(service_provider_qos - us_qos[k])+Math.abs(service_provider_price - us_price[k]) > maximum2)
                                {
                                    choice=l;
                                    maximum2 = Math.abs(service_provider_qos - us_qos[k])+Math.abs(service_provider_price - us_price[k]);
                                }
                            
                            }
                            else if((service_provider_qos - us_qos[k]) < 0  && (service_provider_price - us_price[k])  < 0 && flag1==0 && flag2==0) //region3
                            {
                                flag3=1;
                                
                                 if(Math.abs(service_provider_qos - us_qos[k])+Math.abs(service_provider_price - us_price[k]) < minimum1)
                                {
                                    choice=l;
                                    minimum1 = Math.abs(service_provider_qos - us_qos[k])+Math.abs(service_provider_price - us_price[k]);
                                }
                            
                            
                            }
                            else if((service_provider_qos - us_qos[k]) < 0  && (service_provider_price - us_price[k])  > 0 && flag1==0 && flag2==0 && flag3==0)   //region4
                            {
                                flag4=1;
                                
                                if(Math.abs(service_provider_qos - us_qos[k])+Math.abs(service_provider_price - us_price[k]) < minimum2)
                                {
                                    choice=l;
                                    minimum2 = Math.abs(service_provider_qos - us_qos[k])+Math.abs(service_provider_price - us_price[k]);
                                }
                            
                            }
                            
                        }
                    }
                    
                    
                    if(flag1==1 || flag2==1 || flag3==1 || flag4==1 )
                  { 
                      
                      payoff[i][choice]+=sp[choice].getprice(Grid1[i][choice]);   //payoff calculation using clark's mechanisam(first bid auction)
                      win[i][choice]++;  // tracking number of user for each service privider
                  }
                  else
                        {
                        
                        loss[i]++;
                        
                        }

            }
                ac=1;
                fix=1;
        }
          
          fix1=1;
          
            for(int i=0 ; i<(int)Math.pow(no_of_strategy,no_of_serviceprovider+1)*3;i++ )
            {
                for(int j=0;j<no_of_serviceprovider+1;j++)
                {
                    System.out.print(win[i][j]+"           ");
                }
                System.out.println();
            }
            for(int i=0 ; i<(int)Math.pow(no_of_strategy,no_of_serviceprovider+1)*3;i++ )
            {
                for(int j=0;j<no_of_serviceprovider+1;j++)
                {
                    System.out.print(payoff[i][j]+"           ");
                }
                System.out.println();
            }  
            
            float accuracy=0;
            float total=0;
        for(int i=0 ; i<(int)Math.pow(no_of_strategy,no_of_serviceprovider+1)*3;i++ )
            {
                 
                    if(win[i][no_of_serviceprovider] >= (int)((1000/(no_of_serviceprovider+1)) *.75) )
                    {
                        accuracy++;
                            
                    }
                total++;
            }
            
            double accuracy_percentage;
            
            
            accuracy_percentage = ((double)(accuracy/total))*100;
            
            System.out.println("accuracy_percentage : "+accuracy_percentage + " %");
            
            demo = new PieChart_AWT(accuracy_percentage,100-accuracy_percentage,"Telecom Mraket Analysis" );  
            demo.setSize( 560 , 367 );    
            RefineryUtilities.centerFrameOnScreen( demo );    
            demo.setVisible( true );
            System.out.println();   
            System.out.println("Do You to recheck market with different qos and price press 1 otherwise press anything");
            check = sc.nextInt();
            if(check==1)
            {
              for(int i=0 ; i<(int)Math.pow(no_of_strategy,no_of_serviceprovider+1)*3;i++ )  
              {
                  Arrays.fill(win[i], 0);
              
                Arrays.fill(payoff[i], 0);
              }
              
              
                    sp[no_of_serviceprovider].rem();   
                    sp[no_of_serviceprovider].rem();
                
              
            }
            
            
            
        }while(check==1);
            break;
            
        }     
        
       
    }
}
