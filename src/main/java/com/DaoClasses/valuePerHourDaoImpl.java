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
import com.ModelClasses.KIT_Point_Model;
import com.ModelClasses.ProjectView_Model;
import com.ModelClasses.ValuePerHourModel;

@Repository
public class valuePerHourDaoImpl implements valuePerHourDao {

	Encryption encrypt= new Encryption();
	Decryption decrypt= new Decryption();
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
			    		 SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
			    		 String valueEncryp =encrypt.encryptText(s2, secKey) ;
			    		 v=i+1;
			    		 Value_Per_Hour value_per_hour= new Value_Per_Hour(v,valueEncryp,created_at,batch_id);
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
		 public List < Batch_Master > getAllBatch() {
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
		 public List < Project_Stage_Master > getAllProjectStage() {
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
		 public List < ProjectView_Model > getAllProjectData() {
		  List < Project_Master > project = new ArrayList < Project_Master > ();
		  List < ProjectView_Model > projectList = new ArrayList < ProjectView_Model > ();
		  Transaction trns = null;
		  Session session = HibernateUtil.getSessionFactory().openSession();
		  try {
		   trns = session.beginTransaction();
		   project = session.createQuery("from Project_Master").list();
		   SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
		   for (int i =0;i< project.size();i++){
			   String kitPoint=project.get(i).getKit_point();
			   String decryptedText = decrypt.decryptText( kitPoint, secKey);
			   ProjectView_Model projectViewData= new ProjectView_Model();
			   projectViewData.setId(project.get(i).getId());
			   projectViewData.setProject_name(project.get(i).getProject_name());
			   projectViewData.setProject_code(project.get(i).getProject_code());
			   projectViewData.setProject_type(project.get(i).getProject_type());
			   projectViewData.setProject_leader(project.get(i).getProject_leader());
			   projectViewData.setProject_member(project.get(i).getProject_member());
			   projectViewData.setProject_co(project.get(i).getProject_co());
			   projectViewData.setDeadline(project.get(i).getDeadline());
			   projectViewData.setStart_date(project.get(i).getStart_date());
			   projectViewData.setEnd_date(project.get(i).getEnd_date());
			   projectViewData.setSkillset(project.get(i).getSkillset());
			   projectViewData.setDescription(project.get(i).getDescription());
			   projectViewData.setStatus(project.get(i).getStatus());
			   projectViewData.setBudget(project.get(i).getBudget());
			   projectViewData.setInitially_planned(project.get(i).getInitially_planned());
			   projectViewData.setKit_point(decryptedText);
			   projectViewData.setCreated_at(project.get(i).getCreated_at());
			   projectViewData.setUpdated_at(project.get(i).getUpdated_at());
			   projectList.add(projectViewData);
			   
		   }
		   
		  } catch (RuntimeException e) {
		   e.printStackTrace();
		   return projectList;
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		   session.flush();
		   session.close();
		  }
		  return projectList;
		 }
		 /*=====================  show Project data ============================*/
		public static List<Object> countAllTask() {
				 Transaction trns = null;
				 Session session = HibernateUtil.getSessionFactory().openSession();
				 List<Object> project;
				try {
				  trns = session.beginTransaction();
				  project = session.createQuery("select project_id as project,count(*) as num_task from Task_Master group by project_id").list();
				 } 
				 catch (RuntimeException e) {
				  e.printStackTrace();
				  return null;
				 } finally {
				  session.flush();
				  session.close();
				 }
				 return project ;
		}
		 /*=====================  show Project data by status============================*/
		public List < ProjectView_Model > getProjectBasedOnStatus(String statusData) {
		 List < Project_Master > project = new ArrayList < Project_Master > ();
		 List < ProjectView_Model > projectListView = new ArrayList < ProjectView_Model > ();
		 Transaction trns = null;
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 try {
		  trns = session.beginTransaction();
		  String queryString= "from Project_Master where status=:statusData";
		  Query query=session.createQuery(queryString);
		  query.setString("statusData", statusData);
		 // project = session.createQuery("from Project_Master where status:statusData").list();
		  project=query.list();
		  SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
		  for (int i=0; i<project.size();i++)
		  {
			  String kitPointValue =project.get(i).getKit_point();
			  String decryptedText = decrypt.decryptText( kitPointValue, secKey);
			   ProjectView_Model projectViewData= new ProjectView_Model();
			   projectViewData.setId(project.get(i).getId());
			   projectViewData.setProject_name(project.get(i).getProject_name());
			   projectViewData.setProject_code(project.get(i).getProject_code());
			   projectViewData.setProject_type(project.get(i).getProject_type());
			   projectViewData.setProject_leader(project.get(i).getProject_leader());
			   projectViewData.setProject_member(project.get(i).getProject_member());
			   projectViewData.setProject_co(project.get(i).getProject_co());
			   projectViewData.setDeadline(project.get(i).getDeadline());
			   projectViewData.setStart_date(project.get(i).getStart_date());
			   projectViewData.setDescription(project.get(i).getDescription());
			   projectViewData.setEnd_date(project.get(i).getEnd_date());
			   projectViewData.setSkillset(project.get(i).getSkillset());
			   projectViewData.setStatus(project.get(i).getStatus());
			   projectViewData.setBudget(project.get(i).getBudget());
			   projectViewData.setInitially_planned(project.get(i).getInitially_planned());
			   projectViewData.setKit_point(decryptedText);
			   projectViewData.setCreated_at(project.get(i).getCreated_at());
			   projectViewData.setUpdated_at(project.get(i).getUpdated_at());
			   projectListView.add(projectViewData);
		  }
		  
		 
		 } catch (RuntimeException e) {
		  e.printStackTrace();
		  return  projectListView;
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		  session.flush();
		  session.close();
		 }
		 return  projectListView;
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
		  /*+++++++++++++++++++++++To Apporve project+++++++++++++++++++++++++++++++*/
		 public boolean approveProject(int id){
			 int project_id=id;
			 Project_Master project= new Project_Master();
		        Transaction trns = null;
		        Session session = HibernateUtil.getSessionFactory().openSession();
		        ProjectView_Model projectViewData= new ProjectView_Model();
		        try {
		            trns = session.beginTransaction();
		            String queryString = "from Project_Master where id=:id";
		            Query query = session.createQuery(queryString);
		            query.setInteger("id", project_id);
		            project=(Project_Master)query.uniqueResult();
		            project.setStatus("Approved Project");
		            session.update(project);
				    
					   
		        } catch (RuntimeException e) {
		            e.printStackTrace();
		            return false;
		        } finally {
		            session.flush();
		            session.close();
		        }
			return true;
			 
		 }


}
