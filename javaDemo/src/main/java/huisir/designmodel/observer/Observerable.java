package huisir.designmodel.observer;
/**
 * 
 **********************************************************
 * @作者: huisir
 * @日期: 2018年9月17日
 * @描述: 定义一个抽象被观察者接口，抽象主题，可以增加和删除观察者角色。
 **********************************************************
 */
public interface Observerable {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver();   
}