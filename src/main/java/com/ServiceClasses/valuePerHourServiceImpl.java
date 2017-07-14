package com.ServiceClasses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DaoClasses.valuePerHourDao;
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
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deletePoint(Value_Per_Hour kitPointValue) {
		// TODO Auto-generated method stub
		return false;
	}

}
