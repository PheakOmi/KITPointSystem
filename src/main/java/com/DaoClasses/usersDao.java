package com.DaoClasses;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;















import java.util.Map;

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



public interface usersDao {
	
	
	public User_Info findByUserName(String name);

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
	public List<Task_Master> getAllTask();
	public Project_Master getProjectById(int id) throws Exception;
	public Task_Master getTaskById(int id);
	public int getProjectIdByTaskId(int taskId);
	public Map<Integer, String> getStudentSemester(int[] arr) throws Exception;
	public Map<String, Float> pointCalculation(Map<Integer, String> mm, int t) throws Exception;
	public String getKitPoint();
	public List<Semester_Master> getStudent_Semester(int batch_id) throws XmlRpcException, MalformedURLException, ParseException;
	public String getSemesterByBatchId(int id) throws Exception;
	public String getCurrentSemester(List<Semester_Master> semesters) throws ParseException;
	public int[] getMembersIdByProjectId(int project_id);
	public Project_Member getAMemberById (int id, int project_id);
	public String getKitPointByProjectId (int project_id);
}
