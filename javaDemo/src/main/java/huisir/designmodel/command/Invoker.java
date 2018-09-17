package huisir.designmodel.command;

public class Invoker {  
    
    //一个调用者对象 持有一个命令对象
    private Command command;  
     
    public Invoker() {
        // TODO Auto-generated constructor stub
    }
    
    //构造注入  
    public Invoker(Command command) {  
        this.command = command;  
    }  
      
    //设值注入  
    public void setCommand(Command command) {  
        this.command = command;  
    }  
      
    //业务方法，用于调用命令类的execute()方法  
    public void call() {  
        command.execute();  
    }  
}