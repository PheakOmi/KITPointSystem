package com.DaoClasses;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.EntityClasses.Student;
public class StudentFromOdoo_BatchId
{
	
	public List<Student> getStudent_BatchId() throws XmlRpcException, MalformedURLException{ 
		List <Student> students=new ArrayList<Student>();
		  final String url = "http://192.168.7.222:8069";
		  //final String url = "http://96.9.67.154:8070"; 
	      final String db = "Kirirom_Institute_of_Technology"; 
	      final String username ="admin"; 
	      final String password = "adminn"; 
	       
	       
	      final XmlRpcClient authClient = new XmlRpcClient(); 
	         final XmlRpcClientConfigImpl authStartConfig = new XmlRpcClientConfigImpl(); 
	         authStartConfig.setServerURL(new URL(String.format("%s/xmlrpc/2/common", url))); 
	          
	     
	         List configList = new ArrayList(); 
	         Map paramMap = new HashMap(); 
	          
	         configList.add(db); 
	         configList.add(username); 
	         configList.add(password); 
	         configList.add(paramMap); 
	          
	         int uid = (Integer)authClient.execute( 
	                 authStartConfig, "authenticate", configList); 
	         
	         System.out.println("Connection Success"); 
	         
	         final XmlRpcClient objClient = new XmlRpcClient(); 
	         final XmlRpcClientConfigImpl objStartConfig = new XmlRpcClientConfigImpl(); 
	         objStartConfig.setServerURL(new URL(String.format("%s/xmlrpc/2/object", url))); 
	         objClient.setConfig(objStartConfig); 
	          
	        
	       // List<Object> a= Arrays.asList((Object[])objClient.execute("execute_kw", configList)); 
	     
	          try {
	   	  
	        	  final List get_Result=Arrays.asList((Object[])objClient.execute("execute_kw", Arrays.asList(
	        			    db, uid, password,
	        			    "op.student", "search_read",
	        			    Arrays.asList(Arrays.asList(
	        			    		Arrays.asList("id", ">", 0))),
	        			    		    //OR
	        			    		//Arrays.asList("customer", "=", true))),  //To bring the all value customer should be true
	        			    new HashMap() {{
	        			        put("fields", Arrays.asList("id","name","batch_id","gender"));
	        			        //put("limit", 5);
	        			    }}
	        			)));
	        	  
	   	  
	        	   for(int i=0;i<get_Result.size();i++)
	               {
	        		 Student student = new Student();
	        		 //HashMap complete_Result = (HashMap) get_Result.get(i);                 
	        		   
	                 HashMap<String,Object> complete_Result = (HashMap<String,Object>) get_Result.get(i);               
	        
	                 Integer id=(Integer)complete_Result.get("id");
	                 student.setId(Integer.toString(id));
	                 String gender=(String)complete_Result.get("gender");
	                 student.setGender(gender);
	                 String name=(String)complete_Result.get("name");
	                 student.setName(name);
	                 student.setText(name);
	                  
	                 
	                 Object batch_id1=(Object)complete_Result.get("batch_id");   //batch_id is object so we must get this value as an object       	         	  
	              	 String batch_id2=Arrays.deepToString((Object[]) batch_id1).toString();
	             	 String batch_id3=batch_id2.substring(1,batch_id2.length()-1);  //To trim bracket
	              	 String[] batch_id4=batch_id3.split(",");  //splits the string based on whitespace  
	                int count=0;
	              	for(String batch_id5:batch_id4){
		                 String batch_id6 = batch_id5.trim();     //Remove first space
		                 String batch_id7= batch_id6.replaceAll(" ", "");
		                 count++;
		                 if(count==1)//Remove space
		                 student.setBatch_id(batch_id7);
		                }
	              	students.add(student);
	          	  
	          }
	        	  
	         
	        }
	        catch (XmlRpcException e) {
	            
	            System.err.println(e);
	            //e.printStackTrace();
	        }
			return students;
	        
	      
	 }
}
