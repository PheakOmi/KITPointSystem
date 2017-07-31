package com.DaoClasses;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
class Student{
	String id;
	String name;
	String gender;
	@Override
	public String toString()
	{
		return id+name+gender;
	}
}
public class kk {

	public static void main(String[] args) throws Exception {
		final String url = "http://96.9.67.154:8081";
	     final String db = "Kirirom_Institute_of_Technology";
	     final String username ="admin";
	     final String password = "admin";
	     
	     
	     final XmlRpcClient authClient = new XmlRpcClient();
	        final XmlRpcClientConfigImpl authStartConfig = new XmlRpcClientConfigImpl();
	        authStartConfig.setServerURL(
	                new URL(String.format("%s/xmlrpc/2/common", url)));
	        
	        List configList = new ArrayList();
	        Map paramMap = new HashMap();
	        
	        configList.add(db);
	        configList.add(username);
	        configList.add(password);
	        configList.add(paramMap);
	        
	        int uid = (Integer)authClient.execute(
	                authStartConfig, "authenticate", configList);
	        System.out.println("Connection Success");

	        final XmlRpcClient objClient = new XmlRpcClient();
	        final XmlRpcClientConfigImpl objStartConfig = new XmlRpcClientConfigImpl();
	        objStartConfig.setServerURL(
	                new URL(String.format("%s/xmlrpc/2/object", url)));
	        objClient.setConfig(objStartConfig);
	        
	        List paramList = new ArrayList();
	        List list1 = new ArrayList();
	        List list2 = new ArrayList();
	        List list3 = new ArrayList();
	        List field = new ArrayList();
	        configList.clear();
	        paramMap.clear();
	        paramList.clear();
	        
	        configList.add(db);
	        configList.add(uid);
	        configList.add(password);
	        configList.add("op.student");
	        configList.add("search_read");
	        
	        list3.add("id");
	        list3.add(">");
	        list3.add(0);
	        list2.add(list3);
	        list1.add(list2);

	        configList.add(list1);
	        
	        field.add("name");
	        field.add("gender");
	        paramMap.put("fields", field);
	        configList.add(paramMap);

	        List<Object> a= Arrays.asList((Object[])objClient.execute("execute_kw", configList));
	        
	        List <Student> l=new ArrayList<Student>();
	        String str[]=new String[a.size()];
	        //Student s=new Student();
	        for(int i=0;i<a.size();i++)
	        {
	        	Student s=new Student();
	        	str[i]=a.get(i).toString();
//	        	System.out.println(str[i]);
	        	String str1[]=str[i].split(",");
	        	int cnt=0;
	        	for(int j=0;j<str1.length;j++)
	        	{
	        		String []str2=str1[j].split("=");
	        		if(cnt==0)
	        		{
	        			s.gender=str2[1];
//	        			System.out.println(str2[1]);
	        			cnt++;
	        		}
	        		else if(cnt==1)
	        		{
	        			s.name=str2[1];
//	        			System.out.println(str2[1]);
	        			cnt++;
	        		}
	        		else if(cnt==2)
	        		{
	        			s.id=str2[1].replace("}","").trim();
	        			
//	        			System.out.println(str2[1]);
	        			cnt=0;
	        		}
	        		
	        		
	        	}
	        	
	        	l.add(s);
	        }
	        System.out.println(l.size());

	        
//System.out.println(l);
	}

}
