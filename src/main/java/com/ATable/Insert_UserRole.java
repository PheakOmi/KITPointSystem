package com.ATable;


import java.sql.*;  


public class Insert_UserRole 
{

    public static void main(String args[]) 
    {
        try 
        {
           

            //step1 Register the driver  
         	Class.forName("org.postgresql.Driver");
         	
             //step2 Get the connection from db 
         	Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KITPoint","postgres", "admin");

             //step3 create the statement object  
             Statement stmt = con.createStatement();
            
            //stmt.executeUpdate("INSERT INTO user_info(name,batch,semester,email,password)VALUES ('ganesh',0,0,'admin@gmail.com','$2a$10$lXfYJtUmsePQ/mR3bt4xTueJ5E/UgbbLhpwJ2Tq3/tLkaIr2/1gGG');");
            
            //stmt.executeUpdate("INSERT INTO user_info(name,batch,semester,email,password,User_type,Updated_at)VALUES('kannan',0,0,'kannan@gmail.com','kannan','admin','2017-08-28 17:17:30');");
            
            stmt.executeUpdate("INSERT INTO user_roles (id,role)VALUES (1,'ROLE_ADMIN')");
            stmt.executeUpdate("INSERT INTO user_roles (id,role)VALUES (2,'ROLE_N_ADMIN')");
            stmt.executeUpdate("INSERT INTO user_roles (id,role)VALUES (3,'ROLE_N_ADMIN')");
            stmt.executeUpdate("INSERT INTO user_roles (id,role)VALUES (4,'ROLE_N_ADMIN')");
            stmt.executeUpdate("INSERT INTO user_roles (id,role)VALUES (5,'ROLE_N_ADMIN')");
            
            
    
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

