package huisir.cache;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;



public class ThreadCaChe {

    private static Map<String, Object> cacheMap = new HashMap<String, Object>();

    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    String obj = (String) get("TestKey");
                    System.out.println(obj);
                }
            }).start();

        }

    }

    public static Object get(String key) {
        lock.readLock().lock(); // 先加读锁
        Object value = null;
        try {
            value = cacheMap.get(key); 
            if (value == null) { // 若不存在cache中 

                // 让线程sleep 1秒方便测试 
                try {
                    Thread.sleep(1000);//sleep方法在等待时不会释放任何锁或监视器。wait 方法多用于线程间通信，而 sleep 只是在执行时暂停
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
 
                lock.readLock().unlock(); // 若果value为空 则释放掉读锁，让该线程获取写锁，而其他线程只能等待该写锁释放，才能在进读锁
                lock.writeLock().lock(); // 加写锁 

                value = cacheMap.get(key);
                try {
                    if (value == null) {
                        value = "Cache Data";// 查询数据库 ,从DB中获取数据 
                        // 存入缓存中  
                        cacheMap.put(key, value);
                    }
                } finally {
                    lock.readLock().lock();
                    lock.writeLock().unlock();//必须先释放读锁在获取写锁，不然会被锁死
                }
            }

        } finally {
            lock.readLock().unlock(); // 释放第一次获取的读锁
        }

        return value;
    }
}

/**
 * 
 **********************************************************
 * @作者: huisir
 * @日期: 2018年9月7日
 * @版权: 2018 www.shuzun.net Inc. All rights reserved.
 * @描述:
 **********************************************************
 代码备注
1. 代码中我使用了一个HashMap模拟缓存，在获取cacheMap缓存中的数据时如果cacheMap中没有存在该值得时候就涉及到从数据库中同步数据到cacheMap中，这样对于cacheMap就涉及到读写操作；

2. 当多个线程同时获取同一个数据的时候就就涉及到线程安全和线程同步的问题；

代码中容易出现的问题
1. 代码13行中的lock必须是属性级别的变量，初级程序员容易把lock写成方法变量（这样就达不到lock的作用）

2. 代码49行需要在读取缓存数据不存在的时候，并且已经获得读锁的时候还需要在验证一次这个数据是否存在缓存中（解决高并发时多个线程同时净增读锁是，后产生的多次数据库查询操作）

3. 锁是占用资源的操作，最后必须把锁释放，所以使用的finally模块释放锁

4. 代码46/47和代码57/58中释放锁和获取锁的顺序是不可以改变的（因为）在java锁机制中读锁是可以升级为写锁，然而写锁是不可以降级为读锁的，所以必须先释放读锁在获取写锁，不然会被锁死
 */