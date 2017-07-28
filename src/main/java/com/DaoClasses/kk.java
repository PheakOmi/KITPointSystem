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

public class kk {

	public static void main(String[] args) throws Exception {
		final String url = "http://96.9.67.154";
	     final String db = "Hotel_Test";
	     final String username ="admin";
	     final String password = "123";
	     
	     
	     final XmlRpcClient authClient = new XmlRpcClient();
	        final XmlRpcClientConfigImpl authStartConfig = new XmlRpcClientConfigImpl();
	        authStartConfig.setServerURL(
	                new URL(String.format("%s/xmlrpc/2/common", url)));
	        
	        List configList = new ArrayList();
	        Map paramMap = new HashMap();
	        
	        configList.add(db);
	        configList.add(username);
	        configList.add(password);
	        configList.add(paramMap);
	        
	        int uid = (Integer)authClient.execute(
	                authStartConfig, "authenticate", configList);

	        final XmlRpcClient objClient = new XmlRpcClient();
	        final XmlRpcClientConfigImpl objStartConfig = new XmlRpcClientConfigImpl();
	        objStartConfig.setServerURL(
	                new URL(String.format("%s/xmlrpc/2/object", url)));
	        objClient.setConfig(objStartConfig);
	        
	        List paramList = new ArrayList();
	        List list1 = new ArrayList();
	        List list2 = new ArrayList();
	        List list3 = new ArrayList();
	        List field = new ArrayList();
	        configList.clear();
	        paramMap.clear();
	        paramList.clear();
	        
	        configList.add(db);
	        configList.add(uid);
	        configList.add(password);
	        configList.add("sale.order");
	        configList.add("search_read");
	        
	        list3.add("partner_id");
	        list3.add("=");
	        list3.add(1);
	        list2.add(list3);
	        list1.add(list2);

	        configList.add(list1);
	        field.add("name");
	        field.add("state");
	        paramMap.put("fields", field);
	        configList.add(paramMap);

	        List<Object> a= Arrays.asList((Object[])objClient.execute("execute_kw", configList));
	        
	        for(Object t:a){
	         System.out.println(t);
	        }

	}

}
