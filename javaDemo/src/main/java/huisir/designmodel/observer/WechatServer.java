package huisir.designmodel.observer;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 **********************************************************
 * @日期: 2018年9月17日
 * @描述: 具体的主题，实现了Observerable接口，对Observerable接口的三个方法进行了具体实现，
                    同时有一个List集合，用以保存注册的观察者，等需要通知观察者时，遍历该集合即可。
 **********************************************************
 */
public class WechatServer implements Observerable {
    
    //注意到这个List集合的泛型参数为Observer接口，设计原则：面向接口编程而不是面向实现编程
    private List<Observer> list;
    private String message;
    
    public WechatServer() {
        list = new ArrayList<Observer>();
    }
    
    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }
    
    @Override
    public void removeObserver(Observer o) {
        if(!list.isEmpty())
            list.remove(o);
    }

    @Override
    public void notifyObserver(){
        list.stream().forEach(Observer -> Observer.update(message));
    }
    
    public void setInfomation(String s) {
        this.message = s;
        System.out.println("更新消息： " + s);
        //消息更新，通知所有观察者,让观察者做出自己的动作
        notifyObserver();
    }
}