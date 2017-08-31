package com.DaoClasses;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.xmlrpc.XmlRpcException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.EntityClasses.Project_Member;
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
