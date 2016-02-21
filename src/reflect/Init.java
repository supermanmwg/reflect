package reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class Init {
	public static Properties getPro() throws FileNotFoundException, IOException{
		Properties pro = new Properties();
		File file = new File("src/reflect/fruit.properties");
		if(file.exists()) {
			pro.load(new FileInputStream(file));
		}else {
			System.out.println("file is not existed");
			pro.setProperty("apple", "reflect.Apple");
	        pro.setProperty("orange", "reflect.Orange");
	        pro.store(new FileOutputStream(file), "FRUIT CLASS");
		}
		
		return pro;
	}
}
