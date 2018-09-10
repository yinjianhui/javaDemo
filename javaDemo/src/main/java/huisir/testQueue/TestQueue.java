package huisir.testQueue;

import java.util.concurrent.LinkedBlockingQueue;

import huisir.testQueue.consumer.Consumer;
import huisir.testQueue.producer.Producer;

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
