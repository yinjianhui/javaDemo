package javaCode.se.reflect;
/**
 * 
 **********************************************************
 * @作者: huisir
 * @日期: 2018年7月30日
 * @版权: 2018 www.shuzun.net Inc. All rights reserved.
 * @描述:	测试不同对象的class 对象是否是同一个，结论：是同一个
 **********************************************************
 */
public class TestClassObject {
	
	String aa = "aa";
	String bb = "bb";
	public String getAa() {
		return aa;
	}
	public void setAa(String aa) {
		this.aa = aa;
	}
	public String getBb() {
		return bb;
	}
	public void setBb(String bb) {
		this.bb = bb;
	}
	
	
	public static void main(String[] args) {
		TestClassObject obj1 = new TestClassObject();
		TestClassObject obj2 = new TestClassObject();
		
//	 	Class<? extends TestClassObject> clas1 = obj1.getClass();
//	 	Class<? extends TestClassObject> clas2 = obj2.getClass();
	 	Class clas1 = obj1.getClass();
	 	Class clas2 = obj2.getClass();
	 	
	 	System.out.println(clas1.hashCode() + "\t"+  clas2.hashCode());
	}
}
