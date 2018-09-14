package huisir.mythread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import huisir.mythread.countdownlatch.CustomerInfo;
import huisir.mythread.countdownlatch.DiscountInfo;
import huisir.mythread.countdownlatch.FoodListInfo;
import huisir.mythread.countdownlatch.OrderInfo;
import huisir.mythread.countdownlatch.OtherInfo;
import huisir.mythread.countdownlatch.TenantInfo;
/**
 * 
 **********************************************************
 * @作者: huisir
 * @日期: 2018年9月14日
 * @描述:
 **********************************************************
	我们定义一个OrderTask并且定义五个获取信息的任务，在compute中分别fork执行这五个任务，
	最后在将这五个任务的结果通过Join获得，最后完成我们的并行化的需求。
	
	Fork/Join框架：在必要的情况下，将一个大任务，进行拆分（fork） 成若干个子任务（拆到不能再拆，这里就是指我们制定的拆分的临界值），
	再将一个个小任务的结果进行join汇总。 
 */
public class OrderTask extends RecursiveTask<OrderInfo> {
	
    @Override
    protected OrderInfo compute() {
        System.out.println("执行"+ this.getClass().getSimpleName() + "线程名字为:" + Thread.currentThread().getName());
        // 定义其他五种并行TasK
        CustomerTask customerTask = new CustomerTask();
        TenantTask tenantTask = new TenantTask();
        DiscountTask discountTask = new DiscountTask();
        FoodTask foodTask = new FoodTask();
	    OtherTask otherTask = new OtherTask();
	    
	    invokeAll(customerTask, tenantTask, discountTask, foodTask, otherTask);
	    
	    OrderInfo orderInfo = new OrderInfo(customerTask.join(), tenantTask.join(), discountTask.join(), foodTask.join(), otherTask.join());
	    
	    return orderInfo;
	    }
    
    
	    public static void main(String[] args) {
	        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() -1 );
	        System.out.println(forkJoinPool.invoke(new OrderTask()));
	    }
	    
	    
	}
	class CustomerTask extends RecursiveTask<CustomerInfo>{
	
	    @Override
	    protected CustomerInfo compute() {
	        System.out.println("执行"+ this.getClass().getSimpleName() + "线程名字为:" + Thread.currentThread().getName());
	        return new CustomerInfo();
	    }
	}
	class TenantTask extends RecursiveTask<TenantInfo>{
	
	    @Override
	    protected TenantInfo compute() {
	        System.out.println("执行"+ this.getClass().getSimpleName() + "线程名字为:" + Thread.currentThread().getName());
	        return new TenantInfo();
	    }
	}
	class DiscountTask extends RecursiveTask<DiscountInfo>{
	
	    @Override
	    protected DiscountInfo compute() {
	        System.out.println("执行"+ this.getClass().getSimpleName() + "线程名字为:" + Thread.currentThread().getName());
	        return new DiscountInfo();
	    }
	}
	class FoodTask extends RecursiveTask<FoodListInfo>{
	
	    @Override
	    protected FoodListInfo compute() {
	        System.out.println("执行"+ this.getClass().getSimpleName() + "线程名字为:" + Thread.currentThread().getName());
	        return new FoodListInfo();
	    }
	}
	class OtherTask extends RecursiveTask<OtherInfo>{
	
	    @Override
	    protected OtherInfo compute() {
	        System.out.println("执行"+ this.getClass().getSimpleName() + "线程名字为:" + Thread.currentThread().getName());
	        return new OtherInfo();
	    }
	}
