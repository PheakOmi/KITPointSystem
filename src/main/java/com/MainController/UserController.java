package com.MainController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.EntityClasses.Project_Master;
import com.EntityClasses.Task_Master;
import com.ServiceClasses.valuePerHourService;

@Controller
public class UserController {
	@Autowired
	valuePerHourDaoImpl valuePerHour;
//	=================project============================
	@RequestMapping(value="/projectUserView", method=RequestMethod.GET)
	public ModelAndView viewProject() {
		String message = "project for user";
		return new ModelAndView("projectUserView", "message", message);
	}
//	=================projectDetail============================
	@RequestMapping(value="/projectDetailUserView", method = RequestMethod.GET)
	public ModelAndView projectDetailUserView(@RequestParam(value = "id", required=false) Integer id){
		ModelAndView view =new ModelAndView("projectDetailUserView");
		//System.out.println("ID in Controller is "+id);
		view.addObject("id",id);
		return view;
		}
//	=================taskDetails============================
	@RequestMapping(value="/taskDetailUserView", method = RequestMethod.GET)
	public ModelAndView viewTaskdetail(@RequestParam(value = "id", required=false) Integer id){
		ModelAndView view =new ModelAndView("taskDetailUserView");
		//System.out.println("ID in Controller is "+id);
		view.addObject("id",id);
		return view;
		}
//	=================taskDetails============================
	@RequestMapping(value="/taskUserView", method=RequestMethod.GET)
	public ModelAndView viewTaskView() {
		return new ModelAndView("taskUserView");
	}
	//=================get project stage list and project=======================
	@RequestMapping(value="/getProjectforUser", method=RequestMethod.GET)
	public @ResponseBody Map<String,?> showProjectSatge(){
		 Map<String,List> map = new HashMap<String,List>();
		 Map<String,Object> error = new HashMap<String,Object>();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 UserDetails userDetails=(UserDetails) auth.getPrincipal();
			List<Project_Master> listProject= valuePerHour.getProjectDataBaseOnUser(userDetails.getUsername());
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
		@RequestMapping(value="/getProjectBasedOnStatusUser", method=RequestMethod.GET)
		public @ResponseBody Map<String,?> showBasedOnProjectStatusUser(@RequestParam("status") String project_status){
			 Map<String,List> map = new HashMap<String,List>();
			 Map<String,Object> error = new HashMap<String,Object>();
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			 UserDetails userDetails=(UserDetails) auth.getPrincipal();
				List<Project_Master> listProject= valuePerHour.getProjectBasedOnStatusUser(project_status,userDetails.getUsername());
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
		@RequestMapping(value="/ProjectNTasKUser", method=RequestMethod.GET)
		public @ResponseBody Map<String,?> getProjectNTaskAdmin(){
					
			 Map<String,List> map = new HashMap<String,List>();
			 Map<String,Object> error = new HashMap<String,Object>();
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			 UserDetails userDetails=(UserDetails) auth.getPrincipal();
			 String username=userDetails.getUsername();
				List<Project_Master> listProject = valuePerHour.getProjectDataBaseOnUser(username);
				List<Task_Master> listTask = valuePerHour.getAllTaskBaseOnUser(listProject);
				 		
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
		
}
