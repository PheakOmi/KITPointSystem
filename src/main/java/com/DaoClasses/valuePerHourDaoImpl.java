package com.DaoClasses;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Project_Stage_Master;
import com.EntityClasses.Value_Per_Hour;
import com.HibernateUtil.HibernateUtil;
import com.MainController.ValuePerHourController;
import com.ModelClasses.ValuePerHourModel;

@Repository
public class valuePerHourDaoImpl implements valuePerHourDao {


 public boolean addValuePerHour(ValuePerHourModel valuePerHour) {


   String batch = valuePerHour.getBatch_name();
   int batch_id = Integer.parseInt(batch);
   String s_value_1 = valuePerHour.getValue_1();
   int Count = StringUtils.countOccurrencesOf(s_value_1, ","); 
   try {

	   		Timestamp created_at= new Timestamp(System.currentTimeMillis()); 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        int v=0;
		    Transaction tx = session.beginTransaction();
	        for (int i=0;i<=Count;i++)
	        {
	        	 String[] s1 = s_value_1.split(","); 
	    		 String s2 = s1[i];  		 
	    		 Integer s3= Integer.valueOf(s2);
	    		 v=i+1;
	    		 Value_Per_Hour value_per_hour= new Value_Per_Hour(v,s3,created_at,batch_id);
	    		 session.save(value_per_hour);
	        }
		    
		    tx.commit();

		    System.out.println("Object saved sussessfully");
		    session.flush();
		    session.close();

		
		
		   } catch (Exception ex) {
		    Logger.getLogger(ValuePerHourController.class.getName()).log(Level.SEVERE, null, ex);
		    System.out.println(ex);
		
		   }

   return true;
  }
  /*=====================  show batch list ============================*/
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
  /*=====================  show Project Stage ============================*/
 public static List < Project_Stage_Master > getAllProjectStage() {
   List < Project_Stage_Master > project_Stage = new ArrayList < Project_Stage_Master > ();
   Transaction trns = null;
   Session session = HibernateUtil.getSessionFactory().openSession();
   try {
    trns = session.beginTransaction();
    project_Stage = session.createQuery("from Project_Stage_Master").list();
   } catch (RuntimeException e) {
    e.printStackTrace();
    return project_Stage;
   } finally {
    session.flush();
    session.close();
   }
   return project_Stage;
  }
  /*=====================  show Project data ============================*/
 public static List < Project_Master > getAllProjectData() {
  List < Project_Master > project = new ArrayList < Project_Master > ();
  Transaction trns = null;
  Session session = HibernateUtil.getSessionFactory().openSession();
  try {
   trns = session.beginTransaction();
   project = session.createQuery("from Project_Master").list();
  } catch (RuntimeException e) {
   e.printStackTrace();
   return project;
  } finally {
   session.flush();
   session.close();
  }
  return project;
 }
 public Value_Per_Hour addPointValue1(Value_Per_Hour model1) {
  // TODO Auto-generated method stub
  return null;
 }

 public List < Value_Per_Hour > getAllValuePerHour() {
  // TODO Auto-generated method stub
  return null;
 }

 public List < Value_Per_Hour > getBatchSemester() {
  // TODO Auto-generated method stub
  return null;
 }

 public boolean deletePoint(Value_Per_Hour kitPointValue) {
  // TODO Auto-generated method stub
  return false;
 }


}
