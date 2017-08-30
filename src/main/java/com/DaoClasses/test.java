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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.EntityClasses.Project_Member;
import com.EntityClasses.Student;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;

public class test {
	public static void main(String agrs[]) throws MalformedURLException, XmlRpcException {
		int arr1[] = {1, 2, 3};
        int arr2[] = {1, 3, 2};
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        if (Arrays.equals(arr1, arr2)) // Same as arr1.equals(arr2)
            System.out.println("Same");
        else
            System.out.println("Not same");
        }
}
