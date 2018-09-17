package huisir.designmodel.mediator;


public class ConcreteMediator extends Mediator {
    
    ColleagueA collA;
    ColleagueB collB;
    

    
    
    @Override
    public void notice(String content, Colleague coll) {
        if (coll == collA) {
            collB.getNotice(content);
        } else {
            collA.getNotice(content);
        }
    }




    public void setCollA(ColleagueA colleagueA) {
        this.collA = colleagueA;
    }




    public void setCollB(ColleagueB colleagueB) {
        this.collB = colleagueB;
    }

    

}