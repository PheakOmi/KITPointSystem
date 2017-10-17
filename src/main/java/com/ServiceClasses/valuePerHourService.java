package com.ServiceClasses;

import java.util.List;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Value_Per_Hour;
import com.ModelClasses.ValuePerHourModel;
import com.ModelClasses.ValuePerHourModel2;

public interface valuePerHourService {

	public boolean addValuePerHour(ValuePerHourModel valuePerHour);
	public Value_Per_Hour addPointValue1(Value_Per_Hour model1);
	public List<ValuePerHourModel2> getAllValuePerHour(int id) throws Exception;
	public List<Value_Per_Hour> getBatchSemester();
	public boolean deletePoint(Value_Per_Hour valuePerHour);
	public List < Batch_Master > getAllBatch();
	public List<Project_Master> getAllProjectData();
	public List<Project_Master> getProjectBasedOnStatus(String statusData) ;
	public boolean approveProject(int id);
	public boolean updateValuePerHour(ValuePerHourModel valuePerHour);
	public Value_Per_Hour getValuePerHourByItsId(int semester_id, int batch_id);
	public int getAmountOfVPHById(int id);
}
