package huisir.designmodel.command;


/**
 * 
 **********************************************************
 * @日期: 2018年9月17日
 * @描述:
 **********************************************************
 命令模式的本质是对请求进行封装，一个请求对应于一个命令，将发出命令的责任和执行命令的责任分割开。
  每一个命令都是一个操作：请求的一方发出请求要求执行一个操作；接收的一方收到请求，并执行相应的操作。
  命令模式允许请求的一方和接收的一方独立开来，使得请求的一方不必知道接收请求的一方的接口，更不必知道请求如何被接收、操作是否被执行、何时被执行，以及是怎么被执行的。命令模式的关键在于引入了抽象命令类，请求发送者针对抽象命令类编程，只有实现了抽象命令类的具体命令才与请求接收者相关联。

命令模式的优点是：

类间解藕
调用者角色与接受者角色之间没有任何依赖关系，调用者实现功能时只需要调用Command抽象类的execute方法就可以，不需要知道到底是哪个接收者执行。

可扩展性
Command子类可以非常容易的扩展，而调用者Invoker和高层次的模块Client不产生严重的代码藕合

其缺点是造成了类膨胀，如果有多个子命令对应多个Command子类，比较繁琐。
 */
public class Client {
    
    public static void main(String[] args) {
        Receiver receiver1 = new ConcreteReceiver();
        Command command1 = new ConcreteCommand(receiver1);
        
        Invoker invoker = new Invoker();
        invoker.setCommand(command1);
        invoker.call();
        
        Receiver receiver2 = new ConcreteReceiver2();
        Command command2 = new ConcreteCommand(receiver2);
        
        invoker.setCommand(command2);
        invoker.call();
    }
}