package com.DaoClasses;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKey;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.EncryptionDecryption.Decryption;
import com.EncryptionDecryption.Encryption;
import com.EncryptionDecryption.SecretKeyClass;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Project_Stage_Master;
import com.EntityClasses.Sms_Server_Info;
import com.EntityClasses.Student;
import com.EntityClasses.Task_Master;
import com.EntityClasses.UserRole;
import com.EntityClasses.User_Info;
import com.EntityClasses.Value_Per_Hour;
import com.HibernateUtil.HibernateUtil;
import com.MainController.ValuePerHourController;
import com.ModelClasses.ProjectView_Model;
import com.ModelClasses.ValuePerHourModel;

@Repository
public class test2{
		 public static void main(String args[]) throws Exception {
			 List<Project_Master> projects= new ArrayList<Project_Master> ();
				Date start_date;
				Date end_date;
				start_date = new SimpleDateFormat("MM/dd/yyyy").parse("10/01/2017");
		   		end_date = new SimpleDateFormat("MM/dd/yyyy").parse("10/27/2017");
				String status = "Approved Project";
				int type = 1;
				int co = 1;
				int leader = 6;
				String query = "from Project_Master p where p.start_date between :start_date1 and :end_date1 and p.end_date between :start_date2 and :end_date2";
		   		if(type!=0)
		   			query=query+" and project_type=:type";
		   		if(co!=0)
		   			query=query+" and project_co=:co";
		   		if(leader!=0)
		   			query=query+" and project_leader=:leader";
		   		if(status!=null)
					query=query+" and status=:status";
				Transaction trns25 = null;
				Session session = HibernateUtil.getSessionFactory().openSession();
				try{
					List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
					trns25  = session.beginTransaction();
		  		 	Query q = session.createQuery(query);
		  		 	q.setDate("start_date1", start_date);
		  		 	q.setDate("start_date2", start_date);
		  		 	q.setDate("end_date1", end_date);
		  		 	q.setDate("end_date2", end_date);
		  		 	if(type!=0)
		  	   			q.setInteger("type", type);
		  	   		if(co!=0)
		  	   			q.setInteger("co", co);
		  	   		if(leader!=0)
		  	   			q.setInteger("leader", leader);
		  	   		if(status!=null)
		  	   			q.setString("status", status);
		 		 	projects = q.list();
		 		 	
		 		 	System.out.println(projects.size());
		 		 	System.out.println("Name "+projects.get(0).getProject_name());
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
				}
				catch(RuntimeException e)
				{
					e.printStackTrace();
				}
				finally{
					session.flush();
					session.close();
				}
		        
		 }
		
}