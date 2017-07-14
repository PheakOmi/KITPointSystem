package com.ServiceClasses;

import java.util.List;

import com.EntityClasses.KIT_Point;

public interface kitPointService {
	
	public boolean addPointValue(KIT_Point kitPointValue);
	public List<KIT_Point> getAllPointValue();
	public boolean deletePoint(KIT_Point kitPointValue);
}
