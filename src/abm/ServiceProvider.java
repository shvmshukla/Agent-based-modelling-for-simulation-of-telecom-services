/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.util.ArrayList;

/**
 *
 * @author shivam
 */
public class ServiceProvider {
    
    //int count1=0,count2=0;
    Regulator r = new Regulator();
    int temp = r.getNo_of_strategy();
   ArrayList<Integer> Price = new ArrayList();
   ArrayList<Integer> qos = new ArrayList();
    
   
    
    public void setPrice(int price)
    {
        Price.add(price);
        
    }
    public void setQoS(int QoS)
    {
        
        this.qos.add(QoS);
       
    }
    // 1st strategy means 0th index
     public int getQos(int strategy)
     {
         return qos.get(strategy); 
     }
     
      public int getprice(int strategy)
     {
         return Price.get(strategy); 
     }
     
      public void rem()
      {
          this.qos.removeAll(this.qos);
          this.Price.removeAll(this.Price);
      }
     
}
