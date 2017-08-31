package com.ServiceClasses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DaoClasses.valuePerHourDao;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Value_Per_Hour;
import com.ModelClasses.ValuePerHourModel;

@Service
public class valuePerHourServiceImpl implements valuePerHourService {

	@Autowired
	private valuePerHourDao valuePerHourDao;
	public boolean addValuePerHour(ValuePerHourModel valuePerHour) {
		return valuePerHourDao.addValuePerHour(valuePerHour);
		
	}

	public Value_Per_Hour addPointValue1(Value_Per_Hour model1) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Value_Per_Hour> getAllValuePerHour() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Value_Per_Hour> getBatchSemester() {
		return valuePerHourDao.getBatchSemester();
	}

	public boolean deletePoint(Value_Per_Hour Value) {
		return valuePerHourDao.deletePoint(Value);
	}

	public List<Batch_Master> getAllBatch() {
		return valuePerHourDao.getAllBatch();
	}

	public List<Project_Master> getAllProjectData() {
		return valuePerHourDao.getAllProjectData();
	}

	public List<Project_Master> getProjectBasedOnStatus(String statusData) {
		return valuePerHourDao.getProjectBasedOnStatus(statusData);
	}

	public boolean approveProject(int id) {
		return valuePerHourDao.approveProject(id);
	}

}
