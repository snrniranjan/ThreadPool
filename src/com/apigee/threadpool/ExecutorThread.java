/**
 * 
 */
package com.apigee.threadpool;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * @author Niranjan.Samanu
 *
 */
public class ExecutorThread extends Thread{
	
	private Map<String, BlockingQueue> jobMap = null;
	public boolean allocated = false;
	public Map<String, BlockingQueue> getJobMap() {
		return jobMap;
	}

	public void setJobMap(Map<String, BlockingQueue> jobMap) {
		this.jobMap = jobMap;
	}
	
	public ExecutorThread(Map<String, BlockingQueue> jobMap){
		this.jobMap = jobMap;
	}
	public void run(){
		try {
			while (true) {
			    BlockingQueue queue = null;
				synchronized (jobMap) {
					if(!jobMap.isEmpty()) {
						//entry = jobMap.entrySet().iterator().next();
						queue = jobMap.get(this.getName());
						jobMap.remove(this.getName());
					}
					if(queue != null) {
			            while (!queue.isEmpty()) {
			                Runnable runnable = (Runnable) queue.take();
			                System.out.println("Task  by thread " + this.getName());
			                runnable.run();
			            }
			        }
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isAllocated() {
		return allocated;
	}

	public void setAllocated(boolean allocated) {
		this.allocated = allocated;
	}

}
