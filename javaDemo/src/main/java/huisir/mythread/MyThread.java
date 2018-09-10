package huisir.mythread;

/**
 * 
 **********************************************************
 * @作者: huisir
 * @日期: 2018年8月13日
 * @描述: 线程B永远都得不到锁，因为没有让线程A wait的代码
 **********************************************************
 
 */
public class MyThread implements Runnable{
	
	public void run() {    
		synchronized(this) { 
		     for (int i = 0; i < 1; i--) {    
		          System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);    
		     }    
		}    
	}   
    
    
   public static void main(String[] args) {    
	   MyThread t1 = new MyThread();    
	   Thread ta = new Thread(t1,"A");    
	   Thread tb = new Thread(t1,"B");    
	   ta.start();    
	   tb.start();    
   }

}
/**
 * 对应的stack:
 * 
		"B" prio=10 tid=0x0969a000 nid=0x11d6 waiting for monitor entry [0x8bb22000]  
		   java.lang.Thread.State: BLOCKED (on object monitor)  
		    at org.marshal.MyThread.run(MyThread.java:7)  
		    - waiting to lock <0x94757078> (a org.marshal.MyThread)  //等待锁
		    at java.lang.Thread.run(Thread.java:636)  
		  
		"A" prio=10 tid=0x09698800 nid=0x11d5 runnable [0x8bb73000]  
		   java.lang.Thread.State: RUNNABLE  
		    at java.io.FileOutputStream.writeBytes(Native Method)  
		    at java.io.FileOutputStream.write(FileOutputStream.java:297)  
		    at java.io.BufferedOutputStream.flushBuffer(BufferedOutputStream.java:82)  
		    at java.io.BufferedOutputStream.flush(BufferedOutputStream.java:140)  
		    - locked <0x947571b0> (a java.io.BufferedOutputStream)  
		    at java.io.PrintStream.write(PrintStream.java:449)  
		    - locked <0x94757190> (a java.io.PrintStream)  
		    at sun.nio.cs.StreamEncoder.writeBytes(StreamEncoder.java:220)  
		    at sun.nio.cs.StreamEncoder.implFlushBuffer(StreamEncoder.java:290)  
		    at sun.nio.cs.StreamEncoder.flushBuffer(StreamEncoder.java:103)  
		    - locked <0x947572a0> (a java.io.OutputStreamWriter)  
		    at java.io.OutputStreamWriter.flushBuffer(OutputStreamWriter.java:185)  
		    at java.io.PrintStream.write(PrintStream.java:494)  
		    - locked <0x94757190> (a java.io.PrintStream)  
		    at java.io.PrintStream.print(PrintStream.java:636)  
		    at java.io.PrintStream.println(PrintStream.java:773)  
		    - locked <0x94757190> (a java.io.PrintStream)  
		    at org.marshal.MyThread.run(MyThread.java:8)  
		    - locked <0x94757078> (a org.marshal.MyThread)  //拿到锁
		    at java.lang.Thread.run(Thread.java:636)  
 * 
 *   <0x94757078> 就是两个线程争夺的Monitor。
 * 
 */
