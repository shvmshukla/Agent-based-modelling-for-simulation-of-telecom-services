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
public class Regulator {
    
     int minQoS,maxQoS,minPrice,maxPrice,no_of_strategy;
    
     public int getMinQoS() {
        return this.minQoS;
    }
      public int getMaxQoS() {
        return this.maxQoS;
    }
      public int getMinPrice() {
        return this.minPrice;
    }
      public int getMaxPrice() {
        return this.maxPrice;
    }
      
       public  int getNo_of_strategy() {
        return this.no_of_strategy;
    }

    public void setRangeQos(int min,int max) {
        this.minQoS = min;
        this.maxQoS = max;  
    }
    
    public void setRangePrice(int min,int max) {
        this.minPrice = min;
        this.maxPrice = max;  
    }
    
    public void setNo_of_strategy(int no_of_strategy) 
    {
        this.no_of_strategy = no_of_strategy;
    }
    
}
