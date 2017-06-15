package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sort {
	/**
	 * �鲢����
	 * �����Object�������ʵ����Comparable�ӿڵ�compareTo����
	 * ��Ȼ����
	 * û�����ö���Ĵ�������������Ҫ������������
	 * ʱ�临�Ӷȹ̶�Ϊ O��n+2nlog2n��
	 * ͨ���ݹ飬�����鰴1��1�з֣���ֻ��һ��Ԫ��ʱ����ʱ����������õ�
	 * Ȼ��ͨ����С�Ƚ����
	 * @param arr
	 * @author ������
	 */
	public static  void binnerySort(Object[] arr){
		if(arr.length>1){
			Object[] arr1=Arrays.copyOfRange(arr, 0, arr.length/2);
			Object[] arr2=Arrays.copyOfRange(arr, arr.length/2, arr.length);
			binnerySort(arr1);
			binnerySort(arr2);
			int n1=0,n2=0;
			for(int i=0;i<arr.length;i++){
				if(n1<arr1.length&&n2<arr2.length){
					if(((Comparable)arr1[n1]).compareTo(arr2[n2])<0){
						arr[i]=arr1[n1++];
					}
					else{
						arr[i]=arr2[n2++];
					}
				}
				else if(n1<arr1.length){
					arr[i]=arr1[n1++];
				}
				else if(n2<arr2.length){
					arr[i]=arr2[n2++];
				}
			}
		}
	}
	/**
	 * ���÷����������ͼ��
	 * <T extends Comparable<T>>Ҫ������������ͱ����ǿɱȵļ�ʵ����Comparable��compareTo
	 * <T extends Comparable<��super T>>ָ��T�ĸ�����ʵ���˿ɱ����
	 * ����ʵ��ͬ�鲢��������
	 * @param arr
	 */
	public static <T extends Comparable<T>> void sort(T[] arr){
		if(arr.length>1){
			T[] arr1=Arrays.copyOfRange(arr, 0, arr.length/2);
			T[] arr2=Arrays.copyOfRange(arr, arr.length/2, arr.length);
			sort(arr1);
			sort(arr2);
			int n1=0,n2=0;
			for(int i=0;i<arr.length;i++){
				if(n1<arr1.length&&n2<arr2.length){
					if(((Comparable)arr1[n1]).compareTo(arr2[n2])<0){
						arr[i]=arr1[n1++];
					}
					else{
						arr[i]=arr2[n2++];
					}
				}
				else if(n1<arr1.length){
					arr[i]=arr1[n1++];
				}
				else if(n2<arr2.length){
					arr[i]=arr2[n2++];
				}
			}
		}
	}
	/**
	 * ����Object[]��������ʵ��
	 * ע�⴫��ķ��ͱ�����ʵ����Comparable
	 * ���޷������������飬����ͨ��Object�������ǿת
	 * ����޷����ӷ��ͼ��
	 * @param list
	 */
	public static <T> void sort(List<T> list){
		Object[] arr = list.toArray();
		binnerySort(arr);
		for(int i=0;i<arr.length;i++){
			list.set(i, (T)arr[i]);
		}
	}
	/**
	 * ��������ʹ�÷���Comparator�ӿ�
	 * ����ͨ��ʵ��Comparator�ӿ���дcompare�������綨�Լ�Ҫ�������
	 * @param arr
	 * @param com
	 */
	public static <T> void sort(Object[] arr ,Comparator<T> com){
		if(arr.length>1){
			Object[] arr1=Arrays.copyOfRange(arr, 0, arr.length/2);
			Object[] arr2=Arrays.copyOfRange(arr, arr.length/2, arr.length);
			sort(arr1,com);
			sort(arr2,com);
			int n1=0,n2=0;
			for(int i=0;i<arr.length;i++){
				if(n1<arr1.length&&n2<arr2.length){
					if(com.compare((T)arr1[n1],(T)arr2[n2])<0){
						arr[i]=arr1[n1++];
					}
					else{
						arr[i]=arr2[n2++];
					}
				}
				else if(n1<arr1.length){
					arr[i]=arr1[n1++];
				}
				else if(n2<arr2.length){
					arr[i]=arr2[n2++];
				}
			}
		}
	}
	
	public static <T> void sort(List<T>list ,Comparator<T> com){
		Object[] arr = list.toArray();
		sort(arr,com);
		for(int i=0;i<arr.length;i++){
			list.set(i, (T)arr[i]);
		}
	}
	
	
	public static void main(String[] args){
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("a");
		list.add("abs");
		list.add("abcef");
		list.add("defg");
		sort(list,new StrCompare());
		System.out.println(list);
		
	}
}