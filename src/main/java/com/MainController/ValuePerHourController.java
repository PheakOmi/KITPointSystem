package com.MainController;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.DaoClasses.valuePerHourDaoImpl;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Project_Member;
import com.EntityClasses.Value_Per_Hour;
import com.ModelClasses.ValuePerHourModel;
import com.ModelClasses.ValuePerHourModel2;
import com.ServiceClasses.valuePerHourService;

@Controller
//@RequestMapping("users")
public class ValuePerHourController {
	@Autowired
	valuePerHourService  valuePerHour;
	
//	=================view Value Per Hour============================
	@RequestMapping(value="/valuePerHour", method=RequestMethod.GET)
	public ModelAndView viewValuePerHour() {
		String message = "batch";
		return new ModelAndView("viewValuePerHour", "message", message);
	}
	@RequestMapping(value="/getBatchList", method=RequestMethod.GET)
	public @ResponseBody Map<String,?> showBatch(){
		 Map<String,List> map = new HashMap<String,List>();
		 Map<String,Object> error = new HashMap<String,Object>();
			List<Batch_Master> listBatch = valuePerHour.getAllBatch();
			if (listBatch == null)
				{
					error.put("message","batch not found");
					return error;
				}
				
			else
				{
					map.put("batch", listBatch);
					return map;
				}	
	}
	@RequestMapping(value="/getAllValuePerHour", method=RequestMethod.GET)
	   public @ResponseBody Map<?,?> getRightStudent(@RequestParam(value = "id", required=false,defaultValue = "0") Integer batch_id) throws Exception{
	     Map<String,Object> map = new HashMap<String,Object>();
	     Map<String,Object> error = new HashMap<String,Object>();
	       // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
	     List<ValuePerHourModel2> vph = valuePerHour.getAllValuePerHour(batch_id);
	     map.put("vph", vph);
	     return map;
	      
	     }   
	//=================get project stage list and project=======================
	@RequestMapping(value="/getProject", method=RequestMethod.GET)
	public @ResponseBody Map<String,?> showProjectSatge(){
		 Map<String,List> map = new HashMap<String,List>();
		 Map<String,Object> error = new HashMap<String,Object>();
			List<Project_Master> listProject= valuePerHour.getAllProjectData();
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
	@RequestMapping(value="/getProjectBasedOnStatus", method=RequestMethod.GET)
	public @ResponseBody Map<String,?> showBasedOnProjectStatus(@RequestParam("status") String project_status){
		 Map<String,List> map = new HashMap<String,List>();
		 Map<String,Object> error = new HashMap<String,Object>();
			List<Project_Master> listProject= valuePerHour.getProjectBasedOnStatus(project_status);
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
	
	
	@RequestMapping(value="/getHour", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getSaved(ValuePerHourModel valuePerHourData){

		String value_1=valuePerHourData.getValue_1();
		String value_2=valuePerHourData.getValue_2();
		String value_3=valuePerHourData.getValue_3();
		String value_4=valuePerHourData.getValue_4();
		String value_5=valuePerHourData.getValue_5();
		String value_6=valuePerHourData.getValue_6();
		String value_7=valuePerHourData.getValue_7();
		String value_8=valuePerHourData.getValue_8();
		String batch_name=valuePerHourData.getBatch_name();
		String value=value_1+","+value_2+","+value_3+","+value_4+","+value_5+","+value_6+","+value_7+","+value_8;
		valuePerHourData.setValue_1(value);
		valuePerHourData.setBatch_name(batch_name);
		Map<String,Object> map = new HashMap<String,Object>();
		int batch_id = Integer.parseInt(batch_name);
		int  n = new valuePerHourDaoImpl().getAmountOfVPHById(batch_id);
		if (n>0){
			if( valuePerHour.updateValuePerHour(valuePerHourData)){
				map.put("status","200");
				map.put("message","You have updated it successfully!");
			}
			else{
				map.put("status","999");
				map.put("message","It is not updated!");
			}
		}
		else
		{
			if( valuePerHour.addValuePerHour(valuePerHourData)){
				System.out.println("Add runs");
				map.put("status","200");
				map.put("message","You have created it successfully!");
			}
			else{
				map.put("status","999");
				map.put("message","It is not created!");
			}
		}
		
		return map;
	}
	
	
	@RequestMapping(value="/aprroveTheProject", method=RequestMethod.GET)
	public @ResponseBody Map<String,?> aprroveProject(@RequestParam("id") int project_id){
		Map<String,Object> map = new HashMap<String,Object>();
		//System.out.println("ID is "+project_id);
		if( valuePerHour.approveProject(project_id)){
			
			map.put("status","200");
			map.put("message","Your record has been saved successfully");
		}
		return map;
	}
	
	
}
