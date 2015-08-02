/**
 * 
 */
package com.apigee.threadpool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Niranjan.Samanu
 *
 */
public class ThreadPoolWithJobAffinityImpl implements ThreadPoolWithJobAffinity{
	
    Map<String,BlockingQueue> jobMap = null;
    List<ExecutorThread> threadList = new ArrayList<ExecutorThread>();
    Map <String, ExecutorThread> threadMap = null;
    
	@Override
	public int poolSize() {
		synchronized (jobMap){
			return jobMap.size();
		}
	}

	@Override
	public void submit(String jobId, Runnable job)  {
		try {
			synchronized (jobMap) {			  
			    ExecutorThread thread = getMappedThread(jobId);
			    if(!thread.isAlive()) {
			    	thread.start();
			    }
			    if (jobMap.containsKey(thread.getName()))
			    	jobMap.get(thread.getName()).put(job);
			    else {
			        BlockingQueue queue = new ArrayBlockingQueue(10);
			        queue.put(job);
			        jobMap.put(thread.getName(),queue);
			    }
			    
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void shutdown() {
		System.out.println("Shutting down all threads");
		for(Map.Entry<String, BlockingQueue> entry: jobMap.entrySet()) {
            BlockingQueue queue = entry.getValue();
            while(queue.poll() != null) {}
        }
        for(ExecutorThread thread: threadList){
            thread.stop();
        }
        
		
	}
	
	public ThreadPoolWithJobAffinityImpl(int threadCount) {
		jobMap = new HashMap<String, BlockingQueue>();
		threadMap = new HashMap<String, ExecutorThread>();
        for(int i=0; i<threadCount; i++) {
        	
            threadList.add(new ExecutorThread(jobMap));
        }
      
	}
	public ExecutorThread getMappedThread (String jobId) {
		synchronized (threadMap) {
			if(threadMap.containsKey(jobId)){
				return threadMap.get(jobId);
			} else{
				for(ExecutorThread thread : threadList) {
					
					if(!thread.isAllocated()){
						threadMap.put(jobId, thread);
				    	thread.setAllocated(true);
						return thread;
					}
				}
				Random random = new Random();
				int r = random.nextInt(threadList.size());
				threadMap.put(jobId, threadList.get(r));
				return threadList.get(r);
			}
		}
	}
	
}
