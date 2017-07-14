package com.ATable;


import java.sql.*;  


public class Insert 
{

    public static void main(String args[]) 
    {
        try 
        {
           
            Class.forName("oracle.jdbc.driver.OracleDriver");

             
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ganesh");

            
            Statement stmt = con.createStatement();
            
            
            stmt.executeUpdate("insert into user_info values(1,'eone','5000')");
            
            stmt.executeUpdate("insert into user_info values(2,'etwo','5500')");
            
    
            System.out.println("Success");

            //step5 close the connection object  
            con.close();

        } catch (Exception e) 
        {
            System.out.println(e.toString());
            
        }

    }
}



//C:\oraclexe\app\oracle\product\10.2.0\server\jdbc\lib


/*

3 statement objects are available

1- Statement
2- prepared statement 
3- callable statement


prepared statement is much faster than other statements and secure too.

*/

