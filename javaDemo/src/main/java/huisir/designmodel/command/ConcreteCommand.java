package huisir.designmodel.command;

public  class ConcreteCommand extends Command {  
    
    //һ��������󣬳���һ�������߶���
    private Receiver receiver; //ά��һ������������߶��������  
  
    public ConcreteCommand(Receiver receiver) {
        super();
        this.receiver = receiver;
    }
    public void execute() {  
        receiver.action(); //������������ߵ�ҵ������action()  
    }
    
}