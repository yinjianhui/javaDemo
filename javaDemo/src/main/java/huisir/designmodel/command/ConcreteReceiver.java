package huisir.designmodel.command;

public class ConcreteReceiver extends Receiver{
    
    @Override
    public void action() {
        System.out.println("ConcreteReceiver receives the command!");
    }
}