package com.DaoClasses;

import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.apache.xmlrpc.XmlRpcException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;















import com.EncryptionDecryption.Decryption;
import com.EncryptionDecryption.Encryption;
import com.EncryptionDecryption.SecretKeyClass;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Category_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Project_Member;
import com.EntityClasses.Project_Stage;
import com.EntityClasses.Project_Stage_Master;
import com.EntityClasses.Semester_Master;
import com.EntityClasses.Student;
import com.EntityClasses.Task_Master;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.Project_Model;
import com.ModelClasses.Task_Model;
import com.ModelClasses.retrieve;
import com.ModelClasses.submit;
//import com.ServiceClasses.usersService;



@Repository
public class userDaoImpl implements usersDao{
	
	static Encryption encrypt= new Encryption();
	static Decryption decrypt= new Decryption();
	
    public boolean addUser2(User_Info user) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Timestamp created_at= new Timestamp(System.currentTimeMillis()); 
        String password=user.getPassword();
        String email=user.getEmail();
        try {

        	String queryString = "from User_Info where email= :email";
            Query query = session.createQuery(queryString);
            query.setString("email",email );
            List<User_Info> userDataBase=query.list();
        	if (userDataBase.size()==0){
            SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
            String passwordEncryp =encrypt.encryptText(password, secKey) ;
            user.setPassword(passwordEncryp);
            user.setCreated_at(created_at);
            trns = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        	}
        	else{
        		return false;
        	}
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
            session.flush();
            session.close();
        }
		
    }
    
    
  //===================Get a list of user=================================
    public List<User_Info> getAllUser() {
        List<User_Info> users= new ArrayList<User_Info>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            users = session.createQuery("from User_Info").list();
            //System.out.println(semesters);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return users;
        } finally {
            session.flush();
            session.close();
        }
        return users;
    }  
//=====================get member by project id=========================================
    public List<Project_Member> getMemberByProjectId(int id) {
        List<Project_Member> members= new ArrayList<Project_Member>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Project_Member where project_id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",id);
            members = query.list();
            System.out.println(members);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return members;
        } finally {
            session.flush();
            session.close();
        }
        return members;
    }
    public List<Project_Master> getAllProject() {
        List<Project_Master> projects= new ArrayList<Project_Master>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            projects = session.createQuery("from Project_Master").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return projects;
        } finally {
            session.flush();
            session.close();
        }
        return projects;
    }

    
     public User_Info validate(User_Info user) throws Exception
    {
    	Transaction trns = null;
        
        String email= user.getEmail();
        String password= user.getPassword();
        
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns = session.beginTransaction();
            String queryString = "from User_Info where email = :email and password = :password";
            Query query = session.createQuery(queryString);
            query.setString("email", email);
            SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
   		 	String encryptedPassword =encrypt.encryptText(password, secKey) ;
            query.setString("password", encryptedPassword);
            
            user = (User_Info) query.uniqueResult();
            System.out.println("Try "+user);
			
        } catch (RuntimeException e) {
            e.printStackTrace();
            return user;
        } finally {
            session.flush();
            session.close();
        }
        return user;
    }
  //===========================Save Batch==============================
    public boolean saveBatch(Batch_Master batch)
    {
    	Batch_Master batch2 = batch;
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns = session.beginTransaction();
            String queryString = "FROM Batch_Master where name=:name";
            Query query = session.createQuery(queryString);
            query.setString("name",batch.getName());
            batch=(Batch_Master)query.uniqueResult();
            System.out.println("Batch is"+batch);
    			if(batch!=null)
    				return false;
    		Timestamp created_at = new Timestamp(System.currentTimeMillis());
        	batch2.setCreated_at(created_at);
            session.save(batch2);  
            session.getTransaction().commit();
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.flush();
            session.close();
        }
        return true;
    }
//===========================Update Batch==============================
    public boolean updateBatch(Batch_Master batch)
    {
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns = session.beginTransaction();
            String queryString = "FROM Batch_Master where id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",batch.getId());
            System.out.println("Name is"+batch.getName());
            Batch_Master batch2 = new Batch_Master();
            System.out.println("Name is"+batch2);
            batch2=(Batch_Master)query.uniqueResult();
            System.out.println("Name is"+batch2);
    		Timestamp updated_at = new Timestamp(System.currentTimeMillis());
        	batch2.setUpdated_at(updated_at);
        	batch2.setSemester_id(batch.getSemester_id());
        	System.out.println("Before Up");
            session.update(batch2);  
            System.out.println("After Up");
            session.getTransaction().commit();
        } catch (RuntimeException e) {
        	System.out.println("Catch runs");
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.flush();
            session.close();
        }
        return true;
    }

    
//=================================Save projectCategory=========================================
    public boolean createProjectCategory(Project_Category_Master projectCategory)
    {
    	Transaction trns = null;
        
    	Project_Category_Master projectCategory2 = projectCategory;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns = session.beginTransaction();
            String queryString = "FROM Project_Category_Master where name=:name";
            Query query = session.createQuery(queryString);
            query.setString("name",projectCategory.getName());
            projectCategory=(Project_Category_Master)query.uniqueResult();
    			if(projectCategory!=null)
    				return false;
    		Timestamp created_at = new Timestamp(System.currentTimeMillis());
    		projectCategory2.setCreated_at(created_at);
            session.save(projectCategory2);  
            session.getTransaction().commit();
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            System.out.println("Catch runs");
            return false;
        } finally {
            session.flush();
            session.close();
        }
        return true;
    }
//=================================Save task=========================================
    public boolean toSaveTask(Task_Model t) throws ParseException
    {
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns = session.beginTransaction();
            Date start_date = new SimpleDateFormat("MM/dd/yyyy").parse(t.getStart_date());
    		Date end_date = new SimpleDateFormat("MM/dd/yyyy").parse(t.getEnd_date());
    		Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(t.getDeadline());
    		Task_Master tm = new Task_Master();
    		tm.setProject_id(t.getProject_id());
    		tm.setName(t.getName());
    		tm.setAssigned_to(t.getAssigned_to());
    		tm.setDescription(t.getDescription());
    		tm.setStatus(t.getStatus());
    		tm.setTime_spend(t.getTime_spend());
    		tm.setDeadline(deadline);
    		tm.setStart_date(start_date);
    		tm.setEnd_date(end_date);
    		
            session.save(tm);  
            session.getTransaction().commit();
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            System.out.println("Catch runs");
            return false;
        } finally {
            session.flush();
            session.close();
        }
        return true;
    }
//=================================Save project=========================================
    public int saveProject(Project_Model project) throws Exception
    {
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	
            trns = session.beginTransaction();
    		Timestamp created_at = new Timestamp(System.currentTimeMillis());
    		Date start_date = new SimpleDateFormat("MM/dd/yyyy").parse(project.getStart_date());
    		Date end_date = new SimpleDateFormat("MM/dd/yyyy").parse(project.getEnd_date());
    		Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(project.getDeadline());
    		//SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
   		 	//String encryptedPoint =encrypt.encryptText(project.getKit_point(), secKey) ;
    		Project_Master pm = new Project_Master();
    		pm.setProject_name(project.getProject_name());
    		pm.setProject_code(project.getProject_code());
    		pm.setSkillset(project.getSkillset());
    		pm.setProject_type(project.getProject_type());
    		pm.setProject_co(project.getProject_co());
    		pm.setProject_leader(project.getProject_leader());
    		//pm.setProject_member(project.getProject_member());
    		pm.setDescription(project.getDescription());
    		pm.setInitially_planned(project.getInitially_planned());
    		pm.setBudget(project.getBudget());
    		pm.setKit_point(project.getKit_point());
    		pm.setStart_date(start_date);
    		pm.setEnd_date(end_date);
    		pm.setDeadline(deadline);
    		pm.setCreated_at(created_at);
    		pm.setStatus(project.getStatus());
    		session.save(pm); 
    	    int id = pm.getId();
    		session.getTransaction().commit();
    		return id;
    		
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            System.out.println("Catch runs");
            return 0;
        } finally {
            session.flush();
            session.close();
        }
        
    }
//=================================Update project=========================================
    public boolean updateProject(Project_Model project) throws Exception
    {
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	
            trns = session.beginTransaction();
            //SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
   		 	//String encryptedPoint =encrypt.encryptText(project.getKit_point(), secKey) ;
    		Timestamp updated_at = new Timestamp(System.currentTimeMillis());
    		Date start_date = new SimpleDateFormat("MM/dd/yyyy").parse(project.getStart_date());
    		Date end_date = new SimpleDateFormat("MM/dd/yyyy").parse(project.getEnd_date());
    		Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(project.getDeadline());
    		Project_Master pm = new Project_Master();
    		System.out.println("ID in DAO is "+project.getId());
    		pm.setId(project.getId());
    		pm.setProject_name(project.getProject_name());
    		pm.setProject_code(project.getProject_code());
    		pm.setSkillset(project.getSkillset());
    		pm.setProject_type(project.getProject_type());
    		pm.setProject_co(project.getProject_co());
    		pm.setProject_leader(project.getProject_leader());
    		//pm.setProject_member(project.getProject_member());
    		pm.setDescription(project.getDescription());
    		pm.setInitially_planned(project.getInitially_planned());
    		pm.setBudget(project.getBudget());
    		pm.setKit_point(project.getKit_point());
    		pm.setStart_date(start_date);
    		pm.setEnd_date(end_date);
    		pm.setDeadline(deadline);
    		pm.setStatus(project.getStatus());
    		pm.setCreated_at(project.getCreated_at());
    		pm.setUpdated_at(updated_at);
    		session.update(pm); 
    	    //int id = pm.getId();
    		session.getTransaction().commit();
    		
    		
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            System.out.println("Catch runs");
            return false;
        } finally {
            session.flush();
            session.close();
        }
        return true;
    }
  //=================================Update task=========================================
    public boolean updateTask(Task_Model t) throws ParseException
    {
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	
            trns = session.beginTransaction();
    		Timestamp updated_at = new Timestamp(System.currentTimeMillis());
    		Date start_date = new SimpleDateFormat("MM/dd/yyyy").parse(t.getStart_date());
    		Date end_date = new SimpleDateFormat("MM/dd/yyyy").parse(t.getEnd_date());
    		Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(t.getDeadline());
    		Task_Master tm = new Task_Master();
    		System.out.println("ID in DAO is "+t.getId());
    		tm.setId(t.getId());
    		tm.setProject_id(t.getProject_id());
    		tm.setName(t.getName());
    		tm.setAssigned_to(t.getAssigned_to());
    		tm.setDescription(t.getDescription());
    		tm.setStatus(t.getStatus());
    		tm.setTime_spend(t.getTime_spend());
    		tm.setDeadline(deadline);
    		tm.setStart_date(start_date);
    		tm.setEnd_date(end_date);
    		tm.setCreated_at(updated_at);
    		session.update(tm); 
    	    //int id = pm.getId();
    		session.getTransaction().commit();
    		
    		
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            System.out.println("Catch runs");
            return false;
        } finally {
            session.flush();
            session.close();
        }
        return true;
    }
//=================================Save Stage==================================================
    public void saveMember(int projectid, int arr[]) throws MalformedURLException, XmlRpcException
    {
    	List<Student> students = new StudentFromOdoo().getStudent();
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Timestamp created_at = new Timestamp(System.currentTimeMillis());
        try {
        	
            trns = session.beginTransaction();
    		for (int i = 0; i<arr.length; i++)
    		{
    			Project_Member pm = new Project_Member();
    			for (int j=0;j<students.size();j++)
    			{
    				if (arr[i]==Integer.parseInt(students.get(j).getId()))
    					{
    						pm.setUser_name(students.get(j).getName());
    						break;
    					}
    			}
    			pm.setProject_id(projectid);
    			pm.setUser_id(arr[i]);
        		pm.setCreated_at(created_at);
    			session.save(pm);
    			
    		}  
    		session.getTransaction().commit();
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            System.out.println("Catch in member runs");
        } finally {
            session.flush();
            session.close();
        }
       
    }

//===================Get a list of semesters=================================
    public List<Semester_Master> getAllSemester() {
        List<Semester_Master> semesters= new ArrayList<Semester_Master>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            semesters = session.createQuery("from Semester_Master").list();
            System.out.println(semesters);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return semesters;
        } finally {
            session.flush();
            session.close();
        }
        return semesters;
    }
//===================Get a list of project categories=================================
    public List<Project_Category_Master> getProjectCategories() {
        List<Project_Category_Master> p= new ArrayList<Project_Category_Master>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            p = session.createQuery("from Project_Category_Master").list();
            System.out.println(p);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return p;
        } finally {
            session.flush();
            session.close();
        }
        return p;
    }
//===================Get a list of batch=================================
    public List<Batch_Master> getAllBatch() {
        List<Batch_Master> batch= new ArrayList<Batch_Master>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            batch = session.createQuery("from Batch_Master").list();
            //System.out.println(semesters);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return batch;
        } finally {
            session.flush();
            session.close();
        }
        return batch;
    }  

//===================Get a list of stages=================================
    public List<Project_Stage_Master> getAllStages() {
        List<Project_Stage_Master> stages= new ArrayList<Project_Stage_Master>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            stages = session.createQuery("from Project_Stage_Master").list();
            //System.out.println(semesters);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return stages;
        } finally {
            session.flush();
            session.close();
        }
        return stages;
    }  
//===========================Get all task===================================
    public List<Task_Master> getAllTask()
    {
    	List<Task_Master> tasks= new ArrayList<Task_Master>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            tasks = session.createQuery("from Task_Master").list();
            //System.out.println(semesters);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return tasks;
        } finally {
            session.flush();
            session.close();
        }
        return tasks;
    }
//==================Get project by id=======================
    public Project_Master getProjectById(int id) throws Exception
    {
    	System.out.println("Hi");
    	Project_Master project= new Project_Master();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Project_Master where id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",id);
            project=(Project_Master)query.uniqueResult();
//            SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
//		    String decryptedPoint;
//		    decryptedPoint = decrypt.decryptText(project.getKit_point(), secKey);
//		    decryptedPoint.trim();
//		    project.setKit_point(decryptedPoint);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return project;
        } finally {
            session.flush();
            session.close();
        }
        return project;
    }

	public Task_Master getTaskById(int id) {
		
		Task_Master task= new Task_Master();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Task_Master where id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",id);
            task=(Task_Master)query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return task;
        } finally {
            session.flush();
            session.close();
        }
        return task;
    }
    
  //===========================================================  
    
    
}



