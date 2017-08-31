package com.ServiceClasses;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.DaoClasses.usersDao;
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



@Service
public class usersServiceImpl implements usersService{

	@Autowired
	usersDao usersDao1;
	
	
	public User_Info findByUserName(String name) {
		return usersDao1.findByUserName(name);
	}
	
	public boolean addUser2(User_Info users) {
		return usersDao1.addUser2(users);
	}
	public List<User_Info> getAllUser(){
		return usersDao1.getAllUser();
	}
	public List<Project_Member> getMemberByProjectId(int id){
		return usersDao1.getMemberByProjectId(id);
	}
	public List<Project_Master> getAllProject(){
		return usersDao1.getAllProject();
	}
	public User_Info validate(User_Info user) throws Exception{
		return usersDao1.validate(user);
	}
	public boolean saveBatch(Batch_Master batch){
		return usersDao1.saveBatch(batch);
	}
	public boolean updateBatch(Batch_Master batch){
		return usersDao1.updateBatch(batch);
	}
	public boolean createProjectCategory(Project_Category_Master projectCategory){
		return usersDao1.createProjectCategory(projectCategory);
	}
	public boolean toSaveTask(Task_Model t) throws ParseException{
		return usersDao1.toSaveTask(t);
	}
	public int saveProject(Project_Model project) throws Exception
	{
		return usersDao1.saveProject(project);
	}
	public boolean updateProject(Project_Model project) throws Exception{
		return usersDao1.updateProject(project);
	}
	public boolean updateTask(Task_Model t) throws ParseException{
		return usersDao1.updateTask(t);
	}
	public void saveMember(int projectid, int arr[]) throws MalformedURLException, XmlRpcException{
		usersDao1.saveMember(projectid, arr);
	}
	public List<Semester_Master> getAllSemester(){
		return usersDao1.getAllSemester();
	}
	public List<Project_Category_Master> getProjectCategories(){
		return usersDao1.getProjectCategories();
	}
	public List<Batch_Master> getAllBatch(){
		return usersDao1.getAllBatch();
	}
	public List<Project_Stage_Master> getAllStages(){
		return usersDao1.getAllStages();
	}
	public List<Task_Master> getAllTask(){
		return usersDao1.getAllTask();
	}
	public Project_Master getProjectById(int id) throws Exception{
		return usersDao1.getProjectById(id);
	}
	public Task_Master getTaskById(int id){
		return usersDao1.getTaskById(id);
	}
	public int getProjectIdByTaskId(int taskId){
		return usersDao1.getProjectIdByTaskId(taskId);
	}
	public Map<Integer, String> getStudentSemester(int arr[]) throws Exception{
		return usersDao1.getStudentSemester(arr);
	}
	public float pointCalculation(Map<Integer, String> mm, int t) throws Exception{
		return usersDao1.pointCalculation(mm,t);
	}
	public String getKitPoint(){
		return usersDao1.getKitPoint();
	}
	public List<Semester_Master> getStudent_Semester(int batch_id) throws XmlRpcException,
	MalformedURLException, ParseException{
		return usersDao1.getStudent_Semester(batch_id);
	}
	public String getSemesterByBatchId(int id) throws Exception{
		return usersDao1.getSemesterByBatchId(id);
	}
	public String getCurrentSemester(List<Semester_Master> semesters) throws ParseException{
		return usersDao1.getCurrentSemester(semesters);
	}
}
