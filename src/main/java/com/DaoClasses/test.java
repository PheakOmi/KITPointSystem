package com.DaoClasses;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
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
public class test{
		 public static void main(String args[]) throws Exception {
			 Sms_Server_Info info= new Sms_Server_Info();
		        Transaction trns30 = null;
		        Session session = HibernateUtil.getSessionFactory().openSession();
		        try {
		            trns30 = session.beginTransaction();
		            String queryString = "from Sms_Server_Info";
		            Query query = session.createQuery(queryString);
		            info=(Sms_Server_Info)query.uniqueResult();
		        } catch (RuntimeException e) {
		            e.printStackTrace();
		       
		        } finally {
		            session.flush();
		            session.close();
		        }
		        System.out.println(info);
		 }
		
}