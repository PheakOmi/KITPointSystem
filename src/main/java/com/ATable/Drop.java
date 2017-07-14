package com.ATable;


import java.sql.*;  


public class Drop
{

    public static void main(String args[]) 
    {
        try 
        {
           
            Class.forName("oracle.jdbc.driver.OracleDriver");

            
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ganesh");

           
            Statement stmt = con.createStatement();
            
            stmt.executeUpdate("Drop table user_info");
                    
            
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
