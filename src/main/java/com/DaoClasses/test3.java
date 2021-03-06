package com.DaoClasses;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Semester_Master;
import com.HibernateUtil.HibernateUtil;

public class test3 {
	
	public static void main(String[] args) throws Exception {
		List <Batch_Master> l=new ArrayList<Batch_Master>();
		final String url = "http://192.168.7.222:8069";
		//final String url = "http://96.9.67.154:8070";
	     final String db = "Kirirom_Institute_of_Technology";
	     final String username ="admin";
	     final String password = "adminn";
	     
	    try{ 
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
	        configList.add("op.batch");
	        configList.add("search_read");
	        
	        list3.add("id");
	        list3.add(">");
	        list3.add(0);
	        list2.add(list3);
	        list1.add(list2);

	        configList.add(list1);
	        
	        field.add("name");
	        field.add("start_date");
	        field.add("end_date");
	        paramMap.put("fields", field);
	        configList.add(paramMap);

	        List<Object> a= Arrays.asList((Object[])objClient.execute("execute_kw", configList));
	        
	        String str[]=new String[a.size()];
	        //Student s=new Student();
	        for(int i=0;i<a.size();i++)
	        {
	        	Batch_Master s=new Batch_Master();
	        	str[i]=a.get(i).toString();
	           	String str1[]=str[i].split(",");
	        	int cnt=0;
	        	for(int j=0;j<str1.length;j++)
	        	{
	        		String []str2=str1[j].split("=");
	        		if(cnt==0)
	        		{
	        			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(str2[1]);
	        			s.setEnd_date(date1);
	        			cnt++;
	        		}
	        		else if(cnt==1)
	        		{
	        			s.setName(str2[1]);
	           			cnt++;
	        		}
	        		else if(cnt==2)
	        		{
	        			//s.id=str2[1].replace("}","").trim();
	        			s.setId(Integer.parseInt(str2[1]));
	        			cnt++;
	        		}
	        		else if(cnt==3)
	        		{
	        			String x = str2[1].replace("}","").trim();
	        			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(x);
	        			s.setStart_date(date1);
	        			cnt=0;
	        		}
	        		
	        		
	        	}
	        	
	        	l.add(s);
	        }
	    }
	    catch(XmlRpcException e)
	    {
	    	System.out.println("Thrown");
	    	e.printStackTrace();
	    }
	    
	    
	}
	        
	       
	}
