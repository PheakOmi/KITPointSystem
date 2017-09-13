package com.DaoClasses;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKey;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.EncryptionDecryption.Decryption;
import com.EncryptionDecryption.Encryption;
import com.EncryptionDecryption.SecretKeyClass;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Project_Stage_Master;
import com.EntityClasses.Value_Per_Hour;
import com.HibernateUtil.HibernateUtil;
import com.MainController.ValuePerHourController;
import com.ModelClasses.ProjectView_Model;
import com.ModelClasses.ValuePerHourModel;

@Repository
public class test{

	Encryption encrypt= new Encryption();
	Decryption decrypt= new Decryption();
	
		  /*=====================  show Project data ============================*/
		 public static void main(String args[]){
			  List < Project_Master > project = new ArrayList < Project_Master > ();
			  Transaction trns2 = null;
			  Session session = HibernateUtil.getSessionFactory().openSession();
			  try {
			   trns2 = session.beginTransaction();
			   project = session.createQuery("from Project_Master").list();
			  System.out.println("size is"+project.size());
			  } catch (RuntimeException e) {
			   e.printStackTrace();
			   
			  } finally {
			   session.flush();
			   session.close();
			  }
			  
		 }


}