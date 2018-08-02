package lucky4;

import java.util.concurrent.atomic.AtomicInteger;

import org.testng.annotations.Test;

public class Testing {
	AtomicInteger sequence = new AtomicInteger(0);
	 @Test(priority=1)
     public void resulInsert() throws InterruptedException {
      for(int i=11;i<=12;i++) {
       System.out.println("IN FIRST");
       insertRes();
      }
     }
	
     public void insertRes() throws InterruptedException {
         for(int i=0;i<2;i++) {
    	     System.out.println(i +" count"); 
         }
         
     }
}
