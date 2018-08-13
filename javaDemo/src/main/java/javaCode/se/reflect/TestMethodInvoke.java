package javaCode.se.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestMethodInvoke {
	
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
	 TestClassObject aa = new TestClassObject();
	 Class<? extends TestClassObject> aaClzz = aa.getClass();
	 
	 Method[] meths = aaClzz.getDeclaredMethods();
	 
	 for (Method method : meths) {
		try {
			if (method.getParameterCount() > 0 && method.getName()!= "main") {
				method.invoke(aa, "cc");
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	System.out.println(aa.getAa());
	System.out.println(aa.getBb());
	 
	}
	
}
