package huisir.mythread.CompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import huisir.mythread.countdownlatch.CustomerInfo;
import huisir.mythread.countdownlatch.DiscountInfo;
import huisir.mythread.countdownlatch.FoodListInfo;
import huisir.mythread.countdownlatch.OrderInfo;
import huisir.mythread.countdownlatch.OtherInfo;
/**
 * 
 **********************************************************
 * @日期: 2018年9月14日
 * @描述: CompletableFuture，它是一个多功能的非阻塞的Future。（什么是Future：用来代表异步结果，并且提供了检查计算完成，等待完成，检索结果完成等方法。
 **********************************************************
 
 */
public class CompletableFutureParallel {
    private static final int CORE_POOL_SIZE = 4;
    private static final int MAX_POOL_SIZE = 12;
    private static final long KEEP_ALIVE_TIME = 5L;
    private final static int QUEUE_SIZE = 1600;

    protected final static ExecutorService THREAD_POOL = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
            KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingQueue<>(QUEUE_SIZE));
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
	        OrderInfo orderInfo = new OrderInfo(null, null, null, null, null);
	        //CompletableFuture 的List
	        List<CompletableFuture> futures = new ArrayList<>();
	        futures.add(CompletableFuture.runAsync(() -> {
	            System.out.println("当前任务Customer,线程名字为:" + Thread.currentThread().getName());
	            orderInfo.setCustomerInfo(new CustomerInfo());
	        }, THREAD_POOL));
	        futures.add(CompletableFuture.runAsync(() -> {
	            System.out.println("当前任务Discount,线程名字为:" + Thread.currentThread().getName());
	            orderInfo.setDiscountInfo(new DiscountInfo());
	        }, THREAD_POOL));
	        futures.add( CompletableFuture.runAsync(() -> {
	            System.out.println("当前任务Food,线程名字为:" + Thread.currentThread().getName());
	            orderInfo.setFoodListInfo(new FoodListInfo());
	        }, THREAD_POOL));
	        futures.add(CompletableFuture.runAsync(() -> {
	            System.out.println("当前任务Other,线程名字为:" + Thread.currentThread().getName());
	            orderInfo.setOtherInfo(new OtherInfo());
	        }, THREAD_POOL));
	        CompletableFuture allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
	        allDoneFuture.get(10, TimeUnit.SECONDS);
	        System.out.println(orderInfo);
	    }
	}
