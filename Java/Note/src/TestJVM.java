
public class TestJVM {

	public static void main(String[] args) {
		B b = new B();
	}

}

class A{
	public static int a_int;
	private int test = 10;
	{
		System.out.println("A���� a_int :"+a_int);
		System.out.println("A���� test :"+test);
		test = 20;
	}
	
	static{
		System.out.println("A��̬���� a_int :"+a_int);
		a_int =300;
		//test = 10;�Ǿ�̬�����޷�ʹ��
	}
	
	public A(){
		System.out.println("A������ a_int :"+a_int);
		a_int =400;
		System.out.println("A������ test :"+test);
		test = 30;
	}
}


class B extends A{
	public static int b_int =200;
	private int b_test=10;
	{
		System.out.println("B����b_int :"+b_int);
		System.out.println("B���� test :"+b_test);
		b_test = 20;
	}
	
	
	static{
		System.out.println("B��̬���� b_int :"+b_int);
		b_int =300;
	}
	
	public B(){
		System.out.println("B������ b_int :"+b_int);
		b_int =400;
		System.out.println("B������ test :"+b_test);
		b_test = 30;
	}
	
}