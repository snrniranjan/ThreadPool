package com.apigee.threadpool;

public class Job1 implements Runnable {

	
	@Override
	public void run() {
		System.out.println("Executing job1");
		
	}

}
