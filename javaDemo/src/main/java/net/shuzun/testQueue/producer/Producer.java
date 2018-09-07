package net.shuzun.testQueue.producer;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Producer {
	
	
	public static ExecutorService executor = Executors.newFixedThreadPool(8);
	
	public void produce(Queue<Integer> queue){
		 Runnable task = new Runnable() {
			public void run() {
				int i = 0;
				
					try {
						queue.add(i);
						i++;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		};
		executor.execute(task);
		//executor.shutdown();
	}
}
