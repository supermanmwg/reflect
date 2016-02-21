package reflect;


public class FruitFactory {
	public static IFruit getInstance(String name) {
		IFruit fruit = null;
		
		try {
			fruit = (IFruit) Class.forName(name).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fruit;
	}
}
