package com.MainController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.DaoClasses.kitPointDao;
import com.EntityClasses.KIT_Point;
import com.ModelClasses.KIT_Point_Model;




@Controller
//@RequestMapping("users")
public class KitPointController {
	
	
	@Autowired
	kitPointDao valueKitPoint;	

			@RequestMapping(value="/submitpoint", method=RequestMethod.GET)                                            
			public ModelAndView Submit(KIT_Point newKitPoint,BindingResult result)        
			{                                                                                                
			   if(result.hasErrors())
			   {
				ModelAndView model2 = new ModelAndView("viewKitPoint");
				model2.addObject("headerMsg", "Enter the correct format");  
				return model2;	
			   }
	  		
			   	newKitPoint= valueKitPoint.addPointValue1(newKitPoint);
						
				ModelAndView model2 = new ModelAndView("viewKitPoint");
				model2.addObject("headerMsg", "Value inserted successfully");  
				
				model2.addObject("msg", valueKitPoint);
			
				return model2;
			}
			@RequestMapping(value="/submit1", method=RequestMethod.GET)
			public @ResponseBody Map<String,Object> getvalue(KIT_Point newKitPoint){
				
				Map<String,Object> map = new HashMap<String,Object>();		
				
				if(valueKitPoint.createOrUpdate(newKitPoint)){
					map.put("status","200");
					map.put("message","Your record has been saved successfully");
				}
				return map;
			}
			@RequestMapping(value="/getKitPoint", method=RequestMethod.GET)
			public @ResponseBody Map<String,?> getKitPoint(){
				
				 Map<String,List> map = new HashMap<String,List>();
				 Map<String,Object> error = new HashMap<String,Object>();
					List<KIT_Point_Model> listProject= valueKitPoint.getAllPointValue();
							map.put("kitPoint", listProject);
							return map;
					
			}

}
