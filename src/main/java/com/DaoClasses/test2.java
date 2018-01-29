package com.DaoClasses;

import java.util.HashMap;
import java.util.Map;

public class test2{
		 
		 public static void main(String args[]) throws Exception {
			 dB(40);
			 
			 
			 
		 }
		 public static int dB(int given)
		 {
			 System.out.println(given);
			 
			 HashMap<String, Integer> buses = new HashMap<String,Integer>();
			 buses.put("L", 24);
			 buses.put("M", 14);
			 buses.put("S1", 11);
			 buses.put("S2", 11);
			 System.out.println(given+"   "+buses.get("L"));
			 if(given >=buses.get("L"))
			 {
				 System.out.println("L is chosen");
				 return dB(given-buses.get("L"));
			 }
//			 else if (given >=buses.get("M")|given-buses.get("M"))<11){
//				 System.out.println("M is chosen");
//				 return dB(given-buses.get("M"));
//			 }
			 else {
				 System.out.println("S is chosen");
			 }
			return 0;
			 
		 }
		
}