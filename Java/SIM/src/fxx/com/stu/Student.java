package fxx.com.stu;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Student implements Comparable<Student> {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+" "+adress+" "+score;
	}

	private String name;
	private String adress;
	private int score;
	
	public Student(String name, String adress, int score) {
		super();
		this.name = name;
		this.adress = adress;
		this.score = score;
	}
	@Override
	public int compareTo(Student o) {
		int n =o.score;
		if(this.score>n){
			return 1;
		}
		else if(this.score<n){
			return -1;
		}
		else{
			return 0;	
		}
		
	}
	
	public static void main(String[] args) {

		List<Student> students=new LinkedList<>();
		students.add(new Student("a","aa",98));
		students.add(new Student("b","bb",97));
		students.add(new Student("c","cc",99));
		students.add(new Student("d","aa",97));
		Collections.sort(students);
		Collections.reverse(students);
		Iterator<Student> it = students.iterator();
		for(;it.hasNext();){
			Student temp = it.next();
			System.out.println(temp.toString());
		}
	}

	

}
