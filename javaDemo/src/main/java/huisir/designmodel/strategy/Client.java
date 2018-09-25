package huisir.designmodel.strategy;
public class Client {
    /**
    策略模式的优点：

    支持“开闭原则”，用户可以在不修改原有系统的基础上选择算法或行为，
    也可以灵活地增加新的算法或行为，提供了管理相关的算法族的办法。
    可以避免使用多重条件语句。多重条件语句不易维护，它把采取哪一种算法
    或采取哪一种行为的逻辑与算法或行为的逻辑混合在一起，统统列在一个多重条件语句里面，比使用继承的办法还要原始和落后。
     */
    public static void main(String[] args) {
        Notify strategy = new SMSNotify();
        Context context = new Context(strategy);
        context.contextInterface();
    }
}