package com.MainController;


import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.DaoClasses.StudentFromOdoo_BatchId;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Category_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Project_Member;
import com.EntityClasses.Semester_Master;
import com.EntityClasses.Student;
import com.EntityClasses.Task_Master;
import com.EntityClasses.User_Info;
import com.ModelClasses.Project_Model;
import com.ModelClasses.Task_Model;
import com.ServiceClasses.usersService;



@Controller
//@RequestMapping("users")
public class ControllerFile {
		
	@Autowired
	usersService usersService1;	
	
//	=================project============================
	@RequestMapping(value="/project", method=RequestMethod.GET)
	public ModelAndView viewProject() {
		String message = "Hello World";
		return new ModelAndView("project", "message", message);
	}


// ================================Login Validate================================================	
		@RequestMapping(value="/validate", method=RequestMethod.GET)
		public ModelAndView toValidate(User_Info user, BindingResult result) throws Exception
		{
			if(result.hasErrors())
			   {
				ModelAndView model2 = new ModelAndView("login");  //if it is error send it back to retrieve.jsp 
				model2.addObject("message", "Enter the correct format");  
				return model2;	
			   }
			user = usersService1.validate(user);
			if(user!=null)
				{return new ModelAndView("project");}
			else 
			{	
				ModelAndView model = new ModelAndView("login");
				model.addObject("message","Please input the correct username and password");
				return model;
			}
		}
//	=================projectDetail============================
	@RequestMapping(value="/projectDetail", method=RequestMethod.GET)
	public ModelAndView viewProjectdetail() {
		//String message = "Hello World";
		return new ModelAndView("projectDetail");
	}
//============================Retreive all project category from DB send through Ajax========================
			@RequestMapping(value="/projectCategoryList", method=RequestMethod.GET)
			public @ResponseBody Map<String,Object> getProjectCategoryList(){
						
				 Map<String,Object> map = new HashMap<String,Object>();
			
				   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
					List<Project_Category_Master> list = usersService1.getProjectCategories();
					 		
					if (list != null)
						map.put("data", list);
					else
						map.put("message","Data not found");			
					
					return map;
			}
//============================Get Project AND Task========================
			@RequestMapping(value="/ProjectNTask", method=RequestMethod.GET)
			public @ResponseBody Map<String,?> getProjectNTask(){
						
				 Map<String,List> map = new HashMap<String,List>();
				 Map<String,Object> error = new HashMap<String,Object>();
				   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
					List<Project_Master> listProject = usersService1.getAllProject();
					List<Task_Master> listTask = usersService1.getAllTask();
					 		
					if (listProject == null || listTask == null)
						{
							error.put("message","Data not found");
							return error;
						}
						
					else
						{
							map.put("task", listTask);
							map.put("project", listProject);
							return map;
						}	
					}
			
//============================Retreive all users and ProjectCategory from DB send through Ajax========================
			@RequestMapping(value="/userNProjectCategoryList", method=RequestMethod.GET)
			public @ResponseBody Map<?,?> getUserNProjectCategoryListNStage(@RequestParam(value = "id", required=false, defaultValue = "0") Integer id) throws Exception{
				Project_Master project = usersService1.getProjectById(id);
				 Map<String,Object> map = new HashMap<String,Object>();
				 Map<String,Object> error = new HashMap<String,Object>();
					List<Project_Category_Master> listProjectCategory = usersService1.getProjectCategories();
					List<User_Info> listUser = usersService1.getAllUser();
					List<Student> student = new StudentFromOdoo_BatchId().getStudent_BatchId();
					//List<Project_Stage_Master> listProjectStage = userDaoImpl.getAllStages();	
										
					if (listProjectCategory == null || listUser == null)
						{
							error.put("message","Data not found");
							return error;
						}
						
					else
						{
							map.put("category", listProjectCategory);
							map.put("user", listUser);
							map.put("student",student);
							if(project!=null){
							map.put("currentproject",project);
							int members[] = usersService1.getMembersIdByProjectId(project.getId());
							map.put("member", members);
							}
							return map;
						}	
					}
						 
						 
//=======================get right students base on project. To be used in Task=================================
   @RequestMapping(value="/studentInTask", method=RequestMethod.GET)
   public @ResponseBody Map<?,?> getRightStudent(@RequestParam(value = "id", required=false,defaultValue = "0") Integer projectid) throws ParseException, MalformedURLException, XmlRpcException{
     Map<String,Object> map = new HashMap<String,Object>();
     Map<String,Object> error = new HashMap<String,Object>();
       // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
     List<Project_Member> listMember = usersService1.getMemberByProjectId(projectid);
     if (listMember == null)
      {
       error.put("message","Data not found");
       return error;
      }
     else
      {
       map.put("student", listMember);
       return map;
      }
     }   
			 
						 
						 
						 
//=======================get Project and User===========================
			@RequestMapping(value="/ProjectNUser", method=RequestMethod.GET)
			public @ResponseBody Map<?,?> getProjectNUser(@RequestParam(value = "id", required=false,defaultValue = "0") Integer id) throws Exception{				
				 Map<String,Object> map = new HashMap<String,Object>();
				 Map<String,Object> error = new HashMap<String,Object>();
				   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
				if (id==0)	
				 {
					List<Project_Master> listProject = usersService1.getAllProject();
					if (listProject == null)
						{
							error.put("message","Data not found");
							return error;
						}
					else
						{
							map.put("project", listProject);
							return map;
						}	
				}
				
				else
				{
					Task_Master currenttask = usersService1.getTaskById(id);
					int projectId = usersService1.getProjectIdByTaskId(id);
					List<Project_Member> listMember = usersService1.getMemberByProjectId(projectId);
					Project_Master project = usersService1.getProjectById(projectId);
					if (currenttask == null||listMember==null)
					{
						error.put("message","Data not found");
						return error;
					}
				else
					{
						map.put("currenttask", currenttask);
						map.put("member", listMember);
						map.put("project", project);
						return map;
					}	
				}
}
			@RequestMapping(value="/deleteProjectDetail", method = RequestMethod.GET)
			public @ResponseBody Map<String,String>  deleteProjectDetail(@RequestParam(value = "id", required=false) Integer id){
				Map<String,String> map = new HashMap<String,String>();
				int status = 0;
				status= usersService1.deleteProjectDetail(id);
				if(status==1)
					map.put("status", "200");
				else 	
					map.put("status", "300");
				
				return map;
				}
//========================Save Project========================================================
			@RequestMapping(value="/saveProject", method=RequestMethod.GET)
			public @ResponseBody Map<String,Object> toSaveProject(Project_Model pm) throws Exception{
					DecimalFormat df2 = new DecimalFormat(".##");
					Map<String, Float> pointNbudget = new HashMap<String, Float>();
					int[] m = pm.getMember();
					for(int i=0;i<m.length;i++)
						System.out.println(m[i]);
					Map<String,Object> map = new HashMap<String,Object>();
					Map<Integer, String> mm = usersService1.getStudentSemester(m);
					pointNbudget =usersService1.pointCalculation(mm,pm.getInitially_planned());
					if (pointNbudget.containsKey("No Value"))
							{
								int batch_id = Math.round(pointNbudget.get("batch_id"));
								String batch = new String();
								Set<String> keys = pointNbudget.keySet();								
								Batch_Master b = usersService1.getBatchById(batch_id);
								batch  =b.getName();
								map.put("status", "555");
								map.put("message","Please Create Value Per Hour for "+batch);
								return map;
							}
					else if(pointNbudget.containsKey("No Point"))
					{
						map.put("status", "888");
						map.put("message","Please create KIT point value first!");
						return map;
					}
					//pm.setKit_point(Float.toString(pointNbudget.get("point")));
					pm.setKit_point(df2.format(pointNbudget.get("point")));
					pm.setBudget(Math.round(pointNbudget.get("budget")));
					int id = usersService1.saveProject(pm);
					if(id!=0)
						
					{
						usersService1.saveMember(id,m);
						map.put("status","200");
						map.put("message","Your record has been saved successfully");
						
						return map;
					}
					else {
						map.put("status","999");
						map.put("message","Failed");
						return map;
					}
				}
//========================Update Project========================================================
			@RequestMapping(value="/updateProject", method=RequestMethod.GET)
			public @ResponseBody Map<String,Object> toUpdateProject(Project_Model pm) throws Exception{
	        		//int[] s = pm.getStage();
					DecimalFormat df2 = new DecimalFormat(".##");
					Project_Master project = new Project_Master();
					Map<String,Object> map = new HashMap<String,Object>();	
					Map<Integer, String> mm = usersService1.getStudentSemester(pm.getMember());
					project = usersService1.getProjectById(pm.getId());
					int newa[] = pm.getMember();
					Arrays.sort(newa);
					int old[] = usersService1.getMembersIdByProjectId(pm.getId());
			        Arrays.sort(old);
					if (Arrays.equals(newa, old)&&pm.getInitially_planned()==project.getInitially_planned())
					{
						
					}
					else{
						Map<String, Float> pointNbudget = null;
						pointNbudget =usersService1.pointCalculation(mm,pm.getInitially_planned());
						if (pointNbudget.containsKey("No Value"))
						{
							int batch_id = Math.round(pointNbudget.get("batch_id"));
							String batch = new String();
							Set<String> keys = pointNbudget.keySet();								
							Batch_Master b = usersService1.getBatchById(batch_id);
							batch  =b.getName();
							map.put("status", "555");
							map.put("message","Please Create Value Per Hour for "+batch);
							return map;
						}
						else if(pointNbudget.containsKey("No Point"))
						{
							map.put("status", "888");
							map.put("message","Please create KIT point value first!");
							return map;
						}
						pm.setKit_point(df2.format(pointNbudget.get("point")));
						pm.setBudget(Math.round(pointNbudget.get("budget")));
					}
					if(usersService1.updateProject(pm))
					{
						map.put("status","200");
						map.put("message","Your record has been saved successfully");
						return map;
					}
					else {
						
						map.put("status","999");
						map.put("message","Project name already existed");
						return map;
					}
				}
//========================Update Task========================================================
			@RequestMapping(value="/updateTask", method=RequestMethod.GET)
			public @ResponseBody Map<String,Object> toUpdateTask(Task_Model tm) throws ParseException{
	        		//int[] s = pm.getStage();
					Map<String,Object> map = new HashMap<String,Object>();				
					if(usersService1.updateTask(tm))
					{
						map.put("status","200");
						map.put("message","Your record has been saved successfully");
						return map;
					}
					else {
						map.put("status","999");
						map.put("message","Name already existed");
						return map;
					}
				}
//	=================taskDetails============================
	@RequestMapping(value="/taskDetail", method=RequestMethod.GET)
	public ModelAndView viewTaskdetail() {
		//String message = "Hello World";
		return new ModelAndView("taskDetail");
	}
//	=================taskDetails============================
	@RequestMapping(value="/task", method=RequestMethod.GET)
	public ModelAndView viewTaskView() {
		//String message = "Hello World";
		return new ModelAndView("taskView");
	}
	


//======================save task===============================
	@RequestMapping(value="/saveTask", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toSaveTask(Task_Model task) throws ParseException{
			
			//System.out.println("Name is: "+projectCategoryName.getName());
			Map<String,Object> map = new HashMap<String,Object>();				
			if(usersService1.toSaveTask(task)){
				map.put("status","200");
				map.put("message","Your record has been saved successfully");
				return map;
			}
			else {
				//System.out.println("Else Runs");
				map.put("status","999");
				map.put("message","Your record already existed");
				return map;
			}
		}

//	=================setting============================
	@RequestMapping(value="/setting", method=RequestMethod.GET)
	public ModelAndView viewSetting() {
		String message = "Hello World";
		return new ModelAndView("viewSetting", "message", message);
	}
//	=================view batch============================
	@RequestMapping(value="/batch", method=RequestMethod.GET)
	public ModelAndView viewBatch() {
		String message = "Hello World";
		return new ModelAndView("viewBatch", "message", message);
	}
//	=================kit point============================
	@RequestMapping(value="/kitpoint", method=RequestMethod.GET)
	public ModelAndView viewkitpoint() {
		String message = "Hello World";
		return new ModelAndView("viewKitPoint", "message", message);
	}
//	=================view project category============================
	@RequestMapping(value="/projectCategory", method=RequestMethod.GET)
	public ModelAndView viewProjectCategory() {
		return new ModelAndView("viewProjectCaterory");
	}

//================================Project Category Create============================================
		@RequestMapping(value="/projectCategoryCreate", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> toCreateProjectCategory(Project_Category_Master projectCategoryName){
				Map<String,Object> map = new HashMap<String,Object>();				
				if(usersService1.createProjectCategory(projectCategoryName)){
					map.put("status","200");
					map.put("message","Your record has been saved successfully");
					return map;
				}
				else {
					map.put("status","999");
					map.put("message","Your record already existed");
					return map;
				}
				
			}

//	=================create new user============================
	@RequestMapping(value="/newUser", method=RequestMethod.GET)
	public ModelAndView createUser() {
		String message = "Hello World";
		return new ModelAndView("createUser", "message", message);
	}
//	=================view profile & change password============================
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public ModelAndView changePassword() {
		String message = "Hello World";
		return new ModelAndView("changePassword", "message", message);
	}
//	=================Create Batch============================
	@RequestMapping(value="/createBatch", method=RequestMethod.GET)
	public ModelAndView createBatch() {
		return new ModelAndView("createBatch");
	}
//============================Retreive all semesters from DB send through Ajax========================
		@RequestMapping(value="/semesterList", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> getSemesterList(){
					
			 Map<String,Object> map = new HashMap<String,Object>();
		
			   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
				List<Semester_Master> list = usersService1.getAllSemester();
				 		
				if (list != null)
					map.put("data", list);
				else
					map.put("message","Data not found");			
				
				return map;
		}
//============================Retreive all users from DB send through Ajax========================
				@RequestMapping(value="/allUser", method=RequestMethod.GET)
				public @ResponseBody Map<String,Object> getAllUser(){
							
					 Map<String,Object> map = new HashMap<String,Object>();
				
					   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
						List<User_Info> list = usersService1.getAllUser();
						 		
						if (list != null)
							map.put("data", list);
						else
							map.put("message","Data not found");			
						
						return map;
				}
//================================Update Batch============================================
				@RequestMapping(value="/updateBatch_1", method=RequestMethod.GET)
				public @ResponseBody Map<String,Object> toUpdateBatch(Batch_Master batch){
						//System.out.println("Name in controller "+batch.getName()+" "+batch.getSemester_id());
						Map<String,Object> map = new HashMap<String,Object>();				
						if(usersService1.updateBatch(batch)){
							map.put("status","200");
							map.put("message","Your record has been saved successfully");
							return map;
						}
						else {
							
							map.put("status","999");
							map.put("message","Your record already existed");
							return map;
						}
					}
				//================================Save Batch============================================
				@RequestMapping(value="/batchSubmit", method=RequestMethod.GET)
				public @ResponseBody Map<String,Object> toCreateProjectCategory(Batch_Master batch){
						
						//System.out.println("Name is: "+projectCategoryName.getName());
						Map<String,Object> map = new HashMap<String,Object>();				
						if(usersService1.saveBatch(batch)){
							map.put("status","200");
							map.put("message","Your record has been saved successfully");
							return map;
						}
						else {
							//System.out.println("Else Runs");
							map.put("status","999");
							map.put("message","Your record already existed");
							return map;
						}
					}


//===================View Update Batch======================================
	@RequestMapping(value="/showUpdateBatch", method=RequestMethod.GET)
	public ModelAndView updateBatch() {
		return new ModelAndView("updateBatch");
	}
//============================Retreive all semesters and batches from DB send through Ajax========================
			@RequestMapping(value="/semesterAndBatchList", method=RequestMethod.GET)
			public @ResponseBody Map<String,?> getSemesterAndBatchList(){
						
				 Map<String,List> map = new HashMap<String,List>();
				 Map<String,Object> error = new HashMap<String,Object>();
				   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
					List<Semester_Master> listSemester = usersService1.getAllSemester();
					List<Batch_Master> listBatch = usersService1.getAllBatch();
					 		
					if (listSemester == null || listBatch == null)
						{
							error.put("message","Data not found");
							return error;
						}
						
					else
						{
							map.put("semester", listSemester);
							map.put("batch", listBatch);
							return map;
						}	
					}

	@RequestMapping(value="/addUser", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getSaved(User_Info users){
		
		Map<String,Object> map = new HashMap<String,Object>();		
		if(usersService1.addUser2(users)){
			map.put("status","200");
			map.put("message","Your record has been saved successfully");
		}
		else {
			map.put("status","999");
			map.put("message","Email already existed");
			return map;
		}
		
		return map;
	}
		
	

	@RequestMapping(value="/updateProjectDetail", method = RequestMethod.GET)
	public ModelAndView updateProjectDetail(@RequestParam(value = "id", required=false) Integer id){
		ModelAndView view =new ModelAndView("updateProjectDetail");
		//System.out.println("ID in Controller is "+id);
		view.addObject("id",id);
		return view;
		}
	
	@RequestMapping(value="/updateTaskDetail", method = RequestMethod.GET)
	public ModelAndView updateTask(@RequestParam(value = "id", required=false) Integer id){
		ModelAndView view =new ModelAndView("updateTaskDetail");
		//System.out.println("ID in Controller is "+id);
		view.addObject("id",id);
		return view;
		}
	@RequestMapping(value="/editProfile", method=RequestMethod.GET)
	public @ResponseBody Map<String,String> editProfile(User_Info user) throws Exception{
	// Pass the new password in name variable
			String newPassword = user.getName();
			Map<String,String> map = new HashMap<String,String>();
			if(usersService1.editProfile(user.getEmail(),user.getPassword(),newPassword))
			{
				map.put("status", "999");
				map.put("message", "Updation is completely done!");
			}
			else
			{
				map.put("status", "888");
				map.put("message", "Either email or password is incorrect!");
			}
			return map;
			
		}
}







	

	
	






