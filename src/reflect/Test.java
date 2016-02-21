package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Properties;
import java.io.IOException;
import java.lang.reflect.Array;


import sun.net.www.content.text.plain;

public class Test {

	public static void main(String[] args) {
		//1 通过一个对象获得完整的包名和类名（所有的对象其实都是类的实例）
		Demo demo = new Demo();
		System.out.println("1 通过一个对象获得完整的包名和类名: ");
		System.out.println("  " + demo.getClass().getName());
		
		//2 实例化Class类对象
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
		
		//3 通过Class实例化其他类的对象
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
		
		//4 通过Class调用其他类中的构造函数 
		Person per1 = null;
		Person per2 = null;
		Person per3 = null;
		Person per4 = null;
		Person per5 = null;
		
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
		
		//这个方法比较常见
		Constructor<?> constructor = null;
		try {
			constructor = personClass.getConstructor(int.class);
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			per5 = (Person) constructor.newInstance(28);
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	
		System.out.println("4 通过Class调用类中的构造函数 ");
		System.out.println(" " + per1);
		System.out.println(" " + per2);
		System.out.println(" " + per3);
		System.out.println(" " + per4);
		System.out.println(" " + per5);
		
		//
		
		
		//5 返回一个类实现的接口
		Class<?> childClass = null;
		try {
			childClass = Class.forName("reflect.Child");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("5 返回一个类实现的接口");
		Class<?> intes[] = childClass.getInterfaces();
		for(int i = 0; i < intes.length; i++) {
			System.out.println("Child实现的接口 " + intes[i].getName());
		}
		
		//6 取得其他类中的父类
		Class<?> superClass = null;
		try {
			superClass = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Class<?> temp = superClass.getSuperclass();
		System.out.println("Person继承的父类为： " + temp.getName());
		
		//7 获得其类中的所有构造函数
		Class<?> personConClass = null;
		
		try {
			personConClass = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Constructor<?>[] constructors = personConClass.getConstructors();
		System.out.println("7 获得其类中的所有构造函数");
		for(int i = 0; i < constructors.length; i++) {
			//System.out.println("构造方法： " + constructors[i]);
			Class<?>[] p = constructors[i].getParameterTypes();
			int mo =constructors[i].getModifiers();
			System.out.print(" 构造方法：" + Modifier.toString(mo));
			System.out.print(" " + constructors[i].getName());
			System.out.print("(");
			for(int j = 0; j < p.length; j++) {
				System.out.print(p[j].getName() + " arg" + j);
				if(j < p.length - 1) {
					System.out.print(",");
				}
			}
			System.out.println("){}");
		}
		
		//8 取得其类的全部属性
		Field[] parameters = childClass.getDeclaredFields();
		System.out.println("8 取得其类的全部属性");
		System.out.println("===========本类属性===========");
		for(int i = 0; i < parameters.length; i++) {
			int modifier = parameters[i].getModifiers();
			System.out.println(" " + Modifier.toString(modifier) 
							+ " " + parameters[i].getType()
							+ " " + parameters[i].getName());
		}
		
		Field[] superParams = childClass.getFields();
		System.out.println("===========实现接口或者父类的属性===========");
		
		for(int i = 0; i < superParams.length; i++) {
			int modifier = superParams[i].getModifiers();
			System.out.println(" " + Modifier.toString(modifier) 
							+ " " + superParams[i].getType()
							+ " " + superParams[i].getName());
		}
		
		//9 通过反射调用其类的方法
		System.out.println("9 通过反射调用其类的方法");
		try {
			Method method = childClass.getMethod("sayHello", String.class, int.class);
			method.invoke(childClass.newInstance(), "hello mwg", 25);
			Method haha = childClass.getMethod("haha");
			int r = (int) haha.invoke(childClass.newInstance());
			System.out.println("haha return is " + r);
		} catch (NoSuchMethodException | SecurityException  | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//10 通过反射操作属性
		Field nameField;
		try {
			nameField = personClass.getDeclaredField("name");
			nameField.setAccessible(true);
			Person person2;
			person2 = (Person) personClass.newInstance();
			nameField.set(person2, "mwg");
			System.out.println("10 通过反射操作属性");
			System.out.println("person2 name is " + person2.getName());
		} catch (NoSuchFieldException | SecurityException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//11 通过反射获得并修改数组信息
		int[] tempArray = {1, 2, 3, 4, 5};
		System.out.println("11 通过反射获得并修改数组信息");
		Class<?> tempArrayType = tempArray.getClass().getComponentType();
		System.out.println(" 数组类型 ：" + tempArrayType.getName());
		System.out.println("数组长度 ：" + Array.getLength(tempArray));
		
		System.out.println("数组第一个元素为：" + Array.get(tempArray, 0));
		Array.set(tempArray, 0, 100);
		System.out.println("修改之后数组第一个元素为：" + Array.get(tempArray, 0));
		
		//12 将反射用于工厂模式
		System.out.println("12 将反射用于工厂模式");
		try {
			Properties pro = Init.getPro();
			System.out.println(pro.getProperty("apple"));
			IFruit fruit = FruitFactory.getInstance(pro.getProperty("apple"));
			if(fruit != null) {
				fruit.eat();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
