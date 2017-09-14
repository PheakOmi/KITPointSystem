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
			 String arr[] ={"5/7/2017","11/7/2017","12/7/2017","15/08/2017"};
			 for(int i=0;i<arr.length;i++)
			 {
				 System.out.println(arr[i].indexOf(2, 4));
			 }
			  
		 }


}