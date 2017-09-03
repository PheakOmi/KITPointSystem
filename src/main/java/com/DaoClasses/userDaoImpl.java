<<<<<<< HEAD
package com.DaoClasses;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.EntityClasses.UserRole;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.ProjectView_Model;
import com.ModelClasses.Project_Model;
import com.ModelClasses.Task_Model;
import com.ModelClasses.retrieve;
import com.ModelClasses.submit;
//import com.ServiceClasses.usersService;



@Repository
public class userDaoImpl implements usersDao{
	
	Encryption encrypt= new Encryption();
	Decryption decrypt= new Decryption();
	
	
	
	//===================For SS========================================  
    
    public User_Info findByUserName(String username) {
    	Transaction trns1 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
		List<User_Info> users = new ArrayList<User_Info>();
	
		try {
            trns1 = session.beginTransaction();
            users = session.createQuery("from User_Info where email=?").setParameter(0, username).list();
            		//setParameter(0, username).list();
            
            if (users.size() > 0) {
    			return users.get(0);
    		} else {
    			return null;
    		}
        } catch (RuntimeException e) {
        	
        }                         
		return null;
	}
    
    
  //=================================================================    

	
	
	
    public boolean addUser2(User_Info user) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Timestamp created_at= new Timestamp(System.currentTimeMillis()); 
        String password=user.getPassword();
        String email=user.getEmail();
        String user_type=user.getUser_type();
        String username= user.getName();
        try {

        	String queryString = "from User_Info where email= :email";
            Query query = session.createQuery(queryString);
            query.setString("email",email );
            List<User_Info> userDataBase=query.list();
        	if (userDataBase.size()==0){
        		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        		String hashedPassword = passwordEncoder.encode(password);
	            User_Info userData =new User_Info();
	            userData.setName(username);
	            userData.setEmail(email);
	            userData.setEnabled(true);
	            userData.setPassword(hashedPassword);
	            userData.setCreated_at(created_at);
	            UserRole user_role= new UserRole();
	            user_role.setRole(user_type);
	            user_role.setCreated_at(created_at);
	            user_role.setUser_info(userData);
	            transaction = session.beginTransaction();
	            Set<UserRole> userrole= new HashSet<UserRole>();
	            userrole.add(user_role);
	            userData.setUserRole(userrole);
	            session.save(userData);
	            transaction.commit();
	            transaction = session.beginTransaction();
	            session.save(user_role);
	            transaction.commit();
            return true;
        	}
        	else{
        		return false;
        	}
        } catch (RuntimeException e) {
            if (transaction != null) {
            	transaction.rollback();
            }
            return false;
        } catch (Exception e) {
			return false;
		} finally {
            session.close();
        }
		
    }
    
    
  //===================Get a list of user=================================
    public List<User_Info> getAllUser() {
    	List<User_Info> users= new ArrayList<User_Info>();
        Transaction trns2 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns2 = session.beginTransaction();
            users = session.createQuery("select id,name,email,user_type,created_at,updated_at from User_Info").list();
            
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return users;
    } 
//=====================get member by project id=========================================
    public List<Project_Member> getMemberByProjectId(int id) {
        List<Project_Member> members= new ArrayList<Project_Member>();
        Transaction trns3 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns3 = session.beginTransaction();
            String queryString = "from Project_Member where project_id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",id);
            members = query.list();
            
        } catch (RuntimeException e) {
            e.printStackTrace();
            return members;
        } finally {
            session.flush();
            session.close();
        }
        return members;
    }
//=================get A project id by task id==========================
    public int getProjectIdByTaskId(int taskId){
    	int projectId=0;
    	Transaction trns4 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns4 = session.beginTransaction();
            String queryString = "select project_id from Task_Master where id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",taskId);
            projectId=(Integer)query.uniqueResult();
           
//            SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
//		    String decryptedPoint;
//		    decryptedPoint = decrypt.decryptText(project.getKit_point(), secKey);
//		    decryptedPoint.trim();
//		    project.setKit_point(decryptedPoint);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return projectId;
        } finally {
            session.flush();
            session.close();
        }
        return projectId;		
	}
    public List<Project_Master> getAllProject() {
        List<Project_Master> projects= new ArrayList<Project_Master>();
        Transaction trns5 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns5 = session.beginTransaction();
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

//=========================To validate user whilw loging in====================================================    
     public User_Info validate(User_Info user) throws Exception
    {
    	Transaction trns6 = null;
        
        String email= user.getEmail();
        String password= user.getPassword();
        
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns6 = session.beginTransaction();
            String queryString = "from User_Info where email = :email and password = :password";
            Query query = session.createQuery(queryString);
            query.setString("email", email);
            //SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
   		 	//String encryptedPassword =encrypt.encryptText(password, secKey) ;
            query.setString("password", password);
            
            user = (User_Info) query.uniqueResult();
            //System.out.println("Try "+user);
			
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
    	Transaction trns7 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns7 = session.beginTransaction();
            String queryString = "FROM Batch_Master where name=:name";
            Query query = session.createQuery(queryString);
            query.setString("name",batch.getName());
            batch=(Batch_Master)query.uniqueResult();
         
    			if(batch!=null)
    				return false;
    		Timestamp created_at = new Timestamp(System.currentTimeMillis());
        	batch2.setCreated_at(created_at);
            session.save(batch2);  
            session.getTransaction().commit();
        } catch (RuntimeException e) {
        	if (trns7 != null) {
                trns7.rollback();
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
    	Transaction trns8 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns8 = session.beginTransaction();
            String queryString = "FROM Batch_Master where id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",batch.getId());
            Batch_Master batch2 = new Batch_Master();
            batch2=(Batch_Master)query.uniqueResult();
    		Timestamp updated_at = new Timestamp(System.currentTimeMillis());
        	batch2.setUpdated_at(updated_at);
        	//batch2.setSemester_id(batch.getSemester_id());
        	session.update(batch2);  
            session.getTransaction().commit();
        } catch (RuntimeException e) {
        	if (trns8 != null) {
                trns8.rollback();
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
    	Transaction trns9 = null;
        
    	Project_Category_Master projectCategory2 = projectCategory;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns9 = session.beginTransaction();
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
        	if (trns9 != null) {
                trns9.rollback();
            }
            e.printStackTrace();
           
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
    	Transaction trns10 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	Task_Master tm2;
            trns10 = session.beginTransaction();
            String queryString = "FROM Task_Master where name=:name and project_id=:project_id";
            Query query = session.createQuery(queryString);
            query.setString("name",t.getName());
            query.setInteger("project_id", t.getProject_id());
            tm2=(Task_Master)query.uniqueResult();
    			if(tm2!=null)
    				return false;
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
        	if (trns10 != null) {
                trns10.rollback();
            }
            e.printStackTrace();
           
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
    	Transaction trns11 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	Project_Master pm2;
            trns11 = session.beginTransaction();
            String queryString = "FROM Project_Master where project_name=:name";
            Query query = session.createQuery(queryString);
            query.setString("name",project.getProject_name());
            pm2=(Project_Master)query.uniqueResult();
    			if(pm2!=null)
    				return 0;
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
        	if (trns11 != null) {
                trns11.rollback();
            }
            e.printStackTrace();
          
            return 0;
        } finally {
            session.flush();
            session.close();
        }
        
    }
//=================================Update project=========================================
    public boolean updateProject(Project_Model project) throws Exception
    {
    	int count=0;
    	Transaction trns12 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	
            trns12 = session.beginTransaction();
            List<Project_Master> projects= new ArrayList<Project_Master>();
            projects = getAllProject();
            for(Project_Master p:projects)
            {
            	if(project.getId()!=p.getId())
            			{
            				if (p.getProject_name().equals(project.getProject_name()))
            					count++;
            			}
            }
            if(count>=1)
            	return false;
    		Timestamp updated_at = new Timestamp(System.currentTimeMillis());
    		Date start_date = new SimpleDateFormat("MM/dd/yyyy").parse(project.getStart_date());
    		Date end_date = new SimpleDateFormat("MM/dd/yyyy").parse(project.getEnd_date());
    		Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(project.getDeadline());
    		
    		  int old[] = getMembersIdByProjectId(project.getId());
    		  int newa [] = project.getMember();
    		  List<Integer> myList = new ArrayList<Integer>();
    		   Arrays.sort(newa);
    		   Arrays.sort(old);
       		   for (int i = 0; i < old.length; i++)
    		   {
    		    if(contains(newa,old[i]))
    		     {
    		     }
    		    else{
    		      session.delete(getAMemberById(old[i],project.getId()));
    		     }
    		   }
    		   for (int i = 0; i < newa.length; i++)
    		   {
    		    if(contains(old,newa[i]))
    		     {
    		     }
    		    else{
    		    	myList.add(newa[i]);
    		     }
    		   }
    		   int size = myList.size();
    		   int[] save = new int[size];
    		   Integer[] temp = myList.toArray(new Integer[size]);
    		   for (int n = 0; n < size; ++n) 
    		       save[n] = temp[n];
   		    saveMember(project.getId(),save);
    		
    		Project_Master pm = new Project_Master();
    	
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
        	if (trns12 != null) {
                trns12.rollback();
            }
            e.printStackTrace();
          
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
    	Transaction trns13 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	
            trns13 = session.beginTransaction();
            String queryString = "FROM Task_Master where project_id =:project_id and name=:name";
            Query query = session.createQuery(queryString);
            query.setInteger("project_id",t.getProject_id());
            query.setString("name", t.getName());
            Task_Master t2 = (Task_Master)query.uniqueResult();
    			if(t2!=null)
    				return false;
    		Timestamp updated_at = new Timestamp(System.currentTimeMillis());
    		Date start_date = new SimpleDateFormat("MM/dd/yyyy").parse(t.getStart_date());
    		Date end_date = new SimpleDateFormat("MM/dd/yyyy").parse(t.getEnd_date());
    		Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(t.getDeadline());
    		Task_Master tm = new Task_Master();
  
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
        	if (trns13 != null) {
                trns13.rollback();
            }
            e.printStackTrace();
         
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
    	Transaction trns14 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Timestamp created_at = new Timestamp(System.currentTimeMillis());
        try {
        	
            trns14 = session.beginTransaction();
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
        	if (trns14 != null) {
                trns14.rollback();
            }
            e.printStackTrace();
           
        } finally {
            session.flush();
            session.close();
        }
       
    }

//===================Get a list of semesters=================================
    public List<Semester_Master> getAllSemester() {
        List<Semester_Master> semesters= new ArrayList<Semester_Master>();
        Transaction trns15 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns15 = session.beginTransaction();
            semesters = session.createQuery("from Semester_Master").list();
            
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
        Transaction trns16 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns16 = session.beginTransaction();
            p = session.createQuery("from Project_Category_Master").list();

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
        Transaction trns17 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns17 = session.beginTransaction();
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

//===========================Get all task===================================
    public List<Task_Master> getAllTask()
    {
    	List<Task_Master> tasks= new ArrayList<Task_Master>();
        Transaction trns18 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns18 = session.beginTransaction();
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
    	Project_Master project= new Project_Master();
        Transaction trns19 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns19 = session.beginTransaction();
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
        Transaction trns20 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns20 = session.beginTransaction();
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


	public static Map<Integer,Integer> getBatch(int student[]) throws MalformedURLException, XmlRpcException {
		List<Student> students = new StudentFromOdoo_BatchId().getStudent_BatchId();
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();  
		for (int i =0; i<students.size();i++)
		{
			for(int j=0; j<student.length;j++)
			if(students.get(i).getId().equals(Integer.toString(student[j])))
			{
				map.put(student[j],Integer.parseInt(students.get(i).getBatch_id()));
			}
		}
		return map;
		
	}
    
  //=======================
	public Map<Integer, String> getStudentSemester(int arr[]) throws Exception{
		Map<Integer, Integer> batch = new HashMap<Integer, Integer>();
		Map<Integer, String> semester = new HashMap<Integer, String>();
		
		List<Student> students = new StudentFromOdoo_BatchId().getStudent_BatchId();

		for (int i = 0; i < students.size(); i++) {
			for (int j = 0; j < arr.length; j++) {
				if ((Integer.parseInt(students.get(i).getId())) == arr[j]) {
					batch.put(arr[j],Integer.parseInt(students.get(i).getBatch_id()));
				}
			}

		}
		String s_semester = new String();
		for (Map.Entry<Integer, Integer> entry : batch.entrySet()) {
			s_semester = new userDaoImpl().getSemesterByBatchId(entry.getValue());
			semester.put(entry.getKey(), s_semester+","+entry.getValue());
		}
		return semester;
		}
//=====================Point calculation==============================
	public Map<String, Float> pointCalculation(Map<Integer, String> mm, int t) throws Exception {
		Map<String, Float> map = new HashMap<String, Float>();
		float time = (float)t/mm.size();
		float sum= 0f;
		SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
		String decryptedText;
		for (Map.Entry<Integer, String> entry : mm.entrySet())
		{
			String str[]=entry.getValue().split(",");
			int batch_id = Integer.parseInt(str[1]);
			String semester_name=str[0];
			Transaction trns21 = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns21 = session.beginTransaction();
	            String queryString = "select value from Value_Per_Hour where batch_id=:batch_id and semester_name=:semester_name";
	            Query query = session.createQuery(queryString);
	            query.setInteger("batch_id",batch_id);
	            query.setString("semester_name",semester_name);
	         
	            String value=(String)query.uniqueResult();
	            decryptedText = decrypt.decryptText(value, secKey);
	            //System.out.println(time + " * "+ decryptedText +" = "+(Integer.parseInt(decryptedText)*time));
	            sum = (float)sum + Float.parseFloat(decryptedText)*time;
	    		
	        } catch (RuntimeException e) {
	            e.printStackTrace();
	        } finally {
	            session.flush();
	            session.close();
	        }
			
		}
		String pointValue = new userDaoImpl().getKitPoint();
		String decryptedPointValue = decrypt.decryptText(pointValue, secKey);
		map.put("budget", sum);
		map.put("point", sum/Float.parseFloat(decryptedPointValue));
		return map;
		
		
	}
//========================Retrieve KIT Point from DB====================================
	public String getKitPoint(){
		String pointValue= new String();
        Transaction trns22 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns22 = session.beginTransaction();
            String queryString = "select value from KIT_Point";
            Query query = session.createQuery(queryString);
            pointValue=(String)query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return pointValue;
        } finally {
            session.flush();
            session.close();
        }
        return pointValue;
	}
//===========================
	public List<Semester_Master> getStudent_Semester(int batch_id) throws XmlRpcException,
	MalformedURLException, ParseException {
		  final String url = "http://96.9.67.154:8070"; 
		      final String db = "Kirirom_Institute_of_Technology"; 
		      final String username ="admin"; 
		      final String password = "admin"; 
		       
		      List<Semester_Master> semesters = new ArrayList < Semester_Master >();
		      final XmlRpcClient authClient = new XmlRpcClient(); 
		         final XmlRpcClientConfigImpl authStartConfig = new XmlRpcClientConfigImpl(); 
		         authStartConfig.setServerURL(new URL(String.format("%s/xmlrpc/2/common", url))); 
		          
		     
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
		         objStartConfig.setServerURL(new URL(String.format("%s/xmlrpc/2/object", url))); 
		         objClient.setConfig(objStartConfig); 
		          
		        
		       // List<Object> a= Arrays.asList((Object[])objClient.execute("execute_kw", configList)); 
		     
		          try {
		   	  
		        	  final List get_Result=Arrays.asList((Object[])objClient.execute("execute_kw", Arrays.asList(
		        			    db, uid, password,
		        			    "op.semester", "search_read",
		        			    Arrays.asList(Arrays.asList(
		        			    		Arrays.asList("batch_id", "=", batch_id))),
		        			    		    //OR
		        			    		//Arrays.asList("customer", "=", true))),  //To bring the all value customer should be true
		        			    new HashMap() {{
		        			        put("fields", Arrays.asList("id","name","batch_id","start_date","end_date"));
		        			        //put("limit", 5);
		        			    }}
		        			)));
		        	  
		   	  
		        	  for (int i = 0; i < get_Result.size(); i++) {
		        		  
		  				Semester_Master semester = new Semester_Master();
		  				// HashMap complete_Result = (HashMap) get_Result.get(i);

		  				HashMap<String, Object> complete_Result = (HashMap<String, Object>) get_Result
		  						.get(i);

		  				Integer id = (Integer) complete_Result.get("id");
		  				semester.setId(id);
		  				 //System.out.print("ID "+ id);

		  				String name = (String) complete_Result.get("name");
		  				semester.setName(name);
		  				 //System.out.print("    Name "+ name);

		  				String start_date = (String) complete_Result.get("start_date");
		  				//Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
		  				semester.setStart_date(start_date);
		  				// System.out.print("    Start "+ start_date);

		  				String end_date = (String) complete_Result.get("end_date");
		  				//Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
		  				semester.setEnd_date(end_date);
		  				// System.out.print("    End "+ end_date);

		  				Object batch_id1 = (Object) complete_Result.get("batch_id"); 
		  				String batch_id2 = Arrays.deepToString((Object[]) batch_id1)
		  						.toString();
		  				// System.out.println("The object is: "+ batch_id1);
		  				// System.out.println("The batch2 is: "+ batch_id2);

		  				String batch_id3 = batch_id2.substring(1,
		  						batch_id2.length() - 1); // To trim bracket
		  				// System.out.println("The batch3 is: "+ batch_id3);

		  				// String batch_id9= batch_id3.replaceAll(" ", "  ");
		  				// System.out.println("The batch3 is: "+ batch_id9);

		  				String[] batch_id4 = batch_id3.split(",");
		  				int count = 0;
		  				for (String batch_id5 : batch_id4) {
		  					String batch_id6 = batch_id5.trim(); // Remove first space
		  					String batch_id7 = batch_id6.replaceAll(" ", "");
		  					count++;
		  					//if (count == 1)// Remove space
		  						
		  						//System.out.println(batch_id7);
		  				}
		  				// System.out.println("------------------------");
		  				semesters.add(semester);
		  			}
		        	  }

		        catch (XmlRpcException e) {
		            
		            System.err.println(e);
		            //e.printStackTrace();
		        }
		        
		          //for (Semester_Master s:semesters)
		 			 //System.out.println("bb"+s.getId()+"\t"+s.getName()+"\t"+s.getStart_date()+"\t"+s.getEnd_date());
				return semesters;
		 		 }
//=================================
	public String getSemesterByBatchId(int id) throws Exception
    {
    	String semester= new String();
        Transaction trns23 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns23 = session.beginTransaction();
            String queryString = "select semester from Batch_Master where odoo_id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",id);
            semester=(String)query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return semester;
        } finally {
            session.flush();
            session.close();
        }
        return semester;
    }
//===========================
	public String getCurrentSemester(List<Semester_Master> semesters) throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		Date today=new SimpleDateFormat("yyyy-MM-dd").parse(dtf.format(localDate));
		for(Semester_Master semester:semesters)
		{
			Date from=new SimpleDateFormat("yyyy-MM-dd").parse(semester.getStart_date());
			Date to=new SimpleDateFormat("yyyy-MM-dd").parse(semester.getEnd_date());
			if(semester.getName().contains("Semester"))
			{
	        if(today.after(from) && today.before(to)||today.equals(from)||today.equals(to))
	            return semester.getName();
			}
		}
		return null;
		
	}
	
	public int[] getMembersIdByProjectId(int project_id)
	{
		Transaction trns24 = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns24 = session.beginTransaction();
			String queryString = "select user_id from Project_Member where project_id =:project_id";
			Query query = session.createQuery(queryString);
			query.setInteger("project_id", project_id);
			List user_id = query.list();
			int size = user_id.size();
			int[] members = new int[size];
			Integer[] temp = (Integer[]) user_id.toArray(new Integer[size]);
			for (int n = 0; n < size; ++n) 
			    members[n] = temp[n];
			return members;
			}
		 catch (RuntimeException e) {
				e.printStackTrace();
			} finally {
				session.flush();
				session.close();
			}
		return null;

	}
//=============To find an element in array==============================
	public static boolean contains(int[] arr, int item) {
	      int index = Arrays.binarySearch(arr, item);
	      return index >= 0;
	   }
	public Project_Member getAMemberById (int id, int project_id){
		Transaction trns25 = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns25 = session.beginTransaction();
			String queryString = "from Project_Member where user_id =:id and project_id =:project_id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			query.setInteger("project_id", project_id);
			Project_Member member = (Project_Member) query.uniqueResult();
			return member;
		}
		catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return null;
		
	}
	public String getKitPointByProjectId(int project_id)
	{
		String point = null;
        Transaction trns26 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns26 = session.beginTransaction();
            String queryString = "select kit_point from Project_Master where id=:project_id";
            Query query = session.createQuery(queryString);
            query.setInteger("project_id",project_id);
            point=(String)query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return point;
        } finally {
            session.flush();
            session.close();
        }
        return point;
	}
    
}



=======
package com.DaoClasses;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.EntityClasses.UserRole;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.ProjectView_Model;
import com.ModelClasses.Project_Model;
import com.ModelClasses.Task_Model;
import com.ModelClasses.retrieve;
import com.ModelClasses.submit;
//import com.ServiceClasses.usersService;



@Repository
public class userDaoImpl implements usersDao{
	
	Encryption encrypt= new Encryption();
	Decryption decrypt= new Decryption();
	
	
	
	//===================For SS========================================  
    
    public User_Info findByUserName(String username) {
    	Transaction trns1 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
		List<User_Info> users = new ArrayList<User_Info>();
	
		try {
            trns1 = session.beginTransaction();
            users = session.createQuery("from User_Info where email=?").setParameter(0, username).list();
            		//setParameter(0, username).list();
            
            if (users.size() > 0) {
    			return users.get(0);
    		} else {
    			return null;
    		}
        } catch (RuntimeException e) {
        	
        }                         
		return null;
	}
    
    
  //=================================================================    

	
	
	
    public boolean addUser2(User_Info user) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Timestamp created_at= new Timestamp(System.currentTimeMillis()); 
        String password=user.getPassword();
        String email=user.getEmail();
        String user_type=user.getUser_type();
        String username= user.getName();
        try {

        	String queryString = "from User_Info where email= :email";
            Query query = session.createQuery(queryString);
            query.setString("email",email );
            List<User_Info> userDataBase=query.list();
        	if (userDataBase.size()==0){
        		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        		String hashedPassword = passwordEncoder.encode(password);
	            User_Info userData =new User_Info();
	            userData.setName(username);
	            userData.setEmail(email);
	            userData.setEnabled(true);
	            userData.setPassword(hashedPassword);
	            userData.setCreated_at(created_at);
	            UserRole user_role= new UserRole();
	            user_role.setRole(user_type);
	            user_role.setCreated_at(created_at);
	            user_role.setUser_info(userData);
	            transaction = session.beginTransaction();
	            Set<UserRole> userrole= new HashSet<UserRole>();
	            userrole.add(user_role);
	            userData.setUserRole(userrole);
	            session.save(userData);
	            transaction.commit();
	            transaction = session.beginTransaction();
	            session.save(user_role);
	            transaction.commit();
            return true;
        	}
        	else{
        		return false;
        	}
        } catch (RuntimeException e) {
            if (transaction != null) {
            	transaction.rollback();
            }
            return false;
        } catch (Exception e) {
			return false;
		} finally {
            session.close();
        }
		
    }
    
    
  //===================Get a list of user=================================
    public List<User_Info> getAllUser() {
    	List<User_Info> users= new ArrayList<User_Info>();
        Transaction trns2 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns2 = session.beginTransaction();
            users = session.createQuery("select id,name,email,created_at,updated_at from User_Info").list();
            
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return users;
    } 
//=====================get member by project id=========================================
    public List<Project_Member> getMemberByProjectId(int id) {
        List<Project_Member> members= new ArrayList<Project_Member>();
        Transaction trns3 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns3 = session.beginTransaction();
            String queryString = "from Project_Member where project_id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",id);
            members = query.list();
            
        } catch (RuntimeException e) {
            e.printStackTrace();
            return members;
        } finally {
            session.flush();
            session.close();
        }
        return members;
    }
//=================get A project id by task id==========================
    public int getProjectIdByTaskId(int taskId){
    	int projectId=0;
    	Transaction trns4 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns4 = session.beginTransaction();
            String queryString = "select project_id from Task_Master where id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",taskId);
            projectId=(Integer)query.uniqueResult();
           
//            SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
//		    String decryptedPoint;
//		    decryptedPoint = decrypt.decryptText(project.getKit_point(), secKey);
//		    decryptedPoint.trim();
//		    project.setKit_point(decryptedPoint);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return projectId;
        } finally {
            session.flush();
            session.close();
        }
        return projectId;		
	}
    public List<Project_Master> getAllProject() {
        List<Project_Master> projects= new ArrayList<Project_Master>();
        Transaction trns5 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns5 = session.beginTransaction();
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

//=========================To validate user whilw loging in====================================================    
     public User_Info validate(User_Info user) throws Exception
    {
    	Transaction trns6 = null;
        
        String email= user.getEmail();
        String password= user.getPassword();
        
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns6 = session.beginTransaction();
            String queryString = "from User_Info where email = :email and password = :password";
            Query query = session.createQuery(queryString);
            query.setString("email", email);
            //SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
   		 	//String encryptedPassword =encrypt.encryptText(password, secKey) ;
            query.setString("password", password);
            
            user = (User_Info) query.uniqueResult();
            //System.out.println("Try "+user);
			
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
    	Transaction trns7 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns7 = session.beginTransaction();
            String queryString = "FROM Batch_Master where name=:name";
            Query query = session.createQuery(queryString);
            query.setString("name",batch.getName());
            batch=(Batch_Master)query.uniqueResult();
         
    			if(batch!=null)
    				return false;
    		Timestamp created_at = new Timestamp(System.currentTimeMillis());
        	batch2.setCreated_at(created_at);
            session.save(batch2);  
            session.getTransaction().commit();
        } catch (RuntimeException e) {
        	if (trns7 != null) {
                trns7.rollback();
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
    	Transaction trns8 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns8 = session.beginTransaction();
            String queryString = "FROM Batch_Master where id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",batch.getId());
            Batch_Master batch2 = new Batch_Master();
            batch2=(Batch_Master)query.uniqueResult();
    		Timestamp updated_at = new Timestamp(System.currentTimeMillis());
        	batch2.setUpdated_at(updated_at);
        	//batch2.setSemester_id(batch.getSemester_id());
        	session.update(batch2);  
            session.getTransaction().commit();
        } catch (RuntimeException e) {
        	if (trns8 != null) {
                trns8.rollback();
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
    	Transaction trns9 = null;
        
    	Project_Category_Master projectCategory2 = projectCategory;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns9 = session.beginTransaction();
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
        	if (trns9 != null) {
                trns9.rollback();
            }
            e.printStackTrace();
           
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
    	Transaction trns10 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	Task_Master tm2;
            trns10 = session.beginTransaction();
            String queryString = "FROM Task_Master where name=:name and project_id=:project_id";
            Query query = session.createQuery(queryString);
            query.setString("name",t.getName());
            query.setInteger("project_id", t.getProject_id());
            tm2=(Task_Master)query.uniqueResult();
    			if(tm2!=null)
    				return false;
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
        	if (trns10 != null) {
                trns10.rollback();
            }
            e.printStackTrace();
           
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
    	Transaction trns11 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	Project_Master pm2;
            trns11 = session.beginTransaction();
            String queryString = "FROM Project_Master where project_name=:name";
            Query query = session.createQuery(queryString);
            query.setString("name",project.getProject_name());
            pm2=(Project_Master)query.uniqueResult();
    			if(pm2!=null)
    				return 0;
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
        	if (trns11 != null) {
                trns11.rollback();
            }
            e.printStackTrace();
          
            return 0;
        } finally {
            session.flush();
            session.close();
        }
        
    }
//=================================Update project=========================================
    public boolean updateProject(Project_Model project) throws Exception
    {
    	int count=0;
    	Transaction trns12 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	
            trns12 = session.beginTransaction();
            List<Project_Master> projects= new ArrayList<Project_Master>();
            projects = getAllProject();
            for(Project_Master p:projects)
            {
            	if(project.getId()!=p.getId())
            			{
            				if (p.getProject_name().equals(project.getProject_name()))
            					count++;
            			}
            }
            if(count>=1)
            	return false;
    		Timestamp updated_at = new Timestamp(System.currentTimeMillis());
    		Date start_date = new SimpleDateFormat("MM/dd/yyyy").parse(project.getStart_date());
    		Date end_date = new SimpleDateFormat("MM/dd/yyyy").parse(project.getEnd_date());
    		Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(project.getDeadline());
    		
    		  int old[] = getMembersIdByProjectId(project.getId());
    		  int newa [] = project.getMember();
    		  List<Integer> myList = new ArrayList<Integer>();
    		   Arrays.sort(newa);
    		   Arrays.sort(old);
       		   for (int i = 0; i < old.length; i++)
    		   {
    		    if(contains(newa,old[i]))
    		     {
    		     }
    		    else{
    		      session.delete(getAMemberById(old[i],project.getId()));
    		     }
    		   }
    		   for (int i = 0; i < newa.length; i++)
    		   {
    		    if(contains(old,newa[i]))
    		     {
    		     }
    		    else{
    		    	myList.add(newa[i]);
    		     }
    		   }
    		   int size = myList.size();
    		   int[] save = new int[size];
    		   Integer[] temp = myList.toArray(new Integer[size]);
    		   for (int n = 0; n < size; ++n) 
    		       save[n] = temp[n];
   		    saveMember(project.getId(),save);
    		
    		Project_Master pm = new Project_Master();
    	
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
        	if (trns12 != null) {
                trns12.rollback();
            }
            e.printStackTrace();
          
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
    	Transaction trns13 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	
            trns13 = session.beginTransaction();
            String queryString = "FROM Task_Master where project_id =:project_id and name=:name";
            Query query = session.createQuery(queryString);
            query.setInteger("project_id",t.getProject_id());
            query.setString("name", t.getName());
            Task_Master t2 = (Task_Master)query.uniqueResult();
    			if(t2!=null)
    				return false;
    		Timestamp updated_at = new Timestamp(System.currentTimeMillis());
    		Date start_date = new SimpleDateFormat("MM/dd/yyyy").parse(t.getStart_date());
    		Date end_date = new SimpleDateFormat("MM/dd/yyyy").parse(t.getEnd_date());
    		Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(t.getDeadline());
    		Task_Master tm = new Task_Master();
  
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
        	if (trns13 != null) {
                trns13.rollback();
            }
            e.printStackTrace();
         
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
    	Transaction trns14 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Timestamp created_at = new Timestamp(System.currentTimeMillis());
        try {
        	
            trns14 = session.beginTransaction();
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
        	if (trns14 != null) {
                trns14.rollback();
            }
            e.printStackTrace();
           
        } finally {
            session.flush();
            session.close();
        }
       
    }

//===================Get a list of semesters=================================
    public List<Semester_Master> getAllSemester() {
        List<Semester_Master> semesters= new ArrayList<Semester_Master>();
        Transaction trns15 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns15 = session.beginTransaction();
            semesters = session.createQuery("from Semester_Master").list();
            
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
        Transaction trns16 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns16 = session.beginTransaction();
            p = session.createQuery("from Project_Category_Master").list();

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
        Transaction trns17 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns17 = session.beginTransaction();
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

//===========================Get all task===================================
    public List<Task_Master> getAllTask()
    {
    	List<Task_Master> tasks= new ArrayList<Task_Master>();
        Transaction trns18 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns18 = session.beginTransaction();
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
    	Project_Master project= new Project_Master();
        Transaction trns19 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns19 = session.beginTransaction();
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
        Transaction trns20 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns20 = session.beginTransaction();
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


	public static Map<Integer,Integer> getBatch(int student[]) throws MalformedURLException, XmlRpcException {
		List<Student> students = new StudentFromOdoo_BatchId().getStudent_BatchId();
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();  
		for (int i =0; i<students.size();i++)
		{
			for(int j=0; j<student.length;j++)
			if(students.get(i).getId().equals(Integer.toString(student[j])))
			{
				map.put(student[j],Integer.parseInt(students.get(i).getBatch_id()));
			}
		}
		return map;
		
	}
    
  //=======================
	public Map<Integer, String> getStudentSemester(int arr[]) throws Exception{
		Map<Integer, Integer> batch = new HashMap<Integer, Integer>();
		Map<Integer, String> semester = new HashMap<Integer, String>();
		
		List<Student> students = new StudentFromOdoo_BatchId().getStudent_BatchId();

		for (int i = 0; i < students.size(); i++) {
			for (int j = 0; j < arr.length; j++) {
				if ((Integer.parseInt(students.get(i).getId())) == arr[j]) {
					batch.put(arr[j],Integer.parseInt(students.get(i).getBatch_id()));
				}
			}

		}
		String s_semester = new String();
		for (Map.Entry<Integer, Integer> entry : batch.entrySet()) {
			s_semester = new userDaoImpl().getSemesterByBatchId(entry.getValue());
			semester.put(entry.getKey(), s_semester+","+entry.getValue());
		}
		return semester;
		}
//=====================Point calculation==============================
	public Map<String, Float> pointCalculation(Map<Integer, String> mm, int t) throws Exception {
		Map<String, Float> map = new HashMap<String, Float>();
		float time = (float)t/mm.size();
		float sum= 0f;
		SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
		String decryptedText;
		for (Map.Entry<Integer, String> entry : mm.entrySet())
		{
			String str[]=entry.getValue().split(",");
			int batch_id = Integer.parseInt(str[1]);
			String semester_name=str[0];
			Transaction trns21 = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns21 = session.beginTransaction();
	            String queryString = "select value from Value_Per_Hour where batch_id=:batch_id and semester_name=:semester_name";
	            Query query = session.createQuery(queryString);
	            query.setInteger("batch_id",batch_id);
	            query.setString("semester_name",semester_name);
	         
	            String value=(String)query.uniqueResult();
	            decryptedText = decrypt.decryptText(value, secKey);
	            //System.out.println(time + " * "+ decryptedText +" = "+(Integer.parseInt(decryptedText)*time));
	            sum = (float)sum + Float.parseFloat(decryptedText)*time;
	    		
	        } catch (RuntimeException e) {
	            e.printStackTrace();
	        } finally {
	            session.flush();
	            session.close();
	        }
			
		}
		String pointValue = new userDaoImpl().getKitPoint();
		String decryptedPointValue = decrypt.decryptText(pointValue, secKey);
		map.put("budget", sum);
		map.put("point", sum/Float.parseFloat(decryptedPointValue));
		return map;
		
		
	}
//========================Retrieve KIT Point from DB====================================
	public String getKitPoint(){
		String pointValue= new String();
        Transaction trns22 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns22 = session.beginTransaction();
            String queryString = "select value from KIT_Point";
            Query query = session.createQuery(queryString);
            pointValue=(String)query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return pointValue;
        } finally {
            session.flush();
            session.close();
        }
        return pointValue;
	}
//===========================
	public List<Semester_Master> getStudent_Semester(int batch_id) throws XmlRpcException,
	MalformedURLException, ParseException {
		  final String url = "http://96.9.67.154:8070"; 
		      final String db = "Kirirom_Institute_of_Technology"; 
		      final String username ="admin"; 
		      final String password = "admin"; 
		       
		      List<Semester_Master> semesters = new ArrayList < Semester_Master >();
		      final XmlRpcClient authClient = new XmlRpcClient(); 
		         final XmlRpcClientConfigImpl authStartConfig = new XmlRpcClientConfigImpl(); 
		         authStartConfig.setServerURL(new URL(String.format("%s/xmlrpc/2/common", url))); 
		          
		     
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
		         objStartConfig.setServerURL(new URL(String.format("%s/xmlrpc/2/object", url))); 
		         objClient.setConfig(objStartConfig); 
		          
		        
		       // List<Object> a= Arrays.asList((Object[])objClient.execute("execute_kw", configList)); 
		     
		          try {
		   	  
		        	  final List get_Result=Arrays.asList((Object[])objClient.execute("execute_kw", Arrays.asList(
		        			    db, uid, password,
		        			    "op.semester", "search_read",
		        			    Arrays.asList(Arrays.asList(
		        			    		Arrays.asList("batch_id", "=", batch_id))),
		        			    		    //OR
		        			    		//Arrays.asList("customer", "=", true))),  //To bring the all value customer should be true
		        			    new HashMap() {{
		        			        put("fields", Arrays.asList("id","name","batch_id","start_date","end_date"));
		        			        //put("limit", 5);
		        			    }}
		        			)));
		        	  
		   	  
		        	  for (int i = 0; i < get_Result.size(); i++) {
		        		  
		  				Semester_Master semester = new Semester_Master();
		  				// HashMap complete_Result = (HashMap) get_Result.get(i);

		  				HashMap<String, Object> complete_Result = (HashMap<String, Object>) get_Result
		  						.get(i);

		  				Integer id = (Integer) complete_Result.get("id");
		  				semester.setId(id);
		  				 //System.out.print("ID "+ id);

		  				String name = (String) complete_Result.get("name");
		  				semester.setName(name);
		  				 //System.out.print("    Name "+ name);

		  				String start_date = (String) complete_Result.get("start_date");
		  				//Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
		  				semester.setStart_date(start_date);
		  				// System.out.print("    Start "+ start_date);

		  				String end_date = (String) complete_Result.get("end_date");
		  				//Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
		  				semester.setEnd_date(end_date);
		  				// System.out.print("    End "+ end_date);

		  				Object batch_id1 = (Object) complete_Result.get("batch_id"); 
		  				String batch_id2 = Arrays.deepToString((Object[]) batch_id1)
		  						.toString();
		  				// System.out.println("The object is: "+ batch_id1);
		  				// System.out.println("The batch2 is: "+ batch_id2);

		  				String batch_id3 = batch_id2.substring(1,
		  						batch_id2.length() - 1); // To trim bracket
		  				// System.out.println("The batch3 is: "+ batch_id3);

		  				// String batch_id9= batch_id3.replaceAll(" ", "  ");
		  				// System.out.println("The batch3 is: "+ batch_id9);

		  				String[] batch_id4 = batch_id3.split(",");
		  				int count = 0;
		  				for (String batch_id5 : batch_id4) {
		  					String batch_id6 = batch_id5.trim(); // Remove first space
		  					String batch_id7 = batch_id6.replaceAll(" ", "");
		  					count++;
		  					//if (count == 1)// Remove space
		  						
		  						//System.out.println(batch_id7);
		  				}
		  				// System.out.println("------------------------");
		  				semesters.add(semester);
		  			}
		        	  }

		        catch (XmlRpcException e) {
		            
		            System.err.println(e);
		            //e.printStackTrace();
		        }
		        
		          //for (Semester_Master s:semesters)
		 			 //System.out.println("bb"+s.getId()+"\t"+s.getName()+"\t"+s.getStart_date()+"\t"+s.getEnd_date());
				return semesters;
		 		 }
//=================================
	public String getSemesterByBatchId(int id) throws Exception
    {
    	String semester= new String();
        Transaction trns23 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns23 = session.beginTransaction();
            String queryString = "select semester from Batch_Master where odoo_id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",id);
            semester=(String)query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return semester;
        } finally {
            session.flush();
            session.close();
        }
        return semester;
    }
//===========================
	public String getCurrentSemester(List<Semester_Master> semesters) throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		Date today=new SimpleDateFormat("yyyy-MM-dd").parse(dtf.format(localDate));
		for(Semester_Master semester:semesters)
		{
			Date from=new SimpleDateFormat("yyyy-MM-dd").parse(semester.getStart_date());
			Date to=new SimpleDateFormat("yyyy-MM-dd").parse(semester.getEnd_date());
			if(semester.getName().contains("Semester"))
			{
	        if(today.after(from) && today.before(to)||today.equals(from)||today.equals(to))
	            return semester.getName();
			}
		}
		return null;
		
	}
	
	public int[] getMembersIdByProjectId(int project_id)
	{
		Transaction trns24 = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns24 = session.beginTransaction();
			String queryString = "select user_id from Project_Member where project_id =:project_id";
			Query query = session.createQuery(queryString);
			query.setInteger("project_id", project_id);
			List user_id = query.list();
			int size = user_id.size();
			int[] members = new int[size];
			Integer[] temp = (Integer[]) user_id.toArray(new Integer[size]);
			for (int n = 0; n < size; ++n) 
			    members[n] = temp[n];
			return members;
			}
		 catch (RuntimeException e) {
				e.printStackTrace();
			} finally {
				session.flush();
				session.close();
			}
		return null;

	}
//=============To find an element in array==============================
	public static boolean contains(int[] arr, int item) {
	      int index = Arrays.binarySearch(arr, item);
	      return index >= 0;
	   }
	public Project_Member getAMemberById (int id, int project_id){
		Transaction trns25 = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns25 = session.beginTransaction();
			String queryString = "from Project_Member where user_id =:id and project_id =:project_id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			query.setInteger("project_id", project_id);
			Project_Member member = (Project_Member) query.uniqueResult();
			return member;
		}
		catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return null;
		
	}
	public String getKitPointByProjectId(int project_id)
	{
		String point = null;
        Transaction trns26 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns26 = session.beginTransaction();
            String queryString = "select kit_point from Project_Master where id=:project_id";
            Query query = session.createQuery(queryString);
            query.setInteger("project_id",project_id);
            point=(String)query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return point;
        } finally {
            session.flush();
            session.close();
        }
        return point;
	}
    
}



>>>>>>> d89376ff1246680561c70d7078f51fe0936dba15
