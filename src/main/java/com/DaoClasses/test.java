package com.DaoClasses;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.EntityClasses.Student;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;

public class test {

public static void main(String args[]){
	String email = "kamal@gmail.com";
	String oldPassword = "kamal";
	String newPassword  = "kamal2";
	User_Info user = new User_Info();
	Transaction trns25 = null;
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try{
		trns25  = session.getTransaction();
		String encryptedPassword = passwordEncoder.encode(oldPassword);
//		System.out.println("Pass"+encryptedPassword);
		 	String queryString  = "from User_Info where email=:email";
		 	Query query = session.createQuery(queryString);
		 	query.setString("email",email);
		 	user = (User_Info) query.uniqueResult();
		 	System.out.println(user);
		 	if (user==null){
		 		
		 	}
		 	else{
		 		if(passwordEncoder.matches("kamal2", user.getPassword()))
		 			System.out.println("Matches");
		 		else
		 			System.out.println("No!");
		 	}
	}
	catch(RuntimeException e)
	{
		
		System.out.println("Catch runs");
		e.printStackTrace();
	}
	finally{
		session.close();
		//session.flush();
	}

}}
