package huisir.mythread.forkjoin;

import org.junit.Test;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;
/**
 * 
 **********************************************************
 * @日期: 2018年9月14日
 * @描述:
 **********************************************************
Fork/Join与传统线程池的区别！

Fork/Join采用“工作窃取模式”，当执行新的任务时他可以将其拆分成更小的任务执行，并将小任务加到线程队列中，然后再从一个随即线程中偷一个并把它加入自己的队列中。

就比如两个CPU上有不同的任务，这时候A已经执行完，B还有任务等待执行，这时候A就会将B队尾的任务偷过来，加入自己的队列中，对于传统的线程，ForkJoin更有效的利用的CPU资源！

我们来看一下ForkJoin的实现：实现这个框架需要继承RecursiveTask 或者 RecursiveAction ，RecursiveTask是有返回值的，相反Action则没有 
 */
public class ForkJoinWorkDemo {

    public  void test() {
    	//ForkJoin实现
        long l = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();//实现ForkJoin 就必须有ForkJoinPool的支持
        ForkJoinTask<Long> task = new ForkJoinWork(0L,10000000000L);//参数为起始值与结束值
        Long invoke = forkJoinPool.invoke(task);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + invoke+"  time: " + (l1-l));
        //invoke = -5340232216128654848  time: 76474
    }

    public void test2(){
    	//普通线程实现
        Long x = 0L;
        Long y = 10000000000L;
        long l = System.currentTimeMillis();
        for (Long i = 0L; i <= y; i++) {
            x+=i;
        }
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + x+"  time: " + (l1-l));
        //invoke = -5340232216128654848  time: 160939
    }
    @Test
    public void test3(){
    	//Java 8 并行流的实现
        long l = System.currentTimeMillis();
        //parallelStream中底层使用的那一套也是Fork/Join的那一套，默认的并发程度是可用CPU数-1。
        long reduce = LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0, Long::sum);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke3 = " + reduce+"  time: " + (l1-l));
        //invoke = -5340232216128654848  time: 15531
    }

}