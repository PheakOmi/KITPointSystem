<<<<<<< HEAD
package com.DaoClasses;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Semester_Master;
import com.HibernateUtil.HibernateUtil;

public class UpdateSemester {
	//===================Get a list of batch=================================
	public static List < Batch_Master > getAllBatch() {
		   List < Batch_Master > batch = new ArrayList < Batch_Master > ();
		   Transaction trns = null;
		   Session session = HibernateUtil.getSessionFactory().openSession();
		   try {
		    trns = session.beginTransaction();
		    batch = session.createQuery("from Batch_Master").list();
		    
		    
		   } catch (RuntimeException e) {
		    e.printStackTrace();
		    return batch;
		   } finally {
		    session.flush();
		    session.close();
		   }
		   return batch;
		  }
	public static void main(String[] args) throws Exception {
		final String url = "http://192.168.7.222:8069";
		//final String url = "http://96.9.67.154:8070";
	     final String db = "Kirirom_Institute_of_Technology";
	     final String username ="admin";
	     final String password = "adminn";
	     
	     
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
	        List <Batch_Master> l=new ArrayList<Batch_Master>();
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
	        			s.setOdoo_id(Integer.parseInt(str2[1]));
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
	        Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try{
		        trns = session.beginTransaction();
		        Batch_Master batch = new Batch_Master();
	        for (Batch_Master b: l)
	        {
	        	String s = "from Batch_Master where start_date =:start_date and end_date=:end_date";
	        	Query query = session.createQuery(s);
	        	query.setDate("start_date", b.getStart_date());
	        	query.setDate("end_date", b.getEnd_date());
	        	batch=(Batch_Master) query.uniqueResult();
	        	if(batch==null)
	        	{
	        		List<Semester_Master> semesters = new userDaoImpl().getStudent_Semester(b.getOdoo_id());
	        		String currentSemester = new userDaoImpl().getCurrentSemester(semesters);
	        		batch.setSemester(currentSemester);
	        		session.save(b);
	        	}
	        		
	        	else 
	        	{
	        		List<Semester_Master> semesters = new userDaoImpl().getStudent_Semester(b.getOdoo_id());
	        		String currentSemester = new userDaoImpl().getCurrentSemester(semesters);
	        		batch.setSemester(currentSemester);
	        		batch.setOdoo_id(b.getOdoo_id());
	        		session.update(batch);
	        	}
	        	
	        }
	        session.getTransaction().commit();
	        }
	       
	        
	        
	        catch (RuntimeException e) {
			    e.printStackTrace();
			   } finally {
			    session.flush();
			    session.close();
			   }
	        }
	        
	       
	}


=======
package com.DaoClasses;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Semester_Master;
import com.HibernateUtil.HibernateUtil;

public class UpdateSemester {
	//===================Get a list of batch=================================
	public static List < Batch_Master > getAllBatch() {
		   List < Batch_Master > batch = new ArrayList < Batch_Master > ();
		   Transaction trns = null;
		   Session session = HibernateUtil.getSessionFactory().openSession();
		   try {
		    trns = session.beginTransaction();
		    batch = session.createQuery("from Batch_Master").list();
		    
		    
		   } catch (RuntimeException e) {
		    e.printStackTrace();
		    return batch;
		   } finally {
		    session.flush();
		    session.close();
		   }
		   return batch;
		  }
	public static void main(String[] args) throws Exception {
		final String url = "http://192.168.7.222:8069";
		//final String url = "http://96.9.67.154:8070";
	     final String db = "Kirirom_Institute_of_Technology";
	     final String username ="admin";
	     final String password = "adminn";
	     
	     
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
	        List <Batch_Master> l=new ArrayList<Batch_Master>();
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
	        			s.setOdoo_id(Integer.parseInt(str2[1]));
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
	        Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try{
		        trns = session.beginTransaction();
		        Batch_Master batch = new Batch_Master();
	        for (Batch_Master b: l)
	        {
	        	String s = "from Batch_Master where start_date =:start_date and end_date=:end_date";
	        	Query query = session.createQuery(s);
	        	query.setDate("start_date", b.getStart_date());
	        	query.setDate("end_date", b.getEnd_date());
	        	batch=(Batch_Master) query.uniqueResult();
	        	if(batch==null)
	        	{
	        		List<Semester_Master> semesters = new userDaoImpl().getStudent_Semester(b.getOdoo_id());
	        		String currentSemester = new userDaoImpl().getCurrentSemester(semesters);
	        		batch.setSemester(currentSemester);
	        		session.save(b);
	        	}
	        		
	        	else 
	        	{
	        		List<Semester_Master> semesters = new userDaoImpl().getStudent_Semester(b.getOdoo_id());
	        		String currentSemester = new userDaoImpl().getCurrentSemester(semesters);
	        		batch.setSemester(currentSemester);
	        		batch.setOdoo_id(b.getOdoo_id());
	        		session.update(batch);
	        	}
	        	
	        }
	        session.getTransaction().commit();
	        }
	       
	        
	        
	        catch (RuntimeException e) {
			    e.printStackTrace();
			   } finally {
			    session.flush();
			    session.close();
			   }
	        }
	        
	       
	}


>>>>>>> d89376ff1246680561c70d7078f51fe0936dba15
