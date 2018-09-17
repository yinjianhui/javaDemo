package huisir.designmodel.command;
public class ConcreteReceiver2 extends Receiver{
    
    
    @Override
    public void action() {
        System.out.println("ConcreteReceiver2 receives the command!");
    }
}