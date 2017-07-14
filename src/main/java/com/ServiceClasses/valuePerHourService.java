package com.ServiceClasses;

import java.util.List;

import com.EntityClasses.Value_Per_Hour;
import com.ModelClasses.ValuePerHourModel;

public interface valuePerHourService {

	public boolean addValuePerHour(ValuePerHourModel valuePerHour);
	public Value_Per_Hour addPointValue1(Value_Per_Hour model1);
	public List<Value_Per_Hour> getAllValuePerHour();
	public List<Value_Per_Hour> getBatchSemester();
	public boolean deletePoint(Value_Per_Hour kitPointValue);

}
