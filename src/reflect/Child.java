package reflect;

public class Child implements China {
	
	private String sex;
	
	public Child() {
		
	}
	
	public Child(String sex) {
		this.sex = sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getSex() {
		return sex;
	}

	@Override
	public void sayChina() {
		System.out.println("Hello, China!");
	}

	@Override
	public void sayHello(String name, int age) {
		System.out.println(name + " " + age);
	}

	@Override
	public int haha() {
		
		return 1;
	}

}
