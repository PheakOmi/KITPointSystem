package com.DaoClasses;
import java.sql.*;  


public class test
{

    public static void main(String args[]) 
    {
        try 
        {
           //step1 Register the driver  
        	Class.forName("org.postgresql.Driver");
        	
            //step2 Get the connection from db 
        	Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SBS","postgres", "admin");

            //step3 create the statement object  
            Statement stmt = con.createStatement();
            
            //step4 Excecute the query
            stmt.executeUpdate("create table bus_master(id serial primary key, plate_number varchar(10),name varchar(50), number_of_seat integer, Created_at timestamp, Updated_at timestamp,enabled boolean)");
 //           stmt.executeUpdate("create table User_info(id serial primary key, Name varchar(100),Email varchar(100), Password varchar(100),Created_at timestamp, Updated_at timestamp, enabled boolean)");
            stmt.executeUpdate("create table location_master(id serial primary key,name varchar(50),Created_at timestamp, Updated_at timestamp,enabled boolean)");
            stmt.executeUpdate("create table pick_up_location(id serial primary key, Name varchar(20),location_id integer,Created_at timestamp, Updated_at timestamp,enabled boolean)");
            stmt.executeUpdate("create table schedule_master(id serial primary key, bus_id integer, driver_id integer,dept_date date,dept_time time,description varchar (200),number_seat integer, arrival_time time, remaining_seat integer, Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table booking_master(id serial primary key,destination_id integer, source_id integer, schedule_id integer, notification varchar(200),qr varchar(200),description varchar(100), Created_at timestamp, Updated_at timestamp)");
//            stmt.executeUpdate("create table user_role(id serial primary key, User_id integer, Project_id integer,Batch_id integer, KIT_Point varchar(40), Hours integer,Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table driver_bus_assigned(id serial primary key, schedule_id integer,driver_id integer,bus_id integer,dept_date date,dept_time time, Created_at timestamp, Updated_at timestamp)");
//            stmt.executeUpdate("create table Budget_Master(id serial primary key, Name varchar(150), Created_at timestamp, Updated_at timestamp)");
//            stmt.executeUpdate("create table Budget(id serial primary key,Budget_id integer, Project_id integer, Cost integer, Created_at timestamp, Updated_at timestamp)");
//            stmt.executeUpdate("create table Project_Stage_Master(id serial primary key, Stage_name varchar(70), Created_at timestamp, Updated_at timestamp)");
//            stmt.executeUpdate("create table Project_Stage(id serial primary key, Project_id integer, Stage_id integer, Created_at timestamp, Updated_at timestamp)");
//            stmt.executeUpdate("create table Task_Master(id serial primary key, Project_id integer, Name varchar(150), Assigned_To integer, Description varchar(500),Status varchar(70), Time_spend integer DEFAULT 0, Deadline date, Start_date date, End_date date,Created_at timestamp, Updated_at timestamp)");
//            stmt.executeUpdate("create table Project_Master(id serial primary key, Project_Name varchar(150), Project_Code varchar(40), Skillset varchar(150), Project_type integer, Project_Co integer, Project_leader integer, Project_member varchar(40),Description varchar(500), Status varchar(40),Initially_planned integer, Budget integer DEFAULT 0, KIT_point varchar(40), Deadline date, Start_date date, End_date date, Created_at timestamp, Updated_at timestamp)");
//            stmt.executeUpdate("create table Student(student_id serial primary key, Name varchar(100), Text varchar(100),id varchar(20),gender varchar(20),batch_id varchar(20))");         
//            stmt.executeUpdate("create table sms_server_info(id serial primary key, ip varchar(100), db_name varchar(100))");         
//              stmt.executeUpdate("create table Skillset_Master(id serial primary key, Name varchar(150), Created_at timestamp, Updated_at timestamp)");
//            stmt.executeUpdate("create table Skillset_Project_Wise(id serial primary key, Project_id integer,skillset_id integer, skillset_name varchar(40),Created_at timestamp, Updated_at timestamp)");
//            stmt.executeUpdate("create table additional_hour(id serial primary key, User_id integer, Project_id integer,additional_hour integer,Created_at timestamp, Updated_at timestamp)");
            System.out.println("Success");
            //Don't forget to set budget,timespent in task default as 0
            

            //step5 close the connection object   	
            con.close();

        } 
        catch (Exception e) 
        {
            System.out.println(e.toString());
            
        }

    }
}



//C:\oraclexe\app\oracle\product\10.2.0\server\jdbc\lib   --location of jdbc jar file 


/*

3 statement objects are available

1- Statement
2- prepared statement 
3- callable statement


prepared statement is much faster than other statements and secure too.

*/
