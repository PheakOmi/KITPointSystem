
package com.ATable;


import java.sql.*;  


public class Insert_User_Info 
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
            
            
            stmt.executeUpdate("INSERT INTO user_info(id,name,email,password,User_type,Updated_at,enabled)VALUES(3,'Leo Fernandez','leo@gmail.com','$2a$10$jr3IP860vED9Dknwm4jkH.kH/JtLK/k.fTa.fmbZMVH7FOWVo0rmy','admin','2017-08-28 17:17:30',true)");
            stmt.executeUpdate("INSERT INTO user_info(id,name,email,password,User_type,Updated_at,enabled)VALUES(4,'Chihiro Kurose','chihiro@gmail.com','$2a$10$1jpU86OVODE9Nd/lwlovKurGPgTZ8wBQvGZnqV6dyJRDydt9K7SPW','admin','2017-08-28 17:17:30',true)");
            stmt.executeUpdate("INSERT INTO user_info(id,name,email,password,User_type,Updated_at,enabled)VALUES(5,'Dinesh Kumar','dinesh@gmail.com','$2a$10$gKaAJEsJGGmcnv7K5JgbD.O1UJxQ9V0lrHO1BMSB0OKlIq5UvxCuO','admin','2017-08-28 17:17:30',true)");
            stmt.executeUpdate("INSERT INTO user_info(id,name,email,password,User_type,Updated_at,enabled)VALUES(6,'Tomoko Aono','tomoko@gmail.com','$2a$10$M1Yb8PpTlx57ydw3IhIrL.qVC1mvZV5qwGeXI85Foi40BeCQfjUui','admin','2017-08-28 17:17:30',true)");
            stmt.executeUpdate("INSERT INTO user_info(id,name,email,password,User_type,Updated_at,enabled)VALUES(7,'Vignesh','vignesh@gmail.com','$2a$10$y0jT9qh3DhAfnZlpj5X5gO96nYMp2SISvLnHDIefWyhycIJ0jxLou','admin','2017-08-28 17:17:30',true)");
            
																															            


    
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

