/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;
import java.util.*;
/**
 *
 * @author shivam
 */
public class User {
    
    //we should create an object of QoS calculator class and calculate the qos accordingly
   
    int minQos,maxQos,minPrice,maxPrice;
    static int count_qos=0,count_Price=0;       
    int temp=1000;
    int premax_qos,postmin_qos,premax_price,postmin_price;
    
    
    QosCalculator qos_calulator = new QosCalculator();
    
    
    
    
    User(int minQos,int maxQos,int minPrice,int maxPrice )
    {
        this.minQos = minQos;
        this.maxQos = maxQos;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
    
    
    
    
    public int pickCost()
    {
        
        
        if(count_Price < (0.6 * temp))  //Avg User
        {
            count_Price++;
            premax_price = (maxPrice-(int)((maxPrice-minPrice)*0.3));
            postmin_price =(minPrice +(int)((maxPrice-minPrice)*0.3));
            
            return (int)(Math.random() *(premax_price-postmin_price)+postmin_price);
        }           
        else if(count_Price < (0.8 * temp) && count_Price >= (0.6 * temp))       // price conscious user  
        {
            count_Price++;
            premax_price = (maxPrice-(int)((maxPrice-minPrice)*0.7));
            return (int)(Math.random() *(premax_price-minPrice)+minPrice);
        }
        else // Qos conscious user
        {
            count_Price++;
            return (int)(Math.random() *(maxPrice-minPrice)+minPrice);
    
        }        
    }
    
    
    public int pickQoS()
    {
       if(count_qos < (0.6 * temp))   //Avg User
        {
            count_qos++;
            
           premax_qos = (maxQos-(int)((maxQos-minQos)*0.3));
           postmin_qos =(minQos +(int)((maxQos-minQos)*0.3));
            
            return (int)((double)(qos_calulator.Qos()/10) *(premax_qos-postmin_qos)+postmin_qos);
        }           
        else if(count_qos < (0.8 * temp) && count_qos >= (0.6 * temp) )       //price conscious user  
        {
            count_qos++;
            return (int)((double)(qos_calulator.Qos()/10) *(maxQos-minQos)+minQos);
        }
        else           //qos conscious user
        {
            count_qos++;
            return (int)((double)(qos_calulator.Qos()/10) *(maxQos-(int)(minQos+(maxQos-minQos)*0.7))+(int)(minQos+(maxQos-minQos)*0.7));
        }
        //return (int)(Math.random() *(maxQos-minQos)+minQos);
    }
    
    
    
    public int getQos()
    {
    
        return (int)(Math.random() *(maxQos-minQos)+minQos);
    
    }
    
    public int getPrice()
    {
    
        return (int)(Math.random() *(maxPrice-minPrice)+minPrice);
    }
    
    
}
