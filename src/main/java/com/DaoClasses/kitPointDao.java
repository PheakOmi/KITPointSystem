package com.DaoClasses;

import java.util.List;

import com.EntityClasses.KIT_Point;
import com.ModelClasses.KIT_Point_Model;



public interface kitPointDao {

	
	public boolean addPointValue(KIT_Point kitPointValue);
	public KIT_Point addPointValue1(KIT_Point model1);
	public List<KIT_Point_Model> getAllPointValue();
	public boolean deletePoint(KIT_Point kitPointValue);
	public boolean createOrUpdate(KIT_Point kitPoint);
}
