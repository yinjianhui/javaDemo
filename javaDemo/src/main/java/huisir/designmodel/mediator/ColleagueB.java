package huisir.designmodel.mediator;

public class ColleagueB extends Colleague {
    
    public ColleagueB(String name, Mediator mediator) {
        super(name, mediator);
    }
    
    public void getNotice(String message){
        System.out.println("ͬ��B"+name+"�����Ϣ"+message);
    }
    
    //ͬ��B���н���ͨ��
    public void contact(String message){
        mediator.notice(message, this);
    }
}