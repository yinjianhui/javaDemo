package huisir.designmodel.mediator;

public class Client {
    
    // �н��ߣ�ColleagueA��ColleagueB
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        
        //��λͬ��ͬ�³���ͬһ���н���
        ColleagueA colleagueA = new ColleagueA("A", mediator);
        ColleagueB colleagueB = new ColleagueB("B", mediator);
        
        mediator.setCollA(colleagueA);
        mediator.setCollB(colleagueB);
        
        colleagueA.contact("����A����Ҫ��ϵB��");
        colleagueB.contact("����B���յ�A��Ϣ��");
    }
}