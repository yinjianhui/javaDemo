package huisir.mythread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 
 **********************************************************
 * @作者: huisir
 * @日期: 2018年9月10日
 * @描述:
 **********************************************************
锁的种类挺多，包括：自旋锁、自旋锁的其他种类、阻塞锁、可重入锁、读写锁、互斥锁、悲观锁、乐观锁、公平锁、可重入锁等等，其余就不列出了。我们这边重点看如下几种：可重入锁、读写锁、可中断锁、公平锁。

1.1 可重入锁
如果锁具备可重入性，则称作为可重入锁。synchronized和ReentrantLock都是可重入锁，可重入性在我看来实际上表明了锁的分配机制：基于线程的分配，而不是基于方法调用的分配。举比如说，当一个线程执行到method1 的synchronized方法时，而在method1中会调用另外一个synchronized方法method2，此时该线程不必重新去申请锁，而是可以直接执行方法method2。

1.2 读写锁
读写锁将对一个资源的访问分成了2个锁，如文件，一个读锁和一个写锁。正因为有了读写锁，才使得多个线程之间的读操作不会发生冲突。ReadWriteLock就是读写锁，它是一个接口，ReentrantReadWriteLock实现了这个接口。可以通过readLock()获取读锁，通过writeLock()获取写锁。

1.3 可中断锁
可中断锁，即可以中断的锁。在Java中，synchronized就不是可中断锁，而Lock是可中断锁。
如果某一线程A正在执行锁中的代码，另一线程B正在等待获取该锁，可能由于等待时间过长，线程B不想等待了，想先处理其他事情，我们可以让它中断自己或者在别的线程中中断它，这种就是可中断锁。

Lock接口中的lockInterruptibly()方法就体现了Lock的可中断性。

1.4 公平锁
公平锁即尽量以请求锁的顺序来获取锁。同时有多个线程在等待一个锁，当这个锁被释放时，等待时间最久的线程（最先请求的线程）会获得该锁，这种就是公平锁。

非公平锁即无法保证锁的获取是按照请求锁的顺序进行的，这样就可能导致某个或者一些线程永远获取不到锁。

synchronized是非公平锁，它无法保证等待的线程获取锁的顺序。对于ReentrantLock和ReentrantReadWriteLock，默认情况下是非公平锁，但是可以设置为公平锁。 
 */
public class TestAtomicIntegerLock {
    private static int synValue;
    public static void main(String[] args) {
        int threadNum = 10;
        int maxValue = 1000000;
//        testSync(threadNum, maxValue);
        testLock(threadNum, maxValue);
    }
    
    
    public static void testLock(int threadNum, int maxValue) {
        Thread[] t = new Thread[threadNum];
        Long begin = System.nanoTime();
        for (int i = 0; i < threadNum; i++) {
            Lock locks = new ReentrantLock();
            synValue = 0;
            t[i] = new Thread(() -> {
                for (int j = 0; j < maxValue; j++) {
                    locks.lock();
                    try {
                        synValue++;
                    } finally {
                        locks.unlock();
                    }
                }
            });
        }
        for (int i = 0; i < threadNum; i++) {
            t[i].start();
        }
        //main线程等待前面开启的所有线程结束
        for (int i = 0; i < threadNum; i++) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("使用lock所花费的时间为：" + (System.nanoTime() - begin));
    }

    
    public static void testSync(int threadNum, int maxValue) {
        int[] lock = new int[0];
        Long begin = System.nanoTime();
        Thread[] t = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            synValue = 0;
            t[i] = new Thread(() -> {
                for (int j = 0; j < maxValue; j++) {
                    synchronized(lock) {
                        ++synValue;
                    }
                }
            });
        }
        for (int i = 0; i < threadNum; i++) {
            t[i].start();
        }
        //main线程等待前面开启的所有线程结束
        for (int i = 0; i < threadNum; i++) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("使用synchronized所花费的时间为：" + (System.nanoTime() - begin));
    }
}