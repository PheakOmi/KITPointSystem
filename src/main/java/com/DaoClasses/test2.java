package com.DaoClasses;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.EntityClasses.Semester_Master;
import com.EntityClasses.Student;

public class test2 {
	public static void main(String args[]) throws XmlRpcException,
			MalformedURLException, ParseException {

		int arr[] = { 77, 109, 152 };
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<Student> students = new StudentFromOdoo_BatchId().getStudent_BatchId();

		for (int i = 0; i < students.size(); i++) {
			for (int j = 0; j < arr.length; j++) {
				if ((Integer.parseInt(students.get(i).getId())) == arr[j]) {
					map.put(arr[j],Integer.parseInt(students.get(i).getBatch_id()));
				}
			}

		}
		List<Semester_Master> semesters = new test2().getStudent_Semester(0);
		for (Semester_Master sm:semesters)
		{
			System.out.println(sm.getId()+"\t"+sm.getName()+"\t");
		}
		//for (Map.Entry m : map.entrySet()) {
			//System.out.println(m.getValue());
		//}

	}

	public List<Semester_Master> getStudent_Semester(int batch_id) throws XmlRpcException,
			MalformedURLException, ParseException {
		List<Semester_Master> semesters =new ArrayList<Semester_Master>();
		final String url = "http://96.9.67.154:8070";
		final String db = "Kirirom_Institute_of_Technology";
		final String username = "admin";
		final String password = "admin";

		final XmlRpcClient authClient = new XmlRpcClient();
		final XmlRpcClientConfigImpl authStartConfig = new XmlRpcClientConfigImpl();
		authStartConfig.setServerURL(new URL(String.format(
				"%s/xmlrpc/2/common", url)));

		List configList = new ArrayList();
		Map paramMap = new HashMap();

		configList.add(db);
		configList.add(username);
		configList.add(password);
		configList.add(paramMap);

		int uid = (Integer) authClient.execute(authStartConfig, "authenticate",
				configList);

		// System.out.println("Connection Success");

		final XmlRpcClient objClient = new XmlRpcClient();
		final XmlRpcClientConfigImpl objStartConfig = new XmlRpcClientConfigImpl();
		objStartConfig.setServerURL(new URL(String.format("%s/xmlrpc/2/object",
				url)));
		objClient.setConfig(objStartConfig);

		// List<Object> a=
		// Arrays.asList((Object[])objClient.execute("execute_kw", configList));

		try {

			final List get_Result = Arrays.asList((Object[]) objClient.execute(
					"execute_kw", Arrays.asList(db, uid, password,
							"op.semester", "search_read", Arrays.asList(Arrays
									.asList(Arrays.asList("id", ">",0 ))),
							// OR
							// Arrays.asList("customer", "=", true))), //To
							// bring the all value customer should be true
							new HashMap() {
								{
									put("fields", Arrays.asList("id", "name",
											"batch_id", "start_date",
											"end_date"));
									// put("limit", 5);
								}
							})));

			for (int i = 0; i < get_Result.size(); i++) {
				Semester_Master semester = new Semester_Master();
				// HashMap complete_Result = (HashMap) get_Result.get(i);

				HashMap<String, Object> complete_Result = (HashMap<String, Object>) get_Result
						.get(i);

				Integer id = (Integer) complete_Result.get("id");
				semester.setId(id);
				// System.out.print("ID "+ id);

				String name = (String) complete_Result.get("name");
				semester.setName(name);
				// System.out.print("    Name "+ name);

				String start_date = (String) complete_Result.get("start_date");
				Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
				//semester.setStart_date(date1);
				// System.out.print("    Start "+ start_date);

				String end_date = (String) complete_Result.get("end_date");
				Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
				//semester.setEnd_date(date2);
				// System.out.print("    End "+ end_date);

				Object batch_id1 = (Object) complete_Result.get("batch_id"); 
				String batch_id2 = Arrays.deepToString((Object[]) batch_id1)
						.toString();
				// System.out.println("The object is: "+ batch_id1);
				// System.out.println("The batch2 is: "+ batch_id2);

				String batch_id3 = batch_id2.substring(1,
						batch_id2.length() - 1); // To trim bracket
				// System.out.println("The batch3 is: "+ batch_id3);

				// String batch_id9= batch_id3.replaceAll(" ", "  ");
				// System.out.println("The batch3 is: "+ batch_id9);

				String[] batch_id4 = batch_id3.split(",");
				int count = 0;
				for (String batch_id5 : batch_id4) {
					String batch_id6 = batch_id5.trim(); // Remove first space
					String batch_id7 = batch_id6.replaceAll(" ", "");
					count++;
					//if (count == 1)// Remove space
						//s = batch_id7;
				}
				// System.out.println("------------------------");
			}

		} catch (XmlRpcException e) {

			System.err.println(e);
			// e.printStackTrace();
		}
		return semesters;
	}

	public String getCurrentSemester(List<Semester_Master> semesters) {
		
		return null;
	}
}