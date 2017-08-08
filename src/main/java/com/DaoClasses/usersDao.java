package com.DaoClasses;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;














import org.apache.xmlrpc.XmlRpcException;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Category_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Project_Member;
import com.EntityClasses.Project_Stage_Master;
import com.EntityClasses.Semester_Master;
import com.EntityClasses.Task_Master;
import com.EntityClasses.User_Info;
import com.ModelClasses.Project_Model;
import com.ModelClasses.Task_Model;
import com.ModelClasses.retrieve;
import com.ModelClasses.submit;


public interface usersDao {

	public boolean addUser2(User_Info users);
	public List<User_Info> getAllUser();
	public List<Project_Member> getMemberByProjectId(int id);
	public List<Project_Master> getAllProject();
	public User_Info validate(User_Info user) throws Exception;
	public boolean saveBatch(Batch_Master batch);
	public boolean updateBatch(Batch_Master batch);
	public boolean createProjectCategory(Project_Category_Master projectCategory);
	public boolean toSaveTask(Task_Model t) throws ParseException;
	public int saveProject(Project_Model project) throws Exception;
	public boolean updateProject(Project_Model project) throws Exception;
	public boolean updateTask(Task_Model t) throws ParseException;
	public void saveMember(int projectid, int arr[]) throws MalformedURLException, XmlRpcException;
	public List<Semester_Master> getAllSemester();
	public List<Project_Category_Master> getProjectCategories();
	public List<Batch_Master> getAllBatch();
	public List<Project_Stage_Master> getAllStages();
	public List<Task_Master> getAllTask();
	public Project_Master getProjectById(int id) throws Exception;
	public Task_Master getTaskById(int id);
    
    
    
}
