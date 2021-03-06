package sort;

import java.util.Arrays;
import java.util.List;

public class Sort {
	public static  void bubbleSort(int[] list){
		for(int i=0;i<list.length-1;i++){
			boolean sorted = true;
			for(int j=0;j<list.length-1-i;j++){
				if(list[j]>list[j+1]){
					int temp=list[j];
					list[j]=list[j+1];
					list[j+1]=temp;
					sorted =false;
				}
			}
			if(sorted){
				break;
			}
		}
	}
	public static  void binnerySort(int[] list){
		if(list.length>1){
			int[] list1=Arrays.copyOfRange(list, 0, list.length/2);
			int[] list2=Arrays.copyOfRange(list, list.length/2, list.length);
			binnerySort(list1);
			binnerySort(list2);
			int n1=0,n2=0;
			for(int i=0;i<list.length;i++){
				if(n1<list1.length&&n2<list2.length){
					if(list1[n1]<list2[n2]){
						list[i]=list1[n1++];
					}
					else{
						list[i]=list2[n2++];
					}
				}
				else if(n1<list1.length){
					list[i]=list1[n1++];
				}
				else if(n2<list2.length){
					list[i]=list2[n2++];
				}
			}
		}
	}
	
	public static void main(String[] args){
		int[] arr ={2,8,5,6,9,7,5,6,4,3};
		binnerySort(arr);
		System.out.println(Arrays.toString(arr)) ;
	}
}
