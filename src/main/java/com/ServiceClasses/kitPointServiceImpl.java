package com.ServiceClasses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DaoClasses.kitPointDao;
import com.EntityClasses.KIT_Point;

@Service
public class kitPointServiceImpl implements kitPointService{
	
	@Autowired
	kitPointDao kitpoint;

	public boolean addPointValue(KIT_Point kitPointValue) {
		
		return kitpoint.addPointValue(kitPointValue);
	}

	public List<KIT_Point> getAllPointValue() {
		
		return kitpoint.getAllPointValue();
	}

	public boolean deletePoint(KIT_Point kitPointValue) {
		
		return kitpoint.deletePoint(kitPointValue);
	}

}
