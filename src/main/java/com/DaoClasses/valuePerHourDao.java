package com.DaoClasses;

import java.util.List;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Value_Per_Hour;
import com.ModelClasses.ValuePerHourModel;

public interface valuePerHourDao {
	public boolean addValuePerHour(ValuePerHourModel valuePerHour);
	public Value_Per_Hour addPointValue1(Value_Per_Hour model1);
	public List<Value_Per_Hour> getAllValuePerHour();
	public List<Value_Per_Hour> getBatchSemester();
	public boolean deletePoint(Value_Per_Hour kitPointValue);
	public List < Batch_Master > getAllBatch();
	public List<Project_Master> getAllProjectData();
	public List<Project_Master> getProjectBasedOnStatus(String statusData) ;
	public boolean approveProject(int id);
	public boolean getValuePerHourById(int id);
}
