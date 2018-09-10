package huisir.testQueue.consumer;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer {
	
	
	
	public static ExecutorService executor = Executors.newFixedThreadPool(5);

	
	public void consume(Queue<Integer> queue){
		
		Runnable task = new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread().getName());
					
					try {
						
						Integer ele = queue.poll();
						if (null == ele) {
							System.out.println("队列为空。�?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��??");
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
		};
		
		executor.execute(task);
		//executor.shutdown();
	}
	
}
