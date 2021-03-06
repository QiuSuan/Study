package smg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void print(ArrayList <Student> stu){
		for(Student s : stu)
		{
			System.out.println(s.toString());
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Scanner in = new Scanner(System.in);
	int N = in.nextInt();
	int L = in.nextInt();
	int H = in.nextInt();
	ArrayList <Student> stu1=new ArrayList <Student>();
	ArrayList <Student> stu2=new ArrayList <Student>();
	ArrayList <Student> stu3=new ArrayList <Student>();
	ArrayList <Student> stu4=new ArrayList <Student>();
	for(int i=0;i<N;i++)
	{
		int a,b,c;
		a=in.nextInt();
		b=in.nextInt();
		c=in.nextInt();
		if(b>=H&&c>=H)
		{
			stu1.add(new Student(a,b,c));			
		}
		else if(b>=H&&c>=L)
		{
			stu2.add(new Student(a,b,c));
		}
		else if(b>=L&&c>=L&&b>=c)
		{
			stu3.add(new Student(a,b,c));
		}
		else if(b>=L&&c>=L)
		{
			stu4.add(new Student(a,b,c));
		}
	}
	in.close();
	System.out.println(stu1.size()+stu2.size()+stu3.size()+stu4.size());
	Collections.sort(stu1);print(stu1);
	Collections.sort(stu2);print(stu2);
	Collections.sort(stu3);print(stu3);
	Collections.sort(stu4);print(stu4);
	}
}
/**
 * 引用接口Comparable
 * @author 风潇潇
 *类似于qsort
 */
class Student implements Comparable<Student>{
	private int num;
	private int scor1;
	private int scor2;
	public Student(int num, int scor1, int scor2) {
		this.num = num;
		this.scor1 = scor1;
		this.scor2 = scor2;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+this.num+" "+this.scor1+" "+this.scor2;
	}

	@Override
	public int compareTo(Student that) {
		if((this.scor1+this.scor2)!=(that.scor1+that.scor2)){
			return (that.scor1+that.scor2)-(this.scor1+this.scor2);
		}
		else if(this.scor1!=that.scor1)
		{
			return that.scor1-this.scor1;
		}
		else
		{
			return this.num-that.num;
		}
	}
	
	
	
}
