package com.ATable;


import java.sql.*;  


public class Delete 
{

    public static void main(String args[]) 
    {
        try 
        {
           //step1 load the driver class  
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //step2 create  the connection object  
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ganesh");

            //step3 create the statement object  
            Statement stmt = con.createStatement();
            

            //step4 execute query  
            ResultSet rs = stmt.executeQuery("delete from user_info where id='0'");
            
            ResultSet rs1 = stmt.executeQuery("select * from user_info");
            while (rs1.next()) 
            {
                System.out.println(rs1.getInt(1) + "  " + rs1.getString(2)+ "  " + rs1.getString(3));
            }

            //step5 close the connection object  
            con.close();

        } 
        catch (Exception e) 
        {
            System.out.println(e);
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

