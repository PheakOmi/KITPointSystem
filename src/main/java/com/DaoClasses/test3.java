package com.DaoClasses;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.EntityClasses.Semester_Master;
import com.EntityClasses.Student;
import com.ServiceClasses.usersService;
public class test3
{
	@Autowired
	usersService usersService1;	 
	public static void main(String args[]) throws Exception { 
		 int m[] = {77,78,79,80};
		 Map<Integer, String> mm = usersService1.getStudentSemester(m);
			float point = usersService1.pointCalculation(mm,100);
			//System.out.println("POint is "+point);
		 }

	
}
