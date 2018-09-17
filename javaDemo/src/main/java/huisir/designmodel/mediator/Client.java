package huisir.designmodel.mediator;

public class Client {
    
    // 中介者，ColleagueA、ColleagueB
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        
        //两位同事同事持有同一个中介者
        ColleagueA colleagueA = new ColleagueA("A", mediator);
        ColleagueB colleagueB = new ColleagueB("B", mediator);
        
        mediator.setCollA(colleagueA);
        mediator.setCollB(colleagueB);
        
        colleagueA.contact("我是A，我要联系B！");
        colleagueB.contact("我是B，收到A消息！");
    }
}