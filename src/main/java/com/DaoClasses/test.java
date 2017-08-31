package com.DaoClasses;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.EntityClasses.Project_Member;
import com.HibernateUtil.HibernateUtil;

public class test {
public static void main(String agrs[]){
	 int project_id= 187;
	 int arr[]  = {81,104,77,121};
	 int c=0;
	 int a=0;
	        List<Project_Member> members= new ArrayList<Project_Member>();
	        Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	        	trns = session.beginTransaction();
	        	String queryString = "from Project_Member where project_id =:project_id";
	            Query query = session.createQuery(queryString);
	            query.setInteger("project_id",project_id);
	            members = query.list();
	            
	
	            //System.out.println(semesters);
	            for (int i=0;i<members.size();i++)
	            	for (int j=0;j<arr.length;j++)
	              	{
	            		a++;
	            		if(members.get(i).getUser_id()==arr[j]){
	            			c++;
	            		break;
	            			}
	            	}
	            	System.out.println("A = "+a);
	            	System.out.println("C = "+c);
	        } catch (RuntimeException e) {
	            e.printStackTrace();
	        } finally {
	            session.flush();
	            session.close();
	        }
	    }  
	}

