package huisir.designmodel.command;

public class Invoker {  
    
    //һ�������߶��� ����һ���������
    private Command command;  
     
    public Invoker() {
        // TODO Auto-generated constructor stub
    }
    
    //����ע��  
    public Invoker(Command command) {  
        this.command = command;  
    }  
      
    //��ֵע��  
    public void setCommand(Command command) {  
        this.command = command;  
    }  
      
    //ҵ�񷽷������ڵ����������execute()����  
    public void call() {  
        command.execute();  
    }  
}