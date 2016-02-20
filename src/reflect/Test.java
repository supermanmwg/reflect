package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {

	public static void main(String[] args) {
		//通过一个对象获得完整的包名和类名
		Demo demo = new Demo();
		System.out.println("1 通过一个对象获得完整的包名和类名: ");
		System.out.println("  " + demo.getClass().getName());
		
		//实例化Class类对象
		Class<?> demo1 = null;
		Class<?> demo2 = null;
		Class<?> demo3 = null;
		
		try {
			demo1 = Class.forName("reflect.Demo");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		demo2 = new Demo().getClass();
		demo3 = Demo.class;
		System.out.println("2 实例化Class类对象: ");
		System.out.println("  " + "demo1 类名称 "+ demo1.getName());
		System.out.println("  " + "demo2 类名称 "+ demo2.getName());
		System.out.println("  " + "demo3 类名称 "+ demo3.getName());
		
		//通过Class实例化其他类的对象
		Class<?> personClass = null;
		try {
			personClass = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Person person = null;
		try {
			person = (Person) personClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		person.setAge(27);
		person.setName("mwg");
		System.out.println("3 通过Class实例化其他类的对象");
		System.out.println(" " + person);
		
		//通过Class调用其他类中的构造函数 
		Person per1 = null;
		Person per2 = null;
		Person per3 = null;
		Person per4 = null;
		
		Constructor<?> cons[]  = personClass.getConstructors();
		try {
			//constructors 逆向
			per1 = (Person) cons[3].newInstance();
			per2 = (Person) cons[2].newInstance("mwg");
			per3 = (Person) cons[1].newInstance(27);
			per4 = (Person) cons[0].newInstance("mwg", 27);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
		System.out.println("4 通过Class调用其他类中的构造函数 ");
		System.out.println(" " + per1);
		System.out.println(" " + per2);
		System.out.println(" " + per3);
		System.out.println(" " + per4);
		

	}

}
