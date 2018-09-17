package huisir.designmodel.mediator;

public class ColleagueA extends Colleague {
    
    public ColleagueA(String name, Mediator mediator) {
        super(name, mediator);
    }
    
    public void getNotice(String message){
        System.out.println("ͬ��A"+name+"�����Ϣ"+message);
    }
    
    //ͬ��A���н���ͨ��
    public void contact(String message){
        mediator.notice(message, this);
    }
}