package com.apigee.threadpool;

import java.util.Random;

public class MainThread {

	public static void main (String []args) {
		 Job1 job1 = new Job1();
	     Job2 job2 = new Job2();
	     Job3 job3 = new Job3();
	     Job4 job4 = new Job4();
	     Job5 job5 = new Job5();
	     ThreadPoolWithJobAffinity threadPoolWithJobAffinity = new ThreadPoolWithJobAffinityImpl(5);
	     int count = 0;
	     while(count < 10) {
	    	 Random random = new Random();
	    	 int i = random.nextInt(6);
	    	 
	    	 if(i ==0) {
	    		 threadPoolWithJobAffinity.submit("job1", job1);
	    		 threadPoolWithJobAffinity.submit("job2", job2);
	    		 threadPoolWithJobAffinity.submit("job3", job3);
	    		 threadPoolWithJobAffinity.submit("job4", job4);
	    		 threadPoolWithJobAffinity.submit("job5", job5);
	    		 
	    	 }
	    	 if(i ==1) {
	    		 threadPoolWithJobAffinity.submit("job1", job1);
	    		 threadPoolWithJobAffinity.submit("job2", job2);
	    		 threadPoolWithJobAffinity.submit("job3", job3);
	    		 threadPoolWithJobAffinity.submit("job4", job4);
	    		 threadPoolWithJobAffinity.submit("job5", job5);
	    	 }
	    	 if(i ==2) {
	    		 threadPoolWithJobAffinity.submit("job1", job1);
	    		 threadPoolWithJobAffinity.submit("job2", job2);
	    		 threadPoolWithJobAffinity.submit("job3", job3);
	    		 threadPoolWithJobAffinity.submit("job4", job4);
	    		 threadPoolWithJobAffinity.submit("job5", job5);
	    		 
	    	 }
	    	 if(i ==3) {
	    		 threadPoolWithJobAffinity.submit("job1", job1);
	    		 threadPoolWithJobAffinity.submit("job2", job2);
	    		 threadPoolWithJobAffinity.submit("job3", job3);
	    		 threadPoolWithJobAffinity.submit("job4", job4);
	    		 threadPoolWithJobAffinity.submit("job5", job5);
	    		 
	    	 }
	    	 if(i ==4) {
	    		 threadPoolWithJobAffinity.submit("job1", job1);
	    		 threadPoolWithJobAffinity.submit("job2", job2);
	    		 threadPoolWithJobAffinity.submit("job3", job3);
	    		 threadPoolWithJobAffinity.submit("job4", job4);
	    		 threadPoolWithJobAffinity.submit("job5", job5);
	    		 
	    	 }
	    	 count ++;
	    	 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
	     threadPoolWithJobAffinity.shutdown();
	     
	}
}
