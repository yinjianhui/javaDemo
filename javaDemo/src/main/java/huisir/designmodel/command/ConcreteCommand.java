package huisir.designmodel.command;

public  class ConcreteCommand extends Command {  
    
    //一个命令对象，持有一个接收者对象
    private Receiver receiver; //维持一个对请求接收者对象的引用  
  
    public ConcreteCommand(Receiver receiver) {
        super();
        this.receiver = receiver;
    }
    public void execute() {  
        receiver.action(); //调用请求接收者的业务处理方法action()  
    }
    
}