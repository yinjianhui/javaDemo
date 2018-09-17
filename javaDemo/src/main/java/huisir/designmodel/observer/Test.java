package huisir.designmodel.observer;
public class Test {
    
    public static void main(String[] args) {
        WechatServer server = new WechatServer();
        
        Observer user1 = new User("user1");
      
        server.registerObserver(user1);
        server.setInfomation("test");
    }
}