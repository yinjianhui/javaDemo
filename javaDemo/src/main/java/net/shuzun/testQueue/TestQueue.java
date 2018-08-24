package net.shuzun.testQueue;

import java.util.concurrent.LinkedBlockingQueue;

import net.shuzun.testQueue.consumer.Consumer;
import net.shuzun.testQueue.producer.Producer;

public class TestQueue {
	
	public static void main(String[] args) {
		
		
 		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
 		
 		Producer pro = new Producer();
 		Consumer con = new Consumer();
 		
 		while (true) {

 			pro.produce(queue);
 			con.consume(queue);
		}
 		 
	}
}
