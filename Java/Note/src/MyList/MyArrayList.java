package MyList;

public class MyArrayList {
	StringBuilder s;
	private Object[] value;
	private int count;
	
	public MyArrayList() {
		this(16);
	}
	public MyArrayList(int capacity) {
		value =new Object[capacity];
	}
	
	public void add(Object obj){
		if(count+1>=value.length){
			int newCapacity = value.length+1;
			Object[] newList=new Object[newCapacity];
			System.arraycopy(value, 0, newList, 0, value.length);
			value=newList;
		}
		value[count++]=obj;
	}
	
	public Object get(int index){
		if(index<0||index>count-1)
		{
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return value[index];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyArrayList list =new MyArrayList(1);
		list.add("111");
		list.add("222");
		list.add("333");
		System.out.println(list.get(3));
		
	}

}
