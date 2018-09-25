package huisir.designmodel.strategy;
public class Context {
    private Notify notify;
    public Context(Notify notify){
        this. notify = notify;
    }
    public void contextInterface(){
        notify.notice();
    }
}