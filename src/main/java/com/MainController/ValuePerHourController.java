package com.MainController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.DaoClasses.valuePerHourDao;
import com.DaoClasses.valuePerHourDaoImpl;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Project_Stage_Master;
import com.ModelClasses.ValuePerHourModel;
import com.ServiceClasses.valuePerHourService;

@Controller
@RequestMapping("users")
public class ValuePerHourController {
	@Autowired
	valuePerHourService  valuePerHour;
	valuePerHourDaoImpl  listData;
	
//	=================view Value Per Hour============================
	@RequestMapping("/valuePerHour")
	public ModelAndView viewValuePerHour() {
		String message = "batch";
		return new ModelAndView("viewValuePerHour", "message", message);
	}
	@RequestMapping("/getBatchList")
	public @ResponseBody Map<String,?> showBatch(){
		 Map<String,List> map = new HashMap<String,List>();
		 Map<String,Object> error = new HashMap<String,Object>();
			List<Batch_Master> listBatch = listData.getAllBatch();
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
	//=================get project stage list and project=======================
	@RequestMapping("/getProject")
	public @ResponseBody Map<String,?> showProjectSatge(){
		 Map<String,List> map = new HashMap<String,List>();
		 Map<String,Object> error = new HashMap<String,Object>();
			List<Project_Master> listProject= listData.getAllProjectData();
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

	@RequestMapping(value="/getHour", method=RequestMethod.POST)
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
		
		if( valuePerHour.addValuePerHour(valuePerHourData)){
			map.put("status","200");
			map.put("message","Your record has been saved successfully");
		}
		
		return map;
	}
	
}
