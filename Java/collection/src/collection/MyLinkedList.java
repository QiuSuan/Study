package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MyLinkedList {
	private Node first;
	private Node last;
	private int size;
	public Node getFirst() {
		return first;
	}

	public Node getLast() {
		return last;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	
	public void add(Object obj){
		Node temp = new Node();
		temp.obj = obj;
		if(isEmpty()){
			this.first=temp;
			this.last=temp;
			this.size++;
		}
		else{
			last.next=temp;
			temp.previous=last;
			last=temp;
			this.size++;
		}
	}

	public int indexOf(Object obj){
		Node temp = first;
		for(int i=0;i<size;i++){
			if(obj.equals(temp.obj)){
				return i;
			}
			temp=temp.next;
		}
		return -1;
	}
	private void rangeCheck(int index){
		if(index>=size||index<0){
			throw new IndexOutOfBoundsException("Index out of bounds!");
		}
	}
	
	public Object get(int index){
		rangeCheck(index);
		Node temp = first;
		for(int i=0;i<size;i++){
			if(i==index){
				return temp.obj;
			}
			temp=temp.next;
		}
		return null;
	}
	
	class Node{
		Node previous;
		Object obj;
		Node next;
		public Node(){}
		public Node(Node previous, Object obj, Node next) {
			super();
			this.previous = previous;
			this.obj = obj;
			this.next = next;
		}
		
	}
	public static void main(String[] args){
		Map m=new HashMap();
		m.put(1, "yi");
		m.put(2, "er");
		m.put(3, "san");
		Set <Entry> entryset = m.entrySet();
		for(Iterator iter = entryset.iterator();iter.hasNext();){
			Entry en = (Entry) iter.next();
			System.out.println(en.getKey()+" "+en.getValue());
		}
		
	}
}
