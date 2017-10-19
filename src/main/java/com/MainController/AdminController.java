package com.MainController;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.DaoClasses.valuePerHourDaoImpl;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Task_Master;
import com.ModelClasses.Project_Model;
import com.ModelClasses.Task_Model;

@Controller
public class AdminController {
	@Autowired
	valuePerHourDaoImpl valuePerHour;
//	=================project============================
	@RequestMapping(value="/projectAdminView", method=RequestMethod.GET)
	public ModelAndView viewProject() {
		String message = "project for user";
		return new ModelAndView("projectAdminView", "message", message);
	}
//	=================projectDetail============================
	@RequestMapping(value="/projectDetailAdminView", method=RequestMethod.GET)
	public ModelAndView viewProjectdetail() {
		return new ModelAndView("projectDetailAdminView");
	}
//	=================taskDetails============================
	@RequestMapping(value="/taskDetailAdminView", method=RequestMethod.GET)
	public ModelAndView viewTaskdetail() {
		return new ModelAndView("taskDetailAdminView");
	}
//	=================taskDetails============================
	@RequestMapping(value="/taskAdminView", method=RequestMethod.GET)
	public ModelAndView viewTaskView() {
		return new ModelAndView("taskAdminView");
	}
	//=================get project stage list and project=======================
	@RequestMapping(value="/getProjectforAdmin", method=RequestMethod.GET)
	public @ResponseBody Map<String,?> showProjectAdmin(){
		 Map<String,List> map = new HashMap<String,List>();
		 Map<String,Object> error = new HashMap<String,Object>();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 UserDetails userDetails=(UserDetails) auth.getPrincipal();
			List<Project_Master> listProject= valuePerHour.getProjectDataBaseOnAdmin(userDetails.getUsername());
			if (listProject==null)
				{
					error.put("message","batch not found");
					return error;
				}
			else
				{
					map.put("project", listProject);
					return map;
				}	
	}
	//=================get project stage list and project=======================
			@RequestMapping(value="/getProjectBasedOnStatusAdmin", method=RequestMethod.GET)
			public @ResponseBody Map<String,?> showBasedOnProjectStatusAdmin(@RequestParam("status") String project_status){
				 Map<String,List> map = new HashMap<String,List>();
				 Map<String,Object> error = new HashMap<String,Object>();
				 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				 UserDetails userDetails=(UserDetails) auth.getPrincipal();
					List<Project_Master> listProject= valuePerHour.getProjectBasedOnStatusAdmin(project_status,userDetails.getUsername());
					if (listProject==null)
						{
							error.put("message","batch not found");
							return error;
						}
					else
						{
							map.put("project", listProject);
							return map;
						}	
			}
	
			//============================Get Project AND Task========================
			@RequestMapping(value="/ProjectNTaskAdmin", method=RequestMethod.GET)
			public @ResponseBody Map<String,?> getProjectNTaskAdmin(){
						
				 Map<String,List> map = new HashMap<String,List>();
				 Map<String,Object> error = new HashMap<String,Object>();
				   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
				 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				 UserDetails userDetails=(UserDetails) auth.getPrincipal();
				 String username=userDetails.getUsername();
					List<Project_Master> listProject = valuePerHour.getProjectDataBaseOnAdmin(username);
					List<Task_Master> listTask = valuePerHour.getAllTaskBaseOnAdmin(username);
					 		
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
			
	//========================Update Project========================================================
	/*@RequestMapping(value="/updateProjectAdminView", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toUpdateProject(Project_Model pm) throws Exception{
    		//int[] s = pm.getStage();
			DecimalFormat df2 = new DecimalFormat(".##");
			Project_Master project = new Project_Master();
			Map<String,Object> map = new HashMap<String,Object>();	
			Map<Integer, String> mm = usersService1.getStudentSemester(pm.getMember());
			project = usersService1.getProjectById(pm.getId());
			if (project.getKit_point().equals(pm.getKit_point()))
			{
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
			}
			else{
				pm.setKit_point(df2.format(Float.parseFloat(pm.getKit_point())));
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
	@RequestMapping(value="/updateTaskAdminView", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toUpdateTask(Task_Model tm) throws ParseException{
    		//int[] s = pm.getStage();
			Map<String,Object> map = new HashMap<String,Object>();				
			if(usersService1.updateTask(tm))
			{
				map.put("status","200");
				map.put("message","Your record has been updated successfully");
				return map;
			}
			else {
				map.put("status","999");
				map.put("message","Your record has not been updated");
				return map;
			}
		}*/
}
