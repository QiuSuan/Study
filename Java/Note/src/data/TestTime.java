package data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TestTime {
	
	public static void main(String[] args) throws ParseException{
		Scanner in =new Scanner(System.in);
		System.out.println("请输入日期：（年月日，格式2008-07-14）:");
		String d = in.nextLine();
		DateFormat DF = new SimpleDateFormat("yyyy-MM-dd");
		Calendar CD = new GregorianCalendar();
		CD.setTime(DF.parse(d));
		System.out.printf("日\t一\t二\t三\t四\t五\t六\n");
		int day = CD.get(Calendar.DATE);
		CD.set(Calendar.DATE, 1);
		int first = CD.get(Calendar.DAY_OF_WEEK);
		CD.add(Calendar.MONTH, 1);
		CD.add(Calendar.DATE, -1);
		int last = CD.get(Calendar.DATE);
		for(int i=1;i<first;i++)
		{
			System.out.printf(" \t");
		}
		int week =first;
		
		for(int i=1;i<=last;i++)
		{
			System.out.printf("%d",i);
			if(i==day)
			{
				System.out.printf("*");
			}
			if(week!=7)
			{
				System.out.printf("\t");
				week++;
			}
			else
			{
				System.out.printf("\n");
				week=1;
			}
			
		}
		in.close();
	}
}
