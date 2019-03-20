package com.maxlong.study.quartz;

import java.text.SimpleDateFormat;  
import java.util.Date;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;  

public class QuartzTest {

  public static void main(String[] args) {
      // TODO Auto-generated method stub  
      SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
      Date d = new Date();  
      String returnstr = DateFormat.format(d);          
        
      TestJob job = new TestJob();
      TestJob2 job2 = new TestJob2();
      String job_name ="11"; 
      String job_name2 ="22";  
      try {  
    	  StdSchedulerFactory factory = QuartzManager.initFactory();
    	  Scheduler scheduler = factory.getScheduler();
    	  QuartzManager.addJob(job_name,"maxlong1",job,"0/2 * * * * ?",scheduler);
    	  QuartzManager.addJob(job_name2,"maxlong2",job2,"0/2 * * * * ?",scheduler);
    	  QuartzManager.startScheduler(scheduler);
          System.out.println(returnstr+ "dddsdsds");
      }  catch (Exception e) {  
          e.printStackTrace();  
      }  
  }  
}  
 