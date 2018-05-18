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
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
 
public class PieChart_AWT extends ApplicationFrame {
   static double very_good=0,moderate=0,bad=0;
   public PieChart_AWT(double very_good,double moderate,double bad, String title ) {
      super( title ); 
      this.very_good=very_good;
      this.moderate=moderate;
      this.bad=bad;
      setContentPane(createDemoPanel( ));
   }
   public PieChart_AWT(double very_good,double bad, String title ) {
      super( title ); 
      this.very_good=very_good;
      this.bad=bad;
      setContentPane(createDemoPanel( ));
   }
   
   
   
   
   
   private static PieDataset createDataset( ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      
      
      //dataset.setValue( "" , new Double( 40 ) );    
      dataset.setValue( "bad" , new Double( bad ) ); 
      if(moderate!=0)
      dataset.setValue( "moderate" , new Double( moderate ) );   
       dataset.setValue( "very good" , new Double( very_good ) ); 
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Telecom Market Analysis",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public static JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(createDataset( ) );  
      return new ChartPanel( chart ); 
   }

  /* public static void main( String[ ] args ) {
      PieChart_AWT demo = new PieChart_AWT( "Telecom Mraket Analysis" );  
      demo.setSize( 560 , 367 );    
      RefineryUtilities.centerFrameOnScreen( demo );    
      demo.setVisible( true ); 
   }*/
}
