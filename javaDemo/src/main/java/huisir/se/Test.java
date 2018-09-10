package huisir.se;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

public class Test {

	public static void main(String[] args) {

        Type type = new TypeToken<Map<String, String>>() {}.getType();
        System.out.println(type.getTypeName());
        System.out.println(type.toString());
	}

}
