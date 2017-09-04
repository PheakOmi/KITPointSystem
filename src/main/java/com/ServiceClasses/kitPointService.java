package com.ServiceClasses;

import java.util.List;

import com.EntityClasses.KIT_Point;
import com.ModelClasses.KIT_Point_Model;

public interface kitPointService {
	
	public boolean addPointValue(KIT_Point kitPointValue);
	public List<KIT_Point_Model> getAllPointValue();
	public boolean deletePoint(KIT_Point kitPointValue);
}
